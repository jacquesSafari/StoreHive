package com.store.hive;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.store.hive.custom.ClickSpan;
import com.store.hive.custom.viewpagerindicator.CirclePageIndicator;
import com.store.hive.model.people.RegisteredUser;
import com.store.hive.model.people.StoreOwner;
import com.store.hive.model.response.ErrorCodes;
import com.store.hive.model.response.ResponseResult;
import com.store.hive.service.OnRequestCompleteLister;
import com.store.hive.service.RequestHandler;
import com.store.hive.service.ServiceUtil;
import com.store.hive.utils.AccountsManager;
import com.store.hive.utils.AppConfig;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;


public class OnBoardingActivity extends ActionBarActivity {

    @SuppressWarnings("unused")
    private static final String TAG = OnBoardingActivity.class.getName();

    private ViewPager mViewPager;
    private OnBoardingPagerAdapter mOnBoardingPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        mOnBoardingPagerAdapter =
                new OnBoardingPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mOnBoardingPagerAdapter);
        CirclePageIndicator mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
    }

    public void switchToRegister(View view){
        if(mViewPager != null){
            mViewPager.setCurrentItem(2);
        }
    }

    public void continueToMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public class OnBoardingPagerAdapter extends FragmentStatePagerAdapter {

        public OnBoardingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new OnBoardingFragment();
            Bundle args = new Bundle();
            args.putInt(OnBoardingFragment.ARG_POSITION, i + 1);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }

    public static class OnBoardingFragment extends Fragment {
        public static final String ARG_POSITION = "position";
        private static final int MAX_ANIM_DURATION = 700;
        private SmoothProgressBar smoothProgressBar;

        private ImageLoader mImageLoader;

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            mImageLoader = RequestHandler.getInstance(getActivity()).getImageLoader();

            Bundle args = getArguments();
            int position = args.getInt(ARG_POSITION);

            switch (position){
                case 1:
                    final View rootView = inflater.inflate(
                            R.layout.layout_onboarding_item, container, false);
                    TextView txt_sign_in = (TextView)rootView.findViewById(R.id.label_sign_in);
                    txt_sign_in.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

                    YoYo.with(Techniques.FadeIn)
                            .duration(MAX_ANIM_DURATION)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    rootView.findViewById(R.id.img_logo).setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    YoYo.with(Techniques.SlideInLeft)
                                            .duration(MAX_ANIM_DURATION)
                                            .withListener(new Animator.AnimatorListener() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {
                                                    rootView.findViewById(R.id.txt_swipe_label).setVisibility(View.VISIBLE);
                                                }

                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    YoYo.with(Techniques.SlideInRight)
                                                            .duration(MAX_ANIM_DURATION)
                                                            .withListener(new Animator.AnimatorListener() {
                                                                @Override
                                                                public void onAnimationStart(Animator animation) {
                                                                    rootView.findViewById(R.id.sign_layout_labels).setVisibility(View.VISIBLE);
                                                                }

                                                                @Override
                                                                public void onAnimationEnd(Animator animation) {

                                                                }

                                                                @Override
                                                                public void onAnimationCancel(Animator animation) {

                                                                }

                                                                @Override
                                                                public void onAnimationRepeat(Animator animation) {

                                                                }
                                                            })
                                                            .playOn(rootView.findViewById(R.id.sign_layout_labels));
                                                }

                                                @Override
                                                public void onAnimationCancel(Animator animation) {

                                                }

                                                @Override
                                                public void onAnimationRepeat(Animator animation) {

                                                }
                                            })
                                            .playOn(rootView.findViewById(R.id.txt_swipe_label));
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .playOn(rootView.findViewById(R.id.img_logo));

                    return rootView;
                case 2:
                   View secondView = inflater.inflate(
                            R.layout.layout_onboarding_item_two, container, false);
                    NetworkImageView tatenda = (NetworkImageView) secondView.findViewById(R.id.tatenda);
                    tatenda.setImageUrl("https://lh5.googleusercontent.com/-_euXZpm6CY8/AAAAAAAAAAI/AAAAAAAAAC0/l9nhcNVeuQc/s120-c/photo.jpg", mImageLoader);

                    NetworkImageView tyron = (NetworkImageView) secondView.findViewById(R.id.tyron);
                    tyron.setImageUrl("https://lh4.googleusercontent.com/-vY1GomDZySA/AAAAAAAAAAI/AAAAAAAAAg0/CWjoLGlRHy0/s120-c/photo.jpg", mImageLoader);

                    NetworkImageView tinashe = (NetworkImageView) secondView.findViewById(R.id.tinashe);
                    tinashe.setImageUrl("https://lh6.googleusercontent.com/-Dm29aCF5xy8/AAAAAAAAAAI/AAAAAAAABE8/01V7rKghQt0/s120-c/photo.jpg", mImageLoader);

                    return secondView;
                case 3:
                    View thirdView = inflater.inflate(
                            R.layout.layout_onboarding_item_three, container, false);

                    smoothProgressBar = (SmoothProgressBar)thirdView.findViewById(R.id.app_progress_indicator);

                    Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/handsean.ttf");
                    ((TextView)thirdView.findViewById(R.id.app_name)).setTypeface(tf);

                    final View registerLayout = thirdView.findViewById(R.id.layout_register);
                    final View signInLayout = thirdView.findViewById(R.id.sign_in_layout);

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
                    setUpOnRegisterClickLister(thirdView);
                    setUpOnLoginClickLister(thirdView);
                    return thirdView;
            }

            return null;
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
                    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(user_mail).matches()){
                        email.requestFocus();
                        email.setError(getString(R.string.sh_error_invalid_email));
                    }
                    else{
                        smoothProgressBar.setVisibility(View.VISIBLE);
                        String firstName, last_name;
                        String[] full_name = user_name.split(" ");
                        firstName = full_name[0];

                        if(full_name.length == 1){
                            last_name = " ";
                        } else {
                            last_name = full_name[full_name.length - 1];
                        }

                        performRegistrationRemoteCall(firstName, last_name, user_mail, user_pass);
                    }
                }
            });

        }

        private void performRegistrationRemoteCall(final String name, final String last, String username, String password){

            if(ServiceUtil.isConnected(getActivity())){
                String deviceID = Settings.Secure.getString(getActivity().getContentResolver(),
                        Settings.Secure.ANDROID_ID);

                RequestHandler.getInstance(getActivity()).registerStoreOwner(new StoreOwner(name, last, username, password, deviceID), getActivity(), new OnRequestCompleteLister() {
                    @Override
                    public void onRequestComplete(ResponseResult response) {

                        if (response != null) {
                            boolean isSuccess = response.isSuccessful();

                            if (isSuccess) {
                                Intent intent = new Intent(getActivity(), com.store.hive.store_owner.MainActivity.class);
                                intent.putExtra(getString(R.string.sh_pref_full_name), name + " " + last);
                                AppConfig.saveAuthState(getActivity(), new RegisteredUser(true, name + " " + last));
                                startActivity(intent);
                                getActivity().finish();

                            } else {
                                if(getView() != null){
                                    AutoCompleteTextView email_textView = (AutoCompleteTextView)getView().findViewById(R.id.user_email);
                                    email_textView.requestFocus();
                                    email_textView.setError(response.getErrorMessage());
                                } else {
                                    Toast.makeText(getActivity(), response.getErrorMessage(), Toast.LENGTH_LONG).show();
                                }

                            }
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.sh_error_default), Toast.LENGTH_LONG).show();
                        }

                        smoothProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onRequestError(){
                        smoothProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), getString(R.string.sh_error_default), Toast.LENGTH_LONG).show();
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
                                RequestHandler.getInstance(getActivity()).authenticateStoreOwner(getActivity(), userName, password, new OnRequestCompleteLister() {
                                    @Override
                                    public void onRequestComplete(ResponseResult response) {
                                        smoothProgressBar.setVisibility(View.GONE);

                                        if (response != null) {
                                            boolean isSuccess = response.isSuccessful();

                                            if(isSuccess){
                                                Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getActivity(), com.store.hive.store_owner.MainActivity.class);
                                                startActivity(intent);
                                                getActivity().finish();

                                            } else {
                                                Log.d(TAG, response.getErrorMessage());

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

                                    @Override
                                    public void onRequestError() {
                                        smoothProgressBar.setVisibility(View.GONE);
                                        Toast.makeText(getActivity(), getString(R.string.sh_error_default), Toast.LENGTH_LONG).show();
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

}
