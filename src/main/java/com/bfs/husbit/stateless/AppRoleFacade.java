/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.AppRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lukman
 */
@Stateless
public class AppRoleFacade extends AbstractFacade<AppRole> {

    @PersistenceContext(unitName = "com.bfs.husbit_Husbit_war_1.0PU")
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