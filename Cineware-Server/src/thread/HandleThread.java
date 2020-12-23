/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import common.communication.Receiver;
import common.communication.Request;
import common.communication.Response;
import common.communication.Sender;
import controller.Controller;
import domain.User;
import java.net.Socket;
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

    public HandleThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
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
                            response.setResult(Controller.getInstance().getCountries());
                            break;
                        case GET_ALL_HALLS:
                            response.setResult(Controller.getInstance().getAllHalls());
                            break;
                        case GET_ALL_USERS:
                            response.setResult(Controller.getInstance().getAllUsers());
                            break;
                        case ADD_USER:
                            User userAdd = (User) request.getArguments();
                            Controller.getInstance().addUser(userAdd);
                            break;
                        case DELETE_USER:
                            User user = (User) request.getArguments();
                            Controller.getInstance().deleteUser(user);
                            break;
                        case UPDATE_USER:
                            User userUpdate = (User) request.getArguments();
                            Controller.getInstance().updateUser(userUpdate);
                            break;
                        case CHECK_PASSWORD:
                            User userPassword = (User) request.getArguments();
                            response.setResult(Controller.getInstance().checkPassword(userPassword.getUsername(), userPassword.getPassword()));
                            break;
                        case UPDATE_PASSWORD_ONLY:
                            User userUpdatePassword = (User) request.getArguments();
                            Controller.getInstance().updatePasswordOnly(userUpdatePassword.getUsername(), userUpdatePassword.getPassword());
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
