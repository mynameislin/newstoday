package com.bawei.today.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.today.R;
import com.bawei.today.activity.GuanActivity;
import com.bawei.today.activity.WebViewActivity;
import com.bawei.today.adapter.MyGridAdapter;
import com.bawei.today.adapter.MyGuanAdapter;
import com.bawei.today.app.xutilsApp;
import com.bawei.today.bean.Dbguan;
import com.bawei.today.sqlite.MySqliteOpen;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/10.
 */

public class Host_Fragment3 extends Fragment {

    private ImageView iv;
    private ListView lv;
    private TextView tv;
    private TextView rong;
    private List<Dbguan> guan=new ArrayList<>();
    private MyGuanAdapter myGuanAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view=inflater.inflate(R.layout.host_fragment3,null);
        //找控件
        initView(view);
         tv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), GuanActivity.class);
                 startActivity(intent);
             }
         });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), GuanActivity.class);
                startActivity(intent);
            }
        });
        return view;


    }

    private void initView(View view) {
        tv = (TextView) view.findViewById(R.id.guanzhu_jia);
        lv = (ListView) view.findViewById(R.id.guanzhu_listview);
        iv = (ImageView) view.findViewById(R.id.guanzhu_iv);
        rong = (TextView) view.findViewById(R.id.guanzhu_nrong);
    }

    @Override
    public void onResume() {
         super.onResume();
        guan.clear();
        MySqliteOpen mm=new MySqliteOpen(getActivity());
        final SQLiteDatabase writableDatabase = mm.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from dbguan", null);
        while(cursor.moveToNext()){
            guan.add(new Dbguan(cursor.getInt(0),cursor.getString(1)));
            Log.i("xxx", "initView: "+guan.toString());
        }
        if(guan!=null){
            iv.setVisibility(View.GONE);
            rong.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            myGuanAdapter = new MyGuanAdapter(guan, getActivity());
            lv.setAdapter(myGuanAdapter);
            myGuanAdapter.notifyDataSetChanged();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
                dialog.setTitle("确定要取消关注？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             writableDatabase.execSQL("delete from dbguan where title = ?  ",new Object[]{guan.get(position).getTitle()});
                        guan.remove(guan.get(position));
                        myGuanAdapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }
}
