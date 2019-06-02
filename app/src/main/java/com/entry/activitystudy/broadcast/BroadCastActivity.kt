package com.entry.activitystudy.broadcast

import android.app.ProgressDialog
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.entry.activitystudy.R
import com.entry.activitystudy.service.MyIntentService
import com.entry.activitystudy.utils.IntentManager
import kotlinx.android.synthetic.main.activity_broad_cast.*

class BroadCastActivity : AppCompatActivity() {

    private val MAX_PROGRESS = 100

    private var progressDialog: ProgressDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_cast)

        broad_cast.setOnClickListener {
            showDialog()
            iniBroadCast()
        }
    }

    private fun iniBroadCast() {
        val manager = LocalBroadcastManager.getInstance(this)
        val broadcastReceiver = MyBroadcastReceiver(progressDialog)// 在这之前 progressDialog 要先被实例化出来
        val intentFilter = IntentFilter()
        intentFilter.addAction(IntentManager.ACTION_TYPE_SERVICE)
        intentFilter.addAction(IntentManager.ACTION_TYPE_THREAD)
        manager.registerReceiver(broadcastReceiver, intentFilter)
        val intent = Intent(this, MyIntentService::class.java)
        startService(intent)
    }

    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.max = MAX_PROGRESS

        progressDialog!!.setTitle("Service 模拟下载中")
        progressDialog!!.setMessage("模拟下载由 IntentService 进行，目前完成：")

        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog!!.isIndeterminate = false
        progressDialog!!.show()
    }

    companion object {

        fun actionStart(activity: AppCompatActivity){
            val intent = Intent(activity, BroadCastActivity::class.java)
            activity.startActivity(intent)
        }

    }

}
