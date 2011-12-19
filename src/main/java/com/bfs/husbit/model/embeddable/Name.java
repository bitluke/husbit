package com.bfs.husbit.model.embeddable;

import com.bfs.core.BaseSerializable;
import org.hibernate.validator.constraints.NotEmpty;

import javax.inject.Named;
import javax.persistence.Embeddable;
import javax.enterprise.context.Dependent;

/**
 * Created by IntelliJ IDEA.
 * User: lukman
 * Date: 6/24/11
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
@Named
@Dependent
public class Name implements BaseSerializable {
    private String firstName;
    private String lastName;
    private String middleName;

    public Name() {
    }

    public Name(String firstName) {
        this.firstName = firstName;
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @NotEmpty(message = "First name cannot be empty")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
