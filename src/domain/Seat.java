/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Milan
 */
public class Seat {
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
    
    
}
