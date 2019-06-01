package com.entry.activitystudy.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.entry.activitystudy.R;
import com.entry.activitystudy.utils.ToastUtils;

public class BaseActivity extends AppCompatActivity {

    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        iniToolBar();
    }

    protected void iniToolBar(Toolbar toolbar, String barTitle){
//        toolbarLayout.setTitleEnabled(true);
//        toolbarLayout.setTitle(getResources().getString(R.string.main_toolbar_title));

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(true);
            // 设置 ToolBar 返回事件
            // 注意先后顺序 先 setDisplayHomeAsUpEnabled
            // 后 setNavigationOnClickListener
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolBarNavClick(toolbar);
            actionBar.setTitle(barTitle);
        }
    }

    protected void iniToolBar(Toolbar toolbar, int barTitleId){
//        toolbarLayout.setTitleEnabled(true);
//        toolbarLayout.setTitle(getResources().getString(R.string.main_toolbar_title));
        try {
            String barTitle = getResources().getString(barTitleId);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null){
                ActionBar actionBar = getSupportActionBar();
                actionBar.setDisplayShowTitleEnabled(true);
                // 设置 ToolBar 返回事件
                // 注意先后顺序 先 setDisplayHomeAsUpEnabled
                // 后 setNavigationOnClickListener
                actionBar.setDisplayHomeAsUpEnabled(true);
                toolBarNavClick(toolbar);
                actionBar.setTitle(barTitle);

            }
        }catch (Exception e){

        }

    }

    protected void toolBarNavClick(androidx.appcompat.widget.Toolbar toolbar){
        if (toolbar != null){
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}
