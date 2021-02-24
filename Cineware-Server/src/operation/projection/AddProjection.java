/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operation.projection;

import domain.Article;
import domain.Hall;
import domain.Projection;
import domain.Seat;
import domain.Ticket;
import domain.enums.MeasurementUnit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import operation.AbstractGenericOperation;

/**
 *
 * @author user
 */
public class AddProjection extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object params) throws Exception {
        if (params == null || !(params instanceof Projection)) {
            throw new Exception("Invalid projection data");
        }
    }

    @Override
    protected void executeOperation(Object params) throws Exception {
        try {
            Projection projection = (Projection) params;

            if (!isHallOccupied(projection)) {
                throw new Exception("Hall ocuppied in given time");
            }

            repo.addWithGenKeys(projection, null, null, null);

            addTickets(projection);

        } catch (Exception e) {
            throw e;
        }
    }

    private boolean isHallOccupied(Projection p) throws Exception {
        Hall hall = p.getHall();
        Date dateStart = p.getStartDate();
        Date dateEnd = p.getEndDate();
        ArrayList<Projection> projections = getAllSameDaySameHall(hall, dateStart);
        for (Projection projection : projections) {
            if (overlapping(projection, dateStart, dateEnd)) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Projection> getAllSameDaySameHall(Hall hall, Date dateStart) throws Exception {
        GregorianCalendar greg = new GregorianCalendar();
        greg.setTime(dateStart);
        greg = new GregorianCalendar(greg.get(GregorianCalendar.YEAR), greg.get(GregorianCalendar.MONTH), greg.get(GregorianCalendar.DAY_OF_MONTH));
        GregorianCalendar gregPlus = new GregorianCalendar(greg.get(GregorianCalendar.YEAR), greg.get(GregorianCalendar.MONTH), greg.get(GregorianCalendar.DAY_OF_MONTH) + 1);
        dateStart = greg.getTime();
        Date dateEnd = gregPlus.getTime();
        System.out.println(dateStart);
        System.out.println(dateEnd);
//        String query = "SELECT * FROM projection WHERE hallID=? AND ?<=startTime AND startTime<?";
        String where = "hallID=" + hall.getId() + " AND '" + new java.sql.Timestamp(dateStart.getTime()) + "'<=startTime AND startTime<'" + new java.sql.Timestamp(dateEnd.getTime()) + "'";
        return repo.getAll(new Projection(), where, null, null);
    }

    private boolean overlapping(Projection projection, Date dateStart, Date dateEnd) {
        Date pStart = projection.getStartDate();
        Date pEnd = projection.getEndDate();

        if ((pStart.before(dateStart) && pEnd.after(dateStart)) || (pStart.before(dateEnd) && pEnd.after(dateEnd))) {
            return true;
        } else if (pStart.equals(dateStart) || pEnd.equals(dateEnd)) {
            return true;
        } else {
            return false;
        }
    }

    private void addTickets(Projection p) throws Exception {
        Hall hall = p.getHall();
        BigDecimal price = p.getPrice();
        MeasurementUnit unit = MeasurementUnit.PCS;
        Article article = new Article(price, unit);
        ArrayList<Seat> seats = getAllByHall(hall);
        boolean sold = false;

        for (Seat seat : seats) {
            repo.addWithGenKeys(article, null, null, null);

            Ticket ticket = new Ticket(article.getId(), price, unit, sold, p, seat);
            repo.add(ticket, null, null, null);
        }
    }

    private ArrayList<Seat> getAllByHall(Hall hall) throws Exception {
        ArrayList<Seat> seats = repo.getAll(new Seat(), "hallID=" + hall.getId(), null, null);
        for (Seat seat : seats) {
            seat.setHall(hall);
        }
        return seats;
    }

}
