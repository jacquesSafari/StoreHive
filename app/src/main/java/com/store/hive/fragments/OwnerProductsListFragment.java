package com.store.hive.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.store.hive.R;


/**
 * Created by tinashe.
 */
public class OwnerProductsListFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_products, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initialize(){
        if(!isAdded()){
            return;
        }
        View view = getView();

        if(view != null){
            ListView listView = (ListView) view.findViewById(R.id.product_list);
            listView.setEmptyView(view.findViewById(R.id.txt_empty));
        }
    }
}
