package cn.xxyangyoulin.android_md_desgin_demo;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cn.xxyangyoulin.android_md_desgin_demo.fragment.OneFragment;
import cn.xxyangyoulin.android_md_desgin_demo.fragment.ThreeFragment;
import cn.xxyangyoulin.android_md_desgin_demo.util.ScreenUtils;
import cn.xxyangyoulin.android_md_desgin_demo.fragment.TwoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private FloatingActionButton mFab;
    private OneFragment mOneFragment;
    private TwoFragment mTwoFragment;
    private View mViewStatusColor;
    private ThreeFragment mThreeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUtil();
        initFab();

        initBottomNavigationView();
        initLeftNavigationView();

        initStatusColorView();

        mOneFragment = new OneFragment();
        replaceFragment(mOneFragment);
    }

    private void initLeftNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view);

        /*设置默认选中第一个菜单项*/
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void initFab() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
    }

    private void initBottomNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initUtil() {
        ScreenUtils.init(this);
    }

    private void initStatusColorView() {
        mViewStatusColor = findViewById(R.id.view_status_bar_color);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) mViewStatusColor.getLayoutParams();
        layoutParams.height = ScreenUtils.getSHeight();
        layoutParams.topMargin = -layoutParams.height;
    }

    /**
     * 设置站位view的颜色 如果没有这个view{@link #mViewStatusColor} 直接设置状态栏的颜色
     * 将会导致NavigationView无法透明显示到状态栏
     * 当我们跳转到其他页面的时候,应该隐藏这个view{@link #mViewStatusColor},只要传入-1即可.
     *
     * @param color -1 gone or -2 visible or color value
     */
    public void setStatusViewColor(int color) {
        if (color == -1) {
            mViewStatusColor.setVisibility(View.GONE);
            return;
        } else if (color == -2) {
            mViewStatusColor.setVisibility(View.VISIBLE);
            return;
        }

        if (mViewStatusColor.getVisibility() != View.VISIBLE) {
            mViewStatusColor.setVisibility(View.VISIBLE);
        }

        mViewStatusColor.setBackgroundColor(color);
    }

    private void replaceFragment(Fragment fragment) {
        if (fragment == null) return;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.layout_content, fragment);
        ft.commit();
    }

    /**
     * 绑定侧拉栏和toolbar按钮
     *
     * @param toolbar
     */
    public void initToolbarNavButton(Toolbar toolbar) {
        if (toolbar == null) return;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            replaceFragment(mOneFragment);
            setStatusViewColor(-2);
            getFab().show();

        } else if (id == R.id.nav_gallery) {
            if (mTwoFragment == null) mTwoFragment = new TwoFragment();
            replaceFragment(mTwoFragment);
            setStatusViewColor(-1);
            getFab().hide();

        } else if (id == R.id.nav_slideshow) {
            if (mThreeFragment == null) mThreeFragment = new ThreeFragment();
            replaceFragment(mThreeFragment);
            setStatusViewColor(-1);
            getFab().hide();

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                fab();
                break;
        }
    }

    private void fab() {
        Toast.makeText(this, "fab click", Toast.LENGTH_SHORT).show();
    }


    public FloatingActionButton getFab() {
        return mFab;
    }
}
