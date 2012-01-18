/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.AppUser;
import com.bfs.husbit.util.qualifier.HusbitDatabase;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author lukman
 */
@Stateless
public class AppUserFacade extends AbstractFacade<AppUser> {

    @Inject
    @HusbitDatabase
    private EntityManager em;


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    @PostConstruct
    public void initAfterConstrutor() {

    }

    public AppUserFacade() {
        super(AppUser.class);
    }

    @Override
    public List<AppUser> findAll() {
        List<AppUser> allAppUsers =
                (List<AppUser>) em.createNamedQuery("findAllAppUser").setParameter("uzername", "s0ftwar3renegad3").getResultList();
        return allAppUsers;
    }

    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public AppUser findDefaultAppUserForLogin() {
        List<AppUser> defaultAppUser =
                (List<AppUser>) em.createNamedQuery("findDefaultAppUser").setParameter("uzername", "s0ftwar3renegad3").getResultList();
        return defaultAppUser != null && !defaultAppUser.isEmpty()
                ? defaultAppUser.get(0) : null;
    }

    /**
     * Creates a view at start Up
     */
    public void createView() {
        final String sqlView = "create view AUTH as select apu.username as USERNAME , apu.password as PASSWORD, roleName as GROUP_NAME from APPUSER apu join APPROLE apr on apu.approle_id = apr.id ";
        int executeUpdate = em.createNativeQuery(sqlView).executeUpdate();

    }


    public int remove(Long appUserId) {
        return em.createNamedQuery("deleteAppUser").setParameter("idz", appUserId).executeUpdate();
    }


}
