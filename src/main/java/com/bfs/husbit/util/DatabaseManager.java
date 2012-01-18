package com.bfs.husbit.util;

import com.bfs.husbit.util.qualifier.HusbitDatabase;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * User: lukman
 * Date: 1/4/12
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseManager {


    @Produces
    @PersistenceContext(unitName = "husbitPU")
    @HusbitDatabase
    EntityManager husbitEntityManager;

}
