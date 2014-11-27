/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Personne;
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
@Path("personne")
@Stateless
public class PersonneRessource extends AbstractFacade<Personne> {

    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public PersonneRessource() {
        super(Personne.class);
    }

    @GET
    @Produces({"application/json"})
    public List<Personne> list() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/json"})
    @Override
    public void create(Personne p) {
        super.create(p);
    }
    
    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    public Personne listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({"application/json"})
    public void update(@PathParam("id") Long id, Personne p){
        Personne pt = super.find(id);
        if(pt != null){
            super.edit(p);
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes("text/plain")
    public void remove(@PathParam("id") Long id) {
        Personne a = super.find(id);
        System.out.println(a.getId());
        super.remove(a);
    }
    
    @GET
    @Path("/count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("/{min}/{max}")
    @Produces({"application/json"})
    public List<Personne> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }
}
