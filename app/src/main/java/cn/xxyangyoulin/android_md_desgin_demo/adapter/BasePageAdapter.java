package cn.xxyangyoulin.android_md_desgin_demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * viewpager adapter <br>
 * Created by mnnyang on 17-4-12.
 */

public class BasePageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;

    public BasePageAdapter(FragmentManager manager) {
        super(manager);
    }

    public BasePageAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.size() > position) {
            return titles.get(position);
        }
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public BasePageAdapter addFragment(Fragment fragment) {
        if (fragments == null) {
            fragments = new ArrayList<Fragment>();
        }
        fragments.add(fragment);
        return this;
    }

    public BasePageAdapter setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        return this;
    }

    public BasePageAdapter addTitles(String title) {
        if (titles == null) {
            titles = new ArrayList<String>();
        }
        titles.add(title);

        return this;
    }

    public BasePageAdapter setTitles(List<String> titles) {
        this.titles = titles;
        return this;
    }
}