package com.UserExcel.UserExcel.Repository;

import com.UserExcel.UserExcel.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
