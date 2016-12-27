package controller;

import model.Car;
import org.jsoup.nodes.Document;
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
        car.setDriving_gear(information.get("transmission"));
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
        System.out.println(c.getDriving_gear());
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
        Elements all = document.getElementsByClass("offer-params__value");

        boolean bufor;
        Car car = new Car();
        car.setCategory(all.get(1).text());
        car.setMake(all.get(2).text());
        car.setModel(all.get(3).text());
        car.setVersion(all.get(4).text());
        car.setYear(Integer.parseInt(all.get(5).text()));
        car.setMillage(delLetter(all.get(6).text()));
        car.setEngine_capacity((delLetter(all.get(7).text()) / 10));
        car.setFuel_type(all.get(8).text());
        car.setEngine_power(delLetter(all.get(9).text()));
        car.setGearbox(all.get(10).text());
        car.setDriving_gear(all.get(11).text());
        car.setBody_type(all.get(12).text());
        car.setNr_seats(Integer.parseInt(all.get(13).text()));
        car.setDoor_count(Integer.parseInt(all.get(14).text()));
        car.setColor(all.get(15).text());
        if (all.get(16).text().contains("Nie")) {
            bufor = true;
        } else {
            bufor = false;
        }
        car.setMetalic(bufor);
        car.setCountry_of_origin(all.get(17).text());
        if (all.get(19).text().contains("Tak")) {
            bufor = true;
        } else {
            bufor = false;
        }
        car.setNoAccidents(bufor);
        if (all.get(20).text().contains("Tak")) {
            bufor = true;
        } else {
            bufor = false;
        }
        car.setServices_in_ASO(bufor);
        if (all.get(21).text().contains("Używane")) {
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
