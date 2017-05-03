package com.bawei.today.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.today.R;
import com.bawei.today.app.xutilsApp;
import com.bawei.today.bean.Dbguan;
import com.bawei.today.sqlite.MySqliteOpen;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by admin on 2017/3/15.
 */

public class WebViewActivity extends SwipeBackActivity{

    private ImageView ym;
    private TextView tv;
    private ProgressBar bar;
    private WebView wb;
    private String url;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);
        x.view().inject(this);
        //找控件
        InitView();

        tv.setText(title);
        ym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置webview();
        setWebView();


    }
    private void setWebView() {
        wb.loadUrl(url);
        wb.setBackgroundColor(0);
        wb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
               bar.setProgress(newProgress);
            }
        });
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                  bar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void InitView() {
       Intent intent= getIntent();
        url = intent.getStringExtra("url_3");
        title = intent.getStringExtra("title_3");
        ym = (ImageView) findViewById(R.id.title_iv);
        tv = (TextView) findViewById(R.id.title_tv);
        bar = (ProgressBar) findViewById(R.id.bar);
        wb = (WebView) findViewById(R.id.webview);
    }
}
