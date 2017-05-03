package com.bawei.today.adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.today.R;
import com.bawei.today.bean.videocontent;
import com.bumptech.glide.Glide;

import org.xutils.x;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by admin on 2017/3/18.
 */

public class VideoAdapter extends BaseAdapter {
    private Context context;
    private List<videocontent> list;

    public VideoAdapter(Context context, List<videocontent> list) {
        this.context = context;
        this.list = list;
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
             convertView=View.inflate(context, R.layout.video_adapter,null);
             vv=new viewHolder();
            vv.jcao= (JCVideoPlayer) convertView.findViewById(R.id.jcao);
            vv.iv= (ImageView) convertView.findViewById(R.id.video_iv);
            vv.tv1= (TextView) convertView.findViewById(R.id.video_tv1);
            vv.tv2= (TextView) convertView.findViewById(R.id.video_tv2);
            convertView.setTag(vv);

        }else{
            vv= (viewHolder) convertView.getTag();
        }
        vv.jcao.setUp(list.get(position).getMp4_url(),list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getCover()).into(vv.jcao.ivThumb);
        Log.i("ddd",list.get(position).getMp4_url());
        vv.tv2.setText(list.get(position).getPtime());
        if(list.get(position).getVideoTopic()!=null){
            x.image().bind(vv.iv,list.get(position).getVideoTopic().getTopic_icons());
            vv.tv1.setText(list.get(position).getVideoTopic().getTname());
        }
        return convertView;
    }
    class viewHolder{
        JCVideoPlayer jcao;
        ImageView iv;
        TextView tv1,tv2;
    }
}
