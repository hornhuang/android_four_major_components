package com.entry.activitystudy.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.entry.activitystudy.R;
import com.entry.activitystudy.utils.LogUtils;
import com.entry.activitystudy.utils.ToastUtils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long firstClicked = 0;
    private final int ANIMATE_TIME = 750;

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
//    private CollapsingToolbarLayout toolbarLayout;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            floatingActionButton.animate().rotation(180).setDuration(ANIMATE_TIME);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();
        iniToolBar(toolbar, getResources().getString(R.string.main_toolbar_title));
    }

    private void iniViews(){
        toolbar = findViewById(R.id.toolbar);
//        toolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        floatingActionButton = findViewById(R.id.to_dialog_activity);

        toolbar.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
                        DialogActivity.anctionStart(MainActivity.this);
                    }
                }.start();
                break;

            case R.id.toolbar:
                LogUtils.d("click toolbar");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        floatingActionButton.animate().rotation(0).setDuration(ANIMATE_TIME);
    }

    // 2
@Override
public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK){
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstClicked > 2000) {
            LogUtils.d("up");
            ToastUtils.makeText(MainActivity.this, "在按一次退出");
            firstClicked = secondTime;
            return true;
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);// 退到后台而不结束
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
        return true;
    }else {
        return super.onKeyUp(keyCode, event);
    }
}

//    // 双击退出  1
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        LogUtils.d("down");
//        return true;
////        if (keyCode == KeyEvent.KEYCODE_BACK){
//////            moveTaskToBack(true);
//////            return true;
////        }
////        return super.onKeyDown(keyCode, event);
//    }
//
//    // 3
//    // 双击退回桌面
//    @Override
//    public void onBackPressed() {
////        long secondTime = System.currentTimeMillis();
////        if (secondTime - firstClicked > 2000) {
////            LogUtils.d("click" + secondTime + "---" + firstClicked);
////            ToastUtils.makeText((AppCompatActivity) getApplicationContext(), "在按一次退出");
////            firstClicked = secondTime;
////        } else {
////            Intent intent = new Intent(Intent.ACTION_MAIN);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            intent.addCategory(Intent.CATEGORY_HOME);
////            startActivity(intent);
////        }
////            moveTaskToBack(true);
//        finish();
//    }

    public static void anctionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}
