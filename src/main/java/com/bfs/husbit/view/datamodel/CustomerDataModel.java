/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfs.husbit.view.datamodel;

import com.bfs.husbit.model.Customer;
import com.bfs.husbit.stateless.CustomerFacade;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * @author lukman
 */
public class CustomerDataModel extends ListDataModel<Customer> implements SelectableDataModel<Customer> {

    @EJB
    private CustomerFacade customerFacade;

    public CustomerDataModel(List<Customer> list) {
        super(list);
    }

    public CustomerDataModel() {
    }


    @Override
    public Object getRowKey(Customer customer) {
        return customer.getId().toString();
    }

    @Override
    public Customer getRowData(String rowKey) {
        return customerFacade.find(Long.parseLong(rowKey));
    }

}
