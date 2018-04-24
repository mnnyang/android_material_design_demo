package cn.xxyangyoulin.android_md_desgin_demo.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.xxyangyoulin.android_md_desgin_demo.util.BottomNavigationViewHelper;
import cn.xxyangyoulin.android_md_desgin_demo.MainActivity;
import cn.xxyangyoulin.android_md_desgin_demo.R;
import cn.xxyangyoulin.android_md_desgin_demo.adapter.BasePageAdapter;
import cn.xxyangyoulin.android_md_desgin_demo.base.ToolbarFragment;
import cn.xxyangyoulin.android_md_desgin_demo.util.ImageResizer;

/**
 * Created by xxyangyoulin on 2018/4/24.
 */

public class OneFragment extends ToolbarFragment implements BottomNavigationView.OnNavigationItemSelectedListener {


    private ViewPager mViewPager;
    private BottomNavigationView mNavigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = super.initView(inflater, container, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (((MainActivity)getActivity()) != null) {
            toolbar.inflateMenu(R.menu.toolbar);
        }

        mViewPager = view.findViewById(R.id.view_pager);
        mNavigationView = view.findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(mNavigationView);

        return view;
    }

    @Override
    protected void initData() {
        TabLayout tabLayout = mRootView.findViewById(R.id.tab_layout);

        BasePageAdapter adapter = new BasePageAdapter(getFragmentManager());
        adapter.addFragment(new OneChildFragment1()).addTitles("child1")
                .addFragment(new OneChildFragment2()).addTitles("child2")
                .addFragment(new OneChildFragment3()).addTitles("child3");
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        changeColor(0);

        mNavigationView.setOnNavigationItemSelectedListener(this);
        setBadge();
    }

    /**
     * 来源:
     * https://blog.csdn.net/a_zhon/article/details/78334515
     */
    private void setBadge() {
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigationView.getChildAt(0);

        //这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;

        //加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(getContext()).inflate(R.layout.menu_badge, menuView, false);

        //添加到Tab上
        itemView.addView(badge);
    }

    int[] drawResList = {R.drawable.one, R.drawable.two, R.drawable.three};

    public void changeColor(int position) {

        if (position == -1) {
            position = mViewPager.getCurrentItem();
        }

        Bitmap bitmap = ImageResizer.decodeSampledBitmapFromResources(getResources(), drawResList[position], 100, 100);
        // 用来提取颜色的Bitmap
        // Palette的部分
        Palette.Builder builder = Palette.from(bitmap);

        final Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        final TabLayout tabLayout = mRootView.findViewById(R.id.tab_layout);

        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (null == vibrant) {
                    return;
                }

                toolbar.setBackgroundColor(vibrant.getRgb());

                tabLayout.setSelectedTabIndicatorColor(colorBurn(vibrant.getRgb()));
                tabLayout.setBackgroundColor(vibrant.getRgb());
                toolbar.setBackgroundColor(vibrant.getRgb());

                assert ((MainActivity) getActivity()) != null;
                ((MainActivity) getActivity()).setStatusViewColor(colorBurn(vibrant.getRgb()));

                if (android.os.Build.VERSION.SDK_INT >= 21) {
//                    Window window = getActivity().getWindow();
//                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
//                    window.setNavigationBarColor(Color.TRANSPARENT);
                }
            }
        });
    }

    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_dashboard:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_notifications:
                mViewPager.setCurrentItem(2);
                return true;

            case R.id.navigation_notifications2:
                Toast.makeText(mContext, "child4", Toast.LENGTH_SHORT).show();
                return true;
        }

        return false;
    }


}
