package com.supinfo.test.entity;

import javax.persistence.*;

/**
 * Created by alexa on 02/06/2016.
 */
@Entity
@Table(name= "reservations", schema = "railcommander", catalog = "")
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Utilisateurs MyUser;

    @ManyToOne
    private Voyages MyVoyage;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateurs getMyUser() {
        return MyUser;
    }

    public void setMyUser(Utilisateurs myUser) {
        MyUser = myUser;
    }

    public Voyages getMyVoyage() {
        return MyVoyage;
    }

    public void setMyVoyage(Voyages myVoyage) {
        MyVoyage = myVoyage;
    }
}
