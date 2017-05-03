package com.bawei.today.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bawei.today.R;
import com.bawei.today.sqlite.MySqliteOpen;
import com.bawei.today.utils.HostListAdd;

import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/3/26 19:53
 */

public class GuanActivity extends SwipeBackActivity {

    private ListView lv;
    List<String> list=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.guan_activity);

        lv = (ListView) findViewById(R.id.guan_lv);

       list = HostListAdd.list;

        MyAdapter mm=new MyAdapter();
        lv.setAdapter(mm);

    }


    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(list!=null)
            return list.size();
            else
                return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vv;
            if(convertView==null){
                vv=new ViewHolder();
                convertView=View.inflate(GuanActivity.this,R.layout.guan_item,null);
                vv.tv= (TextView) convertView.findViewById(R.id.guan_item_tv);
                vv.rd= (RadioButton) convertView.findViewById(R.id.guan_item_rd);
                convertView.setTag(vv);
            }else{
              vv= (ViewHolder) convertView.getTag();
            }
            vv.tv.setText(list.get(position));
            vv.rd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     if(isChecked){
                         MySqliteOpen mm=new MySqliteOpen(GuanActivity.this);
                         SQLiteDatabase database = mm.getWritableDatabase();
                             database.execSQL("insert into dbguan(title)values(?)",new Object[]{list.get(position)});
                     }else{

                     }
                }
            });
           return convertView;
        }

        class ViewHolder{
            TextView tv;
            RadioButton rd;
        }
    }

}
