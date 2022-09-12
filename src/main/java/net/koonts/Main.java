package net.koonts;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scraper scraper = new Scraper("miata");
        scraper.scrape();
        //System.out.println("test");
    }
}