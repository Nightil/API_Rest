package com.supinfo.test.dao;

import com.supinfo.test.entity.Ligne;
import com.supinfo.test.entity.Route;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * com.supinfo.test.dao
 * Created by Theo on 04/06/2016 for API_Rest.
 */
public class JpaLigne {

    private EntityManagerFactory entityManagerFactory;

    public JpaLigne() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }


    public Ligne get( String nomligne) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Ligne u WHERE u.nomLigne =:nomgare  ");
        query.setParameter("nomgare",nomligne);
        Ligne result = (Ligne) query.getSingleResult();
        entityManager.close();


        return result;
    }
    public void addLigne(String nomligne)
    {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();


        Ligne toAdd = new Ligne();
        toAdd.setNomLigne(nomligne);

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
