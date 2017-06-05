package com.khannashrey07gmail.newstrunk.BusinessNews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class Business extends Fragment{
    Bundle bundle;
    DatabaseReference ref;
    IndiaNews obj;
    Generic gen;
    RecyclerView businessnews_rv;
    LinearLayoutManager linear_layout;
    BusinessNewsAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    SearchView searchView;
    String searchedItem="Custom text";
  //  Button bookmarkButton;
    ProgressBar progressbar;
    ArrayList<Generic> list_data=new ArrayList<>();
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;

    public Business() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_business, container, false);
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        /*Bundle bundle=getArguments();
        searchedItem = bundle.getString("filteredText");
*/

        progressbar = (ProgressBar) rootView.findViewById(R.id.business_progressbar);
        progressbar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.Progressbar), PorterDuff.Mode.SRC_ATOP);
        businessnews_rv = (RecyclerView) rootView.findViewById(R.id.recycler_business);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiper_businessnews);
        adapter = new BusinessNewsAdapter(getActivity().getApplicationContext(), list_data);
        linear_layout = new LinearLayoutManager(getActivity());
        businessnews_rv.setHasFixedSize(true);
        businessnews_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        businessnews_rv.setAdapter(adapter);

//        bookmarkButton=(Button)rootView.findViewById(R.id.bookmark_button);
//        bookmarkButton.setOnClickListener(new Bookmark());

        businessnews_rv.setHasFixedSize(false);
      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true);


        if (networkInfo != null && networkInfo.isConnected()) {
            ref.child("Business News Table").orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    progressbar.setVisibility(View.VISIBLE);
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                businessnews_rv.setAdapter(new BusinessNewsAdapter(getActivity().getApplicationContext(), list_data));
                                progressbar.setVisibility(View.INVISIBLE);
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
                    Log.v("TAG", "INSIDE SWIPEREFERESH LAYOUT");
                    onrefreshItems();
                }
            });
        } else {
            ref.child("Business News Table").orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    progressbar.setVisibility(View.VISIBLE);
                    for (DataSnapshot template : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : template.getChildren()) {
                            try {
                                gen = child.getValue(Generic.class);
                                list_data.add(gen);
                                businessnews_rv.setAdapter(new BusinessNewsAdapter(getActivity().getApplicationContext(), list_data));
                                progressbar.setVisibility(View.INVISIBLE);
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
                    Log.v("TAG", "INSIDE SWIPEREFERESH LAYOUT");
                    onrefreshItems();
                }
            });
            Toast.makeText(getActivity().getApplicationContext(), "Offline", Toast.LENGTH_SHORT).show();
        }
    return  rootView;
    }

    public void onrefreshItems() {
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Business News Table").orderByKey().addValueEventListener( new ValueEventListener() {
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
        businessnews_rv.setAdapter( new BusinessNewsAdapter( getActivity().getApplicationContext(), list_data ) );
        Toast.makeText(getActivity().getApplicationContext(),"Refreshed",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}

