package com.bfs.husbit.view;

import com.bfs.core.BaseSerializable;
import com.bfs.husbit.model.Room;
import com.bfs.husbit.stateless.RoomCategoryFacade;
import com.bfs.husbit.stateless.RoomFacade;
import com.bfs.husbit.view.datamodel.RoomDataModel;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lukman
 * Date: 1/11/12
 * Time: 9:09 AM
 * To change this template use File | Settings | File Templates.
 */
@Named
@ConversationScoped
public class RoomView implements BaseSerializable {

    @EJB
    private RoomFacade roomFacade;
    @EJB
    private RoomCategoryFacade roomCategoryFacade;
    @Inject
    private Room room;
    @Inject
    private Logger log;
    private List<Room> roomList;
    private Room selectedRoom;
    private RoomDataModel roomDataModel;
    private Boolean editMode;
    @Inject
    private Conversation rmConversation;
    private Long roomDeleteId;
    //-----------------------Basic methods -----------------------------------

    @PostConstruct
    public void initView() {
        roomDataModel = new RoomDataModel(getRoomList());
    }

    public RoomFacade getRoomFacade() {
        return roomFacade;
    }

    public void setRoomFacade(RoomFacade roomFacade) {
        this.roomFacade = roomFacade;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public RoomDataModel getRoomDataModel() {
        return roomDataModel;
    }

    public void setRoomDataModel(RoomDataModel roomDataModel) {
        this.roomDataModel = roomDataModel;
    }

    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Room> getRoomList() {
        roomList = roomFacade.findAll();
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Long getRoomDeleteId() {
        return roomDeleteId;
    }

    public void setRoomDeleteId(Long roomDeleteId) {
        this.roomDeleteId = roomDeleteId;
    }

    //----------------------- CRUD operations ---------------------------------
    public String setUpEdit() {
//        if (rmConversation.isTransient()) {
        rmConversation.begin();
//        }
        return "/assets/room/viewroom.xhtml?faces-redirect=true";
    }

    public String createRoom() {
        if (room.getRoomCategory().getId() != null) {
            room.setRoomCategory(roomCategoryFacade.find(room.getRoomCategory().getId()));
        }
        roomFacade.create(room);
        return "/assets/room/newroom.xhtml?faces-redirect=true";
    }

    public String editRoom() {
        selectedRoom.setRoomCategory(roomCategoryFacade.find(selectedRoom.getRoomCategory().getId()));
        roomFacade.edit(selectedRoom);
        return "/assets/room/newroom.xhtml?faces-redirect=true";
    }

    public String removeRoom() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        roomDeleteId = Long.parseLong(params.get("rmId"));
        roomFacade.remove(roomFacade.find(roomDeleteId));
        return "/assets/room/newroom.xhtml?faces-redirect=true";
    }

    public String findRoom() {
        return null;
    }

    //------------------Other Operations ----------------------------
    public void endConversation() {
        rmConversation.end();
    }
}
