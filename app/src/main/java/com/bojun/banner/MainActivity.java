package com.bojun.banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bojun.banner.indicator.CircleIndicator;
import com.bojun.banner.indicator.RoundLinesIndicator;
import com.bojun.banner.listener.OnBannerListener;
import com.bojun.banner.listener.OnPageChangeListener;
import com.bojun.banner.transformer.AlphaPageTransformer;
import com.bojun.banner.transformer.ZoomOutPageTransformer;
import com.bojun.banner.util.BannerUtils;
import com.bojun.banner.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnPageChangeListener {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.indicator)
    RoundLinesIndicator indicator;
    @BindView(R.id.banner2)
    Banner banner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData());
        //添加画廊效果(可以和其他PageTransformer组合使用，比如AlphaPageTransformer，注意但和其他带有缩放的PageTransformer会显示冲突)
        banner.setBannerGalleryEffect(18, 14);
        //添加透明效果(画廊配合透明效果更棒)
        banner.addPageTransformer(new AlphaPageTransformer());
        banner.setAdapter(adapter)//设置适配器
//              .setCurrentItem(3,false)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
//              .setBannerRound(BannerUtils.dp2px(5))//圆角
//              .addPageTransformer(new RotateYTransformer())//添加切换效果
                .setIndicator(new CircleIndicator(this))//设置指示器
                .addOnPageChangeListener(this)//添加切换监听
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        LogUtils.d("position：" + position);
                    }
                });//设置点击事件,传this也行
        //实现1号店和淘宝头条类似的效果
        banner2.setAdapter(new TopLineAdapter(DataBean.getTestData2()))
                .setOrientation(Banner.VERTICAL)
                .setPageTransformer(new ZoomOutPageTransformer())
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {
                        LogUtils.d("position：" + position);
                    }
                });
        indicator.setVisibility(View.VISIBLE);
        //在布局文件中使用指示器，这样更灵活
        banner.setIndicator(indicator, false);
        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
