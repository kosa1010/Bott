package controller;

import model.Car;

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
        car.setNr_seets(Integer.parseInt(information.get("nr_seats")));
        car.setDoor_count(Integer.parseInt(information.get("door_count")));
        car.setColor(information.get("color"));
        car.setPrice_raw(Double.parseDouble(information.get("price_raw")));

        FeatrureController fc = new FeatrureController(information.get("features"));

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
        System.out.println(c.getNr_seets());
        System.out.println(c.getDoor_count());
        System.out.println(c.getColor());
        System.out.println(c.getPrice_raw());

    }

}
