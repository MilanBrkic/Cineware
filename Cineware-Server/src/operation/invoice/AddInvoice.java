/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice;

import domain.Invoice;
import domain.InvoiceItem;
import domain.Ticket;
import operation.AbstractGenericOperation;
import operation.invoice.item.AddInvoiceItem;
import operation.ticket.SetTicketToSold;
import repository.db.impl.DbGeneric;

/**
 *
 * @author user
 */
public class AddInvoice extends AbstractGenericOperation{

    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Invoice)){
            throw new Exception("Invalid ticket data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        ((DbGeneric)repo).addWithGenKeys((Invoice)params);
        Invoice invoice = (Invoice) params;
        for (InvoiceItem item : invoice.getItems()) {
            item.setId(invoice.getId());
            AbstractGenericOperation ago = new AddInvoiceItem();
            ago.executeWithoutCommit(item);

            if (item.getArticle() instanceof Ticket) {
                AbstractGenericOperation ago2 = new SetTicketToSold();
                ago2.executeWithoutCommit((Ticket) item.getArticle());
            }
        }

    }
    
}
