/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.AppRole;
import com.bfs.husbit.resources.qualifier.HusbitDatabase;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author lukman
 */
@Stateless
public class AppRoleFacade extends AbstractFacade<AppRole> {

    @Inject
    @HusbitDatabase
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppRoleFacade() {
        super(AppRole.class);
    }

    public List<AppRole> findRole(String roleToFind) {
        return em.createNamedQuery("findAppRoleByRoleName").setParameter("rolename", roleToFind).getResultList();
    }

    @Override
    public List<AppRole> findAll() {
        List<AppRole> allAppRoles =
                (List<AppRole>) em.createNamedQuery("findAllAppRole").getResultList();
        return allAppRoles != null && !allAppRoles.isEmpty() ? allAppRoles : null;
    }
}