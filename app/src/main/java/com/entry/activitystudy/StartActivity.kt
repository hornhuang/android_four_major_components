package com.entry.activitystudy

import android.os.Bundle
import android.view.View
import com.entry.activitystudy.activities.BaseActivity
import com.entry.activitystudy.activities.MainActivity
import com.entry.activitystudy.broadcast.BroadCastActivity
import com.entry.activitystudy.contentprovider.ContentProviderActivity
import com.entry.activitystudy.service.ServiceActivity
import com.entry.activitystudy.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        study_activity.setOnClickListener(this)
        study_service.setOnClickListener(this)
        study_broadcast.setOnClickListener(this)
        study_content_provider.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when( v.id ){
                R.id.study_activity -> {
                    MainActivity.anctionStart(this)
                    finish()
                }
                R.id.study_service -> {
                    ServiceActivity.actionStart(this)
                    finish()
                }
                R.id.study_broadcast -> {
                    BroadCastActivity.actionStart(this)
                    finish()
                }
                R.id.study_content_provider -> {
                    ContentProviderActivity.actionStart(this)
                    finish()
                }
                else -> {
                    ToastUtils.makeText(this, "请选择功能")
                }
            }
        }
    }

}
