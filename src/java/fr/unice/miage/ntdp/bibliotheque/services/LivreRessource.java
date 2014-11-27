/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Categorie;
import fr.unice.miage.ntdp.bibliotheque.Livre;
import fr.unice.miage.ntdp.bibliotheque.bean.AbstractFacade;
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
 * @author MoMo
 */
@Path("livre")
@Stateless
public class LivreRessource extends AbstractFacade<Livre> {

    @EJB
    private CategorieRessource categorieRessource;
    
    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
    public LivreRessource() {
        super(Livre.class);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Livre> list() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Override
    public void create(Livre l) {
        super.create(l);
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Livre listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void update(@PathParam("id") Long id, Livre l){
        Livre lt = super.find(id);
        if(lt != null){
            super.edit(l);
        }
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("text/plain")
    public void remove(@PathParam("id") Long id) {
        Livre l = super.find(id);
        System.out.println(l.getId());
        super.remove(l);
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
    public List<Livre> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }
    
    @GET
    @Path("categorie/{id}")
    @Produces({"application/xml", "application/json"})
    public List<Livre> findLivreByCat(@PathParam("{id}") Long id){
        Categorie c = categorieRessource.find(id);
        Query namedQuery = em.createNamedQuery("findByCategory");
        namedQuery.setParameter("categorie",c); 
        return namedQuery.getResultList();
    }
}
