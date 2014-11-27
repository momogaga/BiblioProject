/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Pret;
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
@Path("pret")
@Stateless
public class PretRessource extends AbstractFacade<Pret> {

    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public PretRessource() {
        super(Pret.class);
    }

    @GET
    @Produces({"application/json"})
    public List<Pret> list() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/json"})
    @Override
    public void create(Pret p) {
        super.create(p);
    }
    
    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    public Pret listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({"application/json"})
    public void update(@PathParam("id") Long id, Pret p){
        Pret pt = super.find(id);
        if(pt != null){
            super.edit(p);
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes("text/plain")
    public void remove(@PathParam("id") Long id) {
        Pret p = super.find(id);
        System.out.println(p.getId());
        super.remove(p);
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
    public List<Pret> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }
}
