/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.model;

import com.bfs.core.model.BaseModel;
import com.bfs.husbit.model.embeddable.MonetaryAmount;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author lukman
 */
@Entity
@Named
@Dependent
@NamedQueries({
        @NamedQuery(name = "deleteRoomCategory", query = "delete from RoomCategory RC where RC.id = :rcid")
})
public class RoomCategory extends BaseModel {

    private static final long serialVersionUID = 1L;
    private List<Room> rooms;
    private String name;
    private String description;
    @Inject
    private MonetaryAmount rate;

    public RoomCategory() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public MonetaryAmount getRate() {
        return rate;
    }

    public void setRate(MonetaryAmount rate) {
        this.rate = rate;
    }

    @OneToMany(orphanRemoval = true,cascade = {CascadeType.ALL},mappedBy = "roomCategory")
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomCategory)) {
            return false;
        }
        RoomCategory other = (RoomCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bfs.husbit.model.RoomCategory[ id=" + id + " ]";
    }
}
