package com.justsafe.just.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.justsafe.just.R;


@Deprecated
public class ShiftCustomDialog extends Dialog {
  
    public ShiftCustomDialog(Context context) {
        super(context);  
    }  
  
    public ShiftCustomDialog(Context context, int theme) {
        super(context, theme);  
    }  
  
    public static class Builder {  
        private Context context;  

        private String message;  
        private String positiveButtonText;  

        private View contentView;  
        private OnClickListener positiveButtonClickListener;
  
        public Builder(Context context) {  
            this.context = context;  
        }  
  
        public Builder setMessage(String message) {  
            this.message = message;  
            return this;  
        }  
  

        public Builder setMessage(int message) {  
            this.message = (String) context.getText(message);  
            return this;  
        }  
  


        public Builder setContentView(View v) {  
            this.contentView = v;  
            return this;  
        }  
  

        public Builder setPositiveButton(int positiveButtonText,  
                OnClickListener listener) {
            this.positiveButtonText = (String) context  
                    .getText(positiveButtonText);  
            this.positiveButtonClickListener = listener;  
            return this;  
        }  
  
        public Builder setPositiveButton(String positiveButtonText,  
                OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;  
            this.positiveButtonClickListener = listener;  
            return this;  
        }  
  

        public ShiftCustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context  
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
            // instantiate the dialog with the custom Theme  
            final ShiftCustomDialog dialog = new ShiftCustomDialog(context);
            View layout = inflater.inflate(R.layout.dialog_shift_normal_layout, null);
            dialog.addContentView(layout, new LayoutParams(  
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));  
            // set the dialog title
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.yes))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.yes))
                            .setOnClickListener(v -> positiveButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_POSITIVE));
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.yes).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            // set the content message  
            if (message != null) {  
                ((TextView) layout.findViewById(R.id.message)).setText(message);  
            } else if (contentView != null) {  
                // if no message set  
                // add the contentView to the dialog body  
                ((LinearLayout) layout.findViewById(R.id.message))
                        .removeAllViews();  
                ((LinearLayout) layout.findViewById(R.id.message))
                        .addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));  
            }  
            dialog.setContentView(layout);  
            return dialog;  
        }  
    }  
}  
