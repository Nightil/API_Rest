package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.ReponseRest.VoyageReponse;
import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Route;
import com.supinfo.test.entity.Voyages;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 04/06/2016.
 */
public class JpaVoyages {

    private EntityManagerFactory entityManagerFactory;

    public JpaVoyages() {
        entityManagerFactory = PersistenceManager.getEntityManagerFactory();
    }


    public Boolean find = false;
    public List<Route> getroutesfromgare(Gare dep,List<Route> routeList , EntityManager entityManager ,String Destiantion){
        Query queryy=entityManager.createQuery("  FROM Route rt where rt.Gare_depart=:Gare_depart ");

        queryy.setParameter("Gare_depart",dep);
        for (Route route : (List<Route>) queryy.getResultList()) {
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
            if(find){
              break;
            }
        }
       return  routeList;
    }


    public VoyageReponse search(String gareD, String gareA, Integer heureD, Integer heureR, Integer dateD, Integer dateR) {
        VoyageReponse voyageReponse = new VoyageReponse();

        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query=entityManager.createQuery("FROM Voyages u WHERE u.date_depart >=:date_depart AND u.Gare_depart =:Gare_depart AND  u.Gare_arrivee =:Gare_arrivee");
        query.setParameter("date_depart",dateD);
        query.setParameter("Gare_arrivee",gareA);
        query.setParameter("Gare_depart",gareD);
        List<Voyages> result =query.getResultList();

        JpaGares jpaGares = new JpaGares();
        Gare dep = jpaGares.getid("Gare de Montpellier-Saint-Roch");
         Gare arv =   jpaGares.getid("Gare st jean");

        String queryString = "";
        List<Route> routeList = new ArrayList<Route>();
        find = false;
        List<Route> route = getroutesfromgare(dep,routeList, entityManager, arv.getNom_gare());


     /*   Query queryy=entityManager.createQuery("  FROM Route where rt.Gare_depart=:Gare_depart ");

        List<Route> resultt =query.getResultList();
*/

        entityManager.close();
        if(routeList.isEmpty()){
            voyageReponse.setSuccess(new Success(false,"Aucun Itineraire trouvé"));
        }else {
            voyageReponse.setRouteList(routeList);
            voyageReponse.setVoyages(result);
            voyageReponse.setSuccess(new Success(true,""));
        }

        return voyageReponse;
    }
}
