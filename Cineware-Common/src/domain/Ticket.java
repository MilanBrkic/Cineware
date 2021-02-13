/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class Ticket extends Article{
    private boolean sold;
    private Projection projection;
    private Seat seat;

    public Ticket() {
    }

    public Ticket(int id, BigDecimal price, MeasurementUnit unit, boolean sold, Projection projection, Seat seat) {
        super(id, price, unit);
        this.sold = sold;
        this.projection = projection;
        this.seat = seat;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return projection+" "+seat;
    }
    
    
}
