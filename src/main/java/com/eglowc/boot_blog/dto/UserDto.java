package com.eglowc.boot_blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by hclee on 2016-05-11.
 *
 * @author eglowc
 */
public class UserDto {

    @Data
    public static class Create {
        @NotBlank
        @Size(min = 5)
        private String userName;

        @NotBlank
        @Email
        private String userEmail;

        @NotBlank
        @Size(min = 9)
        private String password;

        private String userNickName;


    }

    @Data
    public static class Response {
        private Long id;
        private String userName;
        private String userEmail;
        private String userNickName;
        @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss")
        private Date joined;
        @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss")
        private Date updated;

    }
}
