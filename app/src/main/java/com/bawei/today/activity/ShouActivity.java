package com.bawei.today.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bawei.today.R;
import com.bawei.today.adapter.ShowAdapter;
import com.bawei.today.bean.DbSqlite;
import com.bawei.today.sqlite.MySqliteOpen;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/3/24 20:49
 */

public class ShouActivity extends SwipeBackActivity {

    private ListView lv;
    private List<DbSqlite> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shou_activity);

        //着空间
        initView();
        //得到数据
        MySqliteOpen mm=new MySqliteOpen(this);
        final SQLiteDatabase writableDatabase = mm.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from dbshou", null);
        while(cursor.moveToNext()){
             list.add(new DbSqlite(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            Log.i("xxx",cursor.getString(1));
        }
        final ShowAdapter ss=new ShowAdapter(this,list);
        lv.setAdapter(ss);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder dialog=new AlertDialog.Builder(ShouActivity.this);
                dialog.setTitle("确定要删除收藏？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        writableDatabase.execSQL("delete from dbshou where title = ?",new Object[]{list.get(position).getTitle()});
                        list.remove(list.get(position));
                        ss.notifyDataSetChanged();
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
    private void initView() {
        lv = (ListView) findViewById(R.id.shou_listview);
    }
}
