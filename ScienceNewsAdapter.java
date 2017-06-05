package com.khannashrey07gmail.newstrunk.ScienceNews;

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
 * Created by shrey on 2/27/2017.
 */

public class ScienceNewsAdapter extends RecyclerView.Adapter<ScienceNewsAdapter.ScienceNewsHolder> {
    TextView datatextview,datetextview;
    TextView headingtextview;
    ImageView imageview;
    ArrayList<Generic> sciencenewsData=new ArrayList<>();
    Context context;

    public ScienceNewsAdapter(Context ctx, ArrayList<Generic> data) {
        context=ctx;
        sciencenewsData=data;
    }
    @Override
    public ScienceNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate( R.layout.layout_science_news,parent,false);
        ScienceNewsHolder holder=new ScienceNewsHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(ScienceNewsHolder holder, int position) {
        datetextview.setText(sciencenewsData.get(getItemCount()-position-1).getDate());
        headingtextview.setText(sciencenewsData.get(getItemCount()-position-1).getHeading());
        datatextview.setText(sciencenewsData.get(getItemCount()-position-1).getNews());
        PicassoClient.loadImage( context,sciencenewsData.get(getItemCount()-position-1).getUrl(),imageview);

    }
    @Override
    public int getItemCount() {
        int size;
        if(sciencenewsData!=null)
        {
            size=sciencenewsData.size();
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
    public class ScienceNewsHolder extends RecyclerView.ViewHolder {

        public ScienceNewsHolder(View itemView) {
            super( itemView );
            imageview=(ImageView)itemView.findViewById(R.id.imageview_science);
            datetextview=(TextView)itemView.findViewById(R.id.dateview_science);
            headingtextview=(TextView)itemView.findViewById(R.id.headingview_science);
            datatextview=(TextView)itemView.findViewById(R.id.textview_science);
        }

    }

}
