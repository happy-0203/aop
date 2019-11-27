package com.jy.aoplogindemo.aspectj;

import android.util.Log;

import com.jy.aoplogindemo.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect //自定义切面类
public class ClickBehaviorAspectj {

    private static final String TAG = "zc>>>>>";

    //1.应用中用到了哪些注解,放到当前的切入点进行处理,(找到需要处理的切入点)
    //execution 以方法执行时作为切点,触发Aspect类
    //* *(..)可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.jy.aoplogindemo.annotation.ClickBehavior * *(..))")
    public void methodPintcut() {
    }

    //2.对切入点如何处理
    @Around("methodPintcut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取签名的方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //获取方法所属的类名
        String className = methodSignature.getDeclaringType().getSimpleName();

        //获取方法名
        String methodName = methodSignature.getName();

        //获取方法注解值
        String funValue = methodSignature.getMethod().getAnnotation(ClickBehavior.class).value();


        //统计方法的执行时间,统计用户点击某功能行为(存储到本地,每过多久上传到服务器);
        long begin = System.currentTimeMillis();
        Log.d(TAG, "ClickBehavior Method start>>> ");
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis()-begin;
        Log.d(TAG, "ClickBehavior Method end>>> ");
        Log.d(TAG, String.format("统计了:%s功能,在%s类的%s方法,用时%d ms",
                funValue,className,methodName,end));
        return result;


    }
}
