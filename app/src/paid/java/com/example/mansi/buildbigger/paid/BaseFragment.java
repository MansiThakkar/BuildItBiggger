package com.example.mansi.buildbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mansi.buildbigger.BuildConfig;
import com.example.mansi.buildbigger.EndpointsAsyncTask;
import com.example.mansi.buildbigger.MainActivity;
import com.example.mansi.buildbigger.R;
import com.example.mylibraryand.JokeActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {

    ImageButton imageButton;
    Boolean paid_version;
    ProgressBar spinner;
    RelativeLayout rl_progress;

    boolean hhhh = BuildConfig.PAID_VERSION;


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

                if(BuildConfig.PAID_VERSION) {

                    Log.d("tetsttststts",""+hhhh);


                    EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new EndpointsAsyncTask.OnCompletionListener() {
                        @Override
                        public void onComplete(ArrayList<String> jokes) {


                            if (jokes != null && jokes.size() > 0) {
                                rl_progress.setVisibility(View.GONE);

                                startActivity(new Intent(getActivity(), JokeActivity.class).putStringArrayListExtra("joke_list", jokes));

                            } else {
                                rl_progress.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                    endpointsAsyncTask.execute(new android.support.v4.util.Pair<Context, String>(getActivity(), "Mansi"));

                }else{
                    Log.d("tetsttststts",""+hhhh);

                    startActivity(new Intent(getActivity(),MainActivity.class));

                }


            }
        });
        return rootView;

    }

}
