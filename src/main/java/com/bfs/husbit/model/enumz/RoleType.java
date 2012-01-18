package com.bfs.husbit.model.enumz;

/**
 * Created by IntelliJ IDEA.
 * User: lukman
 * Date: 1/10/12
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public enum RoleType {
    ROLE_ADMIN("Administrator", "ROLE_ADMIN"),
    ROLE_USER("Basic User", "ROLE_USER"),
    ROLE_ACCOUNT("Accountant", "ROLE_ACCOUNT"),
    ROLE_RECEPTIONIST("Receptionist", "ROLE_RECEPTIONIST");

    RoleType(String description, String roleName) {
        this.roleName = roleName;
        this.description = description;
    }

    private String roleName;
    private String description;

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

}
