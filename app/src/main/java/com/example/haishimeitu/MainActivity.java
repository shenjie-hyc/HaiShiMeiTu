package com.example.haishimeitu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.haishimeitu.activity.BaseActivity;
import com.example.haishimeitu.activity.LoginActivity;
import com.example.haishimeitu.adapter.ImageAdapter;
import com.example.haishimeitu.domain.Image;
import com.example.haishimeitu.util.SharedPreferencesUtil;

import java.util.ArrayList;

public class MainActivity /*extends AppCompatActivity*/ extends BaseActivity
{
    // SharedPreferencesUtil sp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sp = SharedPreferencesUtil.getInstance(getApplicationContext());

        // --------------- 找到RecyclerView初始化设置start ---------------
        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        // 显示2列, 我们这里实现的是每个图片的宽高都是一样
        // 最好的效果其实是根据图片高度和宽度来缩放
        // 因为每一张图片大小不一样,但这样实现涉及到的知识点很多

        // 这里使用了GridLayoutManager,它会显示类似9宫格布局效果
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);
        // --------------- 找到RecyclerView初始化设置end ---------------

        // --------------- 设置数据start ---------------
        // import com.zte.kanmeitu.domain.Image;  !!! 注意Image是我们自己实现的类
        ArrayList<Image> dataList = new ArrayList<Image>();
        for (int i = 1; i < 10; i++)
        {
// 前面我们虽然创建了Image,但没有添加真实的图片地址,这里我们添加一些网络图片地址
            // dataList.add(new Image(""));
            // 图片来自http://image.baidu.com
// 我们把从百度图片上下载下来的10张图片放在阿里云上
// 其中%d.jpg 表示用i替换%d

            dataList.add(new Image(String.format("http://dev-courses-quick.oss-cn-beijing.aliyuncs.com/%d.jpg",i)));
        }

        ImageAdapter adapter = new ImageAdapter(this);
        rv.setAdapter(adapter);

        adapter.setData(dataList);
        // --------------- 设置数据end---------------

    }

    /**
     * 用户退出
     */
    public void onLogoutClick(View view)
    {
        sp.setLogin(false);

        // 退出后,跳转到登录界面
        // 当然大家可以根据业务逻辑调整
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

