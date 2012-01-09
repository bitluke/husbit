/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view;

import com.bfs.core.BaseSerializable;
import com.bfs.core.security.SecurityManager;
import com.bfs.husbit.model.AppRole;
import com.bfs.husbit.model.AppUser;
import com.bfs.husbit.resources.Messages;
import com.bfs.husbit.stateless.AppRoleFacade;
import com.bfs.husbit.stateless.AppUserFacade;
import com.bfs.husbit.view.datamodel.AppUserDataModel;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;


import org.jboss.logging.Logger;

/**
 * @author lukman
 */
@Named
@ConversationScoped
public class AppUserView implements BaseSerializable {

    @EJB
    private AppUserFacade appUserFacade;
    @EJB
    private AppRoleFacade appRoleFacade;
    @Inject
    private Logger log;
    @Inject
    private AppUser appUser;
    private AppUser selectedAppUser;
    private List<AppUser> appUsers;
    private AppUserDataModel appUserDataModel;
    private Boolean editMode;


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

    public AppUser getSelectedAppUser() {
        return selectedAppUser;
    }

    public void setSelectedAppUser(AppUser selectedAppUser) {
        this.selectedAppUser = selectedAppUser;
    }

    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

    //---------------CRUD methods ---------------------------------------------

    /**
     * Creates an appUser at DB level
     *
     * @return The next view which is the view.xhtml
     */
    public String createappUser() {

        AppRole appRole = appRoleFacade.find(appUser.getApprole().getId());
        appUser.setApprole(appRole);
        //hash Password.
        appUser.setPassword(SecurityManager.hashPassword(appUser.getPassword()));
        log.infof("About to Create user with username  ", appUser.getUsername());
        appUserFacade.create(appUser);
        log.infof("Created user with username and redirecting", appUser.getUsername());
        return "/user/manage?faces-redirect=true";
    }


    public void setUpEdit() {

    }

    public void removeAppuser() {

    }

    /**
     * Validate the creation of a user The bean style
     */
    public void validateRole(FacesContext context, UIComponent component, Object value) {

    }

}
