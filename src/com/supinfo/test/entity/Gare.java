package com.supinfo.test.entity;

import org.dom4j.swing.XMLTableColumnDefinition;

import javax.persistence.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by alexa on 02/06/2016.
 */
@Entity
@Table(name = "gare", schema = "railcommander", catalog = "")
public class Gare {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nom_gare")
    private String nom_gare;

    @Column(name = "gps")
    private Point gps;

    @Column(name = "type_train")
    public String typetrain;

    @OneToMany(mappedBy = "Gare_depart")
    private java.util.List<Voyages> Voyage_Depart;

    @OneToMany(mappedBy = "Gare_arrivee")
    private java.util.List<Voyages> Voyage_arrivee;

    @ManyToOne
    private Escale Escale_gare;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom_gare() {
        return nom_gare;
    }

    public void setNom_gare(String nom_gare) {
        this.nom_gare = nom_gare;
    }

    public Point getGps() {
        return gps;
    }

    public void setGps(Point gps) {
        this.gps = gps;
    }

    public java.util.List<Voyages> getVoyage_Depart() {
        return Voyage_Depart;
    }

    public void setVoyage_Depart(List<Voyages> voyage_Depart) {
        Voyage_Depart = voyage_Depart;
    }

    public List<Voyages> getVoyage_arrivee() {
        return Voyage_arrivee;
    }

    public void setVoyage_arrivee(List<Voyages> voyage_arrivee) {
        Voyage_arrivee = voyage_arrivee;
    }
}

