package com.eglowc.boot_blog.service;

import com.eglowc.boot_blog.common.exception.UserDuplicatedException;
import com.eglowc.boot_blog.domain.User;
import com.eglowc.boot_blog.dto.UserDto;
import com.eglowc.boot_blog.repository.UserRepository;
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

    public User createUser(final UserDto.Create dto) {
        final User user = modelMapper.map(dto, User.class);
        final String userName = dto.getUserName();

        if (userRepository.findByUserName(userName) != null) {
            log.error("user duplicated exception. {}", userName);
            throw new UserDuplicatedException(userName);
        }

        final Date now = new Date();
        user.setJoined(now);
        user.setUpdated(now);

        return userRepository.save(user);
    }
}
