package com.store.hive.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.store.hive.MainActivity;
import com.store.hive.R;
import com.store.hive.fragments.ProductListFragment;
import com.store.hive.model.products.Product;
import com.store.hive.service.RequestHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinashe
 */
public class ProductListAdapter extends ArrayAdapter<Product> implements Filterable{

    private ArrayList<Product> products;
    private ArrayList<Product> originalList = new ArrayList<Product>();

    private ImageLoader mImageLoader;

    private LayoutInflater inflater;

    private Context context;

    private ProductItemsFilter filter;

    public ProductListAdapter(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);

        this.products = objects;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.originalList.addAll(objects);
        this.mImageLoader = RequestHandler.getInstance(context).getImageLoader();
    }

    class ViewHolder{
        NetworkImageView networkImageView;
        TextView textName;
        TextView textPrice;
        TextView textDesc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Product product = getItem(position);

        if(convertView == null){
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.layout_product_item, parent, false);
            holder.networkImageView = (NetworkImageView)convertView.findViewById(R.id.product_item_img);
            holder.textName = (TextView)convertView.findViewById(R.id.product_item_name);
            holder.textPrice = (TextView)convertView.findViewById(R.id.product_item_price);
            holder.textDesc = (TextView)convertView.findViewById(R.id.product_item_desc);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.textName.setText(product.getProductName());
        holder.textPrice.setText(product.getPrice());
        holder.textDesc.setText(product.getDescription());

        holder.networkImageView.setDefaultImageResId(R.drawable.prdct_no_img);
        holder.networkImageView.setErrorImageResId(R.drawable.prdct_no_img);
        if(product.hasImage()){
            holder.networkImageView.setImageUrl(product.getImgUrl(), mImageLoader);
        }


        return convertView;

    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new ProductItemsFilter();
        }
        return filter;
    }

    private class ProductItemsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence query) {
            query = query.toString().toLowerCase();
            FilterResults result = new FilterResults();

            if(!TextUtils.isEmpty(query)){
                List<Product> filteredProducts = new ArrayList<Product>();

                for(int i = 0, l = originalList.size(); i < l; i++ ){
                    Product product = originalList.get(i);

                   /* String regex = query.toString().replaceAll(" ", "(.*?)");

                    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(product.getProductName());
                    if(matcher.find()){
                        filteredProducts.add(product);
                    }*/

                    if(product.getProductName().toLowerCase().contains(query.toString().toLowerCase())){
                        filteredProducts.add(product);
                        //Log.d("Filter: added ", product.getProductName());
                    }

                }

                result.count = filteredProducts.size();
                result.values = filteredProducts;

            } else {

                synchronized(this)
                {
                   // products.clear();
                    result.values = products;
                    result.count = products.size();
                }
            }

            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            products = (ArrayList<Product>)results.values;
            clear();
            addAll(products);
            notifyDataSetChanged();

            Fragment productListFragment = ((MainActivity)context).getSupportFragmentManager().findFragmentByTag(ProductListFragment.class.getName());

            if (productListFragment != null) {
              //  ((ProductListFragment) productListFragment).setVisibleViewType();

                if(products.isEmpty()){

                    if (!TextUtils.isEmpty(constraint)) {
                        ((ProductListFragment) productListFragment).showEmptySearchResults();
                    } else {
                        ((ProductListFragment) productListFragment).hideEmptySearchResults();
                    }
                }
           }


        }
    }
}
