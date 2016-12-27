package controller;

import model.Car;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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
        car.setMilage(Integer.parseInt(information.get("mileage")));
        car.setEngine_copacity(Integer.parseInt(information.get("engine_capacity")));
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
        System.out.println(c.getMilage());
        System.out.println(c.getEngine_copacity());
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

    public Car getCar(String url) throws IOException {
        Connection connect = Jsoup.connect(url).timeout(10 * 1000);
        Document document = connect.get();
        Elements all = document.getElementsByClass("offer-params__value");

        System.out.println(all.size());
        for (Element e : all) {
            System.out.println(e.text());
        }
//        System.out.println(all.get(0).getElementsByClass("offer-params__value").text());
//        System.out.println(all.get(1).getElementsByClass("offer-params__value").text());
//        System.out.println(all.get(2).getElementsByClass("offer-params__value").text());
//        System.out.println(all.get(3).getElementsByClass("offer-params__value").text());
//        System.out.println(all.get(4).getElementsByClass("offer-params__value").text());
//        System.out.println(all.get(5).getElementsByClass("offer-params__value").text());
//        System.out.println(all.get(6).getElementsByClass("offer-params__value").text());


//        System.out.println(all.get(0).getElementsByClass("offer-params__value").select("a").get(1).text());
//
//        System.out.println(all.get(0).getElementsByClass("offer-params__value").select("a").get(11).text());
//        System.out.println(all.get(0).getElementsByClass("offer-params__value").select("a").get(12).text());
//        System.out.println(all.get(0).getElementsByClass("offer-params__value").select("a").get(13).text());
//        System.out.println(all.get(0).getElementsByClass("offer-params__value").select("a").get(14).text());
        //.select("ul").get(0).select("li").get(1).html());//text());
//        System.out.println(all.get(0).getElementsByClass("offer-params__list").select("ul").get(0).select("li").get(2).text());

//x        bezwypadkowy
//x        używany
//        metalik
//        pierwszy właściciel
//        zarejestrowany w polsce
//x        serwisowany w aso
//
//x        kategoria
//x        kraj pochodzenia
//        data pierwszej rejestracji


        return null;
    }
}
