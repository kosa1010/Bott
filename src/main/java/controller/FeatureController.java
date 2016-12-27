package controller;

import model.Feature;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kosa1010 on 11.12.16.
 */
public class FeatureController {

    String featuresInText;
    ArrayList<String> features;

    public FeatureController(String featuresInText) {
        this.featuresInText = featuresInText;
        features = cutFeatures();
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    /**
     * Metoda tnie łańcuch znaków aby rozdzielić poszczególne elementy wyposarzenia
     *
     * @return
     */
    public ArrayList<String> cutFeatures() {
        ArrayList<String> features = new ArrayList<String>();
        char[] charsFeatures = featuresInText.toCharArray();
        ArrayList<Integer> indexComma = new ArrayList<Integer>();
        int index = 0;
        for (int i = 0; i < charsFeatures.length; i++) {
            if (charsFeatures[i] == ',') {
                indexComma.add(i);
            }
        }
        for (int i = 0; i < indexComma.size(); i++) {
            features.add(clearFeature(featuresInText.substring(index, indexComma.get(i))));
            index = indexComma.get(i) + 1;
        }
        features.add(clearFeature(featuresInText.substring(index)));
        return features;
    }

    /**
     * Metoda oczyszcza nazwę elementu wyposarzenia z niepotzrebnych znaków
     *
     * @param s
     * @return
     */
    public String clearFeature(String s) {
        s = s.replace("[", "");
        s = s.replace("]", "");
        return s.replace("\"", "");
    }

    /**
     * Zwraca listę obiektów wyposarzenia samochodu
     *
     * @param entityManager
     * @param features
     * @return
     */
    public List<Feature> getFeatureList(EntityManager entityManager, List<String> features) {
        List<Feature> carFeatures = new ArrayList();
        for (String s : features) {
            Feature feature = new Feature(s);
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            try {
                TypedQuery tq = entityManager.createQuery("select f from Feature f " +
                        "where f.name_feature like :featureName", Feature.class).setParameter("featureName", s);
                List<Feature> featuree = tq.getResultList();
                if (featuree.isEmpty()) {
                    entityManager.persist(feature);
                    carFeatures.add(feature);
                } else {
                    carFeatures.add(featuree.get(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            entityManager.getTransaction().commit();
        }
        return carFeatures;
    }


    /**
     * Zwraca listę obiektów wyposarzenia samochodu
     *
     * @param entityManager
     * @param document
     * @return
     */
    public List<Feature> getFeaturesList(EntityManager entityManager, Document document) {
        List<Feature> features = new ArrayList();
        Elements all = document.getElementsByClass("offer-features__item");

        for (Element element : all) {
            Feature feature = new Feature(element.text());
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            try {
                TypedQuery tq = entityManager.createQuery("select f from Feature f " +
                        "where f.name_feature like :featureName", Feature.class).setParameter("featureName", element.text());
                List<Feature> carFeature = tq.getResultList();
                if (carFeature.isEmpty()) {
                    entityManager.persist(feature);
                    features.add(feature);
                } else {
                    features.add(carFeature.get(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            entityManager.getTransaction().commit();
        }
        return features;
    }
}
