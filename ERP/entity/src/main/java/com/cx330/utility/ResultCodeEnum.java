package com.cx330.utility;






/*
* 0 -成功
* 99999 -系统异常
* 1xxxx -参数校验错误
* 2xxxx -业务错误
* 3xxxx -权限错误
* 4xxxx -数据错误
* 5xxxx -第三方错误
* 6xxxx -文件错误
* 7xxxx -缓存错误
* 8xxxx -MQ错误
* 9xxxx -未知错误
* */
public enum ResultCodeEnum {
    SUCCESS(true, 0, "响应成功"),
    UNKNOWN_REASON(false, 90001, "未知错误"),
    SQL_UPDATE_ERROR(false, 40001, "SQL更新错误"),
    SQL_INSERT_ERROR(false, 40002, "SQL插入错误"),
    SQL_DELETE_ERROR(false, 40003, "SQL删除错误"),
    SQL_SELECT_ERROR(false, 40004, "SQL查询错误"),
    DATA_NOT_EXIST(false, 40005, "数据不存在"),
    DATA_EXIST(false, 40006, "数据已存在"),
    DATA_FORMAT_ERROR(false, 40007, "数据格式错误"),
    DATA_OVERFLOW(false, 40008, "数据溢出"),
    DATA_UNDERFLOW(false, 40009, "数据下溢"),
    DATA_TYPE_ERROR(false, 40010, "数据类型错误"),
    ;




    private Boolean success;
    private Integer code;
    private String message;
    ResultCodeEnum() { }
    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
