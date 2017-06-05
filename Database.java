package com.khannashrey07gmail.newstrunk.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.khannashrey07gmail.newstrunk.IndiaNews.IndiaNews;

import java.util.ArrayList;


/**
 * Created by shrey on 2/17/2017.
 */


public class Database {
    DatabaseReference ref;
    DailyNews daily_news;
    Database dtbse;
    String tableNames[] = {"Latest News Table", "India News Table", "Politics News Table", "World News Table", "Sports News Table"};
    String index[]={"0"};
    String category[] = {"heading","url","news"};
    String dates[] = {"17 Feb 2017", "18 Feb 2017", "19 Feb 2017", "20 Feb 2017"};
    ArrayList<IndiaNews> read_IndiaNews = new ArrayList<IndiaNews>();


    public void create() {
        dtbse = new Database();
        ref = FirebaseDatabase.getInstance().getReference();
        for (int i = 0; i < tableNames.length; i++) {
            for (int j = 0; j < dates.length; j++) {
                for (int k = 0; k < index.length; k++) {
                    for (int l = 0; l < category.length; l++) {
                        ref.child(tableNames[i].toString()).child(dates[j].toString()).child(index[k]).child(category[l].toString());
                    }
                }
            }
        }
        //dtbse.update();
        }

    public void update() {
        daily_news = new DailyNews();
        ref = FirebaseDatabase.getInstance().getReference();
        ArrayList<String> latestDailyNews = daily_news.LatestNews();
        for (int i = 0; i<latestDailyNews.size();i++) {
            ref.child( "Latest News Table" ).child("17 Feb 2017").setValue(latestDailyNews);

        }
        ArrayList<String> indiaDailyNews = daily_news.IndiaNews();
        for (int i = 0; i < indiaDailyNews.size(); i++) {
            ref.child("India News Table").child("17 Feb 2017").setValue(indiaDailyNews);

        }
        ArrayList<String> politicsDailyNews = daily_news.PoliticsNews();
        for (int i = 0; i < politicsDailyNews.size(); i++) {
            ref.child("Politics News Table").child("17 Feb 2017").setValue(politicsDailyNews);

        }
        ArrayList<String> worldDailyNews = daily_news.WorldNews();
        for (int i = 0; i < worldDailyNews.size(); i++) {
            ref.child("World News Table").child("17 Feb 2017").setValue(worldDailyNews);

        }
        ArrayList<String> sportsDailyNews = daily_news.SportsNews();
        for (int i = 0; i < sportsDailyNews.size(); i++) {
            ref.child("Sports News Table").child("17 Feb 2017").setValue(sportsDailyNews);
        }
    }




}
