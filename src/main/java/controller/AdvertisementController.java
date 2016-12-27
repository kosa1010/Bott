package controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by kosa1010 on 11.12.16.
 */
public class AdvertisementController {

    private String pageSource;
    private final String URL;


    public AdvertisementController(String pageSource, String URL) {
        this.pageSource = pageSource;
        this.URL = URL;
    }

    public String getDescription() throws IOException {
        System.out.println("AdvertisementController.getDescription");
        Connection connect = Jsoup.connect(URL).timeout(10 * 1000);
        Document document = connect.get();
        Elements all = document.getElementsByAttribute("data-text");
        // if (all.get(0).getElementsByClass("bigImage").attr("data-nr") != null)
        String description = all.get(2).html().toString();//text());//getElementsByClass("photo-item").select("img").get(0).attr("src"));

        //System.out.println(description);

        return description;
    }

}
