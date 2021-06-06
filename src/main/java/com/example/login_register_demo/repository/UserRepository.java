package com.example.login_register_demo.repository;

import com.example.login_register_demo.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findByName(String name);
//    Page<UserEntity> findByName(String name, Pageable pageable);
//    Page<UserEntity> findAll(Pageable pageable);
}
