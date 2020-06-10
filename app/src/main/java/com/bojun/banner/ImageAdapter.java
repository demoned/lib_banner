package com.bojun.banner;

import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bojun.banner.adapter.BannerAdapter;
import com.bojun.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局，图片
 */
public class ImageAdapter extends BannerAdapter<DataBean, ImageHolder> {

    public ImageAdapter(List<DataBean> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView, 30);
        }
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
        holder.imageView.setImageResource(data.imageRes);
    }
}
