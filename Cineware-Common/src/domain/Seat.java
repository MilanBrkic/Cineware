/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Milan
 */
public class Seat implements GenericEntity{
    private int id;
    private Hall hall;
    private int number;
    private char row;

    public Seat() {
    }

    public Seat(int id, Hall hall, int number, char row) {
        this.id = id;
        this.hall = hall;
        this.number = number;
        this.row = row;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "row: "+row+" num: "+number;
    }

    @Override
    public String getTableName() {
        return "seat";
    }

    @Override
    public String columnNamesForInsert() {
        return "hallID, number, row";
    }

    @Override
    public String getInsertValues() {
       StringBuilder sb = new StringBuilder();
       
       sb.append(hall.getId()).append(", ")
               .append(number).append(", '")
               .append(row).append("'");
       
       return sb.toString();
               
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String whereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
