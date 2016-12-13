package model;

import javax.persistence.*;

/**
 * Created by kosa1010 on 26.11.16.
 */
@Entity
public class Image {
    @Id
    @GeneratedValue
    @Column
    private long id_image;
    @Column
    @Lob
    private byte[] image;

    public long getId_image() {
        return id_image;
    }

    public void setId_image(long id_image) {
        this.id_image = id_image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
