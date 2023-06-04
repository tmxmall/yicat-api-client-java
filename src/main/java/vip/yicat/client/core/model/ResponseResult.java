package vip.yicat.client.core.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {

    private String status;

    private String errCode;

    private String errMsg;

    private T result;

}

