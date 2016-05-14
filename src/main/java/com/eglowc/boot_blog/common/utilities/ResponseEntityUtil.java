package com.eglowc.boot_blog.common.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by hclee on 2016-05-12.
 */

public class ResponseEntityUtil<T> {

    @Data
    @AllArgsConstructor
    private static class response<T> {
        private boolean success;
        T body;
    }

    /**
     * 성공한 경우의 응답을 생성합니다. getSuccess 값이 무조건 true 입니다.
     *
     * @param body
     * @param status
     * @return
     */
    public ResponseEntity getSuccess(T body, HttpStatus status) {
        return new ResponseEntity<>(
                new response<>(true, body)
                , status
        );
    }


    /**
     * 성공한 경우의 응답을 생성합니다. getSuccess 값이 무조건 true 입니다.
     * 응답상태 값은 항상 HttpsStatus.OK 입니다.
     *
     * @param body
     * @return
     */
    public ResponseEntity getSuccess(T body) {
        return this.getSuccess(body, HttpStatus.OK);
    }


    /**
     * 오류 또는 예외발생 시 사용, getSuccess 값이 무조건 false 입니다.
     *
     * @param body
     * @param status
     * @return
     */
    public ResponseEntity getFail(T body, HttpStatus status) {
        return new ResponseEntity<>(
                new response<>(false, body)
                , status
        );
    }

    /**
     * 성공 또는 실패, 응답상태를 모두 지정합니다.
     *
     * @param body
     * @param success
     * @param status
     * @return
     */
    public ResponseEntity getResponse(T body, boolean success, HttpStatus status) {
        return new ResponseEntity<>(
                new response<>(success, body)
                , status
        );
    }


}
