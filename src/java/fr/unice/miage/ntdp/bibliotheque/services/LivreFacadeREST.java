/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Categorie;
import fr.unice.miage.ntdp.bibliotheque.Livre;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Bastien
 */
@Stateless
@Path("livre")
public class LivreFacadeREST extends AbstractFacade<Livre> {

    @EJB
    CategorieFacadeREST categorieressource;

    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    public LivreFacadeREST() {
        super(Livre.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Livre entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Long id, Livre entity) {
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
    public Livre find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Livre> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Livre> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("/categorie/{id}")
    @Produces({"application/json"})
    public List<Livre> findLivreByCat(@PathParam("{id}") Long id) {
        Query q = em.createNamedQuery("findLivreByCat");
        Categorie c = categorieressource.find(id);
        q.setParameter("categorie", c);
        return q.getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
