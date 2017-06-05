package com.khannashrey07gmail.newstrunk.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.khannashrey07gmail.newstrunk.R;
import com.squareup.picasso.Picasso;

/**
 * Created by shrey on 2/22/2017.
 */

public class PicassoClient {
    Context context;
    public static void loadImage(Context ctx,String url, ImageView img)
    {
        if(url!=null && url.length()>0)
        {
            Picasso.with(ctx).load(url).placeholder(R.drawable.placeholder).into(img);
        }else
        {
            Picasso.with(ctx).load(R.drawable.placeholder).into(img);
        }
    }
}
