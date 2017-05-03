package com.bawei.today.app;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by admin on 2017/3/14.
 */

public class xutilsApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);


        UMShareAPI.get(this);
    }
    {
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }

   /* public static DbManager getDb1(){
        DbManager.DaoConfig config=new DbManager.DaoConfig().setDbName("news").setDbDir(new File("/mnt/sdcard")).setDbVersion(1);
        DbManager db=x.getDb(config);
        return db;
    }
    public static DbManager getDb2(){
        DbManager.DaoConfig config=new DbManager.DaoConfig().setDbName("newsshou").setDbDir(new File("/mnt/sdcard")).setDbVersion(1);
        DbManager db=x.getDb(config);
        return db;
    }*/
}
