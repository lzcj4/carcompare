package com.carcompare.dto.car;
import com.carcompare.base.BasePOIMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.usermodel.Row;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseCarInfoDto extends BasePOIMapper<BaseCarInfoDto> {
    private String  brand,series,year,model,firstBrandChar;

    public BaseCarInfoDto(){
    }
    public BaseCarInfoDto(String b,String s,String y,String m,String c){
        this.brand =b;
        this.series =s;
        this.year = y;
        this.model = m;
        this.firstBrandChar =c;
    }
    @Override
    public BaseCarInfoDto initDto(Row row) {
        if(row.getLastCellNum() < 3) return  null;
        this.brand = row.getCell(0).getStringCellValue().trim();
        this.series = row.getCell(1).getStringCellValue().trim();
        this.year=row.getCell(2).getStringCellValue().trim();
        this.model=row.getCell(3).getStringCellValue().trim();
        this.firstBrandChar =  row.getCell(4) == null ||row.getCell(4).getStringCellValue().isEmpty()?"": row.getCell(4).getStringCellValue().trim().toUpperCase();
        return this;
    }
    @Override
    public void insertRow(Row row) {
        row.createCell(0).setCellValue(this.brand);
        row.createCell(1).setCellValue(this.series);
        row.createCell(2).setCellValue(this.year);
        row.createCell(3).setCellValue(this.model);
        row.createCell(4).setCellValue(this.firstBrandChar);
    }
}
