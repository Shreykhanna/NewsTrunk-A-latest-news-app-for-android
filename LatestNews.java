package com.khannashrey07gmail.newstrunk.LatestNews;


import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.khannashrey07gmail.newstrunk.GenericClass.Generic;
import com.khannashrey07gmail.newstrunk.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LatestNews extends Fragment{
  ArrayList<Generic> list_data=new ArrayList<>();
  DatabaseReference latestnews_ref;
  RecyclerView latestnews_rv;
  LinearLayoutManager linear_layout;
  SwipeRefreshLayout swipeRefreshLayout;
  ProgressBar progressBar;
  LatestNewsAdapter adapter;
  LatestNews latestnewsobj;
  Generic gen;
  NetworkInfo networkInfo;
  ConnectivityManager connectivityManager;

    public LatestNews() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        latestnewsobj = new LatestNews();
        View rootView = inflater.inflate( R.layout.fragment_latest_news,container,false);
        connectivityManager=(ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo=connectivityManager.getActiveNetworkInfo();
        progressBar=(ProgressBar)rootView.findViewById(R.id.progressbar_latestnews);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.Progressbar), PorterDuff.Mode.SRC_ATOP);
        latestnews_rv = (RecyclerView)rootView.findViewById(R.id.recycler_latestnews);
        swipeRefreshLayout=(SwipeRefreshLayout)rootView.findViewById(R.id.swiper_latestnews);
        latestnews_rv.setHasFixedSize(true);
        latestnews_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new LatestNewsAdapter(getActivity().getApplicationContext(),list_data);
        latestnews_rv.setAdapter(adapter);
        /*if(latestnews_ref==null){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }*/
        latestnews_ref = FirebaseDatabase.getInstance().getReference();
        latestnews_ref.keepSynced(true);
        if(networkInfo!=null && networkInfo.isConnected()) {
            latestnews_ref.child("Latest News Table").orderByKey().limitToLast(10).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                latestnews_rv.setAdapter(new LatestNewsAdapter(getActivity().getApplicationContext(), list_data));
                                progressBar.setVisibility(View.INVISIBLE);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Log.v("TAG", "INSIDE SWIPEREFRESH LAYOUT");
                    onrefreshItems();
                }
            });
        }else{
            latestnews_ref.child("Latest News Table").orderByKey().limitToLast(5).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                latestnews_rv.setAdapter(new LatestNewsAdapter(getActivity().getApplicationContext(), list_data));
                                progressBar.setVisibility(View.INVISIBLE);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Log.v("TAG", "INSIDE SWIPEREFRESH LAYOUT");
                    onrefreshItems();
                }
            });
            Toast.makeText(getActivity().getApplicationContext(),"Offline",Toast.LENGTH_SHORT).show();
        }

    return rootView;
    }
    public void onrefreshItems()
    {
        latestnews_ref = FirebaseDatabase.getInstance().getReference();
        latestnews_ref.child("Latest News Table").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot template : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : template.getChildren()) {
                        try {
                            gen = child.getValue( Generic.class );
                            list_data.add(gen);
                            } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        } );
        latestnews_rv.setAdapter(new LatestNewsAdapter(getActivity().getApplicationContext(), list_data));
        Toast.makeText(getActivity().getApplicationContext(),"Refreshed",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

}
