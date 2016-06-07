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


    public Route get(String gareD , String gareA) {
        JpaGares jpaGares = new JpaGares();

            Gare dep = jpaGares.getid(Long.valueOf(gareD));
            Gare arv =   jpaGares.getid(Long.valueOf(gareA));
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Route u WHERE u.Gare_depart =:nomgare AND u.Gare_arrivee =:nomgare1 ");
        query.setParameter("nomgare",dep);
        query.setParameter("nomgare1",arv);
        Route result = (Route) query.getSingleResult();
        entityManager.close();


        return result;
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
