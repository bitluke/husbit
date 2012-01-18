/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.Attendance;
import com.bfs.husbit.util.qualifier.HusbitDatabase;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author lukman
 */
@Stateless
public class AttendanceFacade extends AbstractFacade<Attendance> {
    @Inject
    @HusbitDatabase
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AttendanceFacade() {
        super(Attendance.class);
    }

}
