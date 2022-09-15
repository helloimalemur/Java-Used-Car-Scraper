package net.koonts.scrapesource;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class AutoTrader {
//https://www.autotrader.com/cars-for-sale/cars-between-5000-and-12000/mazda/mx-5-miata/acworth-ga-30101?requestId=d&dma=&searchRadius=300&priceRange=&location=&marketExtension=include&startYear=2005&endYear=2015&sellerTypes=d&isNewSearch=true&showAccelerateBanner=false&sortBy=relevance&numRecords=25

    ArrayList<String[]> results = new ArrayList<>();
    String url = "https://www.autotrader.com/cars-for-sale/cars-between-5000-and-12000/mazda/mx-5-miata/acworth-ga-30101?requestId=d&dma=&searchRadius=300&priceRange=&location=&marketExtension=include&startYear=2005&endYear=2015&sellerTypes=d&isNewSearch=true&showAccelerateBanner=false&sortBy=relevance&numRecords=25";
    ArrayList<String> prices = new ArrayList<>();
    int priceIndex = 0;
    ArrayList<String> heading = new ArrayList<>();
    ArrayList<String> location = new ArrayList<>();


    public AutoTrader(String searchTerm, String makeModel, int minPrice, int maxPrice, int minYear, int maxYear) throws IOException {
        scrape(searchTerm, makeModel, minPrice, maxPrice, minYear, maxYear);
    }

    public ArrayList<String[]> getResults() {
        return results;
    }

    public void scrape(String searchTerm, String make, int minPrice, int maxPrice, int minYear, int maxYear) throws IOException {
        searchTerm = URLEncoder.encode(searchTerm, "UTF-8");
        String url = "https://www.autotrader.com/cars-for-sale/cars-between-"+minPrice+"-and-"+maxPrice+"/"+make+"/"+searchTerm+"/acworth-ga-30101?requestId=d&dma=&searchRadius=300&priceRange=&location=&marketExtension=include&startYear="+minYear+"&endYear="+maxYear+"&sellerTypes=d&isNewSearch=true&showAccelerateBanner=false&sortBy=relevance&numRecords=25";
        //create web client and set search terms
        String string = "";
        //setup JSoup connection
        Document page = Jsoup.connect(url)
                .userAgent("Mozilla")
                .timeout(2000)
                .get();

        //get listing body by class
        Elements elements = page.getElementsByClass("inventory-listing-body");
        for (Element element: elements) {//get price for each listing
            prices.add(priceIndex, element.getElementsByClass("first-price").text());
        }
//        for () {}
        System.out.println(prices);
        try {
            //get data from page

        } catch(Exception e) {
            System.out.println(e);
        }


        if (!prices.isEmpty()) {

            int size = prices.size();
            int index = 0;
            System.out.println("total heading: "+heading.size());
            System.out.println("total price: "+prices.size());
            //create string array and pack each entry
//            strings[0] = listing title;
//            strings[1] = listing price;
            for (int i=0;i<prices.size();i++) {
                String[] strings = new String[2];
                if (true) {

                    strings[0] = prices.get(i);

//                    strings[1] = prices.get(i);
                    index++;
                }
                //add each string array to results ArrayList<String>
                results.add(i, strings);

            }
            //remove any null entries
            for (int i=0;i<results.size();i++) {
                if (results.get(i)[0]==null) {
                    results.remove(i);
                }
            }
            //print total matches found
            System.out.println("total matches: "+index);
        }
    }
}
