package com.store.hive.store_owner;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.store.hive.R;
import com.store.hive.model.Store;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.response.BaseResponse;
import com.store.hive.model.response.RegisterStoreResponse;
import com.store.hive.service.StoreHiveAPI;

public class OpenStoreActivity extends Activity implements
        GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener{

    private static final String TAG = OpenStoreActivity.class.getName();

    public static final String OWNER_SERIALIZED_KEY = "store_owner";

    private LocationClient mLocationClient;
    private Location mLocation;

    private static StoreOwner mStoreOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_store);

        mLocationClient = new LocationClient(this, this, this);


        mStoreOwner = (StoreOwner)getIntent().getSerializableExtra(OWNER_SERIALIZED_KEY);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new RegisterStoreFragment())
                    .commit();
        }
    }

    public Location getmLocation(){
        return mLocation;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mLocationClient.connect();
    }

    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocation = mLocationClient.getLastLocation();


        if(mLocation != null){
            Log.d(TAG, "Got Location: "+mLocation.getLatitude());
        }


    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class RegisterStoreFragment extends Fragment {

        private EditText storeName;
        private EditText storeDescription;
        private Store mStore;

        public RegisterStoreFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_open_store, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            if(isAdded()){
                View view = getView();

                if(view != null){
                    storeName = (EditText) view.findViewById(R.id.storeName);
                    storeDescription = (EditText) view.findViewById(R.id.storeDesc);

                    final Button register = (Button) view.findViewById(R.id.btnRegisterStore);

                    register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(isValid()){
                                Log.d(TAG, "Making call: "+ mStore.getDescription());

                                StoreHiveAPI.registerStore(getActivity(), mStore, new Response.Listener<RegisterStoreResponse>() {
                                    @Override
                                    public void onResponse(RegisterStoreResponse response) {
                                        if(response != null){
                                            if(response.isSuccessful()){
                                                mStore.setStoreId(response.getStoreId());
                                                mStore.setOpen(true);

                                                Location loc = ((OpenStoreActivity)getActivity()).getmLocation();
                                                if(loc != null){
                                                    mStore.setLatitude(String.valueOf(loc.getLatitude()));
                                                    mStore.setLongitude(String.valueOf(loc.getLongitude()));

                                                    openStore();
                                                } else {
                                                    Toast.makeText(getActivity(), "Location not set", Toast.LENGTH_LONG).show();
                                                }


                                            } else {

                                                Log.d(TAG, "Response unsuccessful " + response.getErrorMessage());
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }


        }

        private boolean isValid(){

            String name = storeName.getText().toString();
            String desc = storeDescription.getText().toString();

            if(TextUtils.isEmpty(name)){
                storeName.requestFocus();
                storeName.setError(getString(R.string.sh_error_input_required));
                return false;
            }
            if(TextUtils.isEmpty(desc)){
                storeDescription.requestFocus();
                storeDescription.setError(getString(R.string.sh_error_input_required));
                return false;
            }


            mStore = new Store();
            mStore.setShopName(name);
            mStore.setDescription(desc);
            mStore.setOwnerId(mStoreOwner.getOwnerID());

            Log.d(TAG, "Details set: "+mStore.toString());

            return true;
        }

        private void openStore(){
            StoreHiveAPI.openStore(getActivity(), mStore, new Response.Listener<BaseResponse>() {
                @Override
                public void onResponse(BaseResponse response) {
                    if(response.isSuccessful()){
                        Intent intent = new Intent(getActivity(), com.store.hive.store_owner.MainActivity.class);
                        intent.putExtra(getString(R.string.sh_pref_full_name), mStoreOwner.getFullName());

                        startActivity(intent);
                    }
                }
            });
        }
    }
}
