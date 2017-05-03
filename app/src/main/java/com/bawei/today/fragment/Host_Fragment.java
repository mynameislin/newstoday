package com.bawei.today.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.today.R;
import com.bawei.today.activity.TitleActivity;
import com.bawei.today.adapter.TabAdapter;
import com.bawei.today.utils.HostListAdd;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/10.
 */

public class Host_Fragment extends Fragment {

    private TabLayout tab;
    private ViewPager vp;
    List<String> list = HostListAdd.addList();
    List<Fragment> flist=new ArrayList<>();
    List<String> url = HostListAdd.url();
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view=inflater.inflate(R.layout.host_fragment1,null);
        //找控件
        initView(view);
        return view;
    }
    private void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.host_tab);
        vp = (ViewPager) view.findViewById(R.id.host_viewpager);
        tv = (TextView) view.findViewById(R.id.tv_jia);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),TitleActivity.class);
                startActivityForResult(intent,1001);
            }
        });
        for (int i=0;i<list.size();i++){
             Fragment fragment=new Tab_fragment();
             Bundle bundle=new Bundle();
            bundle.putString("url",url.get(i));
            fragment.setArguments(bundle);
            flist.add(fragment);

        }

        TabAdapter adapter=new TabAdapter(getFragmentManager(), getActivity(), list, flist);
        vp.setAdapter(adapter);
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return flist.get(position);
            }

            @Override
            public int getCount() {
                return flist.size();
            }
            //重写下面这个方法来使fragment和tablayout的title相关联
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }
        });
        tab.setupWithViewPager(vp);
        tab.setTabsFromPagerAdapter(adapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
          if(requestCode==1001&&resultCode==1002){

          }
    }


}
