/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class Projection implements GenericEntity {

    private int id;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;
    private Hall hall;
    private Movie movie;
    private User user;

    public Projection() {
    }

    public Projection(Date startDate, Date endDate, BigDecimal price, Hall hall, Movie movie, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.hall = hall;
        this.movie = movie;
        this.user = user;
    }

    public Projection(int id, Date startDate, Date endDate, BigDecimal price, Hall hall, Movie movie, User user) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.hall = hall;
        this.movie = movie;
        this.user = user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projection other = (Projection) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM");
        return movie + " - " + sdf.format(startDate);
    }

    @Override
    public String getTableName() {
        return "projection";
    }

    @Override
    public String columnNamesForInsert() {
        return "projectionID, startTime,endTime,price, hallID, movieID, userID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(", ")
                .append("'").append(new java.sql.Timestamp(startDate.getTime())).append("', ")
                .append("'").append(new java.sql.Timestamp(endDate.getTime())).append("', ")
                .append(price).append(", ")
                .append(hall.getId()).append(", ")
                .append(movie.getId()).append(", ")
                .append(user.getId());

        return sb.toString();
    }

    @Override
    public String columnNamesForUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("projectionID=").append(id).append(", ")
          .append("startTime=").append("'").append(new java.sql.Timestamp(startDate.getTime())).append("', ")
          .append("endTime=").append("'").append(new java.sql.Timestamp(endDate.getTime())).append("', ")
          .append("price=").append(price).append(", ")
          .append("hallID=").append(hall.getId()).append(", ")
          .append("movieID=").append(movie.getId()).append(", ")
          .append("userID=").append(user.getId());
        
        return sb.toString();
    }

    @Override
    public String whereCondition() {
        return "projectionID=" + id;
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
