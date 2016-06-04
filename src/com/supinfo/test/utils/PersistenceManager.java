package com.supinfo.test.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Singleton qui initie la connexion à une base de donné avec Hibernate
 * pour rappel un singleton s'auto-instanci lors de l'appel d'une méthode, ici getEntityManagerFactory
 * */
public class PersistenceManager {
	private PersistenceManager(){
		//
	}
	//
	private static EntityManagerFactory entityManagerFactory=null;
	public static EntityManagerFactory getEntityManagerFactory(){
		if(entityManagerFactory==null){
			entityManagerFactory= Persistence.createEntityManagerFactory("test-persistence"); //nom de la persistence à changer ici
		}
		return entityManagerFactory;
	}
	public static void closeEntityManagerFactory(){
		if(entityManagerFactory!=null && entityManagerFactory.isOpen()){
			entityManagerFactory.close();
		}
	}
}
