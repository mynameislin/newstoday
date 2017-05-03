package com.bawei.today.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.today.R;
import com.bawei.today.activity.PhtotViewActivity;
import com.bawei.today.bean.Newscontent;
import com.bawei.today.sqlite.MySqliteOpen;

import org.xutils.x;

import java.util.List;

/**
 * Created by admin on 2017/3/14.
 */

public class HomeListAdapter extends BaseAdapter {
   private Context context;
    private List<Newscontent> list;


    public HomeListAdapter(Context context, List<Newscontent> list) {
        this.context = context;
        this.list = list;
    }

    private int TYPE_1=0;
    private int TYPE_2=1;
    private int TYPE_3=2;


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
    public int getItemViewType(int position) {
        Newscontent  item = (Newscontent) getItem(position);

        if(item.getImgextra()!=null&&item.getImgsrc()!=null&&item.getTitle()!=null)
          {
              return TYPE_3;
          }else if(item.getImgsrc()!=null&&item.getImgextra()==null){
              return TYPE_2;
          }else if(item.getImgsrc()==null&&item.getImgextra()==null){
              return TYPE_1;
          }
              return TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoler1 viewHoler1 = null;
        ViewHoler2 viewHoler2 = null;
        ViewHoler3 viewHoler3 = null;
        if (convertView == null) {
            if (getItemViewType(position) == TYPE_3) {
                viewHoler3 = new ViewHoler3();
                convertView = LayoutInflater.from(context).inflate(R.layout.item3, null);
                viewHoler3.textView = (TextView) convertView.findViewById(R.id.item3_text_title);
                viewHoler3.image_one = (ImageView) convertView.findViewById(R.id.item3_imagelist1);
                viewHoler3.image_two = (ImageView) convertView.findViewById(R.id.item3_imagelist2);
                viewHoler3.image_three = (ImageView) convertView.findViewById(R.id.item3_imagelist3);
                viewHoler3.t1 = (TextView) convertView.findViewById(R.id.item3_text_source);
                viewHoler3.t2 = (TextView) convertView.findViewById(R.id.item3_text_comment_count);
                viewHoler3.t3 = (TextView) convertView.findViewById(R.id.item3_text_behot_time);
                viewHoler3.t4 = (TextView) convertView.findViewById(R.id.item3_dian);
                convertView.setTag(viewHoler3);
            } else if (getItemViewType(position) == TYPE_2) {
                viewHoler2 = new ViewHoler2();
                convertView = LayoutInflater.from(context).inflate(R.layout.item2, null);
                viewHoler2.textview = (TextView) convertView.findViewById(R.id.item2_text_title);
                viewHoler2.iamge = (ImageView) convertView.findViewById(R.id.item2_middle_image);
                viewHoler2.t1 = (TextView) convertView.findViewById(R.id.item2_text_source);
                viewHoler2.t2 = (TextView) convertView.findViewById(R.id.item2_text_comment_count);
                viewHoler2.t3 = (TextView) convertView.findViewById(R.id.item2_text_behot_time);
                viewHoler2.t4 = (TextView) convertView.findViewById(R.id.item2_dian);
                convertView.setTag(viewHoler2);
            } else {
                viewHoler1 = new ViewHoler1();
                convertView = LayoutInflater.from(context).inflate(R.layout.item1, null);
                viewHoler1.textview = (TextView) convertView.findViewById(R.id.item1_text_title);
                viewHoler1.t1 = (TextView) convertView.findViewById(R.id.item1_text_source);
                viewHoler1.t2 = (TextView) convertView.findViewById(R.id.item1_text_comment_count);
                viewHoler1.t3 = (TextView) convertView.findViewById(R.id.item1_text_behot_time);

                convertView.setTag(viewHoler1);
            }

        } else {
            if (getItemViewType(position) == TYPE_3) {
                viewHoler3 = (ViewHoler3) convertView.getTag();
            } else if (getItemViewType(position) == TYPE_2) {
                viewHoler2 = (ViewHoler2) convertView.getTag();
            } else {
                viewHoler1 = (ViewHoler1) convertView.getTag();
            }

        }
        if (getItemViewType(position) == TYPE_3) {
                   viewHoler3.textView.setText(list.get(position).getTitle());
                   viewHoler3.t2.setText(list.get(position).getVotecount() + "条评论");
                   viewHoler3.t3.setText(list.get(position).getPtime());
            x.image().bind(viewHoler3.image_one,list.get(position).getImgextra().get(0).getImgsrc());
            x.image().bind(viewHoler3.image_two,list.get(position).getImgextra().get(1).getImgsrc());
            x.image().bind(viewHoler3.image_three,list.get(position).getImgsrc());
            viewHoler3.image_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PhtotViewActivity.class);
                    intent.putExtra("url",list.get(position).getImgextra().get(0).getImgsrc());
                    context.startActivity(intent);
                }
            });
            viewHoler3.image_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PhtotViewActivity.class);
                    intent.putExtra("url",list.get(position).getImgsrc());
                    context.startActivity(intent);
                }
            });
            viewHoler3.image_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PhtotViewActivity.class);
                    intent.putExtra("url",list.get(position).getImgextra().get(1).getImgsrc());
                    context.startActivity(intent);
                }
            });
            viewHoler3.t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("确定添加收藏？");
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MySqliteOpen mm=new MySqliteOpen(context);
                            SQLiteDatabase writableDatabase = mm.getWritableDatabase();
                            writableDatabase.execSQL("insert into dbshou(tilte,votecount,ptime,imgsrc,imgsrc1,imgsrc2)values(?,?,?,?,?,?)"
                            ,new Object[]{list.get(position).getTitle(),list.get(position).getVotecount() + "条评论",list.get(position).getPtime()
                                    ,list.get(position).getImgextra().get(0).getImgsrc(),list.get(position).getImgextra().get(1).getImgsrc(),list.get(position).getImgsrc()});
                            Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                        }
                    }).create();
                    builder.show();
                }
            });
        } else if (getItemViewType(position) == TYPE_2) {
            viewHoler2.textview.setText(list.get(position).getTitle());
            viewHoler2.t2.setText(list.get(position).getVotecount() + "条评论");
            viewHoler2.t3.setText(list.get(position).getPtime());
            x.image().bind(viewHoler2.iamge,list.get(position).getImgsrc());
            viewHoler2.iamge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PhtotViewActivity.class);
                    intent.putExtra("url",list.get(position).getImgsrc());
                    context.startActivity(intent);
                }
            });
            viewHoler2.t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("确定添加收藏？");
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MySqliteOpen mm=new MySqliteOpen(context);
                            SQLiteDatabase writableDatabase = mm.getWritableDatabase();
                            writableDatabase.execSQL("insert into dbshou(tilte,votecount,ptime,imgsrc,imgsrc1,imgsrc2)values(?,?,?,?,?,?)"
                                    ,new Object[]{list.get(position).getTitle(),list.get(position).getVotecount() + "条评论",list.get(position).getPtime()
                                            ,list.get(position).getImgsrc(),null,null});
                            Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                        }
                    }).create();
                    builder.show();
                }
            });
        } else {
            viewHoler1.textview.setText(list.get(position).getTitle());
            viewHoler1.t2.setText(list.get(position).getVotecount() + "条评论");
            viewHoler1.t3.setText(list.get(position).getPtime());
        }

        return convertView;
    }
    class ViewHoler1 {
        TextView textview, t1, t2, t3;
    }

    class ViewHoler2 {
        TextView textview, t1, t2, t3,t4;
        ImageView iamge;
    }

    class ViewHoler3 {
        TextView textView, t1, t2, t3,t4;
        ImageView image_one, image_two, image_three;

    }

}
