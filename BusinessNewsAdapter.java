package com.khannashrey07gmail.newstrunk.BusinessNews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.khannashrey07gmail.newstrunk.GenericClass.Generic;
import com.khannashrey07gmail.newstrunk.Picasso.PicassoClient;
import com.khannashrey07gmail.newstrunk.R;
import com.google.firebase.database.DataSnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by shrey on 2/26/2017.
 */

public class BusinessNewsAdapter extends RecyclerView.Adapter<BusinessNewsAdapter.BusinessNewsHolder>{
   // BusinessNewsFilter filter;
    TextView datatextview,datetextview;
    TextView headingtextview;
    ImageView imageview;
    ArrayList<Generic> arrayList;
    ArrayList<Generic> businessnewsData=new ArrayList<>();

    Context context;
    Button bookMark;
    DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
    DataSnapshot dataSnapshot;
    public BusinessNewsAdapter(Context ctx, ArrayList<Generic> data) {
        context=ctx;
        businessnewsData=data;
    }
    @Override
    public BusinessNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate( R.layout.layout_business_news,parent,false);
        BusinessNewsHolder holder=new BusinessNewsHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(BusinessNewsHolder holder, int position) {
        datetextview.setText(businessnewsData.get(getItemCount()-position-1).getDate());
        headingtextview.setText(businessnewsData.get(getItemCount()-position-1).getHeading());
        datatextview.setText(businessnewsData.get(getItemCount()-position-1).getNews());
        PicassoClient.loadImage( context,businessnewsData.get(getItemCount()-position-1).getUrl(),imageview);

        //datatextview.setText(filterBusinessnews.get(getItemCount()-position-1).getDate());
    }
    public void setFilter(ArrayList<Generic> filteredList)
    {
        arrayList=new ArrayList<>();
        arrayList.addAll(filteredList);
         notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        int size;
        if(businessnewsData!=null)
        {
            size=businessnewsData.size();
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

    public class BookMark implements View.OnClickListener {

        DatabaseReference businessnewsRef =ref.child("Business News Table");
        Map<String, Object> bookmarkUpdater = new HashMap<String, Object>();
        @Override
        public void onClick(View v)
        {      //businessnewsRef.setValue("bookmark",true);
           bookmarkUpdater.put("bookmark","true");
           businessnewsRef.updateChildren(bookmarkUpdater);
        }
    }
    class BusinessNewsHolder extends RecyclerView.ViewHolder {
        public BusinessNewsHolder(View itemView) {
            super(itemView);
            imageview=(ImageView)itemView.findViewById(R.id.imageview_business);
            datetextview=(TextView)itemView.findViewById(R.id.dateview_business);
            headingtextview=(TextView)itemView.findViewById(R.id.headingview_business);
            datatextview=(TextView)itemView.findViewById(R.id.textview_business);
            //bookMark=(Button)itemView.findViewById(R.id.bookmark_button);
           // bookMark.setOnClickListener(new BookMark());
        }

    }


}
