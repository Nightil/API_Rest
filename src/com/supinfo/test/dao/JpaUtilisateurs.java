package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.ReponseRest.UserReponse;
import com.supinfo.test.entity.Utilisateurs;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * Created by alexa on 02/06/2016.
 */
public class JpaUtilisateurs {
    private EntityManagerFactory entityManagerFactory;

    public JpaUtilisateurs() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    // Methode d'utilisateur existant
    public Boolean isUserExist(String mail) {

        //tester si un utilisateur dans le mail
        //check email valide
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("FROM Utilisateurs mail WHERE mail.email=:mailRetrieve");
        query.setParameter("mailRetrieve", mail);
        if (query.getResultList().size() >= 1 ) {
            return true;
        } else {
            return false;
        }

    }

    public UserReponse testUserLogin(String mail, String mdp){
        //tester si un utilisateur dans le mail
        //on prepare la reponse
        UserReponse userReponse = new UserReponse();

        //requete
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Utilisateurs mail WHERE mail.email=:mailRetrieve");
        query.setParameter("mailRetrieve", mail);
        Utilisateurs result = null;
        try
        {
            result = (Utilisateurs)query.getSingleResult();
            entityManager.close();
            userReponse.setUser(result);
            userReponse.setSuccess(new Success(true,"Vous etes connecté"));

        }catch (Exception e)
        {
            userReponse.setSuccess(new Success(false,"Utilisateur inconnue"));
        }
        return userReponse;
    }

    public UserReponse addUser(String nom, String prenom, String mail, String Mdp, String civilite, String adresse)
    {
        //on prepare la reponse
        UserReponse userReponse = new UserReponse();

        //check si utilisateur existe
        if(isUserExist(mail)){
            userReponse.setSuccess(new Success(false,"L'utilisateur existe deja"));
            return userReponse;
        }
        else
        {
            //ajoute un utilisateur en BDD
            EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
            Utilisateurs Add = new Utilisateurs();
            Add.setNom(nom);
            Add.setPrenom(prenom);
            Add.setEmail(mail);
            Add.setMdp(Mdp);
            Add.setCivilite(civilite);
            Add.setAdresse(adresse);

            EntityTransaction t = em.getTransaction();
            try {
                t.begin();
                em.persist(Add);
                t.commit();
            } finally {
                if (t.isActive()) t.rollback();
                em.close();
            }

            userReponse.setUser(Add);
            userReponse.setSuccess(new Success(true,"vous etes enregistré"));
        }
        return userReponse;
    }
    /*public void deleteUser(long id)
    {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Utilisateurs bdd = entityManager.find(Utilisateurs.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(bdd);
        entityManager.getTransaction().commit();
    }*/

    public void modifUsers(long id, String nom, String prenom, String email, String adresse) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Utilisateurs bdd = entityManager.find(Utilisateurs.class, id);
        entityManager.getTransaction().begin();
        bdd.setEmail(email);
        bdd.setPrenom(prenom);
        bdd.setNom(nom);
        bdd.setAdresse(adresse);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
