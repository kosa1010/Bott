package controller;

import java.util.ArrayList;

/**
 * Created by kosa1010 on 11.12.16.
 */
public class FeatrureController {

    String featuresInText;
    ArrayList<String> features;

    public FeatrureController(String featuresInText) {
        this.featuresInText = featuresInText;
        cutFeatures();
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    /**
     * metoda tnie łańcuch znaków na aby rozdzielić poszczególne elementy wyposarzenia
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
        for (String s :
                features) {
            System.out.println(s);
        }
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

    public void addFeaturesToDataBase() {
        for (String feature :
                features) {
            System.out.println(feature);
        }
    }
}
