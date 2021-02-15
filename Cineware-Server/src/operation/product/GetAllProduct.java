/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.product;

import domain.Product;
import java.util.ArrayList;
import operation.AbstractGenericOperation;
import repository.db.impl.DbProduct;

/**
 *
 * @author user
 */
public class GetAllProduct extends AbstractGenericOperation{
    ArrayList<Product> result;

    public GetAllProduct() {
        repo = new DbProduct();
    }
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Product)){
            throw new Exception("Invalid product data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = repo.getAll(params);
    }

    public ArrayList<Product> getResult() {
        return result;
    }
    
    
}
