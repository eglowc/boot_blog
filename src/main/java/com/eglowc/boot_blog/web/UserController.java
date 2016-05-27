package com.eglowc.boot_blog.web;

import com.eglowc.boot_blog.dto.UserDto;
import com.eglowc.boot_blog.common.exception.UserDuplicatedException;
import com.eglowc.boot_blog.domain.User;
import com.eglowc.boot_blog.service.UserService;
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
@RequestMapping("/users")
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
    @RequestMapping(method = GET)
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
    @RequestMapping(method = PUT)
    public ResponseEntity createUser(@RequestBody @Valid final UserDto.Create create,
                                     BindingResult result) {

        if (result.hasErrors()) {
            final ErrorInfos errorInfos = new ErrorInfos(
                    "잘못된 요청입니다.",
                    "bad.request",
                    result.getFieldErrors()
            );

            return entityUtil.getResponse(errorInfos, false, HttpStatus.BAD_REQUEST);
        }

        final User createdUser = userService.createUser(create);

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
