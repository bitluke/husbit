/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view.datamodel;

import com.bfs.husbit.model.AppUser;
import com.bfs.husbit.stateless.AppUserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lukman
 */
public class AppUserDataModel extends ListDataModel<AppUser> implements SelectableDataModel<AppUser> {

    @EJB
    private AppUserFacade appUserFacade;

    public AppUserDataModel(List<AppUser> list) {
        super(list);
    }

    public AppUserDataModel() {
    }

    @Override
    public Object getRowKey(AppUser appUser) {
        return appUser.getUsername().toString();
    }

    @Override
    public AppUser getRowData(String rowKey) {
        return appUserFacade.find(Long.parseLong(rowKey));
    }
}
