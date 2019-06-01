package com.entry.activitystudy.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.transition.Transition;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.entry.activitystudy.R;
import com.entry.activitystudy.fragments.TextFragment;
import com.entry.activitystudy.service.MyService;
import com.entry.activitystudy.utils.LogUtils;
import com.entry.activitystudy.utils.ToastUtils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long firstClicked = 0;
    private final int ANIMATE_TIME = 750;
    private String a = "";
    private MyService.MyBinder myBinder;
    private ServiceConnection serviceConnection;

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
//    private CollapsingToolbarLayout toolbarLayout;
    private EditText editText;
    private Button bind, unbind;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            floatingActionButton.animate().rotation(180).setDuration(ANIMATE_TIME);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.d("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        editText.setText(a);
        iniToolBar(toolbar, getResources().getString(R.string.main_toolbar_title));
        replaceFragment();
    }

    private void iniViews(){
        toolbar = findViewById(R.id.toolbar);
//        toolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        floatingActionButton = findViewById(R.id.to_dialog_activity);
        editText = findViewById(R.id.hello_edit);
        bind = findViewById(R.id.service_bind);
        unbind = findViewById(R.id.service_unbind);

        toolbar.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
    }

    private void replaceFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transition = manager.beginTransaction();
        transition.replace(R.id.fragment, new TextFragment()).commit();
    }

    @Override
    protected void onStart() {
        LogUtils.d("onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LogUtils.d("onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        LogUtils.d("onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        LogUtils.d("onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LogUtils.d("onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        LogUtils.d("onRestart");
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_dialog_activity:
                new Thread(){
                    @Override
                    public void run() {
                        handler.sendMessage(new Message());
                        try {
                            sleep(ANIMATE_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        MainActivity.anctionStart(MainActivity.this);
//                        DialogActivity.anctionStart(MainActivity.this);
                        a = editText.getText().toString();
                    }
                }.start();
                break;

            case R.id.toolbar:
                // ...
                break;

            case R.id.service_bind:
//                startService(new Intent(this, MyService.class));
                serviceConnection = new MyServiceConnection();
                bindService(new Intent(MainActivity.this, MyService.class), serviceConnection,
                        getApplicationContext().BIND_AUTO_CREATE);
                break;

            case R.id.service_unbind:
//                stopService(new Intent(this, MyService.class));
                unbindService(serviceConnection);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        floatingActionButton.animate().rotation(0).setDuration(ANIMATE_TIME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LogUtils.d("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    class MyServiceConnection implements ServiceConnection {

        //这里的第二个参数IBinder就是Service中的onBind方法返回的
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.d("onServiceConnected");
            myBinder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.d("onServiceDisconnected");
        }
    }

    public static void anctionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}
