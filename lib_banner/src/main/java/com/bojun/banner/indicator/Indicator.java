package com.bojun.banner.indicator;

import android.view.View;

import androidx.annotation.NonNull;

import com.bojun.banner.config.IndicatorConfig;
import com.bojun.banner.listener.OnPageChangeListener;

public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
