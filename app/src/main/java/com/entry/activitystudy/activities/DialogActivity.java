package com.entry.activitystudy.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.entry.activitystudy.R;
import com.entry.activitystudy.utils.ToastUtils;

public class DialogActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为true点击区域外消失
        setFinishOnTouchOutside(true);
        setContentView(R.layout.activity_dialog);
        // 显示位置
        getWindow().setGravity(Gravity.CENTER);

        iniViews();
        iniToolBar(toolbar ,getResources().getString(R.string.dialog_tool_bar));
    }


    private void iniViews(){
        toolbar = findViewById(R.id.toolbar);
    }

    public static void anctionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, DialogActivity.class);
        activity.startActivity(intent);
    }

}
