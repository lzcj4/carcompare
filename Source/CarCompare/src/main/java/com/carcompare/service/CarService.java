package com.carcompare.service;

import com.carcompare.base.UserFriendlyException;
import com.carcompare.dto.car.CarOutput;
import com.carcompare.dto.car.ImageParameter;
import com.carcompare.dto.car.Vehicle;
import com.carcompare.core.logs.Log;
import com.carcompare.core.vehicles.Feature;
import com.carcompare.core.vehicles.FeaturePicture;
import com.carcompare.mapper.VehicleFeatureMapper;
import com.carcompare.mapper.VehicleFeaturePictureMapper;
import com.carcompare.mapper.VehicleMapper;
import com.carcompare.utils.FileUtil;
import com.carcompare.utils.ImageUtil;
import com.carcompare.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Service
public class CarService extends BaseService {

    public static Logger logger = LoggerFactory.getLogger(CarService.class);

    @Value("${spring.images.thumbnail.small.width}")
    private int smallWidth;

    @Value("${spring.images.thumbnail.middle.width}")
    private int middleWidth;

    @Value("${spring.images.path}")
    String imagePath;

    @Value("${spring.images.physicalpath}")
    String imagePyRoot;

    @Value("${spring.images.relativepath}")
    String imageRelativePath;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private VehicleFeatureMapper vehicleFeatureMapper;

    @Autowired
    private VehicleFeaturePictureMapper vehicleFeaturePictureMapper;

    @Autowired
    private LogUtil logUtil;

    public CarOutput get(String id) {
        CarOutput carOutput = new CarOutput();
        Feature feature = vehicleFeatureMapper.getById(id);
        carOutput.setFeatures(feature);

        List<FeaturePicture> featurePictures = vehicleFeaturePictureMapper.getAllByVehicleId(id);
        carOutput.readImages(featurePictures);

        return carOutput;
    }

    public String exists(String bid, String sid, String yid, String mid) {
        List<Feature> features = vehicleFeatureMapper.getAllFeatureByBSYM(bid, sid, yid, mid);
        if (features.size() > 0)
            return features.get(0).getId();
        return "";
    }

    @Transactional
    public String delete(String id) {
        Vehicle vehicle = vehicleMapper.getVehicleById(id);
        if(vehicle == null){
            throw new UserFriendlyException("车型不存在或已删除");
        }

        List<FeaturePicture> pictures = vehicleFeaturePictureMapper.getAllByVehicleId(id);
        if (pictures != null && !pictures.isEmpty()) {
            Collection<String> images = new ArrayList<>();
            for (FeaturePicture pic : pictures) {
                this.deleteImageByEntity(pic, false);
            }
        }
        vehicleFeaturePictureMapper.deleteByVehicleId(id);
        vehicleFeatureMapper.deleteById(id);
        saveDeleteVehicleLog(vehicle);
        return id;
    }

    @Transactional
    public Collection<String> deleteImagebyFeatureId(String fid) {
        List<FeaturePicture> pictures = vehicleFeaturePictureMapper.getAllByVehicleId(fid);
        Collection<String> images = new ArrayList<>();
        if (pictures != null && !pictures.isEmpty()) {
            for (FeaturePicture pic : pictures) {
                this.deleteImageByEntity(pic, false);
            }
        }
        vehicleFeaturePictureMapper.deleteByVehicleId(fid);
        vehicleFeatureMapper.updateDisplayImage(fid,null);
        return images;
    }
    /*
     *新的保存：不再判断重复不重复
     */
    @Transactional
    public String saveFeature(Feature input) {
        try {
            List<Feature> features = vehicleFeatureMapper.getAllFeatureByBSYM(input.getBrand(), input.getSeries(), input.getModelyear(), input.getModel());
            if (features.size() > 0) throw new UserFriendlyException("已经存在相同品牌的数据！");
            input.setId(UUID.randomUUID().toString());
            input.setCreatetime(new Date());
            input.setCreator(this.getCurrentUserId().intValue());
            vehicleFeatureMapper.insertFeature(input);
            saveAddOrUpdateVehicleLog(input.getId(), true);
            return input.getId();
        } catch (Exception ex) {
            logger.error(ex.toString());
            if (ex instanceof UserFriendlyException) throw ex;
            throw new UserFriendlyException("数据插入失败！");
        }
    }

