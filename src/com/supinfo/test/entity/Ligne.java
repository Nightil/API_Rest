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


    @OneToMany(mappedBy = "route")
    private List<Route> routeList;

    public Long getId() {
        return id;
    }



}
