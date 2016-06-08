package com.supinfo.test.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexa on 02/06/2016.
 */
@Entity
@Table(name = "utilisateur", schema ="railcommander", catalog = "")
public class Utilisateurs implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_user;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name="mdp")
    private String mdp;

    @Column(name = "civilite")
    public String civilite;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "reservationsuser")
    private transient List<Reservations> reservationsList;

    public List<Reservations> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public List<Reservations> getListReservation() {
        return ListReservation;
    }

    public void setListReservation(List<Reservations> listReservation) {
        ListReservation = listReservation;
    }

    // transcient : non serialisable
    @OneToMany(mappedBy = "MyUser")
    private transient java.util.List<Reservations> ListReservation;

    public Utilisateurs(String email, String mdp, String nom, String prenom, String adresse, java.util.List<Reservations> listReservation) {
        this.email = email;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        ListReservation = listReservation;
    }

    public Utilisateurs() {

    }

}
