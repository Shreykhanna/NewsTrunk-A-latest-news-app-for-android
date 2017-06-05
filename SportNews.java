package com.khannashrey07gmail.newstrunk.SportsNews;


import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class SportNews extends Fragment {
    ArrayList<Generic> list_data=new ArrayList<>();
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    RecyclerView sportsnews_rv;
    LinearLayoutManager linear_layout;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    DatabaseReference ref;
    Generic gen;
    SportsNewsAdapter adapter;

    public SportNews() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate( R.layout.fragment_sport_news, container, false );
        connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo=connectivityManager.getActiveNetworkInfo();
        progressBar=(ProgressBar)rootview.findViewById(R.id.progressbar_sportsnews);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.Progressbar), PorterDuff.Mode.SRC_ATOP);
        sportsnews_rv=(RecyclerView)rootview.findViewById(R.id.recycler_sportsnews);
        swipeRefreshLayout=(SwipeRefreshLayout)rootview.findViewById(R.id.swiper_sportnews);
        sportsnews_rv.setHasFixedSize(true);
        sportsnews_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new SportsNewsAdapter(getActivity().getApplicationContext(),list_data);
        sportsnews_rv.setAdapter(adapter);

        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);

        if(networkInfo!=null && networkInfo.isConnected()) {
            ref.child("Sports News Table").orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                sportsnews_rv.setAdapter(new SportsNewsAdapter(getActivity().getApplicationContext(), list_data));
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
                    Log.v("TAG", "INSIDE SWIPECREFRSH LAYOUT");
                    onrefreshItems();
                }
            });
        }else{
            ref.child("Sports News Table").orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                sportsnews_rv.setAdapter(new SportsNewsAdapter(getActivity().getApplicationContext(), list_data));
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
                    Log.v("TAG", "INSIDE SWIPECREFRSH LAYOUT");
                    onrefreshItems();
                }
            });
            Toast.makeText(getActivity().getApplicationContext(),"Offline",Toast.LENGTH_SHORT).show();
        }
        return rootview;
        }
    public void onrefreshItems()
    {
        ref= FirebaseDatabase.getInstance().getReference();
        ref.child("Sports News Table").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot template : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : template.getChildren()) {
                        try {
                            gen = child.getValue( Generic.class );
                            list_data.add( gen );
                           }catch (Exception e){
                                                e.getMessage();
                                                }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
        sportsnews_rv.setAdapter( new SportsNewsAdapter( getActivity().getApplicationContext(), list_data ) );
        Toast.makeText(getActivity().getApplicationContext(),"Refreshed",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
    }
