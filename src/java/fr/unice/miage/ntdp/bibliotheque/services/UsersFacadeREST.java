/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.AccountStatus;
import fr.unice.miage.ntdp.bibliotheque.Livre;
import fr.unice.miage.ntdp.bibliotheque.Users;
import fr.unice.miage.ntdp.bibliotheque.client.util.Util;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Bastien
 */
@Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Long id, Users entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Users find(@PathParam("id") Long id) {
        return super.find(id);
    }

    //get sur user / mdp
    @GET
    @Path("/check/{user}/{mdp}")
    @Produces("text/html")
    public String findLivreByCat(@PathParam("user") String user, @PathParam("mdp") String mdp) {

        System.out.println("######### user :   " + user);
        System.out.println("######### mdp :   " + mdp);

        Query q = em.createNamedQuery("findUserByUsernameAndPassword");
        q.setParameter("nomUtilisateur", user);
        q.setParameter("motDePasse", mdp);

        if (q.getResultList().isEmpty()) {
            return "ko";
        } else {
            return "ok";
        }
    }

    //post sur user / mdp
    @POST
    @Path("/check")
    @Produces({"application/json"})
    public Object findLivreByCategory(@FormParam("user") String user, @FormParam("mdp") String mdp) {

        Query q = em.createNamedQuery("findUserByUsernameAndPassword");

        q.setParameter("nomUtilisateur", user);
        q.setParameter("motDePasse", mdp);

        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            Users u = (Users) q.getSingleResult();
            u.setAuthorizationKey(Util.randInt(10000, 99999));
            return u;
        }
    }

    //TEST
//
//    @POST
//    @Path("/test")
//    public Response addUser(
//            @FormParam("name") String name,
//            @FormParam("age") int age) {
//
//        return Response.status(200)
//                .entity("addUser is called, name : " + name + ", age : " + age)
//                .build();
//
//    }
    @GET
    @Override
    @Produces({"application/json"})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @PUT
    @Path("{id}/statutcompte/{statutcompte}")
    @Produces({"application/json"})
    public Users setStatus(@PathParam("statutcompte") Integer statutcompte, @PathParam("id") Long id) {

        Users u = super.find(id);
        //Le statut compte à mettre est soit 1 pour ACTIF ou 2 pour BLOQUE, par défault on a 0 pour ATTENTE 
        u.setStatutCompte(AccountStatus.values()[statutcompte]);

        return u;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
