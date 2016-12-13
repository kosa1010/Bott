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
    @Column
    private long id_feature;
    @Column
    private String name_Feature;

    public long getId_feature() {
        return id_feature;
    }

    public void setId_feature(long id_feature) {
        this.id_feature = id_feature;
    }

    public String getName_Feature() {
        return name_Feature;
    }

    public void setName_Feature(String name_Feature) {
        this.name_Feature = name_Feature;
    }
}
