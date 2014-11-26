/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Categorie;
import fr.unice.miage.ntdp.bibliotheque.bean.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * @author MoMo
 */
@Path("categorie")
@Stateless
public class CategorieRessource extends AbstractFacade<Categorie> {

    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public CategorieRessource() {
        super(Categorie.class);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Categorie> list() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Override
    public void create(Categorie c) {
        super.create(c);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Categorie listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void update(@PathParam("id") Long id, Categorie c){
        Categorie ct = super.find(id);
        if(ct != null){
            super.edit(c);
        }
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("text/plain")
    public void remove(@PathParam("id") Long id) {
        Categorie c = super.find(id);
        System.out.println(c.getId());
        super.remove(c);
    }
    
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("{min}/{max}")
    @Produces({"application/xml", "application/json"})
    public List<Categorie> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }
}
