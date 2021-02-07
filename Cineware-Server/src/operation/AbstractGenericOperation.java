/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation;

import java.sql.SQLException;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbGeneric;

/**
 *
 * @author user
 */
public abstract class AbstractGenericOperation {
    protected Repository repo;

    public AbstractGenericOperation() {
        repo = new DbGeneric();
    }
    
    public final void execute(Object params) throws Exception{
        try {
            startTransaction();
            preconditions(params);
            executeOperation(params);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        }
        finally{
            disconnect();
        }
    }

    private void startTransaction() throws SQLException {
        ((DbRepository) repo).connect();
    }

    protected abstract void preconditions(Object params) throws Exception;

    protected abstract void executeOperation(Object params)throws Exception;

    private void commitTransaction() throws SQLException {
        ((DbRepository) repo).commit();
    }

    private void rollbackTransaction() throws SQLException {
        ((DbRepository) repo).rollback();
    }

    private void disconnect() throws SQLException {
        ((DbRepository) repo).disconnect();
    }
}
