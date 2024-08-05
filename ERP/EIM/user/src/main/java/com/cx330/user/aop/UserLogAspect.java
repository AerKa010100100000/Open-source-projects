package com.cx330.user.aop;


import com.cx330.entity.User;
import com.cx330.user.service.UserLogService;
import com.cx330.utility.ThreadLocalBuffer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/*
* 定义切面类
* */
@Aspect
@Component
public class UserLogAspect {

    @Autowired
    private UserLogService userLogService;

    // 拦截所有注解了UserLog注解的方法
    @Pointcut(value = "@annotation(com.cx330.user.aop.UserLog)")
    public void userLog(){};

    // 定义切点方法，在切点方法中记录日志
    @Transactional
    @AfterReturning(value = "userLog()", returning = "retVal")
    public void doUserLog(JoinPoint joinPoint, Object retVal){
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取注解
        UserLog userLog = method.getAnnotation(UserLog.class);
        // 获取注解值
        String operation = userLog.operation();
        String operationInfo = userLog.operationInfo();
        // 记录日志
        User user = (User) ThreadLocalBuffer.get();
        int addUserLog = userLogService.addUserLog(user.getUserID(), operation, operationInfo);
        if (addUserLog > 0) {
            System.out.println("记录日志成功！");
        } else {
            System.out.println("记录日志失败！");
        }
    }
}
