package com.supinfo.test.dao;

import com.supinfo.test.entity.User;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pierre
 * Date: 22/02/14
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class JpaUsers {
    private EntityManagerFactory entityManagerFactory;

    public JpaUsers() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    @SuppressWarnings("finally")
    public User getUser(String user) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM User AS mail WHERE mail.email=:userRetrieve");
        query.setParameter("userRetrieve",user);
        User result = null;
        try
        {
            result =(User)query.getSingleResult();
            entityManager.close();
            return result;
        }finally
        {
            return result;
        }
    }

    public List<User> getUsers() {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM User AS mail");
        List<User> result =query.getResultList();
        entityManager.close();
        return result;
    }

    @SuppressWarnings("finally")
    public User getUserbyId(long id) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM User AS id WHERE id.id=:userRetrieve");
        query.setParameter("userRetrieve",id);
        User result = null;
        try
        {
            result =(User)query.getSingleResult();
            entityManager.close();
            return result;
        }finally
        {
            return result;
        }
    }

    public void modifyUsers(long id, String name, String fName, String email) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        User bdd = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        bdd.setEmail(email);
        bdd.setFirstName(fName);
        bdd.setLastName(name);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void modifyUsers(long id, double fund) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        User bdd = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        bdd.setParticipation(fund);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteUser(long id)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        User bdd = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(bdd);
        entityManager.getTransaction().commit();
    }

    public void addUser(String mail, String fName, String name, String Mdp)
    {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        User toAdd = new User();
        toAdd.setEmail(mail);
        toAdd.setFirstName(fName);
        toAdd.setLastName(name);
		/*MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(Mdp.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        toAdd.setPassword(Mdp);
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