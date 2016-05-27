package com.eglowc.boot_blog.repository;

import com.eglowc.boot_blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hclee on 2016-05-11.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findByUserName(String userName);
}
