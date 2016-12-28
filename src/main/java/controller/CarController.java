package controller;

import model.Car;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

/**
 * Created by kosa1010 on 11.12.16.
 */
public class CarController {
    public Car initializeCar(HashMap<String, String> information) {
        Car car = new Car();
        car.setMake(information.get("make"));
        car.setModel(information.get("model"));
        car.setVersion(information.get("version"));
        car.setYear(Integer.parseInt(information.get("year")));
        car.setMillage(Integer.parseInt(information.get("mileage")));
        car.setEngine_capacity(Integer.parseInt(information.get("engine_capacity")));
        car.setFuel_type(information.get("fuel_type"));
        car.setEngine_power(Integer.parseInt(information.get("engine_power")));
        car.setGearbox(information.get("gearbox"));
        car.setTransmission(information.get("transmission"));
        car.setBody_type(information.get("body_type"));
        car.setNr_seats(Integer.parseInt(information.get("nr_seats")));
        car.setDoor_count(Integer.parseInt(information.get("door_count")));
        car.setColor(information.get("color"));
        car.setPrice_raw(Double.parseDouble(information.get("price_raw")));
        return car;
    }

    public void toString(Car c) {
        System.out.println(c.getMake());
        System.out.println(c.getModel());
        System.out.println(c.getVersion());
        System.out.println(c.getYear());
        System.out.println(c.getMillage());
        System.out.println(c.getEngine_capacity());
        System.out.println(c.getFuel_type());
        System.out.println(c.getEngine_power());
        System.out.println(c.getGearbox());
        System.out.println(c.getTransmission());
        System.out.println(c.getBody_type());
        System.out.println(c.getNr_seats());
        System.out.println(c.getDoor_count());
        System.out.println(c.getColor());
        System.out.println(c.getPrice_raw());

    }

    /**
     * Metoda pobiera informacje o samochodzie
     *
     * @param document
     * @return
     */
    public Car getCar(Document document) {
        HashMap<String, String> paramsList = new HashMap();
        Elements all = document.getElementsByClass("offer-params__list").select("li");
        String key, value;
        for (Element e : all) {
            key = e.getElementsByClass("offer-params__label").text();
            value = e.getElementsByClass("offer-params__value").text();
            System.out.print(key + "\t\t\t");
            System.out.println(value);
            paramsList.put(key, value);
        }

        boolean bufor;
        Car car = new Car();
        car.setCategory(paramsList.get("Kategoria"));//all.get(1).text());
        car.setMake(paramsList.get("Marka"));//all.get(2).text());
        if (paramsList.containsKey("Model"))
            car.setModel(paramsList.get("Model"));
        if (paramsList.containsKey("Wersja"))
            car.setVersion(paramsList.get("Wersja"));
        car.setYear(Integer.parseInt(paramsList.get("Rok produkcji")));
        car.setMillage(delLetter(paramsList.get("Przebieg")));
        car.setEngine_capacity((delLetter(paramsList.get("Pojemność skokowa")) / 10));
        car.setFuel_type(paramsList.get("Rodzaj paliwa"));
        if (paramsList.containsKey("Moc"))
            car.setEngine_power(delLetter(paramsList.get("Moc")));
        car.setGearbox(paramsList.get("Skrzynia biegów"));
        if (paramsList.containsKey("Napęd"))
            car.setTransmission(paramsList.get("Napęd"));
        if (paramsList.containsKey("Typ"))
            car.setBody_type(paramsList.get("Typ"));
        car.setNr_seats(Integer.parseInt(paramsList.get("Liczba miejsc")));
        car.setDoor_count(Integer.parseInt(paramsList.get("Liczba drzwi")));
        car.setColor(paramsList.get("Kolor"));
        if (paramsList.containsKey("Metalik")) {
            if (paramsList.get("Metalik").contains("Tak")) {
                bufor = true;
            } else {
                bufor = false;
            }
            car.setMetalic(bufor);
        }
        if (paramsList.containsKey("Kraj pochodzenia"))
            car.setCountry_of_origin(paramsList.get("Kraj pochodzenia"));
        if (paramsList.containsKey("Bezwypadkowy")) {
            if (paramsList.get("Bezwypadkowy").contains("Tak")) {
                bufor = true;
            } else {
                bufor = false;
            }
            car.setNoAccidents(bufor);
        }
        if (paramsList.containsKey("Serwisowany w ASO")) {
            if (paramsList.get("Serwisowany w ASO").contains("Tak")) {
                bufor = true;
            } else {
                bufor = false;
            }
            car.setServices_in_ASO(bufor);
        }
        if (paramsList.get("Stan").contains("Używane")) {
            bufor = true;
        } else {
            bufor = false;
        }
        car.setUsed(bufor);
        String price = document.getElementsByClass("offer-price__number").get(0).text();
        car.setPrice_raw(Double.parseDouble(String.valueOf(delLetter(price))));
        return car;
    }

    private static int delLetter(String s) {
        String buf = "";
        char[] array = s.toCharArray();
        for (char c :
                array) {
            if (Character.isDigit(c)) {
                buf += c;
            }
        }
        return Integer.parseInt(buf);
    }
}
