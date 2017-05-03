package com.bawei.today.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bawei.today.bean.Newscontent;

/**
 * Created by admin on 2017/3/18.
 */

public class Videoutils {
    public static final String VIDEO_TOP="http://c.3g.163.com/nc/video/list/";
    public static final String VIDEO_HUAXIAN="/n/";
    public static final String VIDEO_WEI="-10.html";
    public static String videoUrl(String id,int bianliang){
        String url=VIDEO_TOP+id+VIDEO_HUAXIAN+bianliang+VIDEO_WEI;
        return url;
    }

    public static boolean isNewWorkIsAvailable(Context context){

        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络数据
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();

        if(info!=null){
            return true;
        }
        return  false;

    }

    public static boolean isWifi(Context context){

        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
         //
        //获取网络信息
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if(info!=null&&info.getType()==connectivityManager.TYPE_WIFI){
            return true;
        }
        return  false;
    }

    //判断是否是流量
    public static boolean isMobile(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络信息
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&info.getType()==manager.TYPE_MOBILE){
            return true;
        }
        return  false;
    }
}
