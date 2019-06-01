package com.entry.activitystudy.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.entry.activitystudy.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    private final String theme = "app_theme";

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("MyService-onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LogUtils.d("MyService-onStart");
        try {
            // 固定操作与 活动互动较少
            iniAppTheme(intent.getStringExtra(theme));
        }catch (Exception e){
            LogUtils.d("Theme import Lose!");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d("MyService-onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //绑定服务时调用这个方法，返回一个IBinder对象
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d("MyService-onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d("MyService-onUnbind");
        return super.onUnbind(intent);
    }

    //服务挂了
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("MyService-onDestroy");
    }

    private boolean iniAppTheme(String url){
        // ... do downloads
        return true;
    }

    // 自定义 Binder 利用其和活动进行沟通
    public class MyBinder extends Binder implements MyIBinder{

        @Override
        public void invokeMethodInMyService() {
            for(int i =0; i < 20; i ++){
                LogUtils.d("MyService-service is opening");
            }
        }

        public void stopService(ServiceConnection serviceConnection){
            unbindService(serviceConnection);
        }

        public Uri downLoadPic(String url){
            // ...进行下载操作
            Uri uri = Uri.parse(url);
            return uri ;
        }

        public boolean changeTheme(String type){
            // ...进行下载操作
            try {
                // do something ...
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
}
