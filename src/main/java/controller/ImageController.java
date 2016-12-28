package controller;

import model.Image;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.persistence.EntityManager;
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
    private static ArrayList<String> imageLinks;

    public ImageController(String pageSource) {
        this.pageSource = pageSource;
    }

    public String getPageSource() {
        return pageSource;
    }

    public void setPageSource(String pageSource) {
        this.pageSource = pageSource;
    }

    public static ArrayList<String> getImageLinks(Document document) {
        imageLinks = new ArrayList<String>();
        Elements all = document.getElementsByClass("photo-item");
        int numPhotos = 8;
        if (all.size() < 8)
            numPhotos = all.size();
        for (int i = 0; i < numPhotos; i++) {
            //  if (all.get(0).getElementsByClass("bigImage").attr("data-nr") != null)
            imageLinks.add(all.get(i).select("img").get(0).attr("src"));
        }
        return imageLinks;
    }

    /**
     * Metoda pobiera obrazek ze strony i zamienia na tablicę bajtów
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static byte[] getImageAsByteArray(String url) throws MalformedURLException, IOException {

        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("image.jpg");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

        File image = new File("image.jpg");
        FileInputStream fis = new FileInputStream(image);
        byte[] bFile = new byte[(int) image.length()];
        fis.read(bFile);

        return bFile;
    }

    /**
     * Metoda tworzy galerię zdjęć auta
     *
     * @param em
     * @return
     */
    public static ArrayList<Image> downloadGallery(EntityManager em, Document document) {

        ArrayList<Image> gallery = null;
        try {
            ArrayList<String> imgList = getImageLinks(document);
            gallery = new ArrayList<Image>();
            for (int i = 0; i < imgList.size(); i++) {
                Image img = new Image();
                img.setImage(getImageAsByteArray(imgList.get(i)));
                em.getTransaction().begin();
                em.persist(img);
                em.getTransaction().commit();
                gallery.add(img);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return gallery;
    }
}
