package com.jy.aoplogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jy.aoplogindemo.annotation.ClickBehavior;
import com.jy.aoplogindemo.annotation.LoginCheck;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "zc>>>>>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @ClickBehavior("登录")
    public void login(View view) {
        Log.d(TAG, "模拟接口请求,登录成功");
        //startActivity(new Intent(this,OtherActivity.class));
    }

    @ClickBehavior("会员中心")
    @LoginCheck
    public void memberCenter(View view) {
        Log.d(TAG, "跳转到会员中心");
        startActivity(new Intent(this, OtherActivity.class));
    }

    @ClickBehavior("我的优惠券")
    @LoginCheck
    public void coupon(View view) {
        Log.d(TAG, "跳转到优惠券");
        startActivity(new Intent(this, OtherActivity.class));
    }

    @ClickBehavior("我的积分")
    @LoginCheck
    public void score(View view) {
        Log.d(TAG, "跳转到积分");
        startActivity(new Intent(this, OtherActivity.class));
    }
}
