package com.supinfo.test.dao;

import com.supinfo.test.ReponseRest.CorrespondanceReponse;
import com.supinfo.test.ReponseRest.PossibilityReponse;
import com.supinfo.test.ReponseRest.Success;
import com.supinfo.test.ReponseRest.VoyageReponse;
import com.supinfo.test.entity.Gare;
import com.supinfo.test.entity.Ligne;
import com.supinfo.test.entity.Route;
import com.supinfo.test.entity.Trains;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    /*
     * Recupère le nombre de kilometres entre 2 gares de la ligne
      *
      * */
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
    /*
     * Recupère le nombre de kilometres entre le debut de la ligne et une gare de la ligne
      *
      * */
    private int getkmfor(Ligne routeList,  Gare arriv , EntityManager entityManager){
        Query queryy=entityManager.createQuery("FROM Route rt where rt.ligne=:ligne ");

        queryy.setParameter("ligne",routeList);
        int temps = 0;
        for (Route route : (List<Route>) queryy.getResultList()) {
            temps += route.getDistance();
            if( route.getGare_arrivee().getNom_gare().equals(arriv.getNom_gare())){
                break;
            }
        }
        return temps;

    }

    private void gethorairesfromCorrespondanceReponse(ArrayList<CorrespondanceReponse> correspondanceReponses, EntityManager entityManager, Date date , Integer pos) {
        Calendar calendar = Calendar.getInstance();
          calendar.setTime(date);
        boolean comp = false;
        for (CorrespondanceReponse correspondanceReponse : correspondanceReponses) {

            JpaLigne jpaLigne = new JpaLigne();

            Ligne ligne = jpaLigne.get(correspondanceReponse.getLigne());
            int cal = getkmfor(ligne, correspondanceReponse.getGareD() ,   entityManager)*33;
            calendar.add(Calendar.SECOND, -cal);
            Query queryy=entityManager.createQuery("FROM Trains rt where rt.ligne=:ligne and rt.Date_depart >=:dateD  ");
            if(!comp){
                queryy.setFirstResult(pos);
                comp= true;
            }
            queryy.setMaxResults(1);
            queryy.setParameter("ligne",ligne);
            queryy.setParameter("dateD",calendar.getTime());

            Trains trains = (Trains) queryy.getSingleResult();
            correspondanceReponse.setTrain(trains);
            calendar.setTime(trains.getDate_depart());
            calendar.add(Calendar.SECOND, cal);
            correspondanceReponse.setHeureD(calendar.getTime());
            int km = getkmfor(ligne, correspondanceReponse.getGareD() ,correspondanceReponse.getGareA(),   entityManager);
            int call= km *33;
            calendar.add(Calendar.SECOND, call);


            correspondanceReponse.setKm(km);
            correspondanceReponse.setDuree(call);
            correspondanceReponse.setHeureA(calendar.getTime());

        }
    }

    private ArrayList<CorrespondanceReponse> getSimpleroutesfromlist(List<Route> routeList, EntityManager entityManager) {

        ArrayList<CorrespondanceReponse> correspondanceReponses = new ArrayList<CorrespondanceReponse>();
        String tempnimligne =routeList.get(0).getLigne().getNomLigne();

        Gare dep = routeList.get(0).getGare_depart();

        CorrespondanceReponse correspondanceReponse = new CorrespondanceReponse();
        int i = 1;
        for (Route route : routeList) {
            correspondanceReponse.setGareD(dep);
            correspondanceReponse.setLigne(tempnimligne);
            if (i++ == routeList.size() && route.getLigne().getNomLigne().equals(tempnimligne)) {
                correspondanceReponse.setGareA(route.getGare_arrivee());
            }else {
                correspondanceReponse.setGareA(route.getGare_depart());
            }
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

    /*
    *
    *
    * Creer un chemin par arbre pour la ligne
    *
    * */
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


    public VoyageReponse search(Integer gareD, Integer gareA, Date dateD , Date dateR) {
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

        ArrayList<PossibilityReponse> possibilityReponsesarr = new ArrayList<PossibilityReponse>();

        for (int i = 0; i < 5; i++) {
            PossibilityReponse possibilityReponses = new  PossibilityReponse();

            ArrayList<CorrespondanceReponse> correspondanceReponses = new ArrayList<CorrespondanceReponse>();
            correspondanceReponses = getSimpleroutesfromlist(routeList, entityManager);
            gethorairesfromCorrespondanceReponse(correspondanceReponses, entityManager, dateD ,i);
            Integer km = 0 ;
            for (CorrespondanceReponse correspondanceReponse : correspondanceReponses) {
                km+=correspondanceReponse.getKm();
            }
            possibilityReponses.setDepart(correspondanceReponses.get(0).getHeureD());
            possibilityReponses.setArrDate(correspondanceReponses.get(correspondanceReponses.size()-1).getHeureA());

            possibilityReponses.setCorrespondanceReponses(correspondanceReponses);
            possibilityReponses.setDistancetotale(km);
            double surplus = 0.0;
            for (CorrespondanceReponse correspondanceReponse : correspondanceReponses) {
                surplus+=correspondanceReponse.getTrain().getSurplus();
            }
            try {
                Double d = round((possibilityReponses.getDistancetotale()*0.2) +surplus,2);

                possibilityReponses.setPrix(d);
            }catch (Exception e){
                System.out.println(e);
            }

            possibilityReponses.setPos(i);
            possibilityReponsesarr. add(possibilityReponses);
        }



        voyageReponse.setPossibilityReponses(possibilityReponsesarr );



        entityManager.close();


        return voyageReponse;
    }
    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }



}
