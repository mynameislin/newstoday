package com.bawei.today.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by admin on 2017/3/15.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<String>  list;
    private List<Fragment> flist;

    public TabAdapter(FragmentManager fm, Context context, List<String> list, List<Fragment> flist) {
        super(fm);
        this.context = context;
        this.list = list;
        this.flist = flist;
    }
public static int p;

    @Override
    public Fragment getItem(int position) {
        p=position;
        return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return list.get(position);
    }
}
