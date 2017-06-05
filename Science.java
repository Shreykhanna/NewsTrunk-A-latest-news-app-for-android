package com.khannashrey07gmail.newstrunk.ScienceNews;


import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.HandlerThread;
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
import com.khannashrey07gmail.newstrunk.IndiaNews.IndiaNews;
import com.khannashrey07gmail.newstrunk.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Science extends Fragment {
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    DatabaseReference sciencenews_ref;
    IndiaNews obj;
    Generic gen;
    RecyclerView sciencenews_rv;
    LinearLayoutManager linear_layout;
    ScienceNewsAdapter adapter;
    ArrayList<Generic> list_data=new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;


    public Science() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate( R.layout.fragment_science, container, false );
        connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo=connectivityManager.getActiveNetworkInfo();
        progressBar=(ProgressBar)rootView.findViewById(R.id.progressbar_sciencenews);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.Progressbar), PorterDuff.Mode.SRC_ATOP);
        sciencenews_rv = (RecyclerView) rootView.findViewById( R.id.recycler_science );
        swipeRefreshLayout=(SwipeRefreshLayout)rootView.findViewById(R.id.swiper_sciencenews);
        linear_layout=new LinearLayoutManager(getActivity());
       // sciencenews_rv.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        sciencenews_rv.setHasFixedSize(true);
        sciencenews_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new ScienceNewsAdapter(getActivity().getApplicationContext(),list_data);
        sciencenews_rv.setAdapter(adapter);
        System.out.println("EXITING oncreateview()");
        sciencenews_rv.setHasFixedSize( false );

        sciencenews_ref = FirebaseDatabase.getInstance().getReference();
        sciencenews_ref.keepSynced(true);
        if(networkInfo!=null && networkInfo.isConnected()) {
            sciencenews_ref.child("Science News Table").orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            gen = child.getValue(Generic.class);
                            list_data.add(gen);
                            sciencenews_rv.setAdapter(new ScienceNewsAdapter(getActivity().getApplicationContext(), list_data));
                            progressBar.setVisibility(View.INVISIBLE);
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
            sciencenews_ref.child("Science News Table").orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            gen = child.getValue(Generic.class);
                            list_data.add(gen);
                            sciencenews_rv.setAdapter(new ScienceNewsAdapter(getActivity().getApplicationContext(), list_data));
                            progressBar.setVisibility(View.INVISIBLE);
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
        sciencenews_ref= FirebaseDatabase.getInstance().getReference();
        sciencenews_ref.child("Science News Table").orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot template:dataSnapshot.getChildren())
                {
                    for(DataSnapshot child:template.getChildren()){
                        gen=child.getValue(Generic.class);
                        list_data.add(gen);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        } );
        sciencenews_rv.setAdapter(new ScienceNewsAdapter(getActivity().getApplicationContext(),list_data));
        Toast.makeText(getActivity().getApplicationContext(),"Refreshed",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }
}
