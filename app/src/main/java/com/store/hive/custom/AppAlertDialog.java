package com.store.hive.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.store.hive.R;

/**
 * Created by tinashe.
 */
public class AppAlertDialog {

    private int icon;
    private String title;
    private String message;
    private String positiveButton;
    private String negativeButton;
    private String neutralButton;
    private DialogInterface.OnClickListener positiveListener;
    private DialogInterface.OnClickListener negativeListener;
    private DialogInterface.OnClickListener neutralListener;
    private String[] items;
    private AdapterView.OnItemClickListener itemCLickListener;
    private Context context;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    private AppAlertDialog(Builder builder){
        this.icon = builder.icon;
        this.title = builder.title;
        this.message = builder.message;
        this.positiveButton = builder.positiveButton;
        this.negativeButton = builder.negativeButton;
        this.neutralButton = builder.neutralButton;
        this.positiveListener = builder.positiveListener;
        this.negativeListener = builder.negativeListener;
        this.neutralListener = builder.neutralListener;
        this.items = builder.items;
        this.itemCLickListener = builder.itemCLickListener;
        this.context = builder.context;

        buildAlertDialog();
    }

    private void buildAlertDialog(){
        builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_custom_dialog, null);

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

        if(message != null){
            TextView messageTextView = (TextView)view.findViewById(R.id.dialog_message);
            messageTextView.setVisibility(View.VISIBLE);
            messageTextView.setText(message);
        } else {
            if(items != null){
                ListView listView = (ListView)view.findViewById(R.id.listView);
                listView.setVisibility(View.VISIBLE);
                ArrayAdapter adapter = new ArrayAdapter(context, R.layout.simple_list_item_1, items);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(itemCLickListener);

            }

        }

        if(dialog != null){
            ((LinearLayout)view.getParent()).removeView(view);
        }
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



    public void show(){
        if(builder != null){
            dialog = builder.create();
            dialog.show();
        } else {
            throw new IllegalStateException("Dialog has not been initialised");
        }
    }

    public void dismiss(){
        if(dialog != null){
            dialog.dismiss();
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
        private String[] items;
        private AdapterView.OnItemClickListener itemCLickListener;
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

        public Builder setItems(String[] items, AdapterView.OnItemClickListener itemCLickListener){
            this.items = items;
            this.itemCLickListener = itemCLickListener;
            return this;
        }

        public AppAlertDialog build(){
            return new AppAlertDialog(this);
        }


    }

}
