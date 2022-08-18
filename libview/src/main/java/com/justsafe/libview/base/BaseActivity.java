package com.justsafe.libview.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.justsafe.libarch.AppManager;
import com.justsafe.libarch.utils.LogUtils;
import com.justsafe.libarch.utils.StatusBarUtils;
import com.justsafe.libarch.utils.ToastUtils;
import com.justsafe.libview.R;
import com.justsafe.libview.loading.AVLoadingDialog;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {
    protected B mdatabinding;
    public Toolbar toolbar;
    public TextView rightView;
    private TextView titleView;
    private LinearLayout ll_elab;
    public TextView view_toolbar_title_1;
    public TextView view_toolbar_title_2;
    private AVLoadingDialog loading;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        mdatabinding = DataBindingUtil.setContentView(this, getLayout());
        toolbar = findViewById(R.id.view_toolbar_toolbar);
        rightView = findViewById(R.id.view_toolbar_righttv);
        titleView = findViewById(R.id.view_toolbar_title);
        ll_elab = findViewById(R.id.ll_elab);
        view_toolbar_title_1 = findViewById(R.id.view_toolbar_title_1);
        view_toolbar_title_2 = findViewById(R.id.view_toolbar_title_2);
        loading = AVLoadingDialog.getInstance(this);
        init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unRegisterBroadcast();
        AppManager.getInstance().finishActivity(this); //从栈中移除
    }

    public abstract int getLayout();

    public abstract void init();

    public void showLoading() {
        if (!loading.isShowing()) {
            //show dialog
            loading.show();
        }
    }

    public void hideLoading() {
        if (loading.isShowing()) {
            loading.dismiss();
        }
    }

    public enum BaseEnum {
        EMC, ELAB, EMM, EMC2
    }

    protected void initStatus() {
        StatusBarUtils.setRootViewFitsSystemWindows(this, true);
        StatusBarUtils.setTranslucentStatus(this);
        if (!StatusBarUtils.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(this, 0x55000000);
        }
    }

    @SuppressLint("ResourceAsColor")
    protected void setToolbar(String titletv, String righttv, boolean back, int back_icon) {
        toolbar.setVisibility(View.VISIBLE);
        titleView.setVisibility(View.VISIBLE);
        ll_elab.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        titleView.setText(titletv);
        rightView.setText(righttv);
        if (back) {
            toolbar.setNavigationIcon(back_icon);
//            toolbar.setBackgroundColor(R.mipmap.toolbar_background);
            toolbar.setNavigationOnClickListener(view -> finish());
        }
    }

    protected void setToolbar(String titletv, String righttv, boolean back) {
        toolbar.setVisibility(View.VISIBLE);

        titleView.setVisibility(View.VISIBLE);
        ll_elab.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        titleView.setText(titletv);
        rightView.setText(righttv);

        if (back) {
//            toolbar.setNavigationIcon(R.mipmap.back);
            toolbar.setNavigationOnClickListener(view -> finish());
        }
    }

    protected void setToolbar(String titletv, Object back_top, boolean back, BaseEnum type) {
        toolbar.setVisibility(View.VISIBLE);
        titleView.setVisibility(View.VISIBLE);
        ll_elab.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        titleView.setText(titletv);
        if (back_top instanceof Integer) {
            toolbar.setBackgroundColor((int) back_top);
        } else {
            toolbar.setBackground((Drawable) back_top);
        }
        if (back) {
            if (type == BaseEnum.EMC) {
//                toolbar.setNavigationIcon(R.drawable.about_back);
            } else if (type == BaseEnum.ELAB) {
//                toolbar.setNavigationIcon(R.mipmap.back);
            } else if (type == BaseEnum.EMM) {
//                toolbar.setNavigationIcon(R.drawable.emc_back);
            } else {
//                toolbar.setNavigationIcon(R.drawable.emc_activity_back);
            }
            toolbar.setNavigationOnClickListener(view -> finish());
        }
    }

    protected void setToolbar(String titletv) {
        setToolbar(titletv, false);
    }

    protected void hideToolBar() {
        toolbar.setVisibility(View.GONE);
    }

    protected void setELabToobar() {
        titleView.setVisibility(View.GONE);
        rightView.setVisibility(View.GONE);
        titleView.setVisibility(View.GONE);
        ll_elab.setVisibility(View.VISIBLE);
//        toolbar.setNavigationIcon(R.mipmap.icon_home_devicelist);
    }

    protected BaseActivity<B> setToolbar(String titletv, boolean back) {
        setToolbar(titletv, "", back);
        return this;
    }


    protected void startActivity(Class activity, Bundle bundle) {
        Intent intent = new Intent(this, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivity(Class activity) {
        startActivity(activity, null);
//        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }


    /**
     * 全屏显示
     */
    public void setFullScreen() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(params);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); // Activity全屏显示，且状态栏被覆盖掉
    }

    /**
     * 退出全屏
     */
    public void exitFullScreen() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(params);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); // Activity全屏显示，但是状态栏不会被覆盖掉，而是正常显示，只是Activity顶端布局会被覆盖住
    }

    protected void showToast(String msg) {
        //Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        ToastUtils.showToast(this, msg);
        LogUtils.v(msg);
    }

    BaseReceiver receiver;
    private boolean isRegisterReceiver = false;

    private void registerBroadcast() {
        // 注册广播接收者，关闭所有activity
        receiver = new BaseReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("exit_app");
        if (!isRegisterReceiver) {
            getBaseContext().registerReceiver(receiver, filter);
            isRegisterReceiver = true;
        }
    }

    private void unRegisterBroadcast() {
        // 注册广播接收者，关闭所有activity
        if (isRegisterReceiver) {
            getBaseContext().unregisterReceiver(receiver);
            isRegisterReceiver = false;
        }
    }

    class BaseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("exit_app")) {
                LogUtils.e("zs" + "退出登陆");
                finish();
            }
        }
    }
}
