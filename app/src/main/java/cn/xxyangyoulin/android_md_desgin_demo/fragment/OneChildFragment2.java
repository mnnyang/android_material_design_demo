package cn.xxyangyoulin.android_md_desgin_demo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.xxyangyoulin.android_md_desgin_demo.R;
import cn.xxyangyoulin.android_md_desgin_demo.base.BaseLazyFragment;
import cn.xxyangyoulin.android_md_desgin_demo.util.ImageResizer;

/**
 * Created by xxyangyoulin on 2018/4/24.
 */

public class OneChildFragment2 extends BaseLazyFragment {

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1_child_1,null);
    }

    @Override
    protected void initData() {
        ImageView imageView = mRootView.findViewById(R.id.iv_image);
        Bitmap bitmap = ImageResizer.decodeSampledBitmapFromResources(getResources(),
                R.drawable.two, mRootView.getMeasuredWidth(), mRootView.getMeasuredHeight());
        imageView.setImageBitmap(bitmap);
    }
}
