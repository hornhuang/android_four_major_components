package com.entry.activitystudy.service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.entry.activitystudy.R;
import com.entry.activitystudy.broadcast.MyBroadcastReceiver;
import com.entry.activitystudy.utils.IntentManager;
import com.entry.activitystudy.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private final int MAX_PROGRESS = 100;
    private MyConnection connection;

    private SimpleDraweeView imageView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        iniViews();
    }

    private void iniViews(){
        imageView = findViewById(R.id.image);
        Button buttonBind = findViewById(R.id.service_bind);
        Button buttonUnBind = findViewById(R.id.service_unbind);
        Button buttonGetPic = findViewById(R.id.get_pic);
        Button buttonIntent = findViewById(R.id.intent_service);

        imageView.setOnClickListener(this);
        buttonBind.setOnClickListener(this);
        buttonUnBind.setOnClickListener(this);
        buttonGetPic.setOnClickListener(this);
        buttonIntent.setOnClickListener(this);
    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, ServiceActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_bind:
                if (connection == null){
                    connection = new MyConnection();
                    bindService(new Intent(ServiceActivity.this, MyService.class),
                            connection, BIND_AUTO_CREATE);
                    ToastUtils.makeText(ServiceActivity.this, "service_bind");
                }
                break;

            case R.id.service_unbind:
                if (connection != null){
                    unbindService(connection);
                    connection = null;
                    ToastUtils.makeText(ServiceActivity.this, "service_unbind");
                }
                break;

            case R.id.get_pic:
                if (connection != null && connection.myBinder != null){
                    String url ="res://com.example.a30797.androidclock/" + R.drawable.download;
                    imageView.setImageURI(connection.myBinder.downLoadPic(url));
                    ToastUtils.makeText(ServiceActivity.this, "get_pic");
                }
                break;

            case R.id.intent_service:
                showDialog();
                iniBroadCast();
                break;
        }
    }

    private void iniBroadCast(){
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(ServiceActivity.this);
        MyBroadcastReceiver broadcastReceiver = new MyBroadcastReceiver(progressDialog);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(IntentManager.ACTION_TYPE_SERVICE);
        manager.registerReceiver(broadcastReceiver, intentFilter);
        Intent intent = new Intent(ServiceActivity.this, MyIntentService.class);
        startService(intent);
    }

    private void showDialog(){
        progressDialog = new ProgressDialog(ServiceActivity.this);
        progressDialog.setMax(MAX_PROGRESS);

        progressDialog.setTitle("Service 模拟下载中");
        progressDialog.setMessage("模拟下载由 IntentService 进行，目前完成：");

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    private class MyConnection implements ServiceConnection {

        public MyService.MyBinder myBinder;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // myBinder = null;
        }

        @Override
        public void onBindingDied(ComponentName name) {
            // myBinder = null;
        }
    }
}
