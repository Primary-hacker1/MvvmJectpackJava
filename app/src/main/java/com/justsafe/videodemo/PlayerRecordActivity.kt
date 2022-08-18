package com.justsafe.videodemo

import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import com.justsafe.videodemo.util.FileUtil

abstract class PlayerRecordActivity : AppCompatActivity(), SurfaceHolder.Callback {
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        player.release()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        //将播放器和SurfaceView关联起来
        player.setDisplay(holder)

        //异步缓冲当前视频文件，也有一个同步接口
        player.prepareAsync()
    }
    private val player = MediaPlayer()
    private var surfaceView:SurfaceView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        surfaceView = SurfaceView(this)
        setContentView(surfaceView)
        surfaceView!!.holder.addCallback(this@PlayerRecordActivity)
        player.reset()
        try {
            player.setDataSource(FileUtil.getVideoSavePath())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //只有当播放器准备好了之后才能够播放，所以播放的出发只能在触发了prepare之后
        player.setOnPreparedListener { player.start() }
    }
}