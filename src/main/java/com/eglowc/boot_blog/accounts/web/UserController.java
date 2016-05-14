package com.eglowc.boot_blog.accounts.web;

import com.eglowc.boot_blog.accounts.UserDto;
import com.eglowc.boot_blog.accounts.UserDuplicatedException;
import com.eglowc.boot_blog.accounts.domain.User;
import com.eglowc.boot_blog.accounts.service.UserService;
import com.eglowc.boot_blog.common.ErrorInfos;
import com.eglowc.boot_blog.common.utilities.ResponseEntityUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
 * Created by hclee on 2016-05-11.
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseEntityUtil entityUtil;

    @Autowired
    private UserService userService;

    /**
     * User List
     *
     * @return
     */
    @RequestMapping(value = "/users")
    public ResponseEntity users() {
        String message = "Hello Libo.accounts";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * create User
     *
     * @param create
     * @param result
     * @return
     */
    @RequestMapping(value = "/users", method = PUT)
    public ResponseEntity createUser(@RequestBody @Valid UserDto.Create create,
                                     BindingResult result) {

        if (result.hasErrors()) {
            ErrorInfos errorInfos = new ErrorInfos(
                    "잘못된 요청입니다.",
                    "bad.request",
                    result.getFieldErrors()
            );

            return entityUtil.getResponse(errorInfos, false, HttpStatus.BAD_REQUEST);
        }

        User createdUser = userService.createUser(create);

        return entityUtil.getSuccess(modelMapper.map(createdUser, User.class), HttpStatus.CREATED);
    }

    /**
     * 사용자 생성시 중복된 userName 을 사용한 경우 예외처리.
     *
     * @param
     * @return
     */
    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity handleUserDuplicatedException(UserDuplicatedException e) {
        ErrorInfos errorInfos = new ErrorInfos(
                "[" + e.getUserName().toString() + "] 중복된 username 입니다.",
                "users.username.duplicated"
        );
        return entityUtil.getFail(errorInfos, HttpStatus.EXPECTATION_FAILED);
    }

}
