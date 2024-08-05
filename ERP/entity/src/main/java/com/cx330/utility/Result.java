package com.cx330.utility;


import com.cx330.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/*
* 返回状态
*  * 操作成功，没有返回数据 success=true, code=200, message="操作成功"， data=null
*  * 操作成功，有返回数据 success=true, code=200, message="操作成功", data=返回数据
*  * 操作失败，没有返回数据 success=false, code=500, message="操作失败"， data=null
*  * 操作失败，有返回数据 success=false, code=500, message="操作失败", data=返回错误信息
*
* */
@Data
public class Result<T> implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    /*
    * 响应成功没有Data
    * */
    public Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    /*
    *
    * 响应失败没有Data
    * */
    public Result<T> error() {
        Result<T> result = new Result<>();
        result.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        result.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        result.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return result;
    }

    public Result<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result<T> data(T data) {
        this.setData(data);
        return this;
    }


    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static Result fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Result.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
