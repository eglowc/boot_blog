package com.eglowc.boot_blog;


import com.eglowc.boot_blog.common.utilities.ResponseEntityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }

    @Bean
    public ResponseEntityUtil entityUtil() {
        return new ResponseEntityUtil();
    }
}
