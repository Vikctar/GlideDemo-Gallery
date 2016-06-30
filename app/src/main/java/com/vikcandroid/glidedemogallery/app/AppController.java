package com.vikcandroid.glidedemogallery.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * A singleton class where volley's core objects are initialized
 * Created by vikc on 7/1/16.
 */
public class AppController extends Application {
    // TAG
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue requestQueue;

    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }
    
    public static synchronized  AppController getInstance() {
        return appController;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        // set default tag if tag is empty
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> tRequest) {
        tRequest.setTag(TAG);
        getRequestQueue().add(tRequest);
    }

    public void cancelPendingRequests(Object object) {
        if (requestQueue != null) {
            requestQueue.cancelAll(object);
        }
    }
}
