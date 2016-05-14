package com.eglowc.boot_blog.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hclee on 2016-05-11.
 */
@Data
public class ErrorInfos {

    private String errorMessage;

    private String errorCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldErrorInfo> fieldErrors;

    /**
     * Valid FieldError 처리를 위한 내장 클래스
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public class FieldErrorInfo {
        private String field;
        private String rejectedValue;
        private String reason; // defaultMessage
    }

    /**
     * Valid FieldError 를 처리함
     *
     * @param errorMessage
     * @param errorCode
     * @param fieldErrors
     */
    public ErrorInfos(String errorMessage, String errorCode, List<FieldError> fieldErrors) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;

        if (fieldErrors != null) {
            this.fieldErrors = new ArrayList<>();
            fieldErrors.forEach(e -> this.fieldErrors.add(new FieldErrorInfo(e.getField(), e.getRejectedValue().toString(), e.getDefaultMessage())));
        }
    }

    /**
     * Valid FieldError 처리안함
     *
     * @param errorMessage
     * @param errorCode
     */
    public ErrorInfos(String errorMessage, String errorCode) {
        this(errorMessage, errorCode, null);
    }

}
