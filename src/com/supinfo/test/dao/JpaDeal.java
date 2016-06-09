package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.DealReponse;
import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.entity.Deal;
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
public class JpaDeal {

    private EntityManagerFactory entityManagerFactory;

    public JpaDeal() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }


    public DealReponse alldeal() {
        DealReponse garesReponse = new DealReponse();

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Deal as deal ");

        List<Deal> result =query.getResultList();
        entityManager.close();
        garesReponse.setDealList(result);
        garesReponse.setSuccess(new Success(true,""));

        return garesReponse;
    }


    public void addDeal( String fName , String image)
    {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        Deal toAdd = new Deal();

        toAdd.setTexte(fName);
        toAdd.setImage(image);

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
