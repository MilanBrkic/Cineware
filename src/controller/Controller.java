/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domain.Hall;
import domain.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbHall;
import repository.db.impl.DbUser;
/**
 *
 * @author Milan
 */
public class Controller {
    int test;
    private Repository dbUser;
    private Repository dbHall;
    private static Controller instance;
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

    public ArrayList<Hall> getAllHalls() throws Exception{
        ArrayList<Hall> halls = null;
        try {
            halls = dbHall.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return halls;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = null;
        try {
            users = dbUser.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return users;
    }

    public void deleteUser(User user) throws Exception {
        try {
            dbUser.delete(user);
            ((DbRepository)dbUser).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbUser).rollback();
            throw e;
        }finally{
            ((DbRepository)dbUser).disconnect();
        }
    }

    public void updateUser(User user) throws Exception {
        try {
            dbUser.update(user);
            ((DbRepository)dbUser).commit();
        } catch (Exception e) {
            e.printStackTrace();
            ((DbRepository)dbUser).rollback();
            throw e;
        }finally{
            ((DbRepository)dbUser).disconnect();
        }
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
