package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kosa1010 on 26.11.16.
 */
@Entity
public class Features {
    @Id
    @GeneratedValue
    private long id_feature;
    @Column(unique = true)
    private String name_feature;

    public Features() {
    }

    public Features(String name_feature) {
        this.name_feature = name_feature;
    }

    public long getId_feature() {
        return id_feature;
    }

    public void setId_feature(long id_feature) {
        this.id_feature = id_feature;
    }

    public String getName_feature() {
        return name_feature;
    }

    public void setName_feature(String name_Feature) {
        this.name_feature = name_Feature;
    }
}
