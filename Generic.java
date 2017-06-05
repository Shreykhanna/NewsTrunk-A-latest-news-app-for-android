package com.khannashrey07gmail.newstrunk.GenericClass;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shrey on 2/20/2017.
 */

public class Generic {
String heading,news,url,date,bookmarkValue;
    static Context context;

    static Generic geninstance;
    public static RequestQueue requestQueue;

    public Generic()
    {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Generic(String news, String heading, String url, String date) {
        this.news = news;
        this.heading = heading;
        this.url=url;
        this.date=date;
    }
    public Generic(Context context)
    {
        this.context=context;
        requestQueue=getRequestQueue();

    }
    public static RequestQueue getRequestQueue() {
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public static synchronized Generic getGeninstance(Context context) {
        if(geninstance==null)
            geninstance=new Generic(context);
         return geninstance;
    }

    public void addTorequest(Request request)
    {
        getRequestQueue().add(request);
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getBookmarkValue() {
        return bookmarkValue;
    }

    public void setBookmarkValue(String bookmarkValue) {
        this.bookmarkValue = bookmarkValue;
    }
}