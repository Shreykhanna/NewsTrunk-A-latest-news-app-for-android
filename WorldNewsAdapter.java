package com.khannashrey07gmail.newstrunk.WorldNews;

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
 * Created by shrey on 3/8/2017.
 */


public class WorldNewsAdapter extends RecyclerView.Adapter<WorldNewsAdapter.WorldNewsHolder> {
    ArrayList<Generic> worldnewsdata=new ArrayList<>();
    TextView headingview,dateview;
    TextView dataview;
    ImageView imageview;
    Context context;


    public WorldNewsAdapter(Context ctx,ArrayList<Generic> data) {
        context=ctx;
        worldnewsdata=data;

    }
    @Override
    public WorldNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate( R.layout.layout_world_news,parent,false);
        WorldNewsHolder holder=new WorldNewsHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(WorldNewsHolder holder, int position) {
        headingview.setText( worldnewsdata.get(getItemCount()-position-1).getHeading() );
        dateview.setText(worldnewsdata.get(getItemCount()-position-1).getDate());
        dataview.setText( worldnewsdata.get(getItemCount()-position-1).getNews());
        PicassoClient.loadImage(context,worldnewsdata.get(getItemCount()-position-1).getUrl(),imageview);
    }

    @Override
    public int getItemCount() {
        int size;
        if(worldnewsdata!=null)
        {
            size=worldnewsdata.size();
        }
        else
        {
            size=0;
        }
        return size;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class WorldNewsHolder extends RecyclerView.ViewHolder {
        public WorldNewsHolder(View itemView) {
            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview_world);
            dateview=(TextView)itemView.findViewById(R.id.dateview_world);
            headingview=(TextView)itemView.findViewById(R.id.headingview_world);
            dataview=(TextView)itemView.findViewById(R.id.textview_world);

        }
    }
}
