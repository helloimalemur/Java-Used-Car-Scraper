package net.koonts.scrapesource;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AtlantaCraigslist {
    //https://atlanta.craigslist.org/search/cta?query=miata&min_price=&max_price=&auto_make_model=mazda

    String searchURL = "https://atlanta.craigslist.org/search/cta?query=";
    String searchQuery = "";
    ArrayList<String[]> results = new ArrayList<>();

    public AtlantaCraigslist(String searchTerm, String makeModel, int minPrice, int maxPrice) throws IOException {
        scrape(searchTerm, makeModel, minPrice, maxPrice);
    }

    public ArrayList<String[]> getResults() {
        return results;
    }

    public void scrape(String searchTerm, String makeModel, int minPrice, int maxPrice) throws IOException {
        searchTerm = URLEncoder.encode(searchTerm, "UTF-8");
        searchQuery = searchTerm+"&min_price="+minPrice+"&max_price="+maxPrice+"&auto_make_model="+makeModel;
        searchURL += searchQuery;

        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = webClient.getPage(searchURL);
        System.out.println(searchURL);

        List<DomElement> resultHeading = page.getByXPath("//h3[@class='result-heading']");
        List<DomText> resultLink = page.getByXPath("//a[@class='result-title']/text()");
        List<DomText> resultPrice = page.getByXPath("//span[@class='result-price']/text()");

        if (!resultPrice.isEmpty()) {
            int size = resultPrice.size();
            int index = 0;


            for (int i=0;i<resultHeading.size();i++) {
                String[] strings = new String[2];
                if (resultHeading.get(i).getFirstElementChild().getTextContent().contains(searchTerm.substring(1))) {
                    System.out.println(resultHeading.get(i).getFirstElementChild().getTextContent());
                    strings[0] = resultHeading.get(i).getFirstElementChild().getTextContent();
                    //results.add(i,resultHeading.get(i).getFirstElementChild().getTextContent());
                    System.out.println(resultHeading.get(i).getParentNode().getFirstByXPath("//span[@class='result-price']/text()").toString());
                    strings[1] = resultHeading.get(i).getParentNode().getFirstByXPath("//span[@class='result-price']/text()").toString();
                    //results.add(i,resultHeading.get(i).getParentNode().getFirstByXPath("//span[@class='result-price']/text()").toString());

                } else {
                    System.out.println("isempty");
                }
                results.add(i, strings);
            }



        }

    }
}
