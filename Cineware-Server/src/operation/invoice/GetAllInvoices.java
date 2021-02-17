/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice;

import domain.Invoice;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.Repository;
import repository.db.impl.DbInvoice;

/**
 *
 * @author user
 */
public class GetAllInvoices extends AbstractGenericOperation{

    ArrayList<Invoice> result;
    
    public GetAllInvoices() {
        repo = new DbInvoice();
    }

    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Invoice)){
            throw new Exception("Invalid ticket data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbInvoice)repo).getAll((Invoice) params);
    }

    public ArrayList<Invoice> getResult() {
        return result;
    }

    
    
}
