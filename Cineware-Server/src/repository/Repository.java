/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.ArrayList;

/**
 *
 * @author Milan
 */
public interface Repository<T> {
    public ArrayList<T> getAll() throws Exception;
    
    public void add(T t) throws Exception;
    
    public void update(T t)throws Exception;
    
    public void delete(T t)throws Exception;
}
