/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.article;

import domain.Article;
import operation.AbstractGenericOperation;
import repository.db.impl.DbArticle;

/**
 *
 * @author user
 */
public class AddArticle extends AbstractGenericOperation{
    int result;

    public AddArticle() {
        repo = new DbArticle();
    }
    
    
    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Article)){
            throw new Exception("Invalid seat data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        result = ((DbArticle)repo).addWithIndex((Article) params);
    }

    public int getResult() {
        return result;
    }
   
    
    
}
