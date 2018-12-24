package com.example.mansi.buildbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class AsyncTaskText {

    private static final String JOKE_TYPE = "random";

    @Test
    public void testVerifyJoke() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        Context context = InstrumentationRegistry.getContext();
        EndpointsAsyncTask testTask = new EndpointsAsyncTask(new EndpointsAsyncTask.OnCompletionListener() {
            @Override
            public void onComplete(ArrayList<String> jokes) {
                assertNotNull(jokes);
                assertTrue(jokes.size() > 0);
                latch.countDown();
            }
        }) {

        };
        //noinspection unchecked
        testTask.execute(new Pair<Context, String>(context, JOKE_TYPE));
        latch.await();
    }
}
