/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.Room;
import com.bfs.husbit.resources.qualifier.HusbitDatabase;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author lukman
 */
@Stateless
public class RoomFacade extends AbstractFacade<Room> {
    @Inject
    @HusbitDatabase
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomFacade() {
        super(Room.class);
    }

}
