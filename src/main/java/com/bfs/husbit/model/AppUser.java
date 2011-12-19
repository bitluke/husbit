/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.model;

import com.bfs.core.model.BaseModel;

import com.bfs.husbit.model.embeddable.Name;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author lukman
 */
@Entity
@Named
@Dependent
@NamedQueries({
    @NamedQuery(name = "findDefaultAppUser", query = "select A from AppUser A where A.defaultAppUser = :dlt"),
    @NamedQuery(name = "findAllAppUser", query = "select A from AppUser A where A.defaultAppUser = :dlt")
})
public class AppUser extends BaseModel {

    private static final long serialVersionUID = 1L;
    @Inject
    private Name name;
    private String username;
    private String password;
    private AppRole approle;
    private List<AppRole> approles;
    private Boolean defaultAppUser = Boolean.FALSE;

    public AppUser() {
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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    public List<AppRole> getApproles() {
        return approles;
    }

    public void setApproles(List<AppRole> approles) {
        this.approles = approles;
    }

    public void setApprole(AppRole approle) {
        if(getApproles() != null){
            getApproles().add(approle);
        }else{
            List<AppRole> tmproles = new ArrayList<AppRole>();
            tmproles.add(approle);
            setApproles(tmproles);
        }
    }
    
    @Transient
    public AppRole getApprole() {
        return approle;
    }

    
    
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getDefaultAppUser() {
        return defaultAppUser;
    }

    public void setDefaultAppUser(Boolean defaultAppUser) {
        this.defaultAppUser = defaultAppUser;
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
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bfs.husbit.model.AppUser[ id=" + id + " ]";
    }
}
