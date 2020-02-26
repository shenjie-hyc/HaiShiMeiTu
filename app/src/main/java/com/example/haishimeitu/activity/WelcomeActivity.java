package com.example.haishimeitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;


import com.example.haishimeitu.MainActivity;
import com.example.haishimeitu.R;
import com.example.haishimeitu.util.SharedPreferencesUtil;


/**
 * 启动页面
 *
 * 3秒钟后进入登录界面
 *
 * 启动界面根据苹果开发者文档可以理解为是用来让用户加快启动的
 * 而不是在上面显示你的广告和商标的(Android开发我们暂时没有找到相关的定义)
 */
public class WelcomeActivity /* extends AppCompatActivity*/ extends BaseActivity
{
    // (1)
    // import android.os.Handler;  // 注意这里的包不能倒错
    // private Handler handler = new Handler(){};
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg)
        {
            super.handleMessage(msg);
            next();
        }
    };

    // 启动界面判断
    // private SharedPreferencesUtil sp;
    Intent itent = null;

    // (3)
    private void next()
    {
        if (sp.isLogin())
        {
            // 已经登录,跳转到首页
            itent = new Intent(WelcomeActivity.this, MainActivity.class);

        }else
        {
            // 否则跳转到登录界面
            itent = new Intent(WelcomeActivity.this, LoginActivity.class);
        }

        startActivity(itent);

        //关闭当前界面
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // sp = SharedPreferencesUtil.getInstance(getApplicationContext());

        // 去除状态栏
        // import android.view.WindowManager;
        // 其中 WindowManager.LayoutParams.FLAG_FULLSCREEN 为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // (2)
        // 这个开启的runnable会在这个handler所依附线程中运行,而这个handler是在UI线程中创建的,
        // 所以自然地依附在主线程中了
        // postDelayed(@RecentlyNonNull Runnable r, long delayMillis)
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                // 3秒钟后调用run方法
                // 这里调用是在子线程,不能直接操作UI,需要用handler切换到主线程
                // 用多个线程的目的解决,如果有耗时任务,那么就会卡界面
                // 而用了多线程后,将耗时任务放到子线程,这样主线程(UI线程)就不会卡主
                handler.sendEmptyMessage(0);
            }
        }, 3000);
    }
}
