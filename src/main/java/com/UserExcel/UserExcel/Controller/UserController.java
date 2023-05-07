package com.UserExcel.UserExcel.Controller;

import com.UserExcel.UserExcel.Helper.ExcelHelper;
import com.UserExcel.UserExcel.Helper.PdfHelper;
import com.UserExcel.UserExcel.Model.User;
import com.UserExcel.UserExcel.Service.UserService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
  @Autowired
    private UserService userService;


  @PostMapping("/upload")
  public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
      if(ExcelHelper.checkExcelFormat(file)){
       this.userService.save(file);

       return ResponseEntity.ok(Map.of("message","file is uploaded and saved in Db"));
      }

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
  }


  @GetMapping("/getUser")
  public List<User> getAllUser(){
    return this.userService.getAllUser();
  }



    @GetMapping("/report")
    public ResponseEntity<?> generateReport() {
        try {
            byte[] pdfBytes = PdfHelper.generatePdf(userService.getAllUser());
            return ResponseEntity.ok().header("Content-Type", "application/pdf").body(pdfBytes);
        } catch (DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

