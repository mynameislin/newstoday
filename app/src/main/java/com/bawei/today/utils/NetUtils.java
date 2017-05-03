package com.bawei.today.utils;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2017/3/14.
 */

public class NetUtils {
    public  static <T> void loadNetData(String url, final Class<T> clazz, final CallbackData callbackData, final Context context, final View view){
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<T> list = new ArrayList<T>();
                try {
                    JSONObject obj = new JSONObject(result);
                    Iterator<String> keys = obj.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONArray array = obj.optJSONArray(next);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.optJSONObject(i);
                            T newsContent = gson.fromJson(object.toString(), clazz);
                            list.add(newsContent);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                callbackData.success(list);
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
    public interface CallbackData<T> {
        void success(List<T> newsContents);
    }
}
