package controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;


/**
 * Created by kosa1010 on 11.12.16.
 */
public class ImageController {
    private String pageSource;
    private final String URL_IMGS;
    private ArrayList<String> imageLinks;

    public ImageController(String url) {
        URL_IMGS = url;
    }

    public ImageController(String pageSource, String url) {
        this.pageSource = pageSource;
        URL_IMGS = url;
    }

    public String getPageSource() {
        return pageSource;
    }

    public void setPageSource(String pageSource) {
        this.pageSource = pageSource;
    }

    public ArrayList<String> getImageLinks() throws IOException {
        imageLinks = new ArrayList<String>();
        Connection connect = Jsoup.connect(URL_IMGS).timeout(10 * 1000);
        Document document = connect.get();
        Elements all = document.getElementsByClass("om-offer-photos om-offer-photos-slick");
        for (int i = 0; i < 8; i++) {
            if (all.get(0).getElementsByClass("bigImage").attr("data-nr") != null)
                imageLinks.add(all.get(0).getElementsByClass("photo-item").select("img").get(i).attr("src"));
        }
        for (String s : imageLinks) {
            System.out.println(s);
        }
        return imageLinks;
    }

    /**
     * Metoda pobiera obrazek ze strony i zamienia na tablicę bajtów
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public byte[] getImageAsByteArray(String url) throws MalformedURLException, IOException {

        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("image.jpg");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        File image = new File("image.jpg");
        FileInputStream fis = new FileInputStream(image);
        byte[] bFile = new byte[(int) image.length()];
        fis.read(bFile);
        for (byte b : bFile) {
            if (b != 0)
                System.out.println(b);
        }
        return bFile;
    }
}
