
package com.supinfo.test.dao;

import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Ligne;
import com.supinfo.test.entity.Route;
import com.supinfo.test.entity.Trains;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.supinfo.test.dao
 * Created by Theo on 04/06/2016 for API_Rest.
 */
public class JpaTrain {

    private EntityManagerFactory entityManagerFactory;

    public JpaTrain() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }


    public Trains get(Integer train) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Trains u WHERE u.id =:train   ");
        query.setParameter("train",train);
        Trains result = (Trains) query.getSingleResult();
        entityManager.close();


        return result;
    }
    public void addTrain(Date dateDepart, Ligne ligne, Integer places, double surplus)
    {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        Trains toAdd = new Trains();
        toAdd.setLigne(ligne);


        toAdd.setDate_depart(dateDepart);
        toAdd.setNbr_place(places);
        toAdd.setSurplus(surplus);

        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(toAdd);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }


    }




}
