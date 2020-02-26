package com.example.haishimeitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.haishimeitu.util.SharedPreferencesUtil;

public class BaseActivity extends AppCompatActivity {
    /**
     * 访问修饰符改为protected
     */
    protected SharedPreferencesUtil sp;

    /**
     * 重写了setContentView方法
     * 因为在子类调用了setContentView设置布局
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // 配置文件
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());

        // 其他的公共逻辑也可以这样重构
    }
}
