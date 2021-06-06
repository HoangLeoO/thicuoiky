package com.example.login_register_demo.service;

import com.example.login_register_demo.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserEntityService {
    List<UserEntity> findAll();

    void  deleteById(Long id);

    UserEntity findUserById(Long id);

    void save(UserEntity userEntity);

   List<UserEntity> findByName(String name);

//    Page<UserEntity> findByName(String name, Pageable pageable);
//    Page<UserEntity> findAll(Pageable pageable);
}
