package com.supinfo.test.restapi;

import com.sun.research.ws.wadl.Response;
import com.supinfo.test.ReponseRest.UserReponse;
import com.supinfo.test.dao.JpaUsers;
import com.supinfo.test.dao.JpaUtilisateurs;
import com.supinfo.test.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by Alexa on 04/06/2015.
 */

@Path("/userrest")
public class UserRest extends HttpServlet{

    @Path("/addUser")
    @POST
    public String addUser(@QueryParam("email") String email) //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {
        /*JpaUsers jpaUsers = new JpaUsers();
        jpaUsers.addUser("test@test.com","test","non","test");*/

        JpaUtilisateurs test = new JpaUtilisateurs();
        UserReponse userReponse = test.addUser("recule", "alexandre",email, "azerty", "homme", "234 rue de la libération");


        return userReponse.toJson(); //TODO replace this stub to something useful
    }

    @Path("/loginUser")
    @POST
    public String loginUser(@QueryParam("email") String email, @QueryParam("mdp") String mdp) //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {
        /*JpaUsers jpaUsers = new JpaUsers();
        jpaUsers.addUser("test@test.com","test","non","test");*/

        JpaUtilisateurs test = new JpaUtilisateurs();
        UserReponse userReponse = test.testUserLogin(email,mdp);


        return userReponse.toJson(); //TODO replace this stub to something useful
    }

/*
    @Path("/ModifUser")
    @GET
    public String testRequest2() //!!!! Toujours mettre vos méthode en public sinon lourde perte de temps
    {

        JpaUtilisateurs test2 = new JpaUtilisateurs();

        test2.modifUsers(1, "voisin", "Fred", "fred@supinfo.com", "lieu dit le haut blanc D2020");

       // System.out.println("plop"+ jpaUsers.getUser("test").getFirstName());
        return "rfptlfpotk"; //TODO replace this stub to something useful
    }*/


}
