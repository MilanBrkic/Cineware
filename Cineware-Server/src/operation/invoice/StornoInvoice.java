/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice;

import domain.Invoice;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class StornoInvoice extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Invoice)){
            throw new Exception("Invalid invoice data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        AbstractGenericOperation ago = new AddInvoice();
        ago.execute((Invoice)params);
    }
    
}
