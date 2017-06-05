package com.khannashrey07gmail.newstrunk;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by shrey on 26/4/17.
 */

public class NewsTrunk extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
