/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import domain.Actor;
import domain.Movie;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class MovieTableModel extends AbstractTableModel{
    ArrayList<Movie> movies;
    String[] columnNames = new String[]{"Name", "Genre", "Runtime","Year", "Director","Actors"};

    public MovieTableModel(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        switch(columnIndex){
            case 0: return movie.getName();
            case 1: return movie.getGenre();
            case 2: return movie.getRuntime();
            case 3: return movie.getYear();
            case 4: return movie.getDirector().toString();
            case 5:
                String actors = "";
                ArrayList<Actor> list = movie.getActors();
                for (Actor a : list) {
                    actors += a.toString()+", ";
                }
                actors = actors.substring(0, actors.length()-2);
                return actors;
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
    
    
    
    
    
}
