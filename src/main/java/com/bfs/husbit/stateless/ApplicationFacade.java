/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.husbit.model.RoomCategory;
import com.bfs.husbit.model.embeddable.MonetaryAmount;
import com.bfs.husbit.model.enumz.RoleType;
import com.bfs.husbit.model.embeddable.Name;
import com.bfs.husbit.model.AppRole;
import com.bfs.husbit.model.AppUser;
import com.bfs.core.security.SecurityManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.ejb.Singleton;

import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.management.monitor.Monitor;

import org.jboss.logging.Logger;

import java.math.BigDecimal;

/**
 * @author lukman
 */
@Singleton
@Startup
public class ApplicationFacade {

    @EJB
    private AppUserFacade appUserFacade;
    @EJB
    private AppRoleFacade appRoleFacade;
    @Inject
    private Logger log;

    public ApplicationFacade() {
    }

    @PostConstruct
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void initApplication() {
        //initialise demo user 
        initUser();
        System.out.println("App Initialised");


    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void initUser() {
        RoomCategory rc = new RoomCategory();
        //get default user
        AppUser defaultAppUser = appUserFacade.findDefaultAppUserForLogin();
        //check if default user does not exist.
        if (defaultAppUser == null) {
            // create view for glassFish Authentication.
            appUserFacade.createView();
            //persist a default user for login.
            appUserFacade.create(getDefaultUserProperties());


        }

    }

    private AppUser getDefaultUserProperties() {
        // create user
        AppUser appUser = new AppUser();
        appUser.setPassword(SecurityManager.hashPassword("renegade"));
        appUser.setUsername("s0ftwar3renegad3");
        Name name = new Name();
        name.setFirstName("demo");
        name.setLastName("demo");
        name.setMiddleName("demo");
        appUser.setName(name);

        if (appUser.getApprole() == null) {//for the first time
            //initialise roles
            initRoles();
            //set demo to the admin role
            AppRole aRole = (AppRole) appRoleFacade.findRole(RoleType.ROLE_ADMIN.getRoleName()).get(0);
            appUser.setApprole(aRole);
        }

        return appUser;
    }

    public void initRoles() {
        AppRole role = null;
        for (RoleType roleType : RoleType.values()) {
            role = new AppRole();
            role.setRoleName(roleType.getRoleName());
            role.setRoleDescription(roleType.getDescription());
            appRoleFacade.create(role);
        }

    }
}