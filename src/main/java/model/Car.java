package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by kosa1010 on 13.10.16.
 */
@Entity
public class Car {
    @Id
    @GeneratedValue
    private long id_car;
    private String category;
    private String make;
    private String model;
    private String version;
    private double price_raw;
    private int year;
    private int milage;
    private int engine_copacity;
    private String fuel_type;
    private int engine_power;
    private String gearbox;
    private String body_type;
    private String driving_gear;
    private String car_description;
    @OneToMany
//    @JoinColumn(name = "car_features")
    private List<Feature> features;
    @OneToMany
    private List<Image> gallery;
    private int nr_seets;
    private int door_count;
    private String color;
    private boolean metalic;
    private String contry_of_origin;
    private Date date_of_first_register;
    private boolean register_in_polish;
    private boolean first_ovner;
    private boolean accidents;
    private boolean services_in_ASO;
    private boolean used;


    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String car_type) {
        this.category = car_type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String mark) {
        this.make = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getPrice_raw() {
        return price_raw;
    }

    public void setPrice_raw(double price) {
        this.price_raw = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year_production) {
        this.year = year_production;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int distance) {
        this.milage = distance;
    }

    public int getEngine_copacity() {
        return engine_copacity;
    }

    public void setEngine_copacity(int copacity) {
        this.engine_copacity = copacity;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public int getEngine_power() {
        return engine_power;
    }

    public void setEngine_power(int power) {
        this.engine_power = power;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getDriving_gear() {
        return driving_gear;
    }

    public void setDriving_gear(String driving_gear) {
        this.driving_gear = driving_gear;
    }

    public String getCar_description() {
        return car_description;
    }

    public void setCar_description(String car_description) {
        this.car_description = car_description;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public int getNr_seets() {
        return nr_seets;
    }

    public void setNr_seets(int num_seets) {
        this.nr_seets = num_seets;
    }

    public int getDoor_count() {
        return door_count;
    }

    public void setDoor_count(int num_doors) {
        this.door_count = num_doors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isMetalic() {
        return metalic;
    }

    public void setMetalic(boolean metalic) {
        this.metalic = metalic;
    }

    public String getContry_of_origin() {
        return contry_of_origin;
    }

    public void setContry_of_origin(String contry_of_origin) {
        this.contry_of_origin = contry_of_origin;
    }

    public Date getDate_of_first_register() {
        return date_of_first_register;
    }

    public void setDate_of_first_register(Date date_of_first_register) {
        this.date_of_first_register = date_of_first_register;
    }

    public boolean isRegister_in_polish() {
        return register_in_polish;
    }

    public void setRegister_in_polish(boolean register_in_polish) {
        this.register_in_polish = register_in_polish;
    }

    public boolean isFirst_ovner() {
        return first_ovner;
    }

    public void setFirst_ovner(boolean first_ovner) {
        this.first_ovner = first_ovner;
    }

    public boolean isAccidents() {
        return accidents;
    }

    public void setAccidents(boolean accidents) {
        this.accidents = accidents;
    }

    public boolean isServices_in_ASO() {
        return services_in_ASO;
    }

    public void setServices_in_ASO(boolean services_in_ASO) {
        this.services_in_ASO = services_in_ASO;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public List<Image> getGallery() {
        return gallery;
    }

    public void setGallery(List<Image> gallery) {
        this.gallery = gallery;
    }
}
