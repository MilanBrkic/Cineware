/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.article;

import domain.Article;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class AddArticle extends AbstractGenericOperation{
    int result;

    
    @Override
    protected void preconditions(Object params) throws Exception {
        if(params==null || !(params instanceof Article)){
            throw new Exception("Invalid seat data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        Article article = (Article) params;
        repo.addWithGenKeys(article, null, null, null);
        result = article.getId();
    }

    public int getResult() {
        return result;
    }
   
    
    
}