    /*
     *新保存逻辑，保存前根据条件 选择 是否删除已经存在的品牌数据约束
     * 本方法同时适应新增和更新，根据新的更新编辑逻辑只检查品牌约束，
     * @param requireDelete ture  不删除自己，否则 全部删除
     */
    @Transactional
    public String updateAndAuditFeature(Feature input,int status) {
        try {
            vehicleFeatureMapper.updateFeature(input);
            vehicleFeatureMapper.auditById(input.getId(),status);
            this.log(input.getId(),logUtil.VEHICLE_Audited_Save);
            return input.getId();
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new UserFriendlyException("数据保存失败！");
        }
    }
    /*
     *新保存逻辑，保存前根据条件 选择 是否删除已经存在的品牌数据约束
     * 本方法同时适应新增和更新，根据新的更新编辑逻辑只检查品牌约束，
     * @param requireDelete ture  不删除自己，否则 全部删除
     */
    @Transactional
    public String updateFeature(Feature input) {
        try {
            vehicleFeatureMapper.updateFeature(input);
            saveAddOrUpdateVehicleLog(input.getId(), false);
            return input.getId();
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new UserFriendlyException("数据保存失败！");
        }
    }
    /*通过Id审查 status:0 待审核，1：审核通过
    * */
    public boolean auditById(String id,int status) {
        try {
            vehicleFeatureMapper.auditById(id, status);
            this.log(id,logUtil.VEHICLE_Audited);
            return true;
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new UserFriendlyException("审核失败！");
        }
    }

    public Collection<String> deleteImageById(BigInteger id) {
        FeaturePicture image = vehicleFeaturePictureMapper.getById(id);
        return deleteImageByEntity(image, true);
    }

    public Collection<String> deleteImageByEntity(FeaturePicture image, Boolean deleteDB) {
        Collection<String> images = new ArrayList<>();
        if (image != null) {
            File dir = new File(this.imagePyRoot, image.getPath());
            String realPath = dir.getPath();
            if(realPath != null && realPath.length() >0 && (realPath.endsWith("/")
                    || realPath.endsWith("\\")))
                realPath.substring(0,realPath.length() -1);
            String originImage = image.getOriginname();
            String smallImage = image.getSmallname();
            String middleImage = image.getMediumname();
            String bigname = image.getBigname();
            if (originImage != null && !originImage.isEmpty()) images.add(realPath +"/"+ originImage);
            if (smallImage != null && !smallImage.isEmpty()) images.add(realPath +"/"+smallImage);
            if (middleImage != null && !middleImage.isEmpty()) images.add(realPath +"/"+middleImage);
            if (bigname != null && !bigname.isEmpty()) images.add(realPath +"/"+bigname);
        }
        Collection<String > result = this.deleteImages(images);
        if (deleteDB) {
            vehicleFeaturePictureMapper.deleteById(image.getId());
            vehicleFeatureMapper.updateDisplayImage(image.getParentid(),null);
        }
        return result;
    }

    public Collection<String> deleteImages(Collection<String> imgepaths) {
        Collection<String> deletes = new ArrayList<>();
        if (imgepaths != null && !imgepaths.isEmpty()) {
            for (String image : imgepaths) {
                if (FileUtil.deleteFile(image)) {
                    deletes.add(image);
                } else {
                    deletes.add(image + "----delete failed");
                }
            }
        }
        return deletes;
    }

    public Collection<String> addImages(MultipartFile[] images, BigInteger[] imageIds,
                                        Map<String, ImageParameter[]> parameters, String id) {

        if (imageIds != null && imageIds.length > 0) {
            for (BigInteger imageId : imageIds) {
                this.deleteImageById(imageId);
            }
        }

        Collection<String> results = new ArrayList<>();
        if (id != null && !id.isEmpty()) {
            for (MultipartFile image : images) {
                if (parameters.containsKey(image.getOriginalFilename())) {
                    results.add(addImage(image, parameters.get(image.getOriginalFilename()), id));
                } else {
                    results.add(addImage(image, null, id));
                }
            }
        }
        return results;
    }

