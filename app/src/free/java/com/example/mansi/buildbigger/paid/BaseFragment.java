package com.example.mansi.buildbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mansi.buildbigger.BuildConfig;
import com.example.mansi.buildbigger.EndpointsAsyncTask;
import com.example.mansi.buildbigger.R;
import com.example.mylibraryand.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;


public class BaseFragment extends Fragment {

    private InterstitialAd mInterstitialAd;
    ImageButton imageButton;
    Boolean paid_version;
    ProgressBar spinner;
    RelativeLayout rl_progress;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);




        paid_version = BuildConfig.PAID_VERSION;


        imageButton = (ImageButton) rootView.findViewById(R.id.btn_joke);
        spinner = (ProgressBar) rootView.findViewById(R.id.progressbar);
        rl_progress  = (RelativeLayout) rootView.findViewById(R.id.rl_progress);
        rl_progress.setVisibility(View.GONE);




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new EndpointsAsyncTask.OnCompletionListener() {
                    @Override
                    public void onComplete(ArrayList<String> jokes) {


                        if(jokes != null && jokes.size() > 0) {
                            rl_progress.setVisibility(View.GONE);

                            startActivity(new Intent(getActivity(), JokeActivity.class).putStringArrayListExtra("joke_list", jokes));


                        }else{
                            rl_progress.setVisibility(View.VISIBLE);
                        }

                    }
                });
                endpointsAsyncTask.execute(new android.support.v4.util.Pair<Context, String>(getActivity(), "Mansi"));

                if (paid_version) {


                } else {
                    mInterstitialAd = new InterstitialAd(getActivity());
                    mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

                    final AdRequest adRequestInterstitial = new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .addTestDevice("DF1C69C74E18AD7459F5E0F45E8D0A2B")
                            .build();
                    mInterstitialAd.loadAd(adRequestInterstitial);

                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                            showInterstitial();
                        }

                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            // Code to be executed when an ad request fails.
                        }

                        @Override
                        public void onAdOpened() {
                            // Code to be executed when the ad is displayed.
                        }

                        @Override
                        public void onAdLeftApplication() {
                            // Code to be executed when the user has left the app.
                        }

                        @Override
                        public void onAdClosed() {
                            //  mInterstitialAd.loadAd(adRequestInterstitial);
                            rl_progress.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        });
        return rootView;

    }


    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else{
            Toast.makeText(getContext(),"Ad can't load",Toast.LENGTH_LONG).show();
        }
    }

}
