package com.khannashrey07gmail.newstrunk.IndiaNews;


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
public class IndiaNews extends Fragment {
    DatabaseReference ref;
    Generic gen;
    RecyclerView indianews_rv;
    LinearLayoutManager linear_layout;
    IndiaNewsAdapter adapter;
    ArrayList<Generic> list_data=new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBarIndia;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    public IndiaNews() {
        // Required empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         // Inflate the layout for this fragment
        View rootView = inflater.inflate( R.layout.fragment_india_news, container, false );
        connectivityManager=(ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo=connectivityManager.getActiveNetworkInfo();
        swipeRefreshLayout=(SwipeRefreshLayout)rootView.findViewById(R.id.swiper_indianews);
        progressBarIndia=(ProgressBar) rootView.findViewById(R.id.progressbar_indianews);
        progressBarIndia.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.Progressbar), PorterDuff.Mode.SRC_ATOP);
        indianews_rv = (RecyclerView) rootView.findViewById( R.id.india_rv );
        linear_layout=new LinearLayoutManager(getActivity());
        indianews_rv.setHasFixedSize(true);
        indianews_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        progressBarIndia.setVisibility(View.VISIBLE);
        adapter=new IndiaNewsAdapter(getActivity().getApplicationContext(),list_data);
        indianews_rv.setAdapter(adapter);
      //  System.out.println("EXITING oncreateview()");
        indianews_rv.setHasFixedSize(false);
         if(networkInfo!=null && networkInfo.isConnected()) {
        //    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            ref = FirebaseDatabase.getInstance().getReference();
            ref.keepSynced(true);
            ref.child("India News Table").orderByKey().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                indianews_rv.setAdapter(new IndiaNewsAdapter(getActivity().getApplicationContext(), list_data));
                                progressBarIndia.setVisibility(View.INVISIBLE);
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
        }else {
            ref = FirebaseDatabase.getInstance().getReference();
            ref.keepSynced(true);
            ref.child("India News Table").limitToLast(5).orderByKey().addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                indianews_rv.setAdapter(new IndiaNewsAdapter(getActivity().getApplicationContext(), list_data));
                                progressBarIndia.setVisibility(View.INVISIBLE);
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
        return rootView;
    }
    public void onrefreshItems()
    {
        ref=FirebaseDatabase.getInstance().getReference();

        ref.child("India News Table").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot template:dataSnapshot.getChildren()) {
                    for (DataSnapshot child : template.getChildren()) {
                        try {
                            gen = child.getValue( Generic.class );
                            list_data.add( gen );
                           }catch(Exception e)
                        {
                            System.out.println("Exception : "+e.getMessage());
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        } );
        indianews_rv.setAdapter( new IndiaNewsAdapter( getActivity().getApplicationContext(), list_data ) );
        Toast.makeText(getActivity().getApplicationContext(),"Refreshed",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

}


