/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.product;

import domain.Article;
import domain.Product;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class UpdateProduct extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Product)){
            throw new Exception("Invalid product data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Product product = (Product) params;
        Article article = new Article(product.getId(), product.getPrice(), product.getUnit());
        repo.update(article, null, null);
        repo.update(product, null, "articleID="+product.getId());
    }
    
}
