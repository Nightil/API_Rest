package com.supinfo.test.entity;

import javax.persistence.*;
import java.awt.*;

/**
 * Created by alexa on 02/06/2016.
 */
@Entity
@Table(name = "ville", schema = "railcommander", catalog = "")
public class Villes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom;
    private Point gps;
    private int code_postale;
    private int population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Point getGps() {
        return gps;
    }

    public void setGps(Point gps) {
        this.gps = gps;
    }

    public int getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(int code_postale) {
        this.code_postale = code_postale;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
