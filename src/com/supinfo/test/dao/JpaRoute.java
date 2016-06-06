package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.GaresReponse;
import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Route;
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
public class JpaRoute {

    private EntityManagerFactory entityManagerFactory;

    public JpaRoute() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }



    public void addRoute( Gare gareD ,Gare GareA, Integer km )
    {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        Route toAdd = new Route();
        toAdd.setGare_depart(gareD);
        toAdd.setGare_arrivee(GareA);
        toAdd.setDistance(km);

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
