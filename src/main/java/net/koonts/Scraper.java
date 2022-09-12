package net.koonts;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Node;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollection;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import net.koonts.scrapesource.AtlantaCraigslist;
import net.sourceforge.htmlunit.cyberneko.HTMLElements;

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


    public void asdf() throws IOException {
        AtlantaCraigslist atlantaCraigslist = new AtlantaCraigslist(this.searchTerm, this.makeModel, this.minPrice,this.maxPrice);
        for (int i=0;i<atlantaCraigslist.getResults().size();i++) {
            for (int j=0;j<atlantaCraigslist.getResults().get(i).length;j++) {
                //list each entry of Strings[] in each pos of results
                //results[string[]]
                System.out.println(atlantaCraigslist.getResults().get(i)[j]);
            }

        }

    }

}
