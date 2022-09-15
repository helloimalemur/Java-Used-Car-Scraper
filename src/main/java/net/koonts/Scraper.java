package net.koonts;

import net.koonts.scrapesource.AtlantaCraigslist;
import net.koonts.scrapesource.AutoTrader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Scraper {

    String searchQuery;
    String searchTerm = "";
    int minPrice = 2000;
    int maxPrice = 10000;
    int minYear = 2005;
    int maxYear = 2015;
    String makeModel = "";


    Scraper(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }
    Scraper(String searchTerm, String makeModel) {
        super();
        this.searchTerm = searchTerm;
        this.makeModel = makeModel;
    }
    Scraper(String searchTerm, String makeModel, int minPrice, int maxPrice) {
        super();
        this.searchTerm = searchTerm;
        this.makeModel = makeModel;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    Scraper(String searchTerm, String makeModel, int minPrice, int maxPrice, int minYear, int maxYear) {
        super();
        this.searchTerm = searchTerm;
        this.makeModel = makeModel;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minYear = minYear;
        this.maxYear = maxYear;
    }


    public void asdf() throws IOException {
        AutoTrader autoTrader = new AutoTrader(this.searchTerm, this.makeModel, this.minPrice,this.maxPrice, this.minYear, this.maxYear);
        for (int i=0;i<autoTrader.getResults().size();i++) {
            for (int j=0;j<autoTrader.getResults().get(i).length;j++) {
                //list each entry of Strings[] in each pos of results
                //results[string[]]
                System.out.println(autoTrader.getResults().get(i)[j]);
            }

        }
//        System.out.println("-------");
    }

}
