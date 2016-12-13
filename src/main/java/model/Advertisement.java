package model;

import javax.persistence.*;

/**
 * Created by kosa1010 on 11.12.16.
 */
@Entity
public class Advertisement {
    @Id
    @GeneratedValue
    long id_ad;
    @OneToOne
    Car car;
    @Column
    String description;
    @Column
    boolean status;
    @Column
    String title;

    public Advertisement(long id_ad, Car car, String description, boolean status, String title) {
        this.id_ad = id_ad;
        this.car = car;
        this.description = description;
        this.status = status;
        this.title = title;
    }

    public Advertisement() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId_ad() {
        return id_ad;
    }

    public void setId_ad(long id_ad) {
        this.id_ad = id_ad;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
