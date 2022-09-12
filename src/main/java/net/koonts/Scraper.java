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
    String searchQuery = "miata";
    String searchURL = "https://atlanta.craigslist.org/search/cta?query=";

    Scraper() {

    }

    public void scrape() throws IOException {
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        searchURL += URLEncoder.encode(searchQuery, "UTF-8");
        HtmlPage page = webClient.getPage(searchURL);


        List<HtmlDivision> divs = page.getByXPath("//div[@class='result-info']");

        if (!divs.isEmpty()) {
            for (HtmlDivision div: divs) {
                HtmlDivision resultprice = (HtmlDivision) div.getFirstByXPath("span[@class='result-price']");
                System.out.println(div);
                //System.out.println(resultprice.getTextContent());
            }
        }

    }
}
