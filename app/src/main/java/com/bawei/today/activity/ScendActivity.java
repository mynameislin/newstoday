package com.bawei.today.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.today.R;
import com.bawei.today.fragment.Host_Fragment;
import com.bawei.today.fragment.Host_Fragment2;
import com.bawei.today.fragment.Host_Fragment3;
import com.bawei.today.fragment.MenuRight_Fragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import org.w3c.dom.Text;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by admin on 2017/3/10.
 */

public class ScendActivity extends FragmentActivity implements View.OnClickListener{
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] displaylist = new SHARE_MEDIA[] {SHARE_MEDIA.WEIXIN,
            SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ,
            SHARE_MEDIA.QZONE, SHARE_MEDIA.DOUBAN};
    private SharedPreferences sh;
    private SharedPreferences.Editor editor;
    private ImageView hide_iv;
    private FrameLayout hide_fly;
    private EditText ed;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private Host_Fragment f1;
    private Host_Fragment2 f2;
    private Host_Fragment3 f3;
    private int theme=R.style.AppTheme;
    private ImageView shouji;
    private ImageView weixindenglu;
    private ImageView qqdenglu;
    private ImageView denglu;
    private ImageView shouChang;
    private ImageView liShi;
    private ImageView yeJian;
    private TextView tv;
   private List<LinearLayout> list=new ArrayList<>();
    private TextView qq_name;
    private ImageView iv;
    private TextView shizhi;
    private TextView baoliao;
    private TextView shangcheng;
    private TextView xiaoxi;
    private TextView tehong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            theme=savedInstanceState.getInt("theme");
            setTheme(theme);
        }
       setContentView(R.layout.activity_scend);
        x.view().inject(this);
        SMSSDK.initSDK(this,"1c2de40a55c5c","5c55ee6df15e9303dd6ae3f5b3931f9b");
          //找控件
        initView();
        //为布局添加fragment,开启事物
          initFragment();
        //颜色切换
        initColor(R.id.ll1);
        //左侧滑
        menuData();
        initPlatforms();

        }

    @Override
    protected void onResume() {
        super.onResume();
        String image = sh.getString("iamge", null);
        if(image!=null){
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(50,50).setFadeIn(true).build();
            x.image().bind(iv,image,options);
            qq_name.setText(sh.getString("name",null));
            qqdenglu.setVisibility(View.GONE);
            weixindenglu.setVisibility(View.GONE);
            shouji.setVisibility(View.GONE);
            denglu.setVisibility(View.GONE);
            qq_name.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
            x.image().bind(hide_iv,image,options);
        }else{
            qqdenglu.setVisibility(View.VISIBLE);
            weixindenglu.setVisibility(View.VISIBLE);
            shouji.setVisibility(View.VISIBLE);
            denglu.setVisibility(View.VISIBLE);
            qq_name.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
            hide_iv.setImageResource(R.mipmap.default_user_leftdrawer);
        }
    }

    private void initPlatforms() {
        for (SHARE_MEDIA e : displaylist) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    private void menuData() {
        //创建侧滑对象
        final SlidingMenu sliding=new SlidingMenu(this);
        //设置侧滑的方向
        sliding.setMode(SlidingMenu.LEFT);
        //设置整个屏幕是否都能滑出,职能从左侧滑出
        //sliding.setTouchModeAbove(SlidingMenu.LEFT);
        //设置滑出的宽度
        sliding.setBehindOffset(100);
        //让侧滑依附在Activity上
        sliding.attachToActivity(ScendActivity.this,SlidingMenu.SLIDING_CONTENT);
        //设置侧滑的布局
        sliding.setMenu(R.layout.menu);
         menuInitView();
        if(theme==R.style.AppTheme){
            tv.setText("白天");
            shouji.setImageResource(R.mipmap.cellphoneicon_login_profile);
            weixindenglu.setImageResource(R.mipmap.weixin_allshare_normal);
            qqdenglu.setImageResource(R.mipmap.qq_allshare_normal);
            shouChang.setImageResource(R.mipmap.b_film_star_edge);
            liShi.setImageResource(R.mipmap.b_newtoutiao_tabbar);
            yeJian.setImageResource(R.mipmap.dayicon_profile_press);
        }else{
            shouji.setImageResource(R.mipmap.webo);
            weixindenglu.setImageResource(R.mipmap.wixinicon_login_profile);
            qqdenglu.setImageResource(R.mipmap.qqicon_login_profile);
            shouChang.setImageResource(R.mipmap.b_film_star_edge_night);
            liShi.setImageResource(R.mipmap.b_newtoutiao_tabbar_night);
            yeJian.setImageResource(R.mipmap.nighticon_profile);
            tv.setText("黑夜");
        }

        hide_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliding.toggle();
            }
        });
    }

    private void menuInitView() {
        shouji = (ImageView) findViewById(R.id.shouji);
        weixindenglu = (ImageView) findViewById(R.id.weixindenglu);
        qqdenglu = (ImageView) findViewById(R.id.qqdenglu);
        denglu = (ImageView) findViewById(R.id.denglu);
        shouChang = (ImageView) findViewById(R.id.shouChang);
        liShi = (ImageView) findViewById(R.id.liShi);
        yeJian = (ImageView) findViewById(R.id.yeJian);
        tv = (TextView) findViewById(R.id.tv_yejian);
        iv = (ImageView) findViewById(R.id.qq_touxiang);
        qq_name = (TextView) findViewById(R.id.qq_name);
        shizhi = (TextView) findViewById(R.id.menu_shezhi);
        baoliao = (TextView) findViewById(R.id.menu_baoliao);
        shangcheng = (TextView) findViewById(R.id.menu_shangcheng);
        xiaoxi = (TextView) findViewById(R.id.menu_xiaoxi);
        tehong = (TextView) findViewById(R.id.menu_tehong);

        yeJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme=(theme==R.style.AppTheme)?R.style.NightAppTheme:R.style.AppTheme;
                recreate();
            }
        });
        shouChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScendActivity.this,ShouActivity.class);
                startActivity(intent);
            }
        });
      qqdenglu.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              UMShareAPI mShareAPI=UMShareAPI.get(ScendActivity.this);
              //   mShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
              mShareAPI.getPlatformInfo(ScendActivity.this, SHARE_MEDIA.QQ, umAuthListener);
          }
      });
       shouji.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final RegisterPage registerpage=new RegisterPage();
               registerpage.setRegisterCallback(new EventHandler(){
                   @Override
                   public void afterEvent(int event, int result, Object data) {
                       super.afterEvent(event, result, data);
                       if(result==SMSSDK.RESULT_COMPLETE){
                           HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                           String country = (String) phoneMap.get("country");
                           String phone = (String) phoneMap.get("phone");
                       }
                   }
               });
               registerpage.show(ScendActivity.this);
           }
       });

        shizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScendActivity.this,SheActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFragment() {
        f1 = new Host_Fragment();
        f2 = new Host_Fragment2();
        f3 = new Host_Fragment3();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.hide_fly,f1,"f1").show(f1).add(R.id.hide_fly,f2,"f2").hide(f2).add(R.id.hide_fly,f3,"f3").hide(f3);
        fragmentTransaction.commit();
    }

    private void initView() {
        hide_iv = (ImageView) findViewById(R.id.hide_iv);
        hide_fly = (FrameLayout) findViewById(R.id.hide_fly);
        ed = (EditText) findViewById(R.id.hide_edittext);
        iv1 = (ImageView) findViewById(R.id.botton_iv1);
        iv2 = (ImageView) findViewById(R.id.botton_iv2);
        iv3 = (ImageView) findViewById(R.id.botton_iv3);
        tv1 = (TextView) findViewById(R.id.botton_tv1);
        tv2 = (TextView) findViewById(R.id.botton_tv2);
        tv3 = (TextView) findViewById(R.id.botton_tv3);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        list.add(ll1);
        list.add(ll2);
        list.add(ll3);
        sh=getSharedPreferences("login",0);
        editor=sh.edit();
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.ll1:
                 getSupportFragmentManager().beginTransaction().show(f1).hide(f2).hide(f3).commit();
                 break;
             case R.id.ll2:
                 getSupportFragmentManager().beginTransaction().show(f2).hide(f1).hide(f3).commit();
                 break;
             case R.id.ll3:
                 getSupportFragmentManager().beginTransaction().show(f3).hide(f1).hide(f2).commit();
                 break;
         }
        initColor(v.getId());
    }
    private void initColor(int id) {

            if(id==R.id.ll1){
                    iv1.setBackgroundResource(R.mipmap.vw);
                    iv2.setBackgroundResource(R.mipmap.w3);
                    iv3.setBackgroundResource(R.mipmap.vt);
                    tv1.setTextColor(Color.RED);
                    tv2.setTextColor(Color.BLACK);
                    tv3.setTextColor(Color.BLACK);
            }else if(id==R.id.ll2){
                iv1.setBackgroundResource(R.mipmap.vv);
                iv2.setBackgroundResource(R.mipmap.w4);
                iv3.setBackgroundResource(R.mipmap.vt);
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.RED);
                tv3.setTextColor(Color.BLACK);
            }else if(id==R.id.ll3){
                iv1.setBackgroundResource(R.mipmap.vv);
                iv2.setBackgroundResource(R.mipmap.w3);
                iv3.setBackgroundResource(R.mipmap.vu);
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                tv3.setTextColor(Color.RED);
            }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
        outState.putInt("theme",theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme=savedInstanceState.getInt("theme");
    }

    //登录的监听
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
           // Toast.makeText(getApplicationContext(), "Authorize succeed"+data.toString(), Toast.LENGTH_SHORT).show();

            String iconurl = data.get("iconurl");
            Log.i("xxx","s"+iconurl);
            String name = data.get("name");
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setCrop(true).setSize(50,50).setFadeIn(true).build();
            x.image().bind(iv,iconurl,options);
            qq_name.setText(name);
            x.image().bind(hide_iv,iconurl,options);
            editor.putString("iamge",iconurl);
            editor.putString("name",name);
            editor.commit();
            qqdenglu.setVisibility(View.GONE);
            weixindenglu.setVisibility(View.GONE);
            shouji.setVisibility(View.GONE);
            denglu.setVisibility(View.GONE);
            qq_name.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            //Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
