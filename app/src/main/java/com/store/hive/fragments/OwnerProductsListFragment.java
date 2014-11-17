package com.store.hive.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.melnykov.fab.FloatingActionButton;
import com.store.hive.R;
import com.store.hive.model.products.Category;
import com.store.hive.model.response.GetCategoriesResponse;
import com.store.hive.service.StoreHiveAPI;
import com.store.hive.utils.db.CategoriesDB;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tinashe.
 */
public class OwnerProductsListFragment extends Fragment{

    private static final String TAG = OwnerProductsListFragment.class.getName();

    private CategoriesDB db;

    private List<Category> mCategories = new ArrayList<Category>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_products, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize(){
        if(!isAdded()){
            return;
        }
        final View view = getView();

        if(view != null){

            db = new CategoriesDB(getActivity());

            ListView listView = (ListView) view.findViewById(R.id.product_list);
            listView.setEmptyView(view.findViewById(R.id.txt_empty));

            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
            fab.attachToListView(listView);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = View.inflate(getActivity(), R.layout.layout_add_product, null);
                    setUpSpinner(dialogView);

                    new AlertDialog.Builder(getActivity())
                            .setView(dialogView)
                            .show();

                }
            });

            getCategoriesIfNecessary();
        }
    }

    private void setUpSpinner(View view){
        Spinner spinner = (Spinner) view.findViewById(R.id.cat_spinner);

        if(!mCategories.isEmpty()){

            SpinnerAdapter adapter = new SpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, mCategories);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner.setAdapter(adapter);

            spinner.setSelection(0);
        } else {
            Log.d(TAG, "Empty List");
        }
    }

    private class SpinnerAdapter extends ArrayAdapter<Category>{

        LayoutInflater inflater;
        public SpinnerAdapter(Context context, int resource, List<Category> items) {
            super(context, resource, items);
            inflater = LayoutInflater.from(context);
        }

        class ViewHolder{
            TextView textView;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textView.setText(getItem(position).getCategoryName());
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
                holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textView.setText(getItem(position).getCategoryName());
            return convertView;
        }
    }

    private void getCategoriesIfNecessary(){

        mCategories = db.getSavedCategories();

        if(mCategories.size() == 0){
            StoreHiveAPI.getAllCategories(getActivity(), new Response.Listener<GetCategoriesResponse>() {
                @Override
                public void onResponse(GetCategoriesResponse response) {
                    if (response != null) {
                        db.saveCategories(response.getCategories());
                    }
                }
            });
        }


    }
}
