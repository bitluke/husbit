/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.RoomCategory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lukman
 */
@Stateless
public class RoomCategoryFacade extends AbstractFacade<RoomCategory> {
    @PersistenceContext(unitName = "com.bfs.husbit_Husbit_war_1.0PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomCategoryFacade() {
        super(RoomCategory.class);
    }
    
}