    private String addImage(MultipartFile image, ImageParameter[] parameters, String id) {
        String imageUrl = "", smallUrl = "", middleUrl = "";
        if (id != null && !id.isEmpty() && !image.isEmpty()) try {
            String fullName = image.getOriginalFilename();
            String name = fullName.substring(0,fullName.lastIndexOf("."));
            String extention = fullName.substring(fullName.lastIndexOf(".")+1);

            String fileId = UUID.randomUUID().toString();

            ImageUtil.ImageTypes imageType = ImageUtil.ImageTypes.OTHER;
            if (extention.toLowerCase() == "png") imageType = ImageUtil.ImageTypes.PNG;
            else if (extention.toLowerCase() == "gif") imageType = ImageUtil.ImageTypes.GIF;

            File dir = new File(this.imagePath, this.getImagePath() + "/" + id);
            String relativeDir = this.imageRelativePath.endsWith("/") || this.imageRelativePath.endsWith("\\") ?
                    (this.imageRelativePath + this.getImagePath() + id) : (this.imageRelativePath + "/" + this.getImagePath() + id);
            //检车目录
            if (!dir.exists()) {
                dir.mkdirs();
            }

            BufferedImage originImage = ImageIO.read(image.getInputStream());
            int actH = originImage.getHeight();
            int actW = originImage.getWidth();

            if (parameters != null && parameters.length > 0) {
                Collection<ImageUtil.MarkInfo> marks = new ArrayList<>();
                for (ImageParameter parameter : parameters) {
                    ImageUtil.MarkInfo mark = new ImageUtil().new MarkInfo();
                    double hp = (double) actH / parameter.getOriginalHeight();
                    double wp = (double) actW / parameter.getOriginalWidth();

                    mark.setHeight(hp * parameter.getHeight());
                    mark.setWidth(wp * parameter.getWidth());
                    mark.setLeftPosition(wp * parameter.getLeft());
                    mark.setTopPosition(hp * parameter.getTop());
                    marks.add(mark);
                }
                originImage = ImageUtil.markWithColor(originImage, marks,Color.WHITE);//mosaicImage(originImage, marks);
            }

            imageUrl = UUID.randomUUID().toString() + "." + extention;
            ImageIO.write(originImage, extention, new File(dir.getPath(), imageUrl));

            BufferedImage scaleImage = ImageUtil.zoomImageScale(originImage, this.smallWidth, imageType);
            smallUrl = UUID.randomUUID().toString() + "." + extention;
            ImageIO.write(scaleImage, extention, new File(dir.getPath(), smallUrl));

            scaleImage = ImageUtil.zoomImageScale(originImage, this.middleWidth, imageType);
            middleUrl = UUID.randomUUID().toString() + "." + extention;
            ImageIO.write(scaleImage, extention, new File(dir.getPath(), middleUrl));

            FeaturePicture featurePicture = new FeaturePicture();
            featurePicture.setParentid(id);
            featurePicture.setOriginname(imageUrl);
            featurePicture.setSmallname(smallUrl);
            featurePicture.setMediumname(middleUrl);
            featurePicture.setPath(relativeDir);
            featurePicture.setName(name == null || name.length()<50? name:name.substring(0,49));
            vehicleFeaturePictureMapper.insertPicture(featurePicture);
            vehicleFeatureMapper.updateDisplayImage(id,null);

        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new UserFriendlyException("文件保存失败");
        }
        return imageUrl;
    }
    private String getImagePath() {
        return new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
    }

    private void saveDeleteVehicleLog(Vehicle vehicle){
        Log log = new Log();
        log.setUsername(getCurrentUsername());
        log.setTime(new Date());
        log.setType(LogUtil.VEHICLE_DELETE);
        log.setDescription(vehicle.getComplexName());
        logUtil.AddLog(log);
    }

    private void saveAddOrUpdateVehicleLog(String vehicleId, Boolean isAdd){
        Vehicle vehicle = vehicleMapper.getVehicleById(vehicleId);
        Log log = new Log();
        log.setUsername(getCurrentUsername());
        log.setTime(new Date());
        log.setType(isAdd ? LogUtil.VEHICLE_ADD : LogUtil.VEHICLE_UPDATE);
        log.setDescription(vehicle.getComplexName());
        logUtil.AddLog(log);
    }

    private void log(String vehicleId, String logType){
        Vehicle vehicle = vehicleMapper.getVehicleById(vehicleId);
        Log log = new Log();
        log.setUsername(getCurrentUsername());
        log.setTime(new Date());
        log.setType(logType);
        log.setDescription(vehicle.getComplexName());
        logUtil.AddLog(log);
    }
}
