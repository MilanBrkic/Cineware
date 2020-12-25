/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.communication.Receiver;
import common.communication.Request;
import common.communication.Response;
import common.communication.Sender;
import controller.Controller;
import domain.Director;
import domain.Hall;
import domain.User;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class HandleThread extends Thread {

    Socket socket;
    Receiver receiver;
    Sender sender;
    Gson gson;
    public HandleThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = null;
                try {
                    request = (Request) receiver.receive();
                } catch (Exception e) {
                }
                if(request==null) break;
                
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case GET_COUNTRIES:
                            ArrayList<String> countries = Controller.getInstance().getCountries();
                            String jsonCountries = gson.toJson(countries);
                            response.setResult(jsonCountries);
                            break;
                        case GET_ALL_HALLS:
                            ArrayList<Hall> halls = Controller.getInstance().getAllHalls();
                            String jsonHalls = gson.toJson(halls);
                            response.setResult(jsonHalls);
                            break;
                        case GET_ALL_USERS:
                            ArrayList<User> users = Controller.getInstance().getAllUsers();
                            String jsonUsers = gson.toJson(users);
                            response.setResult(jsonUsers);
                            break;
                        case GET_ALL_DIRECTORS:
                            ArrayList<Director> directors = Controller.getInstance().getAllDirectors();
                            String jsonDirectors = gson.toJson(directors);
                            response.setResult(jsonDirectors);
                            break;
                        case ADD_USER:
                            String jsonUserAdd =  request.getArguments();
                            User userAdd = gson.fromJson(jsonUserAdd, User.class);
                            Controller.getInstance().addUser(userAdd);
                            break;
                        case DELETE_USER:
                            String jsonUserDelete = request.getArguments();
                            User userDelete = gson.fromJson(jsonUserDelete, User.class);
                            Controller.getInstance().deleteUser(userDelete);
                            break;
                        case UPDATE_USER:
                            String jsonUserUpdate =  request.getArguments();
                            User userUpdate = gson.fromJson(jsonUserUpdate, User.class);
                            Controller.getInstance().updateUser(userUpdate);
                            break;
                        case CHECK_PASSWORD:
                            String jsonUserPassword = request.getArguments();
                            User userPassword = gson.fromJson(jsonUserPassword, User.class);
                            boolean rezBool = Controller.getInstance().checkPassword(userPassword.getUsername(), userPassword.getPassword());
                            response.setResult(gson.toJson(rezBool));
                            break;
                        case UPDATE_PASSWORD_ONLY:
                            String jsonUserUpdatePassword = request.getArguments();
                            User userUpdatePassword = gson.fromJson(jsonUserUpdatePassword, User.class);
                            Controller.getInstance().updatePasswordOnly(userUpdatePassword.getUsername(), userUpdatePassword.getPassword());
                            break;
                        case ADD_DIRECTOR:
                            String jsonAddDirector = request.getArguments();
                            Director addDirector = gson.fromJson(jsonAddDirector, Director.class);
                            Controller.getInstance().addDirector(addDirector);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(HandleThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
