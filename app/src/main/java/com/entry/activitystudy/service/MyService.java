package com.entry.activitystudy.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.entry.activitystudy.utils.LogUtils;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("MyService-onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        LogUtils.d("MyService-onStart");
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

    public interface MyIBinder{
        void invokeMethodInMyService();
    }

    public class MyBinder extends Binder implements MyIBinder{

        public void stopService(ServiceConnection serviceConnection){
            unbindService(serviceConnection);
        }

        @Override
        public void invokeMethodInMyService() {
            for(int i =0; i < 20; i ++){
                LogUtils.d("MyService-service is opening");
            }
        }
    }
}
