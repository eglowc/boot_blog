package com.eglowc.boot_blog.service;

import com.eglowc.boot_blog.common.exception.UserDuplicatedException;
import com.eglowc.boot_blog.domain.User;
import com.eglowc.boot_blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by hclee on 2016-05-11.
 *
 * @author eglowc
 */
@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(final User user) {
        final String userName = user.getUserName();

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
