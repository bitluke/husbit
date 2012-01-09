/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

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

import org.jboss.logging.Logger;

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
        appUser.setPassword(SecurityManager.hashPassword("demodemo"));
        appUser.setUsername("demo");
        Name name = new Name();
        name.setFirstName("demo");
        name.setLastName("demo");
        name.setMiddleName("demo");
        appUser.setName(name);

        if (appUser.getApprole() == null) {//for the first time
            //initialise roles
            initRoles();
            //set demo to the admin role
            AppRole aRole = (AppRole) appRoleFacade.findRole("ROLE_ADMIN").get(0);
            appUser.setApprole(aRole);
        }
        appUser.setDefaultAppUser(Boolean.TRUE);
        return appUser;
    }

    public void initRoles() {
        AppRole role = new AppRole();
        role.setRoleName("ROLE_ADMIN");
        role.setRoleDescription("Administrator");
        appRoleFacade.create(role);
        role = new AppRole();
        role.setRoleName("ROLE_ACCOUNT");
        role.setRoleDescription("Accountant");
        appRoleFacade.create(role);
        role = new AppRole();
        role.setRoleName("ROLE_RECEPTIONIST");
        role.setRoleDescription("Receptionist");
        appRoleFacade.create(role);
        role = new AppRole();
        role.setRoleName("ROLE_USER");
        role.setRoleDescription("Basic User");
        appRoleFacade.create(role);
    }
}