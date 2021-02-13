/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enums.MeasurementUnit;
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

    public Ticket(BigDecimal price, MeasurementUnit unit, boolean sold, Projection projection, Seat seat) {
        super(price, unit);
        this.sold = sold;
        this.projection = projection;
        this.seat = seat;
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

    @Override
    public String getTableName() {
        return "ticket";
    }

    @Override
    public String columnNamesForInsert() {
        return "articleID, sold, projectionID, seatID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(id).append(", ")
          .append(sold).append(", ")
          .append(projection.getId()).append(", ")
          .append(seat.getId());
        
        
        return sb.toString();
    }
    
    
    
    
    
    
}
