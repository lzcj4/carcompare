package com.carcompare.dto.car;

import com.carcompare.core.vehicles.FeaturePicture;
import lombok.Data;

import java.math.BigInteger;

@Data
public class PictureDto {
    private BigInteger id;
    private String name, smallname, mediumname, bigname, orginnalname,path;

    public void readPicture(FeaturePicture featurePicture) {
        this.id = featurePicture.getId();
        this.name = featurePicture.getName();
        String path = featurePicture.getPath();
        if(path != null && !path.isEmpty() &&
                (!featurePicture.getPath().endsWith("\\") && !featurePicture.getPath().endsWith("/"))){
            this.smallname = path+"\\"+ featurePicture.getSmallname();
            this.mediumname =path+"\\"+  featurePicture.getMediumname();
            this.bigname =path+"\\"+  featurePicture.getBigname();
            this.orginnalname=path+"\\"+  featurePicture.getOriginname();
        }else {
            this.smallname = featurePicture.getSmallname();
            this.mediumname = featurePicture.getMediumname();
            this.bigname = featurePicture.getBigname();
            this.orginnalname=featurePicture.getOriginname();
        }
        this.path = featurePicture.getPath();

    }

    public FeaturePicture toEntity() {
        FeaturePicture featurePicture = new FeaturePicture();
        featurePicture.setId(this.id);
        featurePicture.setName(this.name);
        if (this.smallname != null)
            featurePicture.setSmallname(this.smallname.replace(this.path, ""));
        if (this.mediumname != null)
            featurePicture.setMediumname(this.mediumname.replace(this.path, ""));
        if (this.bigname != null)
            featurePicture.setBigname(this.bigname.replace(this.path, ""));
        if (this.orginnalname != null)
            featurePicture.setOriginname(this.orginnalname.replace(this.path, ""));
        featurePicture.setPath(this.path);
        return featurePicture;
    }
}
