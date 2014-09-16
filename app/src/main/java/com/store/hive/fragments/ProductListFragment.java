package com.store.hive.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.store.hive.R;
import com.store.hive.adapters.ProductListAdapter;
import com.store.hive.model.products.Product;
import com.store.hive.model.products.ProductListGenerator;

import java.util.ArrayList;

/**
 * Created by tinashe
 */
public class ProductListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = ProductListFragment.class.getName();

    private SwipeRefreshLayout refreshLayout;

    private ListView listView;

    private ArrayList<Product> products;

    private ProductListAdapter adapter;

    public ProductListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorScheme(R.color.apptheme_color, R.color.white, R.color.apptheme_color, R.color.white);
        simulateGetData();

        listView = (ListView)rootView.findViewById(R.id.product_list);
        products = ProductListGenerator.getSampleProducts();
        adapter = new ProductListAdapter(getActivity(), R.layout.layout_product_item, products);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onRefresh() {
        simulateGetData();

    }

    @Override
    public void onResume(){
        super.onResume();

        if(refreshLayout != null){
            simulateGetData();
        }
    }

    private void simulateGetData(){
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                refreshLayout.setRefreshing(false);
                listView.smoothScrollToPosition(0);
            }
        }, 3000);

        refreshLayout.setRefreshing(true);
    }

    public void filterProductItems(String query){
       // Log.d(TAG, query);

        adapter.getFilter().filter(query);
    }

    public void reloadAdapterAfterSearch(){
        simulateGetData();

        products = ProductListGenerator.getSampleProducts();
        adapter.clear();
        adapter.addAll(products);
        adapter.notifyDataSetChanged();

    }

    public void showEmptySearchResults(){
        Toast.makeText(getActivity(), "No Search Results", Toast.LENGTH_SHORT).show();
    }

    public void hideEmptySearchResults(){
        Log.d(TAG, "hide search Results");
    }
}
