/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view;

import com.bfs.core.BaseSerializable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

import org.jboss.logging.Logger;

/**
 * @author lukman
 */
@Named("app")
@ApplicationScoped
public class Application implements BaseSerializable {

    public Application() {
    }



    @PostConstruct
    public void initApplication() {

        //createDefaultUser();
        System.out.println("Application Singleton created with location");
    }

    /**
     * Moves the user to the patient's module
     *
     * @return a page of list of patients
     */
    public String moveToPatients() {
        return "/patient/patients.xhtml?faces-redirect=true";
    }

    public String logoutUser() {
        log.debugf("About to Logout user {}", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        log.debugf("Logged out user {}", FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return "/home.xhtml?faces-redirect=true";
    }

    /**
     * Moves the user to the records module
     *
     * @return a page of list of Record
     */
    public String moveToRecords() {
        return "/record/record.xhtml?faces-redirect=true";
    }

    /**
     * Moves the user to the admin's module
     *
     * @return a page of list of users
     */
    public String moveToUsers() {
        return "/user/users.xhtml?faces-redirect=true";

    }

//
//     public void createDefaultUser() {
//        System.out.println("Observing Log Event ");
//        PersistenceManager persistenceManager = identitySession.getPersistenceManager();
//        RelationshipManager relationshipManager = identitySession.getRelationshipManager();
//
//        try {
//            User defaultUser = persistenceManager.createUser("test");
//            Group husbitGroup = persistenceManager.createGroup("GROUP", "husbit");
////            Group userGroup = persistenceManager.createGroup("USER","husbit");
//            relationshipManager.associateUser(husbitGroup, defaultUser);
//            RoleManager roleManager = identitySession.getRoleManager();
//            RoleType adminRoleType = roleManager.createRoleType("admin");
//            Role role = roleManager.createRole(adminRoleType, defaultUser, husbitGroup);
//
//        } catch (IdentityException e) {
//            log.error("User Cannot be created ", e);
//        } catch (FeatureNotSupportedException e) {
//            log.error("Role creation is not supported ", e);
//        }
//    }

    /**
     * Moves the user to the admin's module
     *
     * @return a page of list of users
     */
    public String home() {
        return "/home.xhtml?faces-redirect=true";
    }
    
    @Inject
    Logger log;

    public String getStackTrace() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> request = context.getExternalContext().getRequestMap();
        Throwable ex = (Throwable) request.get("javax.servlet.error.exception");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        fillStackTrace(ex, pw);
        return sw.toString();

    }

    private static void fillStackTrace(Throwable t, PrintWriter w) {
        if (t == null) {
            return;
        }
        t.printStackTrace(w);
        if (t instanceof ServletException) {
            Throwable cause = ((ServletException) t).getRootCause();
            if (cause != null) {
                w.println("Root cause:");
                fillStackTrace(cause, w);

            }
        } else if (t instanceof SQLException) {
            Throwable cause = ((SQLException) t).getNextException();
            if (cause != null) {
                w.println("Next exception:");
                fillStackTrace(cause, w);
            }

        } else {
            Throwable cause = t.getCause();
            if (cause != null) {
                w.println("Cause:");
                fillStackTrace(cause, w);

            }

        }

    }
}
