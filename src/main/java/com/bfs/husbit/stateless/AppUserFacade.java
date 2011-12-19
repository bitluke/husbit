/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.AppUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lukman
 */
@Stateless
public class AppUserFacade extends AbstractFacade<AppUser> {

    @PersistenceContext(unitName = "com.bfs.husbit_Husbit_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppUserFacade() {
        super(AppUser.class);
    }

    @Override
    public List<AppUser> findAll() {
        List<AppUser> allAppUsers =
                (List<AppUser>) em.createNamedQuery("findAllAppUser").setParameter("dlt", false).getResultList();
        return allAppUsers;
    }

    public AppUser findDefaultAppUserForLogin() {
        List<AppUser> defaultAppUser =
                (List<AppUser>) em.createNamedQuery("findDefaultAppUser").setParameter("dlt", true).getResultList();
        return defaultAppUser != null && !defaultAppUser.isEmpty()
                ? defaultAppUser.get(0) : null;
    }
}
