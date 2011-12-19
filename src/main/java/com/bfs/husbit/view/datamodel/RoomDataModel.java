/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view.datamodel;

import com.bfs.husbit.model.Room;
import com.bfs.husbit.stateless.RoomFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author lukman
 */
public class RoomDataModel extends ListDataModel<Room> implements SelectableDataModel<Room> {

    @EJB
    private RoomFacade roomFacade;

    public RoomDataModel(List<Room> list) {
        super(list);
    }

    public RoomDataModel() {
    }

    @Override
    public Object getRowKey(Room room) {
        return room.getId().toString();
    }

    @Override
    public Room getRowData(String rowKey) {
        return roomFacade.find(Long.parseLong(rowKey));
    }
}
