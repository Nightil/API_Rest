package com.supinfo.test.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 02/06/2016.
 */
@Entity
@Table(name = "train", schema = "railcommander", catalog = "")
public class Trains {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "surplus")
    private double surplus;

    @Column(name = "heure_depart")
    private Date heure_depart;

    @Column(name = "nbr_place")
    private int nbr_place;

    @OneToMany(mappedBy = "train")
    private List<Trains> train;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSurplus() {
        return surplus;
    }

    public void setSurplus(double surplus) {
        this.surplus = surplus;
    }

    public Date getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(Date heure_depart) {
        this.heure_depart = heure_depart;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public List<Trains> getTrain() {
        return train;
    }

    public void setTrain(List<Trains> train) {
        this.train = train;
    }
}
