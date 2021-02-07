/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author user
 */
public interface GenericEntity extends Serializable{

    public String getTableName();


    public String columnNamesForInsert();

    public String getInsertValues();

    public String columnNamesForUpdate();

    public String whereCondition();

    
}
