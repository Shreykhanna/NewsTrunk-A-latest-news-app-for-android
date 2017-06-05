package com.khannashrey07gmail.newstrunk.SportsNews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khannashrey07gmail.newstrunk.GenericClass.Generic;
import com.khannashrey07gmail.newstrunk.Picasso.PicassoClient;
import com.khannashrey07gmail.newstrunk.R;

import java.util.ArrayList;

/**
 * Created by shrey on 2/24/2017.
 */

public class SportsNewsAdapter extends RecyclerView.Adapter<SportsNewsAdapter.SportsNewsHolder> {
    ArrayList<Generic> sportsnewsData=new ArrayList<>();
    TextView headingview,dateview;
    TextView dataview;
    ImageView imageview;
    Context context;

    public SportsNewsAdapter(Context ctx,ArrayList<Generic> data) {
        context=ctx;
        sportsnewsData=data;

    }

    @Override
    public SportsNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate( R.layout.layout_sports_news,parent,false);
        SportsNewsHolder holder=new SportsNewsHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(SportsNewsHolder holder, int position) {
        headingview.setText(sportsnewsData.get(getItemCount()-position-1).getHeading() );
        dataview.setText(sportsnewsData.get(getItemCount()-position-1).getNews() );
         dateview.setText(sportsnewsData.get(getItemCount()-position-1).getDate());
        PicassoClient.loadImage(context,sportsnewsData.get(getItemCount()-position-1).getUrl(),imageview);
    }
    @Override
    public int getItemCount() {
        int size;
        if(sportsnewsData!=null)
        {
            size=sportsnewsData.size();
        }
        else
        {
            size=0;
        }
        return size;
    }
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class SportsNewsHolder extends RecyclerView.ViewHolder {
        public SportsNewsHolder(View itemView) {
            super( itemView );
            imageview=(ImageView)itemView.findViewById(R.id.imageview_sports);
            dateview=(TextView)itemView.findViewById(R.id.dateview_sports);
            headingview=(TextView)itemView.findViewById(R.id.headingview_sports);
            dataview=(TextView)itemView.findViewById(R.id.textview_sports);
        }
    }
}
