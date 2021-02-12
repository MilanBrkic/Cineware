/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Milan
 */
public class Hall implements GenericEntity{
    private int id;
    private String name;
    private int numberOfSeats;

    public Hall() {
    }

    public Hall(int id, String name, int numberOfSeats) {
        this.id = id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName() {
        return "hall";
    }

    @Override
    public String columnNamesForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String columnNamesForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String whereCondition() {
        return "hallID="+id;
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception{
        ArrayList<Hall> halls = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("hallID");
            String hallName = rs.getString("hallName");
            int number = rs.getInt("numberOfSeats");
            Hall hall = new Hall(id, hallName, number);
            halls.add(hall);
        }
        return new ArrayList<>(halls);
    }
    
    
}
