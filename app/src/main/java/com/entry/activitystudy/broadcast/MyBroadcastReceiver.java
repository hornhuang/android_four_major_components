package com.entry.activitystudy.broadcast;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.entry.activitystudy.utils.IntentManager;
import com.entry.activitystudy.utils.LogUtils;

/**广播，监听
 * 回掉 onReceive 更新 ProgressDiaLog
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private int progressStatus = 0;
    private final String EXTRA_ID = "status";

    private ProgressDialog progressDialog;

    MyBroadcastReceiver(){

    }

    public MyBroadcastReceiver(ProgressDialog progressDialog){
        this.progressDialog = progressDialog;
    }

    // 回调判断意图 更新进度条
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null){
            switch (intent.getAction()){
                case IntentManager.ACTION_TYPE_SERVICE:
                    progressDialog.setMessage("线程状态：" + intent.getStringExtra(IntentManager.INTENT_STATUS_SERVICE));
                    if (intent.getStringExtra(IntentManager.INTENT_STATUS_SERVICE).equals("服务已结束运行")){
                        progressDialog.dismiss();// 从源码看出调用后 在调用了 dismiss()
                    }
                    break;

                case IntentManager.ACTION_TYPE_THREAD:
                    progressStatus = intent.getIntExtra(IntentManager.INTENT_PROGRESS_SERVICE, 0);
                    progressDialog.setMessage("线程状态：" + intent.getStringExtra(IntentManager.INTENT_STATUS_SERVICE));
                    progressDialog.setProgress(progressStatus);
                    break;
            }
            progressStatus = intent.getIntExtra(EXTRA_ID, 0);
        }else {
            // ...
        }
    }
}
