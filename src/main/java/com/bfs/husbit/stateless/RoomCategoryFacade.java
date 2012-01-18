/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.RoomCategory;
import com.bfs.husbit.util.qualifier.HusbitDatabase;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author lukman
 */
@Stateless
public class RoomCategoryFacade extends AbstractFacade<RoomCategory> {
    @Inject
    @HusbitDatabase
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomCategoryFacade() {
        super(RoomCategory.class);
    }

    public int remove(Long roomCategoryId) {
        return em.createNamedQuery("deleteRoomCategory").setParameter("rcid", roomCategoryId).executeUpdate();
    }
}
