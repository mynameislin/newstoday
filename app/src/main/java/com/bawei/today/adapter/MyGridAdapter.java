package com.bawei.today.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.today.R;

import java.util.List;

/**
 * Created by admin on 2017/3/12.
 */

public class MyGridAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public MyGridAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder vv;
        if(convertView==null){
            vv=new viewHolder();
            convertView=View.inflate(context, R.layout.title_grid,null);
            vv.bt= (TextView) convertView.findViewById(R.id.grid_bt);
            convertView.setTag(vv);
        }else{
           vv= (viewHolder) convertView.getTag();
        }
        vv.bt.setText(list.get(position));
        return convertView;
    }
    class viewHolder{
        TextView bt;
    }
}
