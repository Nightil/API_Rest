package com.supinfo.test.entity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.supinfo.test.ReponseRest.PossibilityReponse;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by alexa on 02/06/2016.
 */
@Entity
@Table(name= "reservations", schema = "railcommander", catalog = "")
public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    long id;

    @ManyToOne(fetch=FetchType.LAZY)
    Utilisateurs reservationsuser;

    @Column(name = "civilite")
    String civilite;

    @Column(name = "nom")
    String nom;

    @Column(name = "prenom")
    String prenom;

    @Column(name = "email")
    String email;

    @Column(name = "possibilityreponse")
    @Type(type="text")
    String possibilityReponse;

    public PossibilityReponse getPossibilityReponse() {
        Gson gson = new Gson();
        return  gson.fromJson(possibilityReponse, PossibilityReponse.class);
    }


    public void setPossibilityReponse(String possibilityReponse) {
        Gson gson = new Gson();
        gson.fromJson(possibilityReponse, PossibilityReponse.class);
        this.possibilityReponse = possibilityReponse;
    }
    public void setPossibilityReponse(PossibilityReponse possibilityReponse) {
        Gson gson = new Gson();
        this.possibilityReponse =  gson.toJson(possibilityReponse);
    }

    public long getId() {
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

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Utilisateurs getReservationsuser() {
        return reservationsuser;
    }

    public void setReservationsuser(Utilisateurs reservationsuser) {
        this.reservationsuser = reservationsuser;
    }
}
