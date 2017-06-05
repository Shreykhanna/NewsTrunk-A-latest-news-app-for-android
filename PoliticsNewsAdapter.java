package com.khannashrey07gmail.newstrunk.PoliticsNews;

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

public class PoliticsNewsAdapter extends RecyclerView.Adapter<PoliticsNewsAdapter.PoliticsNewsHolder> {
    ArrayList<Generic> politicsnewsData=new ArrayList<>();
    TextView headingview,dateview;
    TextView dataview;
    ImageView imageview;
    Context context;

    public PoliticsNewsAdapter(Context ctx, ArrayList<Generic> data) {
        context=ctx;
        politicsnewsData=data;
    }

    @Override
    public PoliticsNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate( R.layout.layout_politics_news,parent,false);
        PoliticsNewsHolder holder=new PoliticsNewsHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PoliticsNewsHolder holder, int position) {
        headingview.setText(politicsnewsData.get(getItemCount()-position-1).getHeading());
        dateview.setText(politicsnewsData.get(getItemCount()-position-1).getDate());
        dataview.setText(politicsnewsData.get(getItemCount()-position-1).getNews());
        PicassoClient.loadImage(context,politicsnewsData.get(getItemCount()-position-1).getUrl(),imageview);
    }

    @Override
    public int getItemCount() {
        int size;
        if(politicsnewsData!=null)
        {
            size=politicsnewsData.size();
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

    public class PoliticsNewsHolder extends RecyclerView.ViewHolder {
        public PoliticsNewsHolder(View itemView) {
            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview_politics);
            dateview=(TextView)itemView.findViewById(R.id.dateview_politics);
            headingview=(TextView)itemView.findViewById(R.id.headingview_politics);
            dataview=(TextView)itemView.findViewById(R.id.textview_politics);
        }
    }
}
