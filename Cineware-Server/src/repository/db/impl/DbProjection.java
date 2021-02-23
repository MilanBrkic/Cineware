/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import controller.Controller;
import domain.Hall;
import domain.Movie;
import domain.Projection;
import domain.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import repository.db.DbRepository;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class DbProjection implements DbRepository<Projection> {

    public ArrayList<Projection> getAll() throws Exception {
        String query = "SELECT * FROM projection ORDER BY startTime";
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);

        ArrayList<Projection> projections = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("projectionID");
            Date startDate = new Date(rs.getTimestamp("startTime").getTime());
            Date endDate = new Date(rs.getTimestamp("endTime").getTime());;

            int userID = rs.getInt("userID");
            User user = Controller.getInstance().getUser(userID);

            int movieID = rs.getInt("movieID");
            Movie movie = Controller.getInstance().getMovie(movieID);

            int hallID = rs.getInt("hallID");
            Hall hall = Controller.getInstance().getHall(hallID);

            BigDecimal price = rs.getBigDecimal("price");

            Projection p = new Projection(id, startDate, endDate, price, hall, movie, user);
            projections.add(p);
        }

        return projections;
    }

    public boolean isTheHallOccupied(Projection p) throws Exception {
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
    
    public boolean isTheHallOccupiedForUpdate(Projection p) throws Exception {
        Hall hall = p.getHall();
        Date dateStart = p.getStartDate();
        Date dateEnd = p.getEndDate();
        ArrayList<Projection> projections = getAllSameDaySameHall(hall, dateStart);
        for (Projection projection : projections) {
            if(projection.getId()==p.getId()) continue;
            if (overlapping(projection, dateStart, dateEnd)) {
                return false;
            }
        }
        return true;
    }

    private boolean overlapping(Projection projection, Date dateStart, Date dateEnd) {
        Date pStart = projection.getStartDate();
        Date pEnd = projection.getEndDate();

        if ((pStart.before(dateStart) && pEnd.after(dateStart)) || (pStart.before(dateEnd) && pEnd.after(dateEnd))) {
            return true;
        }
        else if(pStart.equals(dateStart) || pEnd.equals(dateEnd)){
            return true;
        }
        else {
            return false;
        }
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
        String query = "SELECT * FROM projection WHERE hallID=? AND ?<=startTime AND startTime<?";
        PreparedStatement ps = connect().prepareStatement(query);

        ps.setInt(1, hall.getId());
        ps.setTimestamp(2, new java.sql.Timestamp(dateStart.getTime()));
        ps.setTimestamp(3, new java.sql.Timestamp(dateEnd.getTime()));

        ResultSet rs = ps.executeQuery();

        ArrayList<Projection> projections = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("projectionID");
            Date startDate = new Date(rs.getTimestamp("startTime").getTime());
            Date endDate = new Date(rs.getTimestamp("endTime").getTime());;

            int userID = rs.getInt("userID");
            User user = Controller.getInstance().getUser(userID);

            int movieID = rs.getInt("movieID");
            Movie movie = Controller.getInstance().getMovie(movieID);
            BigDecimal price = rs.getBigDecimal("price");

            Projection p = new Projection(id, startDate, endDate, price, hall, movie, user);
            projections.add(p);
        }

        return projections;
    }

    @Override
    public ArrayList<Projection> getAll(Projection t,String where,String orderby, String innerJoin) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Projection t, String table, String columns, String values) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Projection t, String values, String where) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Projection t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Projection get(Projection p,String innerJoin,String where) throws Exception {
        int id = p.getId();
        String query = "SELECT * FROM projection WHERE projectionID="+id;
        Statement s = connect().createStatement();
        ResultSet rs = s.executeQuery(query);
        Projection projection = null;
        if(rs.next()){
            Date startDate = new Date(rs.getTimestamp("startTime").getTime());
            Date endDate = new Date(rs.getTimestamp("endTime").getTime());;

            int userID = rs.getInt("userID");
            User user = Controller.getInstance().getUser(userID);

            int movieID = rs.getInt("movieID");
            Movie movie = Controller.getInstance().getMovie(movieID);
            BigDecimal price = rs.getBigDecimal("price");
            
            Hall hall = Controller.getInstance().getHall(rs.getInt("hallID"));

            projection = new Projection(id, startDate, endDate, price, hall, movie, user);
        }
        
        return projection;
    }

    @Override
    public void addWithGenKeys(Projection t, String table, String columns, String values) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
