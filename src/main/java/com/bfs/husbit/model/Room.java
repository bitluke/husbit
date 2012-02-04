/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.model;

import com.bfs.core.model.BaseModel;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;

/**
 * @author lukman
 */
@Entity
@Named
@Dependent
@NamedQueries({
        @NamedQuery(name = "deleteRoom", query = "delete from Room R where R.id = :rid")
})
public class Room extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * The name given to the room
     */
    private String name;
    @Inject
    private RoomCategory roomCategory;
    private Long numberOfUsage;
    private List<Attendance> attendances;

    public Room() {
    }


    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Long getNumberOfUsage() {
        return numberOfUsage;
    }

    public void setNumberOfUsage(Long numberOfUsage) {
        this.numberOfUsage = numberOfUsage;
    }


    @OneToMany(mappedBy = "room")
    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bfs.husbit.model.Room[ id=" + id + " name " + name  + "]";
    }
}
