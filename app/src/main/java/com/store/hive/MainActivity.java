package com.store.hive;

import java.io.Serializable;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.store.hive.custom.AppAlertDialog;
import com.store.hive.fragments.ProductListFragment;
import com.store.hive.tabs.MaterialTab;
import com.store.hive.tabs.MaterialTabHost;
import com.store.hive.tabs.MaterialTabListener;


public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener, MaterialTabListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private ViewType mViewType = ViewType.LIST;

    private SearchView mSearchView;

    private AppAlertDialog dialog;

    private MaterialTabHost mTabHost;


    @Override
    protected int getLayoutResource() {
        return R.layout.default_activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);


        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                mTabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }

        // set the first item selected
        mTabHost.setSelectedNavigationItem(0);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setQueryHint(getString(R.string.sh_search));
        mSearchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                menu.findItem(R.id.action_toggle).setVisible(true);

                ProductListFragment fragment = (ProductListFragment) mSectionsPagerAdapter.getRegisteredFragment(0);

                if(fragment != null){
                    fragment.reloadAdapterAfterSearch();
                }
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                menu.findItem(R.id.action_toggle).setVisible(false);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toggle:
              /*  if(mViewType == ViewType.LIST){
                    mViewType = ViewType.GRID;
                    item.setIcon(getResources().getDrawable(R.drawable.ic_action_grid));
                } else {
                    mViewType = ViewType.LIST;
                    item.setIcon(getResources().getDrawable(R.drawable.ic_action_list));
                }*/
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                return true;
            case R.id.action_settings:
              //  testDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void testDialog(){
        String[] items = {"Title and Message", "Message Only", "Message Title no Icon"};
        final Activity activity = this;
        dialog = new AppAlertDialog.Builder(this)
                .setItems(items, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog.dismiss();

                        switch (position){
                            case 0:
                                dialog = new AppAlertDialog.Builder(activity)
                                        .setMessage(R.string.sh_details_about_app)
                                        .setTitle(R.string.sh_already_have_account)
                                        .setPositiveButton(R.string.sh_ok, null)
                                        .build();
                                break;
                            case 1:
                                dialog = new AppAlertDialog.Builder(activity)
                                        .setMessage(R.string.sh_already_have_account)
                                        .setPositiveButton(R.string.sh_ok, null)
                                        .build();
                                dialog.show();
                                break;
                            case 2:
                                dialog = new AppAlertDialog.Builder(activity)
                                        .setMessage(R.string.sh_details_about_app)
                                        .setTitle(R.string.sh_already_have_account)
                                        .setPositiveButton(R.string.sh_ok, null)
                                        .build();
                                break;
                        }
                        dialog.show();
                    }
                })
                .setIcon(R.drawable.ic_action_info)
                .setTitle(R.string.sh_options)
                .build();
     //  dialog.show();

        new AlertDialog.Builder(this)
                .setMessage(R.string.sh_details_about_app)
                .setTitle(R.string.sh_already_have_account)
                .setPositiveButton(R.string.sh_ok, null)
                .show();


    }



    @Override
    public boolean onQueryTextChange(String s) {

        ProductListFragment fragment = (ProductListFragment) mSectionsPagerAdapter.getRegisteredFragment(0);

        if(fragment != null){
            fragment.filterProductItems(s);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s){
        return false;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {

        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {
        tab.setTextColor(getResources().getColor(R.color.theme_accent));
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new ProductListFragment();
            } else {
                return PlaceholderFragment.newInstance(position + 1);
            }


        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            // Because size of 1 contains only position 0. If size of 0, then .get(x) = NPE.
            if (registeredFragments.size() > position) {
                return registeredFragments.get(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.sh_all_items).toUpperCase(l);
                case 1:
                    return getString(R.string.sh_most_recent).toUpperCase(l);
                case 2:
                    return getString(R.string.sh_most_popular).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        private SwipeRefreshLayout refreshLayout;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
            refreshLayout.setOnRefreshListener(this);
            refreshLayout.setColorScheme(R.color.apptheme_color, R.color.white, R.color.apptheme_color, R.color.white);

            return rootView;
        }

        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                }
            }, 5000);
        }
    }

    public enum ViewType implements Serializable {
        LIST,
        GRID;
    }

}
