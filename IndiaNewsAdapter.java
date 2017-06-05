package com.khannashrey07gmail.newstrunk.IndiaNews;

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
import java.util.logging.Handler;

/**
 * Created by shrey on 2/19/2017.
 */

public class IndiaNewsAdapter extends RecyclerView.Adapter<IndiaNewsAdapter.MyViewHolder>
{

    private Context mContext;
    TextView datatextview,datetextview;
    TextView headingtextview;
    ImageView imageview;
    ArrayList<Generic> indianewsData=new ArrayList<>();
    Context context;
    Handler handler;


    public IndiaNewsAdapter(Context ctx,ArrayList<Generic> data)
    {
        context=ctx;
        indianewsData=data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate( R.layout.layout_india_news,parent,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }
    @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
        System.out.println("Position "+position);
        datetextview.setText(indianewsData.get(getItemCount()-position-1).getDate());
        headingtextview.setText(indianewsData.get(getItemCount()-position-1).getHeading());
        datatextview.setText(indianewsData.get(getItemCount()-position-1).getNews());
        PicassoClient.loadImage( context,indianewsData.get(getItemCount()-position-1).getUrl(),imageview);
    }

    @Override
    public int getItemCount() {
        int size;
        if(indianewsData!=null)
        {
            size=indianewsData.size();
        }
        else{
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
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview_india);
            datetextview=(TextView)itemView.findViewById(R.id.dateview_india);
            headingtextview=(TextView)itemView.findViewById(R.id.headingview_india);
            datatextview=(TextView)itemView.findViewById(R.id.text_view);
          // System.out.println("Data in mtextview"+mtextview);

        }
    }





}
