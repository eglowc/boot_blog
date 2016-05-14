package com.eglowc.boot_blog.accounts.service;

import com.eglowc.boot_blog.accounts.UserDto;
import com.eglowc.boot_blog.accounts.UserDuplicatedException;
import com.eglowc.boot_blog.accounts.UserRepository;
import com.eglowc.boot_blog.accounts.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by hclee on 2016-05-11.
 */
@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public User createUser(UserDto.Create dto) {

        User user = modelMapper.map(dto, User.class);

        String userName = dto.getUserName();

        if (userRepository.findByUserName(userName) != null) {
            log.error("user duplicated exception. {}", userName);
            throw new UserDuplicatedException(userName);
        }

        Date now = new Date();
        user.setJoined(now);
        user.setUpdated(now);

        return userRepository.save(user);
    }
}
