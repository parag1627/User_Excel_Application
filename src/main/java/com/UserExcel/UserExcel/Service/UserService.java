package com.UserExcel.UserExcel.Service;

import com.UserExcel.UserExcel.Helper.ExcelHelper;
import com.UserExcel.UserExcel.Model.User;
import com.UserExcel.UserExcel.Repository.UserRepository;
import lombok.experimental.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public void save(MultipartFile file){
        try {
            List<User> users = ExcelHelper.convertExcelToListOfUser(file.getInputStream());
             this.userRepository.saveAll(users);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<User>getAllUser(){
     return this.userRepository.findAll();
    }
}
