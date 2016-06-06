package com.supinfo.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
@Entity
@Table(name = "route", schema = "railcommander", catalog = "")
public class Route implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "distance")
    public Integer distance;

    @ManyToOne
    private Gare Gare_depart;

    @ManyToOne
    private  Gare Gare_arrivee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Gare getGare_depart() {
        return Gare_depart;
    }

    public void setGare_depart(Gare gare_depart) {
        Gare_depart = gare_depart;
    }

    public Gare getGare_arrivee() {
        return Gare_arrivee;
    }

    public void setGare_arrivee(Gare gare_arrivee) {
        Gare_arrivee = gare_arrivee;
    }
}
