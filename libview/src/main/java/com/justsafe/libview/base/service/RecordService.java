package com.justsafe.libview.base.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.HandlerThread;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

import com.justsafe.libarch.utils.FileUtils;
import com.justsafe.libarch.utils.LogUtils;

import java.io.File;
import java.io.IOException;


public class RecordService extends Service {


    private MediaProjection mediaProjection;
    private MediaRecorder mediaRecorder;
    private VirtualDisplay virtualDisplay;

    private boolean running;
    private int width = 720;
    private int height = 1080;
    private int dpi;
    private String mSavePath = "";


    @Override
    public IBinder onBind(Intent intent) {
        return new RecordBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread serviceThread = new HandlerThread("service_thread", android.os.Process.THREAD_PRIORITY_BACKGROUND);
        serviceThread.start();
        running = false;
        mediaRecorder = new MediaRecorder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setMediaProject(MediaProjection project) {
        mediaProjection = project;
    }

    public boolean isRunning() {
        return running;
    }

    public void setConfig(int width, int height, int dpi) {
        this.width = width;
        this.height = height;
        this.dpi = dpi;
    }

    /**
     * 兼容接口
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean startRecordCompat(boolean isConsulation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            LogUtils.i("=======================我是Android P");
            return startRecord(!isConsulation);
        } else {
            return startRecord(!isConsulation);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean startRecord(boolean isAudio) {
        if (mediaProjection == null || running) {
            return false;
        }
        initRecorder(isAudio);
        createVirtualDisplay();
        mediaRecorder.start();
        running = true;
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean startRecord() {
        if (mediaProjection == null || running) {
            return false;
        }
        initRecorder(true);
        createVirtualDisplay();
        mediaRecorder.start();
        running = true;
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean stopRecord() {
        if (!running) {
            return false;
        }
        running = false;
        try {
            if (mediaRecorder != null) {
                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder = null;
            }
            if (virtualDisplay != null) {
                virtualDisplay.release();
            }
            if (mediaRecorder != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mediaProjection.stop();
                }
            }
            //Toast.makeText(this, " RuntimeException: ", Toast.LENGTH_SHORT).show();
        } catch (RuntimeException e) {
            LogUtils.e("   stopRecord :    " + e.toString());
        }
        if (!mSavePath.equals("")) {
            FileUtils.scanFile(this, mSavePath);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void createVirtualDisplay() {
        virtualDisplay = mediaProjection.createVirtualDisplay("MainScreen", width, height, dpi, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, mediaRecorder.getSurface(), null, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initRecorder(boolean isAudio) {
        try {
            if (mediaRecorder == null) {
                mediaRecorder = new MediaRecorder();
            }
            mSavePath = getsaveDirectory() + System.currentTimeMillis() + ".mp4";

            if (isAudio) {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            }
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setVideoSize(width, height);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            if (isAudio) {
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            }
            mediaRecorder.setVideoEncodingBitRate(8 * 1024 * 1024);
            mediaRecorder.setVideoFrameRate(35);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mediaRecorder.setOutputFile(new File(mSavePath));
            } else {
                mediaRecorder.setOutputFile(mSavePath);
            }
            mediaRecorder.prepare();
            mediaRecorder.setOnErrorListener((mr, what, extra) -> LogUtils.e("setOnErrorListener  what: " + what + " extra: " + extra));

        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("initRecorder: " + e.toString());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initRecorderCompat(boolean isAudio) {
        try {
            if (mediaRecorder == null) {
                mediaRecorder = new MediaRecorder();
            }
            mSavePath = getsaveDirectory() + System.currentTimeMillis() + ".mp4";

            if (isAudio) {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            }
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setVideoSize(width, height);
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            if (isAudio) {
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            }
            mediaRecorder.setVideoEncodingBitRate(8 * 1024 * 1024);
            mediaRecorder.setVideoFrameRate(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mediaRecorder.setOutputFile(new File(mSavePath));
            } else {
                mediaRecorder.setOutputFile(mSavePath);
            }


            mediaRecorder.prepare();
            mediaRecorder.setOnErrorListener((mr, what, extra) -> LogUtils.e("setOnErrorListener  what: " + what + " extra: " + extra));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getsaveDirectory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String rootDir = null;
            try {
//                记得打开
//                rootDir = ELabProfileManager.getELabScreenRecordSavePath();
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e("RecordService： " + e.toString());
                rootDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ELab/" + "ScreenRecord" + "/";
            }

            File file = new File(rootDir);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return null;
                }
            }

            // Toast.makeText(getApplicationContext(), rootDir, Toast.LENGTH_SHORT).show();

            return rootDir;
        } else {
            return null;
        }
    }

    public class RecordBinder extends Binder {
        public RecordService getRecordService() {
            return RecordService.this;
        }
    }
}