package com.entry.activitystudy.activities

import android.os.Bundle
import android.view.View
import com.entry.activitystudy.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        start_activity_main.setOnClickListener {
            MainActivity.anctionStart(this)
            finish()
        }

    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
