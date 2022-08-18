package com.justsafe.libarch.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.justsafe.libarch2.R;


public class ToastUtils {

    /**
     * 69      * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
     * 70
     */
    public static void ToastMessage(Activity activity, String messages) {
        //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
        LayoutInflater inflater = activity.getLayoutInflater();//调用Activity的getLayoutInflater()
        View view = inflater.inflate(R.layout.toast_style, null); //加載layout下的布局
        //ImageView iv = view.findViewById(R.id.tvImageToast);
        //iv.setImageResource(R.mipmap.atm);//显示的图片
        //TextView title = view.findViewById(R.id.tvTitleToast);
        //title.setText(titles); //toast的标题
        TextView text = view.findViewById(R.id.message);
        text.setText(messages); //toast内容
        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER, 0, 0);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(text); //添加视图文件
        toast.show();
    }


    public static void showToast(@NonNull Context context, @StringRes int resId) {
        showToast(context, context.getResources().getString(resId), false);
    }

    public static void showToast(@NonNull Context context, String content) {
        showToast(context, content, false);
    }

    public static void showToast(@NonNull Context context, String content, boolean longTime) {
        showToast(context, content, longTime, 0, 0);
    }

    /**
     * {@link layout/transient_notification.xml}
     *
     * @param content              content to show
     * @param longTime             short or long
     * @param context              context
     * @param textColor            toast text color
     * @param toastBackgroundColor toast background color
     */
    public static void showToast(@NonNull Context context, String content, boolean longTime, @ColorInt
            int textColor, @ColorInt int toastBackgroundColor) {
        int type = longTime ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, content, type);
        ViewGroup toastView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.toast_style, null, false);
        if (toastBackgroundColor != 0) {
            toastView.setBackgroundDrawable(getToastBackground(context, toastBackgroundColor));
        }
        TextView textView = (TextView) toastView.findViewById(android.R.id.message);
        // 内部已经作非空判断了
        if (textColor != 0) {
            textView.setTextColor(textColor);
        }
        Typeface typeface = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
        textView.setTypeface(typeface);
        toast.setView(toastView);
        toast.setText(content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private static Drawable getToastBackground(@NonNull Context context, @ColorInt int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(DimensUtils.dp2px(context, 24));
        gradientDrawable.setColor(color);
        return gradientDrawable;

    }

}
