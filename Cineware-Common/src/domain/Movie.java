/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enums.Genre;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Movie implements GenericEntity{
    private int id;
    private String name;
    private String description;
    private Genre genre;
    private int runtime;
    private int year;
    private Director director;
    private ArrayList<Actor> actors;
    private User user;
    
    public Movie() {
    }

    
    public Movie(String name, String description, Genre genre, int runtime, int year, Director director, ArrayList<Actor> actors, User user) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.runtime = runtime;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.user = user;
    }
    
    public Movie(int id, String name, String description, Genre genre, int runtime, int year, Director director, ArrayList<Actor> actors, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.runtime = runtime;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Movie other = (Movie) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName() {
        return "movie";
    }

    @Override
    public String columnNamesForInsert() {
        return "name, description, genre, runtime, year, directorID, userID";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("', ")
          .append("'").append(description).append("', ")
          .append("'").append(genre).append("', ")
          .append(runtime).append(", ")
          .append(year).append(", ")
          .append(director.getId()).append(", ")
          .append(user.getId());
        
        return sb.toString();
    }

    @Override
    public String columnNamesForUpdate() {
//        UPDATE movie SET name=?, description=?, genre=?, runtime=?, year=?, directorID=?, userID=? WHERE movieID=?
        StringBuilder sb = new StringBuilder();
        sb.append("name='").append(name).append("', ")
          .append("description='").append(description).append("', ")
          .append("genre='").append(genre).append("', ")
          .append("runtime=").append(runtime).append(", ")
          .append("year=").append(year).append(", ")
          .append("userID=").append(user.getId());
                
        return sb.toString();
    }

    @Override
    public String whereCondition() {
        return "movieID="+id;
    }

    @Override
    public ArrayList<GenericEntity> getFromResultSet(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> movies = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("movieID");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Genre genre = Genre.valueOf(rs.getString("genre"));
            int runtime = rs.getInt("runtime");
            int year = rs.getInt("year");
            Director director = new Director();
            director.setId(rs.getInt("directorID"));
            User user = new User();
            user.setId(rs.getInt("userID"));
            ArrayList<Actor> actors = new ArrayList<>();
            Movie movie = new Movie(id, name, description, genre, runtime, year, director, actors, user);
            movies.add(movie);
        }

        return movies;
    }
    
    
    
    
}
