package com.UserExcel.UserExcel.Helper;

import com.UserExcel.UserExcel.Model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {


     //check that  file is in Excel format or not
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
         if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
             return true;
         }
         return false;
    }


    //convert excel to list of user
    public static List<User> convertExcelToListOfUser(InputStream is){

        List<User> list = new ArrayList<>();
        try{
            XSSFWorkbook workBook = new XSSFWorkbook(is);

            XSSFSheet sheet = workBook.getSheet("data");

            int rowsNo =0;

            Iterator<Row> iterator = sheet.iterator();


            while(iterator.hasNext()){
                Row row = iterator.next();

                if(rowsNo == 0){
                    rowsNo++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;

                User  u = new User();
                while(cells.hasNext()){
               Cell cell = cells.next();
                 switch (cid){
                     case 0:
                         u.setId((int)cell.getNumericCellValue());
                         break;
                     case 1:
                         u.setName(cell.getStringCellValue());
                         break;

                     case 2:
                         u.setEmail(cell.getStringCellValue());
                         break;

                     case 3:
                         u.setPhone((int)cell.getNumericCellValue());
                         break;
                     default:
                         break;
                 }
                 cid++;
                }
                list.add(u);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
