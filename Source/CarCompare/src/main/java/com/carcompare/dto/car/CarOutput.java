package com.carcompare.dto.car;

import com.carcompare.core.vehicles.Feature;
import com.carcompare.core.vehicles.FeaturePicture;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class CarOutput {

    private Feature features;

    Collection<PictureDto> pictureOutputs;

    public void readImages(List<FeaturePicture> images) {
        if (!images.isEmpty()) {
            if(pictureOutputs == null ) pictureOutputs = new ArrayList<>();
            for (FeaturePicture image : images) {
                PictureDto imageDto = new PictureDto();
                imageDto.readPicture(image);
                pictureOutputs.add(imageDto);
            }
        }
    }
    public List<FeaturePicture> getFeaturePictures() {
        List<FeaturePicture> featurePictures = new ArrayList<>();
        if (this.pictureOutputs != null && !this.pictureOutputs.isEmpty()) {
            for (PictureDto pic : this.pictureOutputs) {
                featurePictures.add(pic.toEntity());
            }
        }
        return featurePictures;
    }
}
