package com.supinfo.test.entity;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @Column(name = "Date_depart", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date Date_depart;

    @Column(name = "nbr_place")
    private int nbr_place;

    @ManyToOne
    private Ligne ligne;


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

    public Date getDate_depart() {
        return Date_depart;
    }

    public void setDate_depart(Date date_depart) {
        Date_depart = date_depart;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }
}
