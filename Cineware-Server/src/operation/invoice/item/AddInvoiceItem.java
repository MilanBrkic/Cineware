/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice.item;

import domain.InvoiceItem;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class AddInvoiceItem extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof InvoiceItem)){
            throw new Exception("Invalid invoice item data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        repo.add((InvoiceItem)params,null,null,null);
    }
    
}
