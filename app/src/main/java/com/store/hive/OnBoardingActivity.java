package com.store.hive;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.store.hive.custom.viewpagerindicator.CirclePageIndicator;
import com.store.hive.fragments.RegistrationFragment;
import com.store.hive.service.RequestHandler;



public class OnBoardingActivity extends ActionBarActivity {

    @SuppressWarnings("unused")
    private static final String TAG = OnBoardingActivity.class.getName();

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        OnBoardingPagerAdapter mOnBoardingPagerAdapter =
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
            if(i == 2){
                return new RegistrationFragment();
            }

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

            }

            return null;
        }




    }

}
