package com.example.mansi.buildbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;


import java.io.IOException;
import java.util.ArrayList;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, ArrayList<String>> {
    private static MyApi myApiService = null;
    private Context context;
    private OnCompletionListener listener;


    public EndpointsAsyncTask(OnCompletionListener listener) {
        this.listener = listener;

    }

    public EndpointsAsyncTask() {

    }


    @Override
    protected final ArrayList<String> doInBackground(Pair<Context, String>... params) {

        if (myApiService == null) {

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            myApiService = builder.build();
        }

        context = params[0].first;


        try {

            return (ArrayList<String>) myApiService.jokes().execute().getJokes();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> jokes) {
        listener.onComplete(jokes);
    }

    public interface OnCompletionListener {
        void onComplete(ArrayList<String> jokes);
    }
}
