package com.bawei.today.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.today.R;
import com.bawei.today.adapter.TabAdapter;
import com.bawei.today.adapter.VideoAdapter;
import com.bawei.today.bean.videocontent;
import com.bawei.today.utils.Videoutils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * Created by admin on 2017/3/10.
 */

public class Host_Fragment2 extends Fragment {
    //private String[] video=new String[]{"热点视频 ","娱乐视频 ","搞笑视频 ","精品视频 "};
   /* private List<String> id=new ArrayList<>();
    private List<Fragment> flist=new ArrayList<>();
    private List<String> list=new ArrayList<>();
    private TabLayout tab;
    private ViewPager vp;*/
    private XListView xlv;
    private String url="http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/";
    private String url_1="-10.html";
    private int count=0;
    private  List<videocontent> list=new ArrayList<>();;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view=inflater.inflate(R.layout.host2_fragment,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        xlv = (XListView) view.findViewById(R.id.host_xlv);
        getDataJosn();
        onLoadTime();
        /*tab = (TabLayout) view.findViewById(R.id.host_tab2);
        vp = (ViewPager) view.findViewById(R.id.host_viewpager);
        list.add("热点视频");
        list.add("娱乐视频");
        list.add("搞笑视频");
        list.add("精品视频");
        id.add("V9LG4B3A0");
        id.add("V9LG4CHOR");
        id.add("V9LG4E6VR");
        id.add("00850FRB");
        tab.setTabMode(TabLayout.MODE_FIXED);
        for (int i=0;i<list.size();i++){
            tab.addTab(tab.newTab().setText(list.get(i)));
         Fragment fragment=new video_fragment();
           Bundle bundle=new Bundle();
            bundle.putString("id",id.get(i));
            fragment.setArguments(bundle);
            flist.add(fragment);
        }
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
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
        });
        tab.setupWithViewPager(vp);
*/
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                count=0;
                list.clear();
                getDataJosn();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlv.stopRefresh();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                count=count+10;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xlv.stopLoadMore();
                        getDataJosn();
                    }
                },2000);
            }
        });
    }
    private void onLoadTime() {
        xlv.stopRefresh();
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(timeMillis);
        String format1 = format.format(date);
        xlv.setRefreshTime(format1);
    }
    private void getDataJosn() {
        StringBuffer sb=new StringBuffer();
        sb.append(url).append(count).append(url_1);

        RequestParams params = new RequestParams(sb.toString());
        params.setCacheMaxAge(1000*60*2);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                if(result!=null){
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        Iterator<String> keys = jsonObject.keys();
                        while(keys.hasNext()){
                            JSONArray jsonArray = jsonObject.optJSONArray(keys.next());
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                videocontent videocontent = gson.fromJson(jsonObject1.toString(), videocontent.class);
                                list.add(videocontent);
                            }
                        }
                        VideoAdapter vv=new VideoAdapter(getActivity(),list);
                        xlv.setAdapter(vv);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
