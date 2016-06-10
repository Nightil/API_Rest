package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.ReservationReponse;
import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Ligne;
import com.supinfo.test.entity.Reservations;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
public class JpaReservations {

    private EntityManagerFactory entityManagerFactory;

    public JpaReservations() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    // Ajout d'une r�servation
    public ReservationReponse addReservation(Integer id_utilisateur, String civilite, String nom, String prenom, String PossibilityReponse, String mail)
    {

        ReservationReponse reservationReponse = new ReservationReponse();
        try {
            EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
            Reservations toAdd = new Reservations();
            if (id_utilisateur != null){
                JpaUtilisateurs jpaUtilisateurs = new JpaUtilisateurs();
                toAdd.setReservationsuser(jpaUtilisateurs.getid(Long.valueOf(id_utilisateur)));
            }
           /* Document document = new Document();

            PdfPTable table = new PdfPTable(3);

            //On créer l'objet cellule.
            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Fusion de chaque première cellule de chaque colonne");
                    cell.setColspan(3);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fusion de 2 cellules de la première colonne")
                    cell.setRowspan(2);
            table.addCell(cell);

            //contenu du tableau.
            table.addCell("Colonne 1; Cellule 1");
            table.addCell("Colonne 1; Cellule 2");
            table.addCell("Colonne 2; Cellule 1");
            table.addCell("Colonne 2; Cellule 2");
            PdfWriter.getInstance(document, new FileOutputStream(chemin));
            document.open();
            document.add(new Paragraph("Reservation"));
            document.add(table);
            document.close();*/

            toAdd.setCivilite(civilite);
            toAdd.setNom(nom);
            toAdd.setEmail(mail);
            toAdd.setPrenom(prenom);
            toAdd.setPossibilityReponse(PossibilityReponse);
            EntityTransaction t = em.getTransaction();
            try {
                t.begin();
                em.persist(toAdd);
                t.commit();
                reservationReponse.setSuccess(new Success(true,""));
                reservationReponse.setReservation(toAdd);
            } finally {
                if (t.isActive()) t.rollback();
                em.close();
            }
        }catch (Exception e){
            reservationReponse.setSuccess(new Success(false,"Une erreur est survenue lors de la reservation"));
        }

        return reservationReponse;
    }

    // Recherche de r�servation par Id de r�servation
    public ReservationReponse getById(Integer id_reservation){

        ReservationReponse reservationReponse = new ReservationReponse();
        try {
            EntityManager entityManager=entityManagerFactory.createEntityManager();
            Query query=entityManager.createQuery("FROM Reservations r WHERE r.id =:id_reservation ");
            query.setParameter("id_reservation",id_reservation);
            query.setMaxResults(1);
            Reservations result = (Reservations) query.getSingleResult();
            entityManager.close();
            reservationReponse.setReservation(result);
            reservationReponse.setSuccess(new Success(true, ""));
        }catch (Exception e){
            reservationReponse.setSuccess(new Success(false, "Id introuvable"));
        }


        return reservationReponse;
    }

    // Recherche de r�servation par Id utilisateur
    public ReservationReponse getByUserId(Integer id_utilsateur){

        ReservationReponse reservationReponse = new ReservationReponse();

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Reservations r WHERE r.id =:id_utilisateur ");
        query.setParameter("id_utilisateur",id_utilsateur);
        query.setMaxResults(1);
        Reservations result = (Reservations) query.getSingleResult();
        entityManager.close();
        reservationReponse.setReservation(result);
        reservationReponse.setSuccess(new Success(true, ""));

        return reservationReponse;
    }
}
