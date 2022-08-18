package com.justsafe.libview.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.justsafe.libarch.utils.LogUtils;
import com.justsafe.libarch.utils.ToastUtils;
import com.justsafe.libview.R;
import com.justsafe.libview.loading.AVLoadingDialog;


public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {

    protected Context mContext;
    protected View conttentView;
    protected B mdatabinding;
    public Toolbar toolbar;
    private TextView titleView, rightView, leftView;
    private AppCompatImageView titleIcon;
    protected AppCompatImageView titleRightIcon;

    private AVLoadingDialog loading;
    private LinearLayout ll_elab;
    public TextView view_toolbar_title_1;
    public TextView view_toolbar_title_2;

    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public enum BaseEnum {
        EMC, ELAB, EMM, EMC2
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mdatabinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        loading = AVLoadingDialog.getInstance(getContext());
        mdatabinding.setLifecycleOwner(this);
        conttentView = mdatabinding.getRoot();
        toolbar = conttentView.findViewById(R.id.view_toolbar_toolbar);
        rightView = conttentView.findViewById(R.id.view_toolbar_righttv);
        titleView = conttentView.findViewById(R.id.view_toolbar_title);
        titleIcon = conttentView.findViewById(R.id.view_toolbar_icon);
        titleRightIcon = conttentView.findViewById(R.id.view_right_icon);

        leftView = conttentView.findViewById(R.id.view_toolbar_lefttv);
        ll_elab = conttentView.findViewById(R.id.ll_elab);
        view_toolbar_title_1 = conttentView.findViewById(R.id.view_toolbar_title_1);
        view_toolbar_title_2 = conttentView.findViewById(R.id.view_toolbar_title_2);
        init();
        return conttentView;
    }

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

    /**
     * 全屏显示
     */
    public void setFullScreen() {
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getActivity().getWindow().setAttributes(params);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); // Activity全屏显示，且状态栏被覆盖掉
    }

    public abstract void init();

    public abstract int getLayout();

    protected void setToolbar(String lefttv, String titletv, String righttv, boolean back) {
//        setToolbar(lefttv, titletv, righttv, back, R.color.color_toolbar);
    }

    protected void setToolbar(String titletv, Integer back_top, boolean back, BaseEnum type) {
        toolbar.setVisibility(View.VISIBLE);
        titleView.setVisibility(View.VISIBLE);
        ll_elab.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        titleView.setText(titletv);
        toolbar.setBackgroundColor(back_top);
        if (back) {
            if (type == BaseEnum.EMC) {
//                toolbar.setNavigationIcon(R.drawable.about_back);
            } else if (type == BaseEnum.ELAB) {
//                toolbar.setNavigationIcon(R.mipmap.back);
            } else {
//                toolbar.setNavigationIcon(R.drawable.emc_back);
            }
            toolbar.setNavigationOnClickListener(view -> {
                boolean is = Navigation.findNavController(view).popBackStack();
                if (!is) getActivity().finish();
            });
        }
    }

    protected void setToolbar(String lefttv, String titletv, String righttv, boolean back, int backgroundColor) {
        toolbar.setVisibility(View.VISIBLE);
        ll_elab.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        //toolbar.setBackgroundColor(backgroundColor);
        toolbar.setBackgroundResource(backgroundColor);

        titleView.setText(titletv);
        rightView.setText(righttv);
        leftView.setText(lefttv);
        if (back) {
//            toolbar.setNavigationIcon(R.mipmap.back);
            toolbar.setNavigationOnClickListener(view -> {
                        boolean is = Navigation.findNavController(view).popBackStack();
                        if (!is) getActivity().finish();
                    }
            );
        }
    }

    protected void setToolbar(String titletv) {
        setToolbar(titletv, false);
    }

    protected void setToolbar(String titletv, int backcolor) {
        setToolbar(titletv, false, backcolor);
    }

    protected void setToolbar(String lefttv, String titletv) {
        setToolbar(lefttv, titletv, "", false);
    }

    protected void setToolbar(String lefttv, String titletv, int backcolor) {
        setToolbar(lefttv, titletv, "", false, backcolor);
    }

    protected void setToolbar(String titletv, boolean back) {
        setToolbar("", titletv, "", back);
    }

    protected void setToolbar(String titletv, boolean back, int backgroundColor) {
        setToolbar("", titletv, "", back, backgroundColor);
    }


    protected void setELabToolbar() {
        titleView.setVisibility(View.GONE);
        rightView.setVisibility(View.GONE);
        titleView.setVisibility(View.GONE);
        ll_elab.setVisibility(View.VISIBLE);
//        toolbar.setNavigationIcon(R.mipmap.icon_home_devicelist);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
    }

    protected void setELabToolbar(boolean isLandscape) {
        titleView.setVisibility(View.GONE);
        rightView.setVisibility(View.GONE);
        titleView.setVisibility(View.GONE);
        titleIcon.setVisibility(View.GONE);
        ll_elab.setVisibility(View.VISIBLE);
        titleRightIcon.setVisibility(View.VISIBLE);
//        toolbar.setNavigationIcon(R.mipmap.icon_home_devicelist);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        toolbar.setVisibility(isLandscape ? View.GONE : View.VISIBLE);
    }

    protected void setDeleteToolbar() {
        titleView.setVisibility(View.VISIBLE);
        ll_elab.setVisibility(View.GONE);
        setToolbar("关闭");
//        toolbar.setBackgroundColor(getResources().getColor(R.color.drag_enter_bg));
    }

    protected void setDeleteToolbar(@ColorRes int id) {
        titleView.setVisibility(View.VISIBLE);
        ll_elab.setVisibility(View.GONE);
        titleIcon.setVisibility(View.VISIBLE);
//        titleIcon.setImageResource(R.drawable.delete_dis);
        setToolbar("关闭");
        toolbar.setBackgroundColor(getResources().getColor(id));
    }

    protected void setDeleteToolbar(@ColorRes int id, @DrawableRes int icon) {
        titleView.setVisibility(View.GONE);
        ll_elab.setVisibility(View.GONE);
        titleRightIcon.setVisibility(View.GONE);
        titleIcon.setVisibility(View.VISIBLE);
        titleIcon.setImageResource(icon);
        setToolbar("");
        toolbar.setBackgroundColor(getResources().getColor(id));
    }

    protected void setRightIcon(@DrawableRes int icon){
        titleRightIcon.setVisibility(View.VISIBLE);
        rightView.setVisibility(View.GONE);
        titleRightIcon.setImageResource(icon);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mdatabinding != null)
            mdatabinding.unbind();
    }


    protected void startActivity(Class activity, Bundle bundle) {
        Intent intent = new Intent(getActivity(), activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivity(Class activity) {
        startActivity(activity, null);
    }

    protected void navigate(View view, int id) {
        Navigation.findNavController(view).navigate(id);
    }

    protected void navigate(View view, int id, Bundle bundle) {
        Navigation.findNavController(view).navigate(id, bundle);
    }

    /**
     * @param msg
     */
    protected void showToast(String msg) {
        //Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        ToastUtils.showToast(getContext(), msg);
        LogUtils.v(msg);
    }

    protected void showLogToast(String msg) {
        //Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        LogUtils.v(msg);
    }


    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    public void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void setScreenSensor(boolean isAuto) {
        if (isAuto) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        }
    }

}
