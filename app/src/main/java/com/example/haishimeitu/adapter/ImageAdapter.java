package com.example.haishimeitu.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haishimeitu.R;
import com.example.haishimeitu.domain.Image;
import com.example.haishimeitu.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }

        public void bindData(Image data) {
// 在ImageAdapter 类中使用ImageUtil --> 现在只需要在Adapter中的ViewHolder的bindData方法中使用该方法
            // 显示图片  这里的context就是Activity
            ImageUtil.show((Activity) context, iv, data.getUri());
        }
    }

    // import com.zte.kanmeitu.domain.Image;  !!! 注意Image是我们自己实现的类
    private List<Image> dataList = new ArrayList<Image>();
    private final Context context;
    private final LayoutInflater inflater;

    public ImageAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<Image> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);

        // 刷新数据
        // 只有刷新数据, RecyclerView才知道数据变更了
        // 有很多刷新的方法
        notifyItemRangeInserted(0, dataList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 注意这里, 一定要将parent传递到inflate方法
        // LayoutInflater的作用是将一个xml布局转换成一个View
        return new ViewHolder(inflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
