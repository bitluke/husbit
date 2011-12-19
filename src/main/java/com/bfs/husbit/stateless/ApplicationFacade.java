/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.stateless;

import com.bfs.core.security.SecurityManager;
import com.bfs.husbit.model.embeddable.Name;
import com.bfs.husbit.model.AppRole;
import com.bfs.husbit.model.AppUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author lukman
 */
@Singleton
@Startup
public class ApplicationFacade {
    
    @EJB
    private AppUserFacade appUserFacade;
    @EJB
    private AppRoleFacade appRoleFacade;
    
    public ApplicationFacade() {
    }
    
    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void initApplication() {
        initRoles();
        //initialise demo user 
        initUser();
        System.out.println("App Initialised");
    }
    
    private void initUser() {
        //get default user
        AppUser defaultAppUser = appUserFacade.findDefaultAppUserForLogin();
        //check if default user does not exist.
        if (defaultAppUser == null) {
            //persist a default user for login.
            appUserFacade.create(getDefaultUserProperties());
        }
        
    }
    
    private AppUser getDefaultUserProperties() {
        // create user 
        AppUser appUser = new AppUser();
        appUser.setPassword(SecurityManager.hashPassword("demo123"));
        appUser.setUsername("demo");
        Name name = new Name();
        name.setFirstName("demo");
        name.setLastName("demo");
        name.setMiddleName("demo");
        appUser.setName(name);
       // List<AppRole> approles = new ArrayList<AppRole>();
        AppRole aRole = (AppRole) appRoleFacade.findRole("admin").get(0);

        if (appUser.getApproles() == null) {//if user is not admin yet.
            appUser.setApprole(aRole);
        }
        appUser.setDefaultAppUser(Boolean.TRUE);
        return appUser;
    }
    
    public void initRoles() {
        List<AppRole> approlez = appRoleFacade.findAll();
        if (approlez == null) {
            AppRole role = new AppRole();
            role.setRoleName("admin");
            role.setRoleDescription("Administrator");
            appRoleFacade.create(role);
            role = new AppRole();
            role.setRoleName("accountant");
            role.setRoleDescription("Accountant");
            appRoleFacade.create(role);
            role = new AppRole();
            role.setRoleName("receptionist");
            role.setRoleDescription("Receptionist");
            appRoleFacade.create(role);
            role = new AppRole();
            role.setRoleName("basicuser");
            role.setRoleDescription("Basic user");
            appRoleFacade.create(role);
        }
    }
}
