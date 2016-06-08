package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.CorrespondanceReponse;
import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.ReponseRest.VoyageReponse;
import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Ligne;
import com.supinfo.test.entity.Route;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
public class JpaVoyages {

    private EntityManagerFactory entityManagerFactory;

    public JpaVoyages() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }

    private int getkmfor(Ligne routeList, Gare dep , Gare arriv , EntityManager entityManager){
        Query queryy=entityManager.createQuery("FROM Route rt where rt.ligne=:ligne ");

        queryy.setParameter("ligne",routeList);
        int temps = 0;
        boolean comp = false;
        for (Route route : (List<Route>) queryy.getResultList()) {
            if(route.getGare_depart().getNom_gare().equals(dep.getNom_gare())){
                comp = true;
            }
            if(comp){
                temps += route.getDistance();
                if( route.getGare_arrivee().getNom_gare().equals(arriv.getNom_gare())){
                    break;
                }
            }

        }
        return temps;

    }


    private ArrayList<CorrespondanceReponse> getlignesfromroute(List<Route> routeList, EntityManager entityManager) {








        ArrayList<CorrespondanceReponse> correspondanceReponses = new ArrayList<CorrespondanceReponse>();
        String tempnimligne =routeList.get(0).getLigne().getNomLigne();

        Gare dep = routeList.get(0).getGare_depart();

        CorrespondanceReponse correspondanceReponse = new CorrespondanceReponse();

        for (Route route : routeList) {
            correspondanceReponse.setGareD(dep);
            correspondanceReponse.setLigne(tempnimligne);
            correspondanceReponse.setGareA(route.getGare_depart());
            if(!route.getLigne().getNomLigne().equals(tempnimligne)){

                correspondanceReponses.add(correspondanceReponse);
                correspondanceReponse = new CorrespondanceReponse();
                dep=  route.getGare_depart();
                tempnimligne = route.getLigne().getNomLigne();
                correspondanceReponse.setLigne(tempnimligne);
                correspondanceReponse.setGareD(dep);
                correspondanceReponse.setLigne(tempnimligne);
                correspondanceReponse.setGareA(route.getGare_arrivee());
            }


            /*
            Query queryy=entityManager.createQuery("  FROM Ligne rt where rt.routeList=:Gare_depart ");

            queryy.setParameter("Gare_depart",route);*/
        }

        correspondanceReponses.add(correspondanceReponse);





        return correspondanceReponses;
    }


    public Boolean find = false;
    public List<Route> getroutesfromgare(Gare dep,List<Route> routeList , EntityManager entityManager ,String Destiantion){
        Query queryy=entityManager.createQuery("  FROM Route rt where rt.Gare_depart=:Gare_depart ");

        queryy.setParameter("Gare_depart",dep);
        for (Route route : (List<Route>) queryy.getResultList()) {
            Boolean conti =false;
            for (Route route1 : routeList) {
                if(route1.getGare_depart().equals(route.getGare_arrivee())){
                    conti = true;
                    break;
                }
            }
            if (conti){
                continue;
            }
            routeList.add(route);
            if(route.getGare_arrivee().getNom_gare().equals(Destiantion)){
                System.out.println("Trajet trouvé");
                find = true;
                return  routeList;
            }else {
                getroutesfromgare(route.getGare_arrivee(),routeList, entityManager , Destiantion);
                if(!find){
                    routeList.remove(route);
                }
            }
            if(find){         break;       }
        }
       return  routeList;
    }


    public VoyageReponse search(Integer gareD, Integer gareA, Integer heureD, Integer heureR, Integer dateD, Integer dateR) {
        VoyageReponse voyageReponse = new VoyageReponse();
        Success succ = new Success(true, "");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        /*
            Query query=entityManager.createQuery("FROM Voyages u WHERE u.date_depart >=:date_depart AND u.Gare_depart =:Gare_depart AND  u.Gare_arrivee =:Gare_arrivee");
            query.setParameter("date_depart",dateD);
            query.setParameter("Gare_arrivee",gareA);
            query.setParameter("Gare_depart",gareD);
            List<Voyages> result =query.getResultList();
        */
        JpaGares jpaGares = new JpaGares();
        Gare dep = null;
        Gare arv = null;
        try {

              dep = jpaGares.getid(Long.valueOf(gareD));
              arv =   jpaGares.getid(Long.valueOf(gareA));
        }catch (Exception e){
            succ.setSuccess(false);
            succ.setMessage("Gare introuvable");
            voyageReponse.setSuccess(succ);
            entityManager.close();

            return voyageReponse;
        }
            List<Route> routeList = new ArrayList<Route>();
            find = false;
            List<Route> route = getroutesfromgare(dep,routeList, entityManager, arv.getNom_gare());

            if(routeList.isEmpty()){
                voyageReponse.setSuccess(new Success(false,"Aucun Itineraire trouvé"));
                entityManager.close();
                return voyageReponse;
            }
                voyageReponse.setRouteList(routeList);
                voyageReponse.setSuccess(succ);

         voyageReponse.setCorrespondanceReponses( getlignesfromroute(routeList, entityManager));



        entityManager.close();


        return voyageReponse;
    }


}
