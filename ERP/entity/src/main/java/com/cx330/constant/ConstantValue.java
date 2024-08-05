package com.cx330.constant;

public class ConstantValue {
    // 加密密钥
    public static final String KEY = "CLH520ZWG";
    // 重置密码
    public static final String NEW_PASSWORD = "CLH520ZWG";
    // 注销状态
    public static final int CANCEL_STATUS = 0;
    // 激活状态
    public static final int APPROVE_STATUS = 1;
    // JWT令牌过期时间
    public static final int JWT_EXPIRE_TIME = 60 * 60 * 24 * 7;
    // JWT加密密钥
    public static final String JWT_SECRET = "CLH520ZWG";
    // Token请求头
    public static final String AUTHORIZATION = "Authorization";
    // 日志--删除
    public static final String LOG_DELETE = "删除";
    // 日志--新增
    public static final String LOG_INSERT = "新增";
    // 日志--修改
    public static final String LOG_UPDATE = "修改";
    // 日志-- 查询
    public static final String LOG_SELECT = "查询";
    // 公司信息模块访问路径及端口
    public static final String FIRM_URL = "http://localhost:8013/";
    // 用户模块访问路径及端口
    public static final String USER_URL = "http://localhost:8014/";
    // 部门模块访问路径及端口
    public static final String DEPARTMENT_URL = "http://localhost:8015/";
    // 岗位信息以及员工信息模块访问路径及端口
    public static final String POSITION_URL = "http://localhost:8016/";
    // 日志模块访问路径及端口
}
