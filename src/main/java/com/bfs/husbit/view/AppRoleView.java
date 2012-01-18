/*
 * This Class describes the Roles  in the Application
 * Which are Admin, Receptionist, Accountant, User.
 */
package com.bfs.husbit.view;

import com.bfs.core.BaseSerializable;
import com.bfs.husbit.model.AppRole;
import com.bfs.husbit.stateless.AppRoleFacade;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author lukman
 */
@Named
@ConversationScoped

public class AppRoleView implements BaseSerializable {

    @EJB
    private AppRoleFacade appRoleFacade;
    @Inject
    private AppRole appRole;
    private List<AppRole> appRoleList;

    //-----------------------Basic methods -----------------------------------
    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }


    public List<AppRole> getAppRoleList() {
        appRoleList = appRoleFacade.findAll();
        return appRoleList;
    }

    public void setAppRoleList(List<AppRole> appRoleList) {
        this.appRoleList = appRoleList;
    }

    //----------------------- CRUD operations ---------------------------------
    public String createRole() {
        return null;
    }

    public String editRole() {
        return null;
    }

    //------------------Other Operations ----------------------------

}
