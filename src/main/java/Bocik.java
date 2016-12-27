import controller.AdvertisementController;
import controller.CarController;
import controller.FeatureController;
import controller.ImageController;
import model.Advertisement;
import model.Car;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * Created by kosa1010 on 13.10.16.
 */
public class Bocik {

    public static void main(String[] args) {
        String webPage = null;
        String url = null;
        String carDescription;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my_app");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String[] tabNotices = new String[2];
        tabNotices[0] = "https://www.otomoto.pl/oferta/renault-19-jedyna-taka-stan-kolekcjonerski-65000-z-niemiec-ID6yF3sR.html#a2d6b037dd";
        tabNotices[1] = "https://www.otomoto.pl/oferta/alfa-romeo-159-rok-2006-2-4-265-km-krakow-zamiana-ID6yFK1J.html#fac044b1a9";

        for (int i = 0; i < 2; i++) {
            url = tabNotices[i];
            Connection connect = Jsoup.connect(url).timeout(10 * 1000);
            Document document = null;
            try {
                webPage = getWebPageSource(url);
                document = connect.get();
            } catch (IOException e) {
                System.err.println("zepsuło się");
            }

            carDescription = getInformation(webPage, "targeting = {", '}');
            String[] information = cutTheSpecification(carDescription);

            HashMap<String, String> info = changeInfoOnMap(information);
            CarController cc = new CarController();
            // Car car = cc.initializeCar(info);
            Car c = cc.getCar(document);


            FeatureController fc = new FeatureController();//info.get("features"));
            ArrayList<String> features = fc.getFeatures();

            ImageController ic = new ImageController(webPage);

            c.setGallery(ic.downloadGallery(entityManager, document));
            //c.setFeatures(fc.getFeatureList(entityManager, features));
            c.setFeatures(fc.getFeaturesList(entityManager, document));
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();

            AdvertisementController ac = new AdvertisementController(webPage, url);
            String description = ac.getDescription(document);

            Advertisement adv = new Advertisement(c, description, false, info.get("title"));
            entityManager.getTransaction().begin();
            entityManager.persist(adv);
            entityManager.getTransaction().commit();
        }


        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Buduje string z zakresu od podanego indexu do drugiego podanego indexu
     *
     * @param source
     * @param begin
     * @param length
     * @return
     */
    private static String buildString(char[] source, int begin, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(source[begin]);
            begin++;
        }
        return sb.toString();
    }

    private static int getEndIndex(int start, char[] c, char endChar) {
        int index = start;
        for (int i = start; i < c.length; i++) {
            if (i > start && c[i] == endChar) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static String getInformation(String text, String beginText, char endChar) {
        int start = 0;
        int finish;
        char[] source = text.toCharArray();
        for (int i = 0; i < source.length - 13; i++) {
            String word = buildString(source, i, 13);
            if (word.contains(beginText)) {
                start = i + 13;
            }
        }
        finish = getEndIndex(start, source, endChar);
        return text.substring(start, finish);
    }

    /**
     * Metoda pobiera żródło strony internetowej
     *
     * @param sURL
     * @return
     * @throws IOException
     */
    private static String getWebPageSource(String sURL) throws IOException {
        URL url = new URL(sURL);
        URLConnection urlCon = url.openConnection();
        BufferedReader in;

        if (urlCon.getHeaderField("Content-Encoding") != null
                && urlCon.getHeaderField("Content-Encoding").equals("gzip")) {
            in = new BufferedReader(new InputStreamReader(new GZIPInputStream(
                    urlCon.getInputStream())));
        } else {
            in = new BufferedReader(new InputStreamReader(
                    urlCon.getInputStream()));
        }
        String inputLine;
        StringBuilder sb = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();
        return sb.toString();
    }

    private static String[] cutTheSpecification(String s) {
        System.out.println(s);
        char[] charsSpecification = s.toCharArray();
        int count = 0;
        ArrayList<Integer> indexColon = new ArrayList<Integer>();   //:
        ArrayList<Integer> indexComma = new ArrayList<Integer>();   //,
        for (int i = 0; i < charsSpecification.length; i++) {
            if (charsSpecification[i] == ':') {
                count++;
                indexColon.add(i);
            }
            if (charsSpecification[i] == ',') {
                indexComma.add(i);
            }
        }
        String[] information = new String[count];

        int commaP = s.length() - 1;
        int ii = indexComma.size() - 1;
        int i = indexColon.size() - 1;
        while (i > 0) {
            if (indexColon.get(i) > indexComma.get(ii)) {
                information[i] = s.substring(indexComma.get(ii) + 1, commaP);
                commaP = indexComma.get(ii);
                ii--;
            } else {
                while (indexComma.get(ii) > indexColon.get(i)) {
                    ii--;
                }
                information[i] = s.substring(indexComma.get(ii) + 1, commaP);
                commaP = indexComma.get(ii);
            }
            i--;
        }
        information[0] = s.substring(0, indexComma.get(0));
        return information;
    }

    private static HashMap<String, String> changeInfoOnMap(String[] s) {
        HashMap<String, String> info = new HashMap<String, String>();
        int index;
        int indexchar;
        int indexcharEnd;
        String key;
        String value;
        for (int i = 0; i < s.length - 2; i++) {
            index = s[i].indexOf(':');
            if (s[i].substring(1, index - 1).equals("features")) {
                key = s[i].substring(1, index - 1);
                value = s[i].substring(index + 1);
                info.put(key, value);
            } else {
                key = s[i].substring(1, index - 1);
                indexchar = s[i].substring(index + 1).indexOf('"');
                indexchar += index;
                indexcharEnd = s[i].substring(indexchar + 2).indexOf('"');
                indexcharEnd += indexchar;
                value = s[i].substring(indexchar + 2, indexcharEnd + 2);
                info.put(key, value);
            }
        }
        return info;
    }

}