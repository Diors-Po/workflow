package com.flowable.workflow.web.exception;

import lombok.NoArgsConstructor;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 18:29
 */
@NoArgsConstructor
public class HttpBadRequestException extends RuntimeException{
    public HttpBadRequestException(String msg){
        super(msg);
    }

    public HttpBadRequestException(String msg, Throwable cause){
        super(msg, cause);
    }
}
