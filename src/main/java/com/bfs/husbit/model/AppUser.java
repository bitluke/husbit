/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.model;


import com.bfs.husbit.model.embeddable.Name;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lukman
 */
@Entity
@Named
@Dependent
@NamedQueries({
        @NamedQuery(name = "findDefaultAppUser", query = "select A from AppUser A where A.defaultAppUser = :dlt"),
        @NamedQuery(name = "findAllAppUser", query = "select A from AppUser A where A.defaultAppUser = :dlt")
})

public class AppUser  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Name name;
    private String username;
    private String password;
    @Inject
    private AppRole approle;
    private Boolean defaultAppUser = Boolean.FALSE;
    private Boolean enabled = Boolean.TRUE;
    private Long version;

    public AppUser() {

    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Name getName() {
        return name;
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    public AppRole getApprole() {
        return approle;
    }

    public void setApprole(AppRole approle) {
        this.approle = approle;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @NotNull
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bfs.husbit.model.AppUser[ id=" + username + " ]";
    }
}
