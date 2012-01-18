package com.bfs.husbit.view;

import com.bfs.core.BaseSerializable;
import com.bfs.husbit.model.Room;
import com.bfs.husbit.model.RoomCategory;
import com.bfs.husbit.stateless.RoomCategoryFacade;
import com.bfs.husbit.view.datamodel.RoomCategoryDataModel;
import com.bfs.husbit.view.datamodel.RoomDataModel;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
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
public class RoomCategoryView implements BaseSerializable {

    @EJB
    private RoomCategoryFacade roomCategoryFacade;
    @Inject
    private RoomCategory roomCategory;
    @Inject
    private Logger log;
    private List<RoomCategory> roomCategoryList;
    /**
     * room to add from the interface
     */
    @Inject
    private Room room;
    private RoomCategory selectedRoomCategory;
    private RoomCategoryDataModel roomCategoryDataModel;
    /**
     * for sorting and filtering of rooms in roomCategory
     */
    private RoomDataModel internalRoomDataModel;
    private Boolean editMode;
    @Inject
    private Conversation rmCatConversation;
    private Long roomCategoryDeleteId;

    //-----------------------Basic methods -----------------------------------
    @PostConstruct
    public void initView() {
        roomCategoryDataModel = new RoomCategoryDataModel(getRoomCategoryList());

    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Produces
    @Named
    public List<RoomCategory> getRoomCategoryList() {
        roomCategoryList = roomCategoryFacade.findAll();
        return roomCategoryList;
    }

    public void setRoomCategoryList(List<RoomCategory> roomCategoryList) {
        this.roomCategoryList = roomCategoryList;
    }

    public RoomCategory getSelectedRoomCategory() {
        return selectedRoomCategory;
    }

    public void setSelectedRoomCategory(RoomCategory selectedRoomCategory) {
        this.selectedRoomCategory = selectedRoomCategory;
    }

    public RoomCategoryDataModel getRoomCategoryDataModel() {
        return roomCategoryDataModel;
    }

    public void setRoomCategoryDataModel(RoomCategoryDataModel roomCategoryDataModel) {
        this.roomCategoryDataModel = roomCategoryDataModel;
    }

    public RoomDataModel getInternalRoomDataModel() {
        return internalRoomDataModel;
    }

    public void setInternalRoomDataModel(RoomDataModel internalRoomDataModel) {
        this.internalRoomDataModel = internalRoomDataModel;
    }

    public Boolean getEditMode() {
        return editMode;
    }

    public void setEditMode(Boolean editMode) {
        this.editMode = editMode;
    }

    public Long getRoomCategoryDeleteId() {
        return roomCategoryDeleteId;
    }

    public void setRoomCategoryDeleteId(Long roomCategoryDeleteId) {
        this.roomCategoryDeleteId = roomCategoryDeleteId;
    }

    //----------------------- CRUD operations ---------------------------------
    public String createRoomCategory() {
        roomCategoryFacade.create(roomCategory);
        return "/assets/roomcategory/newroomcategory.xhmtl?faces-redirect=true";
    }

    public String editRoomCategory() {
        roomCategoryFacade.edit(selectedRoomCategory);
        return "/assets/roomcategory/newroomcategory.xhmtl?faces-redirect=true";
    }

    public String setUpEdit() {
       if(rmCatConversation.isTransient()){
        System.out.println("rmCatConversation " + rmCatConversation + " Transient >>>" + rmCatConversation.isTransient() + " ID " + rmCatConversation.getId());
        rmCatConversation.begin();
        System.out.println("After rmCatConversation " + rmCatConversation + " Transient >>>" + rmCatConversation.isTransient() + " ID " + rmCatConversation.getId());
         }
        internalRoomDataModel = new RoomDataModel(selectedRoomCategory.getRooms());
        return "/assets/roomcategory/viewroomcategory.xhmtl?faces-redirect=true";
    }

    public String removeRoomCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        roomCategoryDeleteId = Long.parseLong(params.get("sRoomCategoryId"));
        int remove = roomCategoryFacade.remove(roomCategoryDeleteId);
        if (remove < 1) {
            return null;
        }
        return "/assets/roomcategory/newroomcategory?faces-redirect=true";
    }

    public String findRoomCategory() {
        return null;
    }

    public void addRoomToRoomCategory() {
        room.setRoomCategory(selectedRoomCategory);
    }

    /**
     * To re-initialise the collector at the interface.
     * @return
     */
    public String reinitRoom() {
        room = new Room();
        return null;
    }

    //------------------Other Operations ----------------------------
    public void endConversation() {
        rmCatConversation.end();
    }
}
