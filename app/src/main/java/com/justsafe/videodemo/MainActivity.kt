package com.justsafe.videodemo

import android.content.Intent
import android.graphics.Camera
import android.widget.Toast
import com.justsafe.videodemo.manager.VideoRecordManager
import com.justsafe.app_test.R
import com.justsafe.app_test.databinding.ActivityMain2Binding
import com.justsafe.libview.base.BaseActivity
import com.justsafe.videodemo.widget.VideoRecord


class MainActivity : BaseActivity<ActivityMain2Binding>() {
    private var flagPlay:Boolean = false
    lateinit var videoRecord: VideoRecord
    override fun getLayout(): Int {
        return R.layout.activity_main_2
    }

    override fun init() {
        val videoRecordManager = VideoRecordManager(videoRecord)
        mdatabinding.bt.setOnClickListener {
            if(flagPlay){
                videoRecordManager.stop()
                mdatabinding.bt.text = "开始"
                Toast.makeText(this@MainActivity,"结束",Toast.LENGTH_SHORT).show()
            }else{
                videoRecordManager.start()
                mdatabinding.bt.text = "结束"
                Toast.makeText(this@MainActivity,"开始",Toast.LENGTH_SHORT).show()
            }
            flagPlay = !flagPlay
        }
        mdatabinding.btPlay.setOnClickListener {
            startActivity(Intent(this@MainActivity, PlayerRecordActivity::class.java))
        }
    }
}
