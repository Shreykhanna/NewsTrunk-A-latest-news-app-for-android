package com.khannashrey07gmail.newstrunk.PushNotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.khannashrey07gmail.newstrunk.GenericClass.Generic;
import com.khannashrey07gmail.newstrunk.MainActivity;
import com.khannashrey07gmail.newstrunk.R;

/**
 * Created by shrey on 3/1/2017.
 */

public class NotificationService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getData().size()>0)
        {
            String title,img_url;
            title=remoteMessage.getData().get("heading");
            img_url=remoteMessage.getData().get("url");
            Intent intent=new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingintent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            Uri sounduri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            final NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle(title);
            builder.setContentIntent(pendingintent);
            builder.setSound(sounduri);
            //builder.setSmallIcon(R.drawable.);
            ImageRequest imageRequest=new ImageRequest(img_url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    builder.setStyle( new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationmanager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notificationmanager.notify(0,builder.build());
                }
            },0,0, null,Bitmap.Config.RGB_565,new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            } );
            mySingleton.getMinstance(this).addToRequestQue(imageRequest);
           // Generic.getGeninstance(this).addTorequest(imageRequest);

        }
        super.onMessageReceived(remoteMessage);
    }
}
