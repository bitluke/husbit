/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view;

import com.bfs.core.BaseSerializable;
import com.bfs.husbit.model.AppUser;
import com.bfs.husbit.stateless.AppUserFacade;
import com.bfs.husbit.view.datamodel.AppUserDataModel;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.logging.Logger;

/**
 *
 * @author lukman
 */
@Named
@ConversationScoped
public class AppUserView implements BaseSerializable {

    @EJB
    private AppUserFacade appUserFacade;
    @Inject
    private Logger log;
    @Inject
    private AppUser appUser;
    private List<AppUser> appUsers;
    private AppUserDataModel appUserDataModel;
    
    
    //------------- internal bean method -----------------------------------

    public AppUserView() {
    }

    @PostConstruct
    public void checkBeans() {
        log.infof("app User view created ");
        appUserDataModel = new AppUserDataModel(getAppUsers());
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUserDataModel getAppUserDataModel() {
        return appUserDataModel;
    }

    public void setAppUserDataModel(AppUserDataModel appUserDataModel) {
        this.appUserDataModel = appUserDataModel;
    }

    public List<AppUser> getAppUsers() {
        appUsers = appUserFacade.findAll();
        return appUsers;
    }

    public void setAppUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
    //---------------CRUD methods ---------------------------------------------
    /**
     * Creates an appUser at DB level
     * @return The next view which is the view.xhtml
     */
    public String createappUser() {
//        appUser.getRole().setUserName(appUser.getUsername());
//        //hash Password. &amp;includeViewParams=true
//        appUser.setPassString(SecurityManager.hashPassword(appUser.getPassword()));
//        log.infof("About to Create user with username  ", appUser.getUsername());
//        appUserFacade.create(appUser);
//        log.infof("Created user with username and redirecting", appUser.getUsername());
        return "/user/manage?faces-redirect=true";
    }

}
