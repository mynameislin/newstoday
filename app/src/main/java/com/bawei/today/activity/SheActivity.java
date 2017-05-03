package com.bawei.today.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bawei.today.R;
import com.bawei.today.utils.Videoutils;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/3/21 14:28
 */

public class SheActivity  extends Activity implements View.OnClickListener {

    private ImageView iv;
    private RelativeLayout shizhi_daxiao;
    private RelativeLayout shizhi_fiewifi;
    private RelativeLayout shizhi_wifitixing;
    private RelativeLayout shizhi_genxin;
    private RelativeLayout shizhi_liebiao;
    private RelativeLayout shizhi_qingchu;
    private RelativeLayout shizhi_xiazai;
    private Button she_bt;
    private SharedPreferences sh;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi_activity);
        //找控件
        initView();
        //监听事件
        she_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 editor.clear();
                 editor.putString("iamge",null);
                 editor.putString("name",null);
                 editor.commit();
                finish();
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shizhi_genxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SheActivity.this);
                builder.setTitle("现在已经是最新版本！！！").create();
                builder.show();
            }
        });
      shizhi_xiazai.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              boolean newWorkIsAvailable = Videoutils.isNewWorkIsAvailable(SheActivity.this);
              if(!newWorkIsAvailable){
                  Toast.makeText(SheActivity.this, "网络未连接", Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                  startActivity(intent);
              }else{
                  Toast.makeText(SheActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();
                  downLoad();
              }


          }
      });
    }

    private void downLoad() {
        String [] items={"wifi","手机流量"};
        new AlertDialog.Builder(this).setTitle("网络选择").setIcon(R.mipmap.ic_launcher).setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        //wifi下 下载apk
                        Toast.makeText(SheActivity.this, "现在使用wifi下载，安全", Toast.LENGTH_SHORT).show();
                        downloadApk();
                        break;
                    case 1:
                        boolean mobile = Videoutils.isMobile(SheActivity.this);
                        if(mobile){
                            Toast.makeText(SheActivity.this, "现在未使用wifi,将耗用手机流量", Toast.LENGTH_SHORT).show();
                            Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                            startActivity(wifiSettingsIntent);
                        }
                        break;
                }
                dialog.dismiss();
            }
        }).show();
    }

    private void downloadApk() {
        Intent intent=new Intent(SheActivity.this,XiaZaiActivity.class);
        startActivity(intent);
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.shezhi_iv);
        shizhi_daxiao = (RelativeLayout) findViewById(R.id.shizhi_daxiao);
        shizhi_fiewifi = (RelativeLayout) findViewById(R.id.shizhi_fiewifi);
        shizhi_wifitixing = (RelativeLayout) findViewById(R.id.shizhi_wifitixing);
        shizhi_genxin = (RelativeLayout) findViewById(R.id.shizhi_genxin);
        shizhi_liebiao = (RelativeLayout) findViewById(R.id.shizhi_liebiao);
        shizhi_qingchu = (RelativeLayout) findViewById(R.id.shizhi_qingchu);
        shizhi_xiazai = (RelativeLayout) findViewById(R.id.shizhi_xiazai);
        she_bt = (Button) findViewById(R.id.she_bt);
        shizhi_daxiao.setOnClickListener(this);
        shizhi_fiewifi.setOnClickListener(this);
        shizhi_qingchu.setOnClickListener(this);
        shizhi_liebiao.setOnClickListener(this);
        shizhi_wifitixing.setOnClickListener(this);
        sh=getSharedPreferences("login",0);
        editor=sh.edit();
    }


    @Override
    public void onClick(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("功能暂未开发");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).create();
        builder.show();
    }
}
