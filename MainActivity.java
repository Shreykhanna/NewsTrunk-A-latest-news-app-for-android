package com.khannashrey07gmail.newstrunk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.khannashrey07gmail.newstrunk.BusinessNews.Business;
import com.khannashrey07gmail.newstrunk.GenericClass.Generic;
import com.khannashrey07gmail.newstrunk.IndiaNews.IndiaNews;
import com.khannashrey07gmail.newstrunk.LatestNews.LatestNews;
import com.khannashrey07gmail.newstrunk.PoliticsNews.PoliticsNews;
import com.khannashrey07gmail.newstrunk.ScienceNews.Science;
import com.khannashrey07gmail.newstrunk.Settings.Settings;
import com.khannashrey07gmail.newstrunk.SportsNews.SportNews;
import com.khannashrey07gmail.newstrunk.WorldNews.WorldNews;




public class MainActivity extends AppCompatActivity implements  OnQueryTextListener{
    TabLayout mytab;
    ViewPager mypager;
    Button search_button;
    SearchView searchView;
    Generic generic=new Generic();
    Bundle bundle=new Bundle();
    boolean ischecked;
    RelativeLayout layout_mainActivity;
    Business businessFragment=new Business();
   /* SensorManager sensorManager;
    Sensor sensor;*/
    double g=0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Utils.onActivityCreateSetTheme(this);
        super.onCreate(savedInstanceState);
        System.out.println("REGISTRATION FIREBASE TOKEN : "+ FirebaseInstanceId.getInstance().getToken());
       /* sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        System.out.println("SENSOR VALUE : "+sensor);*/
        setContentView(R.layout.activity_main);
        layout_mainActivity=(RelativeLayout)findViewById(R.id.activity_main);
        mytab = (TabLayout) findViewById(R.id.mytab);
        mypager = (ViewPager) findViewById(R.id.viewPager);
        mypager.setAdapter(new Pageradapter(getSupportFragmentManager()));
        mytab.setupWithViewPager(mypager);
        mytab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mypager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
   /* @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
   public void onSensorChanged(SensorEvent event) {
        // final double alpha=0.8;
        g = 0.9 * g + 0.1 * event.values[0];
        double x = event.values[0] - g;
        double y = event.values[1] - g;
        double z = event.values[2] - g;
        if (Math.abs(x) > Math.abs(y)) {
            if (x>0) {
                System.out.println("left");
                    }else if (x<0){
                System.out.println("right");
                  }
         }else{
            if(y<0){
                System.out.println("top");
            }if (y>0){
                System.out.println("bottom");
            }
        }
        if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
            System.out.println("center");
        }
    }*/
    /*@Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ischecked=false;
        getMenuInflater().inflate(R.menu.menu_main,menu);
        /*MenuItem checkable=menu.findItem(R.id.button_nightmode);*/
        /*checkable.setChecked(ischecked);*/
      //  MenuItem searchItem = menu.findItem(R.id.search);
        System.out.println("Inside onCreateOpionsMenu");
       // searchView=(SearchView) MenuItemCompat.getActionView(searchItem);
        //searchView.setOnQueryTextListener(this);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_share) {
            openShareMenu();

        }/*else if(id==R.id.button_nightmode){*/
         /*   nightMode(item);*/
        /**/
         return true;
    }
    /*public void nightMode(MenuItem item)*/
    /*{*/
    /*    System.out.println("isChecked value : "+!item.isChecked());*/
    /*    if(!item.isChecked())*/
    /*    {*/
    /*        Utils.changetotheme(this,Utils.Theme_Black);*/
    /*        Toast.makeText(this,"Night Mode On",Toast.LENGTH_SHORT).show();*/
    /*        item.setChecked(ischecked);*/
    /*        System.out.println(item.setChecked(ischecked));*/
    /*    }else{*/
    /*        Utils.changetotheme(this,Utils.Theme_Default);*/
    /*        Toast.makeText(this,"Night Mode Off",Toast.LENGTH_SHORT).show();*/
    /*    }*/
/*
*/

    /*}*/
    public void openShareMenu(){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT,"NewsTrunk");
        String packagename = getApplicationContext().getPackageName();
        String message = "\nTry Latest News App-NewsTrunk\n\n";
        message = message + "https://play.google.com/store/apps/details?id=" + packagename;
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "choose one"));
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        Bundle bundle=new Bundle();
        bundle.putString("filteredText","newText");
        businessFragment.setArguments(bundle);
        return false;
    }
    static {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
           }

    class Pageradapter extends FragmentPagerAdapter{
        String data[]={"Latest News","India","World","Politics","Sports","Business","Science & Technology"};

        public Pageradapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

                if (position == 0) {
                    return new LatestNews();
                } else if (position == 1) {
                    return new IndiaNews();
                } else if (position == 2) {
                    return new WorldNews();
                } else if (position == 3) {
                    return new PoliticsNews();
                } else if (position == 4) {
                    return new SportNews();
                } else if (position == 5) {
                    return new Business();
                } else if (position == 6) {
                    return new Science();
                }

            return null;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data[position];
        }
    }
}
