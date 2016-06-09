package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.GaresReponse;
import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.entity.Gare;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.awt.*;
import java.util.List;

/**
 * com.supinfo.test.dao
 * Created by Theo on 04/06/2016 for API_Rest.
 */
public class JpaGares {

    private EntityManagerFactory entityManagerFactory;

    public JpaGares() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }


    public GaresReponse search(String search) {
        GaresReponse garesReponse = new GaresReponse();

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Gare u WHERE u.nom_gare like:nomgare OR u.ville like:nomgare ");
        query.setParameter("nomgare","%"+search+"%");
        List<Gare> result =query.getResultList();
        entityManager.close();
        garesReponse.setGares(result);
        garesReponse.setSuccess(new Success(true,""));

        return garesReponse;
    }

    public Gare get(String search) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Gare u WHERE u.nom_gare =:nomgare  ");
        query.setParameter("nomgare",search);
        Gare result = (Gare) query.getSingleResult();
        entityManager.close();


        return result;
    }
    public Gare getid(Long search) {

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Gare u WHERE u.id =:idgare");
        query.setParameter("idgare",search);
        Gare result = (Gare) query.getSingleResult();
        entityManager.close();

        return result;
    }

    public void addGare( String fName ,String ville, Integer cp,  Point pts)
    {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        Gare toAdd = new Gare();
        toAdd.setGps(pts);
        toAdd.setNom_gare(fName);
        toAdd.setVille(ville);
        toAdd.setCode_postal(cp);

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
