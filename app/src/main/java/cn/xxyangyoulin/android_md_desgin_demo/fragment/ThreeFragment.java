package cn.xxyangyoulin.android_md_desgin_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xxyangyoulin.android_md_desgin_demo.R;
import cn.xxyangyoulin.android_md_desgin_demo.base.ToolbarFragment;

/**
 * Created by xxyangyoulin on 2018/4/24.
 */

public class ThreeFragment extends ToolbarFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.initView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {

    }
}
