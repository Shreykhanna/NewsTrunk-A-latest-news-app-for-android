package com.khannashrey07gmail.newstrunk.PushNotifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by shrey on 3/1/2017.
 */

public class InstanceIdService extends FirebaseInstanceIdService {
   // String REG_TOKEN="REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        String token=FirebaseInstanceId.getInstance().getToken();
        Log.v("REG_TOKEN",token);

    }
}
