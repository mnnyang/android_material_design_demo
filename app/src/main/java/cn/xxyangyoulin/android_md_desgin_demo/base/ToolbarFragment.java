package cn.xxyangyoulin.android_md_desgin_demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.xxyangyoulin.android_md_desgin_demo.MainActivity;
import cn.xxyangyoulin.android_md_desgin_demo.R;

/**
 * Created by xxyangyoulin on 2018/4/24.
 */

public abstract class ToolbarFragment extends BaseLazyFragment {

    protected Toolbar mToolbar;

    protected abstract int getLayoutId();

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        mToolbar = inflate.findViewById(R.id.toolbar);

        Activity activity = getActivity();
        if (activity instanceof MainActivity){
            ((MainActivity)activity).initToolbarNavButton(mToolbar);
        }
        return inflate;
    }

}
