package com.store.hive.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.store.hive.R;

/**
 * Created by tinashe.
 */
public class CustomDialog {

    private int icon;
    private String title;
    private String message;
    private String positiveButton;
    private String negativeButton;
    private String neutralButton;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;
    private DialogInterface.OnClickListener neutralListener;
    private Context context;
    private AlertDialog.Builder builder;

    private CustomDialog(Builder builder){
        this.icon = builder.icon;
        this.title = builder.title;
        this.message = builder.message;
        this.positiveButton = builder.positiveButton;
        this.negativeButton = builder.negativeButton;
        this.neutralButton = builder.neutralButton;
        this.positiveListener = builder.positiveListener;
        this.negativeListener = builder.negativeListener;
        this.neutralListener = builder.neutralListener;
        this.context = builder.context;

        buildAlertDialog();
    }

    private void buildAlertDialog(){
        builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.layout_custom_dialog, null);

        //Build the title
        if(title != null){
            TextView titleTextView = (TextView)view.findViewById(R.id.dialog_title);
            view.findViewById(R.id.title_layout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.title_body_separator).setVisibility(View.VISIBLE);
            titleTextView.setText(title);

            if(icon != 0){
                ImageView iconImageView = (ImageView)view.findViewById(R.id.dialog_icon);
                iconImageView.setVisibility(View.VISIBLE);
                iconImageView.setImageResource(icon);
            }
        }

        TextView messageTextView = (TextView)view.findViewById(R.id.dialog_message);
        messageTextView.setText(message);
        builder.setView(view);

        //Build The Actions
        if(positiveButton != null){
            builder.setPositiveButton(positiveButton, positiveListener);
        }
        if(negativeButton != null){
            builder.setNegativeButton(negativeButton, negativeListener);
        }
        if(neutralButton != null){
            builder.setNeutralButton(neutralButton, neutralListener);
        }


    }

    private void buildDialogListItems(ListView listView, String[] items){
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }


    public void show(){
        if(builder != null){
            builder.show();
        } else {
            throw new IllegalStateException("Dialog has not been initialised");
        }
    }

    public static class Builder {
        private int icon;
        private String title;
        private String message;
        private String positiveButton;
        private String negativeButton;
        private String neutralButton;
        private DialogInterface.OnClickListener positiveListener;
        private DialogInterface.OnClickListener negativeListener;
        private DialogInterface.OnClickListener neutralListener;
        private Context context;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setIcon(int resID){
            this.icon = resID;
            return this;
        }

        public Builder setTitle(int text){
            this.title = context.getString(text);
            return this;
        }

        public Builder setMessage(int text){
            this.message = context.getString(text);
            return this;
        }

        public Builder setPositiveButton(int text, DialogInterface.OnClickListener listener){
            this.positiveButton = context.getString(text);
            this.positiveListener = listener;
            return this;
        }

        public Builder setNegativeButton(int text, DialogInterface.OnClickListener listener){
            this.negativeButton = context.getString(text);
            this.negativeListener = listener;
            return this;
        }

        public Builder setNeutralButton(int text, DialogInterface.OnClickListener listener){
            this.neutralButton = context.getString(text);
            this.neutralListener = listener;
            return this;
        }

        public CustomDialog build(){
            return new CustomDialog(this);
        }


    }

}
