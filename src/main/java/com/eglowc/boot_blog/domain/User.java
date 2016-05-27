package com.eglowc.boot_blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hclee on 2016-05-11.
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String userName;

    private String userEmail;

    private String userNickName;

    private String password;

//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss")
    private Date joined;

//    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:ss")
    private Date updated;

}
