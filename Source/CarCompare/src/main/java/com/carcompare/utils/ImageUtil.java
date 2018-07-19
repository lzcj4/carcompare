package com.carcompare.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

@Data
public class ImageUtil {

    @Value("${spring.images.mosaic.size}")
    private static double MosaicSize;

    public static BufferedImage zoomImageScale(BufferedImage imageBuffer, int newWidth, ImageTypes imageType) throws RuntimeException {

        if (imageBuffer == null)
            throw new IllegalArgumentException("imageBuffer is null!");
        if (newWidth <= 0)
            throw new IllegalArgumentException("newWidth must > 0!");

        int originalWidth = imageBuffer.getWidth();
        int originalHeight = imageBuffer.getHeight();

        double scale = (double) originalWidth / (double) newWidth;

        int newHeight = (int) (originalHeight / scale);

        return zoomImageScale(imageBuffer, newWidth, newHeight, imageType);
    }

    public static BufferedImage zoomImageScale(BufferedImage imageBuffer, int newWidth, int newHeight, ImageTypes imageType)
            throws RuntimeException {
        if (imageBuffer == null)
            throw new IllegalArgumentException("imageBuffer is null!");
        if (newWidth <= 0)
            throw new IllegalArgumentException("newWidth must > 0!");
        if (newHeight <= 0)
            throw new IllegalArgumentException("newHeight must > 0!");

        BufferedImage newImageBuffer;

        if (imageType == ImageTypes.PNG || imageType == ImageTypes.GIF) {
            newImageBuffer = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = newImageBuffer.createGraphics();
            //处理黑色背景
            newImageBuffer = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);

            g2d.drawImage(imageBuffer.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING), 0, 0, null);

            g2d.dispose();
        } else {
            newImageBuffer = new BufferedImage(newWidth, newHeight, imageBuffer.getType());
            Graphics g = newImageBuffer.getGraphics();
            g.drawImage(imageBuffer.getScaledInstance(newWidth,newHeight,Image.SCALE_AREA_AVERAGING), 0, 0, newWidth, newHeight, null);
            g.dispose();
        }
        return newImageBuffer;
    }

    /*
     *
     *
     *
     * */
    public static BufferedImage mosaicImage(BufferedImage imageBuffer, Collection<MarkInfo> mosaics) throws RuntimeException {

        if (imageBuffer == null)
            throw new IllegalArgumentException("imageBuffer is null!");

        if (mosaics == null || mosaics.size() == 0)
            throw new IllegalArgumentException("mosaics == null or mosaics.size ==0");

        int originalWidth = imageBuffer.getWidth();
        int originalHeight = imageBuffer.getHeight();
        Graphics g = imageBuffer.getGraphics();
        for (MarkInfo mosaic : mosaics) {

            if (mosaic.getLeftPosition() + mosaic.getWidth() > originalWidth)
                continue;
            if (mosaic.getTopPosition() + mosaic.getHeight() > originalHeight)
                continue;

            int mosaicRowCount = ImageUtil.caculcMosaicCount(mosaic.getHeight());
            int mosaicColumnCount = ImageUtil.caculcMosaicCount(mosaic.getWidth());

            if (mosaicColumnCount <= 0 || mosaicRowCount <= 0) continue;

            double cellMosaicWidth = mosaic.getWidth() / mosaicColumnCount;
            double cellMosaicHeight = mosaic.getHeight() / mosaicRowCount;

            for (int row = 1; row <= mosaicRowCount; row++) {
                for (int column = 1; column <= mosaicColumnCount; column++) {

                    double currentCellLeft = mosaic.getLeftPosition() + (column - 1) * cellMosaicWidth;
                    double currentCellTop = mosaic.getTopPosition() + (row - 1) * cellMosaicHeight;

                    double centerX = currentCellLeft + cellMosaicWidth / 2;
                    double centerY = currentCellTop + cellMosaicHeight / 2;

                    Color color = new Color(imageBuffer.getRGB((int) centerX, (int) centerY));

                    g.setColor(color);
                    g.fillRect((int) currentCellLeft, (int) currentCellTop, (int) cellMosaicWidth, (int) cellMosaicHeight);
                }
            }
        }
        g.dispose();

        return imageBuffer;
    }

    public static BufferedImage markWithColor(BufferedImage imageBuffer, Collection<MarkInfo> marks, Color color) throws RuntimeException {
        if (imageBuffer == null)
            throw new IllegalArgumentException("imageBuffer is null!");

        if (marks == null || marks.size() == 0)
            throw new IllegalArgumentException("marks == null or marks.size ==0");

        int originalWidth = imageBuffer.getWidth();
        int originalHeight = imageBuffer.getHeight();
        Graphics g = imageBuffer.getGraphics();

        for (MarkInfo mark : marks) {

            if (mark.getLeftPosition() + mark.getWidth() > originalWidth)
                continue;
            if (mark.getTopPosition() + mark.getHeight() > originalHeight)
                continue;

            g.setColor(color);
            g.fillRect((int) mark.getLeftPosition(), (int) mark.getTopPosition(), (int) mark.getWidth(), (int) mark.getHeight());
        }
        g.dispose();

        return imageBuffer;
    }


    private static int caculcMosaicCount(double mosaicLength) {
        if(MosaicSize < 6.0) MosaicSize = 6.0;
        return (int) (mosaicLength / MosaicSize);
    }

    public enum ImageTypes {
        OTHER,
        PNG,
        GIF
    }

    public class MarkInfo {

        private double leftPosition;
        private double topPosition;
        private double width;
        private double height;

        public double getLeftPosition() {
            return leftPosition;
        }

        public void setLeftPosition(double leftPosition) {
            this.leftPosition = leftPosition;
        }

        public double getTopPosition() {
            return topPosition;
        }

        public void setTopPosition(double topPosition) {
            this.topPosition = topPosition;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }
    }
}
