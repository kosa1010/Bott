package controller;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


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

    public String getDescription(Document document) {
        System.out.println("AdvertisementController.getDescription");
        Elements all = document.getElementsByAttribute("data-text");
        // if (all.get(0).getElementsByClass("bigImage").attr("data-nr") != null)
        String description = all.get(2).html().toString();//text());//getElementsByClass("photo-item").select("img").get(0).attr("src"));
        return description;
    }

}
