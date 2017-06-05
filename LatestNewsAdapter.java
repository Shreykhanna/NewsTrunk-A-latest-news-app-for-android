package com.khannashrey07gmail.newstrunk.LatestNews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.khannashrey07gmail.newstrunk.Filter.FilterHelper;
import com.khannashrey07gmail.newstrunk.GenericClass.Generic;
import com.khannashrey07gmail.newstrunk.Picasso.PicassoClient;
import com.khannashrey07gmail.newstrunk.R;

import java.util.ArrayList;

/**
 * Created by shrey on 2/21/2017.
 */

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.LatestNewsHolder> implements Filterable{

    ArrayList<Generic> latestnewsData=new ArrayList<>();
    TextView datatextview,dateview;
    TextView headingtextview;
    ImageView imageview;
    Context context;
    public LatestNewsAdapter(Context ctx,ArrayList<Generic> data) {
        context=ctx;
        latestnewsData=data;
    }
    @Override
    public LatestNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_latest_news,parent,false);
        LatestNewsHolder holder=new LatestNewsHolder(v);
        return holder;
        }
    @Override
    public void onBindViewHolder(LatestNewsHolder holder, int position) {
        System.out.println("Position : "+position);
        headingtextview.setText(latestnewsData.get(getItemCount()-position-1).getHeading());
        dateview.setText(latestnewsData.get(getItemCount()-position-1).getDate());
        datatextview.setText(latestnewsData.get(getItemCount()-position-1).getNews());
        PicassoClient.loadImage(context,latestnewsData.get(getItemCount()-position-1).getUrl(),imageview);
    }
    @Override
    public int getItemCount() {
        int size;
        if(latestnewsData!=null)
        {
            size=latestnewsData.size();
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

    public void setFilter(ArrayList<Generic> finalList)
    {
           latestnewsData=finalList;
    }

    @Override
    public Filter getFilter() {
        return FilterHelper.Filter(latestnewsData,this);

    }
    public class LatestNewsHolder extends RecyclerView.ViewHolder {

        public LatestNewsHolder(View itemView) {
            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview_latest);
            headingtextview=(TextView)itemView.findViewById(R.id.headingview_latest);
            dateview=(TextView)itemView.findViewById(R.id.dateview_latest);
            datatextview=(TextView)itemView.findViewById(R.id.newsview_latest);

        }
    }
}
