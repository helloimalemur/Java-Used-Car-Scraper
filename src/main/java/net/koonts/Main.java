package net.koonts;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scraper scraper = new Scraper("mx-5-miata", "mazda", 2000, 12000, 2005, 2015);
        scraper.asdf();
        //System.out.println("test");

    }
}