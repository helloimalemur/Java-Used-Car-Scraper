package net.koonts.scrapesource;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class AutoTrader {
//https://www.autotrader.com/cars-for-sale/cars-between-5000-and-12000/mazda/mx-5-miata/acworth-ga-30101?requestId=d&dma=&searchRadius=300&priceRange=&location=&marketExtension=include&startYear=2005&endYear=2015&sellerTypes=d&isNewSearch=true&showAccelerateBanner=false&sortBy=relevance&numRecords=25

    ArrayList<String[]> results = new ArrayList<>();

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
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = webClient.getPage(url);
        //System.out.println(searchURL);

        //get data from page
        List<DomElement> resultHeading = page.getByXPath("//div[@class='display-flex']");
        List<DomText> resultLink = page.getByXPath("//a[@class='result-title']/text()");
        List<DomText> resultPrice = page.getByXPath("//span[@class='first-price']/text()");

        //check results are not empty
        if (!resultPrice.isEmpty()) {

            int size = resultPrice.size();
            int index = 0;
            System.out.println("total heading: "+resultHeading.size());
            System.out.println("total price: "+resultPrice.size());
            //create string array and pack each entry
            //strings[0] = listing title
            //strings[1] = listing price
            for (int i=0;i<resultHeading.size();i++) {
                String[] strings = new String[2];
                if (true) {
                    //resultHeading.get(i).getFirstElementChild().getTextContent().contains(searchTerm.substring(1))
                    //System.out.println(resultHeading.get(i).getFirstElementChild().getTextContent());
                    strings[0] = resultHeading.get(i).getFirstElementChild().getTextContent();
                    //results.add(i,resultHeading.get(i).getFirstElementChild().getTextContent());
                    //System.out.println(resultHeading.get(i).getParentNode().getFirstByXPath("//span[@class='result-price']/text()").toString());
                    strings[1] = resultHeading.get(i).getParentNode().getFirstByXPath("//span[@class='result-price']/text()");
                    //results.add(i,resultHeading.get(i).getParentNode().getFirstByXPath("//span[@class='result-price']/text()").toString());
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
