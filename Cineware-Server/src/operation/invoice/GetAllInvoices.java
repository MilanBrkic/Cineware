/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.invoice;

import domain.Invoice;
import domain.InvoiceItem;
import domain.Product;
import domain.Ticket;
import domain.User;
import java.util.ArrayList;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class GetAllInvoices extends AbstractGenericOperation{

    ArrayList<Invoice> result;
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Invoice)){
            throw new Exception("Invalid invoice data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll((Invoice) params,null,null, null);
        for (Invoice invoice : result) {
            String where = "invoiceID="+invoice.getId();
            invoice.setItems(repo.getAll(new InvoiceItem(), where, null, null));
            for (InvoiceItem item : invoice.getItems()) {
                String innerJoin = "article a INNER JOIN ticket t ON a.articleID=t.articleID";
                Ticket ticket = (Ticket) repo.get((Ticket)item.getArticle(), innerJoin, null);
                if(ticket==null){
                    innerJoin = "article a INNER JOIN product p ON a.articleID=p.articleID";
                    Product product = new Product();
                    product.setId(item.getArticle().getId());
                    item.setArticle((Product) repo.get(product, innerJoin, null));
                }
            }
            invoice.setUser((User) repo.get(invoice.getUser(), null, null));
        }
    }

    public ArrayList<Invoice> getResult() {
        return result;
    }

    
    
}
