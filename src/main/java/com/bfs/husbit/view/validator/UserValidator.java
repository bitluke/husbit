/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view.validator;

import com.bfs.husbit.util.Messages;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author lukman
 */
@FacesValidator("com.bfs.husbit.model.AppUser")
public class UserValidator implements Validator {


    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput roleId = (UIInput) component.findComponent("seleApprole");
        if (roleId.getSubmittedValue().equals("-1")) {
            FacesMessage facesMessage = Messages.getMessage("messages", "selectRole", null);
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesMessage.setDetail("display");
            throw new ValidatorException(facesMessage);
        }
    }
}
