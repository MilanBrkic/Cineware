/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

/**
 *
 * @author user
 */
public class Validation {
    public static String firstnameValidation(String firstname){
        String error="";
        if (firstname.length() < 3) error += "Firstname can not have less than 3 chars\n";
        else{
            String regex="[A-Z]+[a-z0-9]+";
            if(!firstname.matches(regex)) error+="Firstname must start with a capital\n";
            else{
                regex="[A-Z]+[a-z]+";
                if(!firstname.matches(regex)) error+="Firstname can not have a number\n"; 
            }                    
        }
        return error;
    }
    
    public static String lastnameValidation(String lastname){
        String error = "";
        if (lastname.length() < 3) error += "Lastname can not have less than 3 chars\n";
        else{
            String regex="[A-Z]+[a-z0-9]+";
            if(!lastname.matches(regex)) error+="Lastname must start with a capital\n";
            else{
                regex="[A-Z]+[a-z]+";
                if(!lastname.matches(regex)) error+="Lastname can not have a number\n"; 
            }
        }
        return error;
    }
    
    public static String usernameValidation(String username){
        String error = "";
        
        if (username.length() < 4) error += "Username can not have less than 4 chars\n";
        else{
            String regex ="[A-Za-z0-9.]+";
            if(!username.matches(regex)) error+="Username can not gave blanks and special characters";
            
        }
        return error;
    }
}
