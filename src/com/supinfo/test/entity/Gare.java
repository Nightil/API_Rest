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

    @Column(name = "code_postal")
    public Integer code_postal;

    @Column(name = "ville")
    public String ville;



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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(Integer code_postal) {
        this.code_postal = code_postal;
    }

}

