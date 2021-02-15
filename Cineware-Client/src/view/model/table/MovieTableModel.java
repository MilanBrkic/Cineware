/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Actor;
import domain.Movie;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class MovieTableModel extends AbstractTableModel{
    ArrayList<Movie> movies;
    ArrayList<Movie> moviesCopy;
    String sortValue = "";
    String[] columnNames = new String[]{"Name", "Genre", "Runtime","Year", "Director","Actors"};

    public MovieTableModel(ArrayList<Movie> movies) {
        this.movies = movies;
        moviesCopy = new ArrayList<>(movies);
    }

    
    @Override
    public int getRowCount() {
        return moviesCopy.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = moviesCopy.get(rowIndex);
        switch(columnIndex){
            case 0: return movie.getName();
            case 1: return movie.getGenre().toString();
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

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }
    
    

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public ArrayList<Movie> getMovies() {
        return moviesCopy;
    }

    public void refresh() throws Exception {
        movies = Communcation.getInstance().getAllMovies();
        sort();
    }

    public void sort() {
        moviesCopy = new ArrayList<>();
        for (Movie movy : movies) {
            if(movy.getName().contains(sortValue)){
                moviesCopy.add(movy);
            }
        }
        fireTableDataChanged();
    }
    
    
    
    
    
}
