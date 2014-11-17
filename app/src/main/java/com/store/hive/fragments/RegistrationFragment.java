package com.store.hive.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.store.hive.R;
import com.store.hive.custom.ClickSpan;
import com.store.hive.model.people.RegisteredUser;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.response.BaseResponse;
import com.store.hive.model.response.ErrorCodes;
import com.store.hive.model.response.RegisterOwnerResponse;
import com.store.hive.service.ServiceUtil;
import com.store.hive.service.StoreHiveAPI;
import com.store.hive.store_owner.OpenStoreActivity;
import com.store.hive.utils.AccountsManager;
import com.store.hive.utils.AppConfig;

/**
 * Created by tinashe.
 */
public class RegistrationFragment extends Fragment {
    
    private static final String TAG = RegistrationFragment.class.getName();
    private static final int MAX_ANIM_DURATION = 700;

    private ProgressBar smoothProgressBar;
    

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_onboarding_item_three, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        if(isAdded()){
            initialize();
        }
    }

    private void initialize() {
        
        View view = getView();
        if(view != null){
            smoothProgressBar = (ProgressBar)view.findViewById(R.id.app_progress_indicator);

            final View registerLayout = view.findViewById(R.id.layout_register);
            final View signInLayout = view.findViewById(R.id.sign_in_layout);

            TextView login_text_view = (TextView)registerLayout.findViewById(R.id.loginLink);
            new ClickSpan().clickify(login_text_view, login_text_view.getText().toString(), new ClickSpan.OnClickListener() {
                @Override
                public void onClick() {
                    YoYo.with(Techniques.FadeOutLeft)
                            .duration(MAX_ANIM_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    registerLayout.setVisibility(View.GONE);
                                    YoYo.with(Techniques.FadeInLeft)
                                            .duration(MAX_ANIM_DURATION)
                                            .playOn(signInLayout);
                                    signInLayout.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .playOn(registerLayout);



                    TextView register_text_view = (TextView)signInLayout.findViewById(R.id.registerLink);
                    new ClickSpan().clickify(register_text_view, register_text_view.getText().toString(), new ClickSpan.OnClickListener() {
                        @Override
                        public void onClick() {

                            YoYo.with(Techniques.FadeOutLeft)
                                    .duration(MAX_ANIM_DURATION)
                                    .withListener(new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            signInLayout.setVisibility(View.GONE);

                                            YoYo.with(Techniques.FadeInLeft)
                                                    .duration(MAX_ANIM_DURATION)
                                                    .playOn(registerLayout);
                                            registerLayout.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    })
                                    .playOn(signInLayout);

                        }
                    });
                }
            });
            setUpOnRegisterClickLister(view);
            setUpOnLoginClickLister(view);
        }
    }


    private void setUpOnRegisterClickLister(View rootView){
        final EditText full_name = (EditText)rootView.findViewById(R.id.user_name);
        final AutoCompleteTextView email = (AutoCompleteTextView)rootView.findViewById(R.id.user_email);
        final EditText password = (EditText)rootView.findViewById(R.id.user_password);
        final EditText password_match = (EditText)rootView.findViewById(R.id.user_password_two);

        String[] accounts = AccountsManager.getInstance().getAccounts(getActivity());
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.simple_list_item_1, accounts);
        email.setAdapter(adapter);

        Button registerBtn = (Button)rootView.findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = full_name.getText().toString();
                String user_mail = email.getText().toString();
                String user_pass = password.getText().toString();
                String pass2 = password_match.getText().toString();

                if(TextUtils.isEmpty(user_name)){
                    full_name.requestFocus();
                    full_name.setError(getString(R.string.sh_error_empty_full_name));
                }else
                if(TextUtils.isEmpty(user_mail)){
                    email.requestFocus();
                    email.setError(getString(R.string.sh_error_empty_email));
                }else if(TextUtils.isEmpty(user_pass)){
                    password.requestFocus();
                    password.setError(getString(R.string.sh_error_empty_password));
                }else
                if(TextUtils.isEmpty(pass2)){
                    password_match.requestFocus();
                    password_match.setError(getString(R.string.sh_error_empty_password_match));
                }
                else
                if(!TextUtils.equals(user_pass, pass2)){
                    password_match.requestFocus();
                    password_match.setError(getString(R.string.sh_error_passwords_do_not_match));
                }else
                if(!Patterns.EMAIL_ADDRESS.matcher(user_mail).matches()){
                    email.requestFocus();
                    email.setError(getString(R.string.sh_error_invalid_email));
                }
                else{
                    smoothProgressBar.setVisibility(View.VISIBLE);
                    String deviceID = Settings.Secure.getString(getActivity().getContentResolver(),
                            Settings.Secure.ANDROID_ID);

                    StoreOwner owner = new StoreOwner(user_name, user_mail, user_pass, deviceID);
                    performRegistrationRemoteCall(owner);
                }
            }
        });

    }

    private void performRegistrationRemoteCall(final StoreOwner owner){

        if(ServiceUtil.isConnected(getActivity())){

            StoreHiveAPI.registerStoreOwner(getActivity(), owner, new Response.Listener<RegisterOwnerResponse>() {
                @Override
                public void onResponse(RegisterOwnerResponse response) {
                    smoothProgressBar.setVisibility(View.GONE);

                    if (response != null) {

                        Log.d(TAG, response.toString());

                        if (response.isSuccessful()) {
                            Log.d(TAG, "is successful");


                            Intent intent = new Intent(getActivity(), OpenStoreActivity.class);
                            owner.setOwnerID(response.getOwnerID());
                            AppConfig.saveAuthState(getActivity(), new RegisteredUser(true, owner.getOwnerID(), owner.getFullName(), owner.getUserName()));
                            intent.putExtra(OpenStoreActivity.OWNER_SERIALIZED_KEY, owner);

                            getActivity().startActivity(intent);
                            getActivity().finish();

                        } else {
                            Log.d(TAG, "not successful: "+response.getErrorMessage());

                            if (getView() != null) {
                                AutoCompleteTextView email_textView = (AutoCompleteTextView) getView().findViewById(R.id.user_email);
                                email_textView.requestFocus();
                                email_textView.setError(response.getErrorMessage());
                            } else {
                                Toast.makeText(getActivity(), response.getErrorMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    } else {
                        Log.d(TAG, "Null response");
                        Toast.makeText(getActivity(), getString(R.string.sh_error_default), Toast.LENGTH_LONG).show();
                    }


                }
            });

        } else {
            smoothProgressBar.setVisibility(View.GONE);
            ServiceUtil.showNoConnectionDialog(getActivity());
        }

    }

    private void setUpOnLoginClickLister(View rootView){

        final EditText email_txt = (EditText)rootView.findViewById(R.id.login_user_email);
        final EditText password_txt = (EditText)rootView.findViewById(R.id.login_user_password);
        Button login_btn = (Button)rootView.findViewById(R.id.signinBtn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = email_txt.getText().toString();
                String password = password_txt.getText().toString();


                if(TextUtils.isEmpty(userName)){
                    email_txt.requestFocus();
                    email_txt.setError("");
                } else
                if(TextUtils.isEmpty(password)){
                    password_txt.requestFocus();
                    password_txt.setError("");
                } else {
                    smoothProgressBar.setVisibility(View.VISIBLE);

                    if(ServiceUtil.isConnected(getActivity())){

                        StoreOwner owner = new StoreOwner();
                        owner.setUserName(userName);
                        owner.setPassword(password);

                        StoreHiveAPI.authenticateStoreOwner(getActivity(), owner, new Response.Listener<BaseResponse>() {
                            @Override
                            public void onResponse(BaseResponse response) {
                                smoothProgressBar.setVisibility(View.GONE);

                                if (response != null) {
                                    boolean isSuccess = response.isSuccessful();

                                    if(isSuccess){
                                        Intent intent = new Intent(getActivity(), com.store.hive.store_owner.MainActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();

                                    } else {

                                        if(response.getErrorCode().equals(ErrorCodes.LOG_ERR_1)){
                                            email_txt.requestFocus();
                                            email_txt.setError(response.getErrorMessage());

                                        } else {
                                            password_txt.requestFocus();
                                            password_txt.setError(response.getErrorMessage());
                                        }


                                    }


                                } else {
                                    Toast.makeText(getActivity(), getString(R.string.sh_error_default), Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                    } else {
                        smoothProgressBar.setVisibility(View.GONE);
                        ServiceUtil.showNoConnectionDialog(getActivity());
                    }
                }
            }
        });

    }
}
