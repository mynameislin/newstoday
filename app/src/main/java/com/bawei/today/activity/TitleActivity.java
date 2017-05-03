package com.bawei.today.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.today.R;
import com.bawei.today.adapter.MyGridAdapter;
import com.bawei.today.utils.HostListAdd;

import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by admin on 2017/3/12.
 */

public class TitleActivity extends SwipeBackActivity {

    private TextView bianji;
    private TextView fanhui;
    private GridView pindao_grid;
    private GridView tuijian_grid;
    private List<String> titlelist;
    private List<String> tuijianlist;
    private MyGridAdapter mm1;
    private MyGridAdapter mm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        String title = getIntent().getStringExtra("title");
        //找控件
        initView();
        //得到各个控件的事件
        pindao_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String s = titlelist.get(position);
                tuijianlist.add(s);
                mm2.notifyDataSetChanged();
                titlelist.remove(titlelist.get(position));
                mm1.notifyDataSetChanged();
            }
        });
        tuijian_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = tuijianlist.get(position);
                titlelist.add(s);
                mm1.notifyDataSetChanged();
                tuijianlist.remove(tuijianlist.get(position));
                mm2.notifyDataSetChanged();

            }
        });
    }
    private void initView() {
        bianji = (TextView) findViewById(R.id.title_bianji);
        fanhui = (TextView) findViewById(R.id.title_fan);
        pindao_grid = (GridView) findViewById(R.id.pindao_grid);
        tuijian_grid = (GridView) findViewById(R.id.tuijian_grid);
        titlelist = HostListAdd.list;
        tuijianlist = HostListAdd.addList2();
        mm1 = new MyGridAdapter(titlelist,this);
        pindao_grid.setAdapter(mm1);
        mm2 = new MyGridAdapter(tuijianlist,this);
        tuijian_grid.setAdapter(mm2);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TitleActivity.this.setResult(1002);
                TitleActivity.this.finish();
            }
        });
    } 
}
