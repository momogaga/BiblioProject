/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Auteur;
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
@Path("auteur")
@Stateless
public class AuteurRessource extends AbstractFacade<Auteur> {

    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public AuteurRessource() {
        super(Auteur.class);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Auteur> list() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Override
    public void create(Auteur a) {
        super.create(a);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Auteur listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void update(@PathParam("id") Long id, Auteur a){
        Auteur at = super.find(id);
        if(at != null){
            super.edit(a);
        }
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("text/plain")
    public void remove(@PathParam("id") Long id) {
        Auteur a = super.find(id);
        System.out.println(a.getId());
        super.remove(a);
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
    public List<Auteur> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }
}
