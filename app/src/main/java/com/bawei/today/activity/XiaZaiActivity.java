package com.bawei.today.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.today.R;
import com.bawei.today.bean.XiaZai;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/3/23 10:41
 */

public class XiaZaiActivity extends SwipeBackActivity {
    private String url="http://mapp.qzone.qq.com/cgi-bin/mapp/mapp_subcatelist_qq?yyb_cateid=-10&categoryName=%E8%85%BE%E8%AE%AF%E8%BD%AF%E4%BB%B6&pageNo=1&pageSize=20&type=app&platform=touch&network_type=unknown&resolution=412x732";
    private TextView tv;
    private ListView lv;
    private ImageView iv;
    private List<XiaZai> app=new ArrayList<>();
    private ProgressBar pro;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.xiazai_activity);
           //找控件
        initView();
         //解析数据
        getData();
        progressDialog = new ProgressDialog(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = app.get(position).getUrl();
                RequestParams params=new RequestParams(url);
                params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/lin/");
                params.setAutoRename(true);
                Log.i("xxx",""+params);
                x.http().post(params, new Callback.ProgressCallback<File>() {
                    @Override
                    public void onWaiting() {

                    }

                    @Override
                    public void onStarted() {

                    }

                    @Override
                    public void onLoading(final long total, final long current, boolean isDownloading) {

                       progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setMessage("亲，努力下载中。。。");
                        progressDialog.show();;
                        progressDialog.setMax((int) total);
                        progressDialog.setProgress((int) current);

                    }
                    @Override
                    public void onSuccess(File result) {
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
                        startActivity(intent);
                        progressDialog.dismiss();
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
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.xiazai);
        lv = (ListView) findViewById(R.id.xia_listview);
        iv = (ImageView) findViewById(R.id.xiazia_iv);
        pro = (ProgressBar) findViewById(R.id.pro);
    }

    public void getData() {
        RequestParams params=new RequestParams(url);
        params.setCacheMaxAge(1000*2*60);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray jsonArray = jsonObject.optJSONArray("app");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                        String string = jsonObject1.optString("name");
                         XiaZai xx=new XiaZai();
                        xx.setName(string);
                        String url = jsonObject1.optString("url");
                        xx.setUrl(url);
                        app.add(xx);
                        Log.i("xxxx","aaaaaa"+app.size());
                    }
                    MyAdapter mm=new MyAdapter();
                    lv.setAdapter(mm);
                } catch (Exception e) {
                    e.printStackTrace();
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

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return app.size();
        }

        @Override
        public Object getItem(int position) {
            return app.get(position);
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
                convertView=View.inflate(XiaZaiActivity.this,R.layout.xiazai_item,null);
                vv.tv= (TextView) convertView.findViewById(R.id.xia_item_tv);
                 convertView.setTag(vv);
            }else{
                vv= (viewHolder) convertView.getTag();
            }
            vv.tv.setText(app.get(position).getName());


            return convertView;
        }
        class viewHolder{
             TextView tv;
        }
    }
}
