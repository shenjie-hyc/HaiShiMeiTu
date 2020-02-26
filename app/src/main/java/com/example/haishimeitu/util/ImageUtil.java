package com.example.haishimeitu.util;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.haishimeitu.R;

/**
 * Glide框架的基础使用方法的封装
 */
public class ImageUtil {

    /**
     * 传递ImageView和图片地址
     * 就可以将图片地址的图片显示到该ImageView
     */
    public static void show(Activity activity, ImageView view, String uri) {
        RequestOptions options = getCommonRequestOptions();
        // 把uri对应的图片显示到ImageView上
        // 其中options是对图片的处理  如图片不能显示则显示什么样的占位图片  图片正在加载中显示什么样的默认图片
        Glide.with(activity).load(uri).apply(options).into(view);
    }

    /**
     * 获取公共的请求图片选项
     */
    public static RequestOptions getCommonRequestOptions() {
        RequestOptions options = new RequestOptions();

        // 加载前占位图
        options.placeholder(R.drawable.ic_place_holder);

        // 加载错误图
        // 其中占位图 和 错误图 可以一样  也可以不一样
        options.error(R.drawable.ic_place_holder);
        options.centerCrop();  // 根据ImageView的大小进行裁剪

        // 测试 ---> 禁用所有缓存
        // options.diskCacheStrategy(DiskCacheStrategy.NONE);

        return options;
    }
}
