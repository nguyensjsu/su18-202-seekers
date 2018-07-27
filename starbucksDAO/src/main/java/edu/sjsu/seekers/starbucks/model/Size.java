package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;

@Entity
public class Size {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="Size_Key")
    private Integer sizeKey;

    @Column(name="Size_Name")
    private String sizeName;


    public Integer getSizeKey() {
        return sizeKey;
    }

    public void setSizeKey(Integer sizeKey) {
        this.sizeKey = sizeKey;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    @Override
    public String toString() {
        return "Size{" +
                "sizeKey=" + sizeKey +
                ", sizeName='" + sizeName + '\'' +
                '}';
    }
}


