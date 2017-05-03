package com.bawei.today.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.today.R;
import com.bawei.today.activity.WebViewActivity;
import com.bawei.today.adapter.HomeListAdapter;
import com.bawei.today.bean.Newscontent;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2017/3/11.
 */

public class Tab_fragment extends Fragment {

    private PullToRefreshListView pulltnrefresh;
    private View view;
    private String url;
    private HomeListAdapter adapter;
    List<Newscontent> list=new ArrayList<Newscontent>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_fragment,null);
        Bundle bundle=  getArguments();
         url=bundle.getString("url");
         //找控件
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
          //
        initData();
    }

    private void initData() {
        RequestParams params=new RequestParams(url);
        params.setCacheMaxAge(1000*60*2);
         x.http().get(params, new Callback.CacheCallback<String>() {
             @Override
             public boolean onCache(String result) {
                 return false;
             }

             @Override
             public void onSuccess(String result) {
                   if(result!=null){
                       Gson gsn=new Gson();
                       try {
                           JSONObject jsonObject=new JSONObject(result);
                           Iterator<String> keys = jsonObject.keys();
                           while(keys.hasNext()){
                               JSONArray jsonArray = jsonObject.optJSONArray(keys.next());
                               for (int i=0;i<jsonArray.length();i++){
                                   JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                                   Newscontent newscontent = gsn.fromJson(jsonObject1.toString(), Newscontent.class);
                                   list.add(newscontent);
                               }
                           }
                           adapter = new HomeListAdapter(getActivity(),list);
                           pulltnrefresh.setAdapter(adapter);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }

                   }else{

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

    private void initView(View view) {
        pulltnrefresh = (PullToRefreshListView) view.findViewById(R.id.pulltorefresh);
        pulltnrefresh.setMode(PullToRefreshBase.Mode.BOTH);
        pulltnrefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                String url_3w = list.get(position-1).getUrl_3w();
                String title = list.get(position-1).getTitle();
                intent.putExtra("url_3",url_3w);
                intent.putExtra("title_3",title);
                startActivity(intent);
            }
        });
        pulltnrefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pulltnrefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pulltnrefresh.onRefreshComplete();
                    }
                },2000);

            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pulltnrefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pulltnrefresh.onRefreshComplete();
                    }
                },2000);
            }
        });

    }


}
