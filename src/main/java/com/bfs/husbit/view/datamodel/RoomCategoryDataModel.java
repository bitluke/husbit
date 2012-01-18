/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view.datamodel;

import com.bfs.husbit.model.RoomCategory;
import com.bfs.husbit.stateless.RoomCategoryFacade;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * @author lukman
 */
public class RoomCategoryDataModel extends ListDataModel<RoomCategory> implements SelectableDataModel<RoomCategory> {

    @EJB
    private RoomCategoryFacade roomCategoryFacade;

    public RoomCategoryDataModel(List<RoomCategory> list) {
        super(list);
    }

    public RoomCategoryDataModel() {
    }

    @Override
    public Object getRowKey(RoomCategory roomCategory) {
        return roomCategory.getId().toString();
    }

    @Override
    public RoomCategory getRowData(String rowKey) {
        return roomCategoryFacade.find(Long.parseLong(rowKey));
    }

}
