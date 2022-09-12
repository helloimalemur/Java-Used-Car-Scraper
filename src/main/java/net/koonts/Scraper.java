package net.koonts;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Node;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollection;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import net.sourceforge.htmlunit.cyberneko.HTMLElements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


public class Scraper {
    //https://atlanta.craigslist.org/search/cta?query=miata&min_price=&max_price=&auto_make_model=mazda
    String searchQuery;
    String searchTerm = "";
    int minPrice = 2000;
    int maxPrice = 10000;
    String makeModel = "";

    String searchURL = "https://atlanta.craigslist.org/search/cta?query=";


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


    public void scrape() throws IOException {
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
                if (resultHeading.get(i).getFirstElementChild().getTextContent().contains(searchTerm.substring(1))) {
                    System.out.println(resultHeading.get(i).getFirstElementChild().getTextContent());
                    System.out.println(resultHeading.get(i).getParentNode().getFirstByXPath("//span[@class='result-price']/text()").toString());
                } else {
                    System.out.println("isempty");
                }
            }



        }

    }
}
