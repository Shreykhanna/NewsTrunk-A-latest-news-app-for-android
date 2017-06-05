package com.khannashrey07gmail.newstrunk.PushNotifications;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shrey on 3/1/2017.
 */

public class mySingleton {
    private static mySingleton minstance;
    private static Context context;
    private static RequestQueue requestQueue;
    public mySingleton(Context context)
    {
        this.context=context;
        requestQueue=getRequestQueue();
    }
    public static RequestQueue getRequestQueue() {
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

       public static synchronized mySingleton getMinstance(Context context) {
        if(minstance==null)
            minstance=new mySingleton(context);
          return minstance;
    }
    public <T>void addToRequestQue(Request<T> request)
    {
        getRequestQueue().add(request);
    }


}
