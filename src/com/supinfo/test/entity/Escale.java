package com.supinfo.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
@Entity
@Table(name = "escale", schema = "railcommander", catalog = "")
public class Escale implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "Escale_voyage")
    private List<Voyages> voyages;

    @OneToMany(mappedBy = "Escale_gare")
    private List<Gare> gare;

}
