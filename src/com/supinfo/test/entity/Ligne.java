package com.supinfo.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Théo on 07/06/2016.
 */
@Entity
@Table(name = "ligne", schema ="railcommander", catalog = "")
public class Ligne implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    long id;


    @Column(name="nomLigne")
    String nomLigne;


    @OneToMany(mappedBy = "ligne")
    private transient List<Route> routeList;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }
}
