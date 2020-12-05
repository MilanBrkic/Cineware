/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import domain.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter.Entry;
import repository.Repository;
import repository.db.impl.DbHall;
import repository.db.impl.DbUser;
/**
 *
 * @author Milan
 */
public class Controller {
    private Repository dbUser;
    private Repository dbHall;
    private static Controller instance;
    private User user;
    private ArrayList<String> countries;
    
    private Controller(){
        dbUser = new DbUser();
        dbHall = new DbHall();
        countries = readCoutries();
    }
    
    public static Controller getInstance(){
        if(instance==null) instance = new Controller();
        return instance;
    }

    public Repository getDbUser() {
        return dbUser;
    }

    public Repository getDbHall() {
        return dbHall;
    }
   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    public String encrypt(String password) {

        String algorithm = "SHA";

        byte[] plainText = password.getBytes();

        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);

            md.reset();
            md.update(plainText);
            byte[] encodedPassword = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < encodedPassword.length; i++) {
                if ((encodedPassword[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<String> getCountries() {
        return countries;
    }
    
    
    
    public ArrayList<String> readCoutries(){
        ArrayList<String> list = new ArrayList<>();
        try {
            
            String basePath = new File("").getAbsolutePath();
            String jsonPath = basePath + "\\resources\\countries.json";
            System.out.println(jsonPath);
            
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(jsonPath));
            
            List<Countries> coutries = Arrays.asList(gson.fromJson(reader, Countries[].class));
            
            for (Countries coutry : coutries) {
                list.add(coutry.getName());
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    
    class Countries{
        String name;
        

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
        
        
        
    }
}
