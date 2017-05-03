package com.bawei.today.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.today.R;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
       if(msg.what==1){
           Intent intent=new Intent(MainActivity.this,ScendActivity.class);
           startActivity(intent);
       }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
        //创建子线程
        setThreadData();
    }

    private void setThreadData() {
         new Thread(){
             @Override
             public void run() {
                 super.run();
                 handler.sendEmptyMessageDelayed(1,3000);
             }
         }.start();
    }

}
