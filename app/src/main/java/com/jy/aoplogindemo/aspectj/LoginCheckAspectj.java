package com.jy.aoplogindemo.aspectj;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.jy.aoplogindemo.OtherActivity;
import com.jy.aoplogindemo.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect //自定义切面类
public class LoginCheckAspectj {

    private static final String TAG = "zc>>>>>";

    //1.应用中用到了哪些注解,放到当前的切入点进行处理,(找到需要处理的切入点)
    //execution 以方法执行时作为切点,触发Aspect类
    //* *(..)可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.jy.aoplogindemo.annotation.LoginCheck * *(..))")
    public void methodPintcut() {
    }

    //2.对切入点如何处理
    @Around("methodPintcut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();

        if (true){//从Sp中获取登录状态
            Log.d(TAG, "检测到已经登录");
            return joinPoint.proceed();//方法继续执行
        }else {
            Log.d(TAG, "检测到未登录");
            Toast.makeText(context,"请先登录",Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, OtherActivity.class));
            return null;//不在执行方法
        }

    }
}
