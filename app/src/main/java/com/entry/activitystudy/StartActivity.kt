package com.entry.activitystudy

import android.os.Bundle
import android.view.View
import com.entry.activitystudy.activities.BaseActivity
import com.entry.activitystudy.activities.MainActivity
import com.entry.activitystudy.service.ServiceActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        study_activity.setOnClickListener(this)
        study_service.setOnClickListener(this)
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
                else -> {
                    // ...
                }
            }
        }
    }

}
