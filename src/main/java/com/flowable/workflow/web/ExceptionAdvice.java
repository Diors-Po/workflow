package com.flowable.workflow.web;

import com.flowable.workflow.constant.Constant;
import com.flowable.workflow.data.ResponseMessage;
import com.flowable.workflow.web.exception.HttpBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 18:51
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(HttpBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage badRequestException(HttpBadRequestException e){
        return new ResponseMessage(Constant.FAIL, e.getMessage());
    }


}
