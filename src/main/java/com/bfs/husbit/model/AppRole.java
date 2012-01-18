/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 */
package com.bfs.husbit.model;

import com.bfs.core.model.BaseModel;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.*;

/**
 * @author lukman
 */
@Entity
@Named
@Dependent
@NamedQueries({
        @NamedQuery(name = "findAllAppRole", query = "select R from AppRole R order by R.roleDescription"),
        @NamedQuery(name = "findAppRoleByRoleName", query = "select R from AppRole R where R.roleName = :rolename")
})
public class AppRole extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String roleName;//ROLE_ADMIN,ROLE_ACCOUNTANT,ROLE_RECEEPTIONIST
    private String roleDescription; //Admin, Accountant, Receptionist
    private List<AppUser> appUsers;

    public AppRole() {
    }

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @OneToMany(mappedBy = "approle")
    public List<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
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
        if (!(object instanceof AppRole)) {
            return false;
        }
        AppRole other = (AppRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bfs.husbit.model.AppRole[ id=" + id + " roleDescription " + roleDescription + " ]";
    }
}
