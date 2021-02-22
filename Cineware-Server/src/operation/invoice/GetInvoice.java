/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice;

import domain.Invoice;
import operation.AbstractGenericOperation;
import repository.db.impl.DbGeneric;

/**
 *
 * @author user
 */
public class GetInvoice extends AbstractGenericOperation{

    Invoice result;
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Invoice)){
            throw new Exception("Invalid ticket data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = (Invoice) ((DbGeneric)repo).get((Invoice)params,null,null);
    }

    public Invoice getResult() {
        return result;
    }
    
    
    
    
}
