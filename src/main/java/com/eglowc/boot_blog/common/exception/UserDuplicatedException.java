package com.eglowc.boot_blog.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Created by hclee on 2016-05-11.
 * <p/>
 * 사용자 추가시 중복된 사용자이름 발생으로 인한 예외
 *
 * @author eglowc
 */
@AllArgsConstructor
public class UserDuplicatedException extends RuntimeException {

    @Getter
    String userName;

}
