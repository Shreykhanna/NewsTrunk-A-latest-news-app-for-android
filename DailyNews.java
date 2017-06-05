package com.khannashrey07gmail.newstrunk.Model;

import java.util.ArrayList;

/**
 * Created by shrey on 2/18/2017.
 */

public class DailyNews {

        public ArrayList<String> LatestNews()
        {
            ArrayList<String> latestDailyNews=new ArrayList<String>();
            latestDailyNews.add("Arvind Kejriwal on visit to punjab elections.He conducted rally for upcoming punjab elections");
            latestDailyNews.add("India Prime Minister Narendra modi most searchd person on google.Data revealed by google recently");
            latestDailyNews.add("India Blind cricket team wins Blind Cricket world cup.Team defeated pakistan second time");
            return latestDailyNews;

        }
    public ArrayList<String> IndiaNews()
    {
        ArrayList<String> indiaDailyNews=new ArrayList<String>();
        indiaDailyNews.add("Arvind Kejriwal on visit to punjab elections.He conducted rally for upcoming punjab elections");
        indiaDailyNews.add("India Prime Minister Narendra modi most searchd person on google.Data revealed by google recently");
        indiaDailyNews.add("India Blind cricket team wins Blind Cricket world cup.Team defeated pakistan second time");
        indiaDailyNews.add("hello word.");
        return indiaDailyNews;
    }
    public ArrayList<String> PoliticsNews()
    {
        ArrayList<String> politicsDailyNews=new ArrayList<String>();
        politicsDailyNews.add("Arvind Kejriwal on visit to punjab elections.He conducted rally for upcoming punjab elections");
        politicsDailyNews.add("India Prime Minister Narendra modi most searchd person on google.Data revealed by google recently");
        politicsDailyNews.add("India Blind cricket team wins Blind Cricket world cup.Team defeated pakistan second time");
        politicsDailyNews.add("");
        return politicsDailyNews;
    }
    public ArrayList<String> SportsNews()
    {
        ArrayList<String> sportsDailyNews=new ArrayList<String>();
        sportsDailyNews.add("");
        sportsDailyNews.add("");
        sportsDailyNews.add("");
        sportsDailyNews.add("");
        return sportsDailyNews;
    }
    public ArrayList<String> WorldNews()
    {
        ArrayList<String> worldDailyNews=new ArrayList<String>();
        worldDailyNews.add("");
        worldDailyNews.add("");
        worldDailyNews.add("");
        worldDailyNews.add("");
        return worldDailyNews;
    }

}
