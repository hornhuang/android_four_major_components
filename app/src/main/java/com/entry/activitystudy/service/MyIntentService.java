package com.entry.activitystudy.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.entry.activitystudy.utils.IntentManager;

public class MyIntentService extends IntentService {

    private int progressCount = 0;
    private boolean isRunning = false;
    private LocalBroadcastManager manager;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = LocalBroadcastManager.getInstance(this);
        sendIfoToBroadCast("服务启动");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(1000);// 模拟初始化耗时
            sendIfoToBroadCast("服务正在运行中");
            int progressCount = 0;
            boolean isRunning = true;

            while (isRunning){
                progressCount ++;
                if (progressCount > 100){
                    isRunning = false;
                }else {
                    Thread.sleep(50);
                    sendIfoToBroadCast("下载进度：", progressCount);
                }
            }
            sendIfoToBroadCast("下载完成");
            Thread.sleep(1000);// 模拟初始化耗时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        sendIfoToBroadCast("服务已结束运行");
        super.onDestroy();
    }

    private void sendIfoToBroadCast(String serviceStatus){
        Intent intent1 = new Intent(IntentManager.ACTION_TYPE_SERVICE);
        intent1.putExtra(IntentManager.INTENT_STATUS_SERVICE, serviceStatus);
        manager.sendBroadcast(intent1);
    }

    private void sendIfoToBroadCast(String serviceStatus, int progressStatus){
        Intent intent = new Intent(IntentManager.ACTION_TYPE_THREAD);
        intent.putExtra(IntentManager.INTENT_STATUS_SERVICE, serviceStatus);
        intent.putExtra(IntentManager.INTENT_PROGRESS_SERVICE, progressStatus);
        manager.sendBroadcast(intent);
    }
}
