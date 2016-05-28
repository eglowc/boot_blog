package com.eglowc.boot_blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by hclee on 2016-05-11.
 *
 * @author eglowc
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, length = 50)
    @NotBlank
    @Size(min = 5, max = 50)
    private String userName;

    @NotBlank
    @Email
    private String userEmail;

    private String userNickName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 9, max = 50)
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss")
    private Date joined;

    @JsonFormat(pattern = "yyyy-MM-dd HH:MM:ss")
    private Date updated;

}
