package com.carcompare.service;

import com.carcompare.base.BasePOIMapper;
import com.carcompare.base.UserFriendlyException;
import com.carcompare.controller.AuthController;
import com.carcompare.dto.car.BaseCarInfoDto;
import com.carcompare.core.vehicles.VirtualBSYM;
import com.carcompare.mapper.VehicleMapper;
import com.carcompare.utils.ChineseUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InOutDataService {


    public static Logger logger = LoggerFactory.getLogger(InOutDataService.class);

    @Resource
    private VehicleMapper vehicleMapper;

    @Transactional
    public String importExcel(MultipartFile file, int startRowIndex, int sheetIndex) throws Exception{
        try {
            return importExcel(getWorkbook(file), startRowIndex, sheetIndex);
        }catch (Exception ex){
            logger.error(ex.toString());
            throw new UserFriendlyException("导入数据失败");
        }
    }

    public String importExcel(String  filePath, int startRowIndex, int sheetIndex) throws Exception{
        try {
            return importExcel(getWorkbook(filePath), startRowIndex, sheetIndex);
        }catch (Exception ex){
            logger.error(ex.toString());
            throw new UserFriendlyException("导入数据失败");
        }
    }

    static  int count =0,updateCount=0;
    static  String message = "";

    public String  importExcel(Workbook workbook, int startRowIndex, int sheetIndex) throws Exception{
        message="" ;
        Pair<List<BaseCarInfoDto>,String> pair = this.importExcel(workbook,BaseCarInfoDto.class,startRowIndex,sheetIndex);
        List<BaseCarInfoDto> dtoList = pair.getLeft() ;
        message += pair.getRight();
        if(dtoList == null || dtoList.size() ==0) {
            message += "未收集到任何有效数据！";
            return message;
        }
        message += "总共收集："+ dtoList.size()+"条数据。|";
        System.out.println(message);

        //去重複
        List<BaseCarInfoDto> tmpList =new ArrayList<>();
        for(int i = 0;i< dtoList.size();i++){
            if(i==0) {
                tmpList.add(new BaseCarInfoDto(dtoList.get(i).getBrand(),dtoList.get(i).getSeries(),dtoList.get(i).getYear(),dtoList.get(i).getModel()));
                continue;
            }
            boolean exsits = false;
            for(int j =0;j<tmpList.size();j++){
                if( dtoList.get(i).getBrand().equals(tmpList.get(j).getBrand()) &&
                        dtoList.get(i).getSeries().equals(tmpList.get(j).getSeries()) &&
                        dtoList.get(i).getYear().equals(tmpList.get(j).getYear()) &&
                        dtoList.get(i).getModel().equals(tmpList.get(j).getModel())){
                    String tmpMessage=String.format("行 %d 同  行 %d 重複！|",i+startRowIndex,j+startRowIndex);
                    message += tmpMessage;
                    logger.info(tmpMessage);
                    exsits = true;
                    break;
                }
            }

            if(!exsits)
                tmpList.add(new BaseCarInfoDto(dtoList.get(i).getBrand(),dtoList.get(i).getSeries(),dtoList.get(i).getYear(),dtoList.get(i).getModel()));
        }
        String tmpMessage= String.format("去重前 %d条，去重后 %d条， 共有重复数据　%d 条！|",dtoList.size(),tmpList.size(),dtoList.size() - tmpList.size());
        message += tmpMessage;
        logger.info(tmpMessage);
        dtoList = tmpList;

        count =0;
        updateCount=0;

        System.out.println(message);
        Map<String,List<BaseCarInfoDto>> brandGroups= dtoList.stream().collect(Collectors.groupingBy(BaseCarInfoDto::getBrand));
        brandGroups.forEach((b,bv)->{
            Pair<String,Boolean> result = saveOrUpdateVirtualBSYM("biz_brand",b,"");
            String bid = result.getLeft();
            if(bid != null && bid.length() ==36 ){
                Map<String,List<BaseCarInfoDto>> seriesGroups = bv.stream().collect(Collectors.groupingBy(BaseCarInfoDto::getSeries));
                seriesGroups.forEach((s,sv)->{
                    Pair<String,Boolean> resultS=saveOrUpdateVirtualBSYM("biz_series",s,bid);
                    String sid =resultS.getLeft();
                    if(sid != null && sid.length() ==36){
                        Map<String,List<BaseCarInfoDto>> yearGroups = sv.stream().collect(Collectors.groupingBy(BaseCarInfoDto::getYear));
                        yearGroups.forEach((y,yv)->{
                            Pair<String,Boolean> resultY=saveOrUpdateVirtualBSYM("biz_year",y,sid);
                            String yid =resultY.getLeft();
                            if(yid != null && yid.length() ==36) {
                                yv.forEach((m) -> {
                                    Pair<String,Boolean> resultM=  saveOrUpdateVirtualBSYM("biz_model", m.getModel(), yid);
                                    if(resultM.getRight()){
                                       String tmp="更新model:"+ m.getModel()+ "yid:"+yid;
                                       //message += tmp;
                                       logger.info(tmp);
                                       updateCount++;
                                    }else {
                                        count++;
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        tmpMessage="总共导入："+ count+"条数据,更新"+updateCount +"条数据。";
        message += tmpMessage;
        logger.info(tmpMessage);
        return message;
    }

    private  Pair<String,Boolean> saveOrUpdateVirtualBSYM(String tableName,String name ,String parentId) {
        VirtualBSYM bsym = new VirtualBSYM();
        bsym.setId(UUID.randomUUID().toString());
        bsym.setName(name);
        bsym.setParentid(parentId);
        bsym.setOrder(0);
        bsym.setTableName(tableName);
        if (tableName.compareToIgnoreCase("biz_year") == 0 && name.length() > 4)
            bsym.setCode(name.substring(0, 4));
        if (tableName.compareToIgnoreCase("biz_brand") == 0)
            bsym.setInitials(Character.toUpperCase(ChineseUtil.getFirstPinyin(name.charAt(0))));
        vehicleMapper.saveOrUpdate(bsym);

        return bsym.getOid() == null ? Pair.of(bsym.getId(),false):Pair.of(bsym.getOid(),true);
    }

    private  <T extends BasePOIMapper>  Pair<List<T>,String> importExcel(Workbook workbook,Class<T> mapperRow,int startRowIndex,int sheetIndex) throws Exception{
        List<T> dataList = null;
        Map map = new HashMap();
        if(workbook != null) {
            dataList = new ArrayList<>();
            if (sheetIndex < 0) sheetIndex = 0;
            Sheet sheet = workbook.getSheetAt(0);
            int limitNullRow =3;
            if (startRowIndex < 0 && startRowIndex > sheet.getFirstRowNum()) startRowIndex = 0;
            for (int rowIndex = startRowIndex; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                int nullCollum =0;
                if (row == null) continue;
                String tmp="";
                if (!setCellString(row.getCell(0)) || row.getCell(0).getStringCellValue() == "") {

                     String tmpMessage= String.format("%d 行 %d 列 遇到空值！",rowIndex,1);
                    tmp += tmpMessage;
                    logger.info(tmpMessage );
                    nullCollum ++;
                }
                if (!setCellString(row.getCell(1) ) || row.getCell(1).getStringCellValue() == "") {
                    String tmpMessage= String.format("%d 行 %d 列 遇到空值！",rowIndex,2);
                    tmp += tmpMessage;
                    logger.info(tmpMessage );
                    nullCollum ++;
                }
                if (!setCellString(row.getCell(2)) || row.getCell(2).getStringCellValue() == "") {
                    String tmpMessage= String.format("%d 行 %d 列 遇到空值！",rowIndex,3);
                    tmp += tmpMessage;
                    logger.info(tmpMessage );
                    nullCollum ++;
                }
                if (!setCellString(row.getCell(3)) || row.getCell(3).getStringCellValue() == "") {
                    String tmpMessage= String.format("%d 行 %d 列 遇到空值！",rowIndex,4);
                    tmp += tmpMessage;
                    logger.info(tmpMessage );
                    nullCollum ++;
                }
                if(nullCollum <4) message += tmp;
                if(nullCollum ==4) limitNullRow--;
                if(limitNullRow ==0) break;

                T obj = mapperRow.newInstance();
                if (nullCollum ==0 && obj.initDto(row) != null) {
                    dataList.add(obj);
                }
            }
            logger.info("总共收集到 %d 条数据",dataList.size());
        }
        return Pair.of(dataList,message);
    }

    private  boolean setCellString(Cell cell){
        if(cell == null) return  false;
        cell.setCellType(CellType.STRING);
        return  true;
    }

    private  <T extends BasePOIMapper> Workbook exportExcel(List<T> mapperRows,Workbook workbook,String templatePath,int startRowIndex) throws Exception{
        if(workbook == null){
            workbook = this.getWorkbook(templatePath);
        }
        List<T> dataList = null;
        if(workbook != null){
            Sheet sheet= workbook.createSheet();
            if(startRowIndex <0 ) startRowIndex =0;
            for (T obj :mapperRows) {
                obj.insertRow(sheet.createRow(startRowIndex));
                startRowIndex++;
            }
        }
        return  workbook;
    }

    private Workbook getWorkbook(MultipartFile file) throws IOException{
        Workbook workbook = null;
        if(file != null ){
            String fileName = file.getOriginalFilename();
            if(fileName != null && !fileName.isEmpty()){
                if(fileName.endsWith(".xls"))
                {
                    workbook = new HSSFWorkbook(file.getInputStream());
                }else if(fileName.endsWith("xlsx"))
                {
                    workbook = new XSSFWorkbook(file.getInputStream());
                }
            }
        }else {
            workbook = new XSSFWorkbook();
        }
        return  workbook;
    }

    private Workbook getWorkbook(String  templatePath) throws IOException{
        Workbook workbook = null;
        if(templatePath != null && !templatePath.isEmpty() ){
            if(templatePath != null && !templatePath.isEmpty()){
                if(templatePath.endsWith(".xls"))
                {
                    workbook = new HSSFWorkbook(new FileInputStream(templatePath));
                }else if(templatePath.endsWith("xlsx"))
                {
                    workbook = new XSSFWorkbook(new FileInputStream(templatePath));
                }
            }
        }else {
            workbook = new XSSFWorkbook();
        }
        return  workbook;
    }
}
