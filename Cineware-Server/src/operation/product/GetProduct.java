/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.product;

import domain.Product;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class GetProduct extends AbstractGenericOperation{

    Product result;

    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Product)){
            throw new Exception("Invalid product data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Product product = (Product) params;
        String innerJoin = "article a INNER JOIN product p ON a.articleID=p.articleID";
                    
        result = (Product) repo.get(product,innerJoin, null);
    }

    public Product getResult() {
        return result;
    }
    
    
    
}
