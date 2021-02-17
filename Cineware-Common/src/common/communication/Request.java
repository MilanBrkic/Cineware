/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.communication;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Request implements Serializable{
    Operation operation;
    String arguments;
    Object object;
    public Request() {
    }

    public Request(Operation operation, String arguments) {
        this.operation = operation;
        this.arguments = arguments;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
    
    
    
            
    
}
