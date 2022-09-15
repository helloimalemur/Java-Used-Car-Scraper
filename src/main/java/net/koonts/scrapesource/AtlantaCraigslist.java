package net.koonts.scrapesource;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AtlantaCraigslist {
    //https://atlanta.craigslist.org/search/cta?query=miata&min_price=&max_price=&auto_make_model=mazda

    String searchURL = "https://atlanta.craigslist.org/search/cta?query=";
    String searchQuery = "";
    ArrayList<String[]> results = new ArrayList<>();

    public AtlantaCraigslist(String searchTerm, String makeModel, int minPrice, int maxPrice, int minYear, int maxYear) throws IOException {
        scrape(searchTerm, makeModel, minPrice, maxPrice, minYear, maxYear);
    }

    public ArrayList<String[]> getResults() {
        return results;
    }

    public void scrape(String searchTerm, String makeModel, int minPrice, int maxPrice, int minYear, int maxYear) throws IOException {
        searchTerm = URLEncoder.encode(searchTerm, "UTF-8");
        searchQuery = searchTerm+"&min_price="+minPrice+"&max_price="+maxPrice+"&auto_make_model="+makeModel;
        searchURL += searchQuery;
        //create web client and set search terms
        //System.out.println(searchURL);

        //get data from page

        //check results are not empty

    }
}
