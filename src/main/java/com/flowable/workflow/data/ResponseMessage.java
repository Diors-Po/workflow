package com.flowable.workflow.data;

import com.flowable.workflow.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: xuexb
 * @Date: 2019.3.25 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    private int code;
    private String msg;
    private T data;

    public static final ResponseMessage OK = new ResponseMessage(Constant.OK, Constant.REQUEST_SUCCESS);

    public ResponseMessage(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
