package com.eglowc.boot_blog.web;

import com.eglowc.boot_blog.common.ErrorInfos;
import com.eglowc.boot_blog.common.exception.UserDuplicatedException;
import com.eglowc.boot_blog.domain.User;
import com.eglowc.boot_blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


/**
 * Created by hclee on 2016-05-11.
 *
 * @author eglowc
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * User List
     *
     * @return 200 OK
     */
    @RequestMapping(method = GET)
    public ResponseEntity users() {
        String message = "Hello. accounts";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * create User
     *
     * @param user   새롭게 생성할 {@link User} 정보
     * @param result {@link BindingResult}
     * @return {@link ResponseEntity}
     */
    @RequestMapping(method = PUT)
    public ResponseEntity createUser(@RequestBody @Valid final User user,
                                     BindingResult result) {

        if (result.hasErrors()) {
            final ErrorInfos errorInfos = new ErrorInfos(
                    "잘못된 요청입니다.",
                    "bad.request",
                    result.getFieldErrors()
            );

            return new ResponseEntity<>(errorInfos, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    /**
     * 사용자 생성시 중복된 userName 을 사용한 경우 예외처리.
     *
     * @param e {@link UserDuplicatedException}
     * @return {@link ErrorInfos}
     */
    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity handleUserDuplicatedException(UserDuplicatedException e) {
        ErrorInfos errorInfos = new ErrorInfos(
                "[" + e.getUserName() + "] 중복된 username 입니다.",
                "users.username.duplicated"
        );

        return new ResponseEntity<>(errorInfos, HttpStatus.EXPECTATION_FAILED);
    }

}
