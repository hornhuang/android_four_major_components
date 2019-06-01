package com.entry.activitystudy.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.entry.activitystudy.R;
import com.entry.activitystudy.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private MyConnection connection;

    private SimpleDraweeView imageView;

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

        imageView.setOnClickListener(this);
        buttonBind.setOnClickListener(this);
        buttonUnBind.setOnClickListener(this);
        buttonGetPic.setOnClickListener(this);
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
        }
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
