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
import domain.Actor;
import domain.Director;
import domain.Hall;
import domain.Invoice;
import domain.Movie;
import domain.Projection;
import domain.User;
import domain.Product;
import domain.Ticket;
import java.net.Socket;
import java.util.ArrayList;
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
    HandleThread[] clients;
    HandleExit[] exits;
    
    public HandleThread(Socket socket, HandleThread[] clients, HandleExit[] exits) {
        this.clients = clients;
        this.socket = socket;
        this.exits = exits;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Socket getSocket() {
        return socket;
    }

    
    
    
    @Override
    public void run() {
        while (true) {
            try {
                Request request = null;
                try {
                    request = (Request) receiver.receive();
                } catch (Exception e) {
                    break;
                }
                if(request==null) break;
                
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            String jsonLoggedUser = request.getArguments();
                            User user = gson.fromJson(jsonLoggedUser, User.class);
                            Controller.getInstance().newLoggedInUser(user);
                            break;
                        case LOGOUT:
                            String jsonLoggedOutUser = request.getArguments();
                            User userOut = gson.fromJson(jsonLoggedOutUser, User.class);
                            Controller.getInstance().loggedOutUser(userOut);
                            break;
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
                        case GET_ALL_DIRECTORS:
                            ArrayList<Director> directors = Controller.getInstance().getAllDirectors();
                            String jsonDirectors = gson.toJson(directors);
                            response.setResult(jsonDirectors);
                            break;
                        case UPDATE_DIRECTOR:
                            String jsonUpdateDirector = request.getArguments();
                            Director updateDirector = gson.fromJson(jsonUpdateDirector, Director.class);
                            Controller.getInstance().updateDirector(updateDirector);
                            break;
                        case DELETE_DIRECTOR:
                            String jsonDeleteDirector = request.getArguments();
                            Director deleteDirector = gson.fromJson(jsonDeleteDirector, Director.class);
                            Controller.getInstance().deleteDirector(deleteDirector);
                            break;
                        case ADD_ACTOR:
                            String jsonAddActor = request.getArguments();
                            Actor addActor = gson.fromJson(jsonAddActor, Actor.class);
                            Controller.getInstance().addActor(addActor);
                            break;
                        case GET_ALL_ACTORS:
                            ArrayList<Actor> actors = Controller.getInstance().getAllActors();
                            String jsonActors = gson.toJson(actors);
                            response.setResult(jsonActors);
                            break;
                        case UPDATE_ACTOR:
                            String jsonUpdateActor = request.getArguments();
                            Actor updateActor = gson.fromJson(jsonUpdateActor, Actor.class);
                            Controller.getInstance().updateActor(updateActor);
                            break;
                        case DELETE_ACTOR:
                            String jsonDeleteActor = request.getArguments();
                            Actor deleteActor = gson.fromJson(jsonDeleteActor, Actor.class);
                            Controller.getInstance().deleteActor(deleteActor);
                            break;
                        case ADD_MOVIE:
                            String jsonAddMovie = request.getArguments();
                            Movie addMovie = gson.fromJson(jsonAddMovie, Movie.class);
                            Controller.getInstance().addMovie(addMovie);
                            break;
                        case UPDATE_MOVIE:
                            String jsonUpdateMovie = request.getArguments();
                            Movie updateMovie = gson.fromJson(jsonUpdateMovie, Movie.class);
                            Controller.getInstance().updateMovie(updateMovie);
                            break;
                        case DELETE_MOVIE:
                            String jsonDeleteMovie = request.getArguments();
                            Movie deleteMovie = gson.fromJson(jsonDeleteMovie, Movie.class);
                            Controller.getInstance().deleteMovie(deleteMovie);
                            break;
                        case GET_ALL_MOVIES:
                            ArrayList<Movie> allMovies = Controller.getInstance().getAllMovies();
                            String jsonAllMovies = gson.toJson(allMovies);
                            response.setResult(jsonAllMovies);
                            break;
                        case ADD_PROJECTION:
                            String jsonAddProjection = request.getArguments();
                            Projection addProjection = gson.fromJson(jsonAddProjection, Projection.class);
                            Controller.getInstance().addProjection(addProjection);
                            break;
                        case GET_ALL_PROJECTIONS:
                            ArrayList<Projection> allProjections = Controller.getInstance().getAllProjections();
                            String jsonAllProjections = gson.toJson(allProjections);
                            response.setResult(jsonAllProjections);
                            break;
                        case UPDATE_PROJECTION:
                            String jsonUpdateProjection = request.getArguments();
                            Projection updateProjection = gson.fromJson(jsonUpdateProjection, Projection.class);
                            Controller.getInstance().updateProjection(updateProjection);
                            break;
                        case DELETE_PROJECTION:
                            String jsonDeleteProjection = request.getArguments();
                            Projection deleteProjection = gson.fromJson(jsonDeleteProjection, Projection.class);
                            Controller.getInstance().deleteProjection(deleteProjection);
                            break;
                        case ADD_PRODUCT:
                            String jsonAddProduct = request.getArguments();
                            Product addProduct = gson.fromJson(jsonAddProduct, Product.class);
                            Controller.getInstance().addProduct(addProduct);
                            break;
                        case GET_ALL_PRODUCTS:
                            ArrayList<Product> allProducts = Controller.getInstance().getAllProducts();
                            String jsonAllProducts = gson.toJson(allProducts);
                            response.setResult(jsonAllProducts);
                            break;
                        case UPDATE_PRODUCT:
                            String jsonUpdateProduct = request.getArguments();
                            Product updateProduct = gson.fromJson(jsonUpdateProduct, Product.class);
                            Controller.getInstance().updateProduct(updateProduct);
                            break;
                        case DELETE_PRODUCT:
                            String jsonDeleteProduct = request.getArguments();
                            Product deleteProduct = gson.fromJson(jsonDeleteProduct, Product.class);
                            Controller.getInstance().deleteProduct(deleteProduct);
                            break;
                        case GET_TICKETS_FROM_PROJECTION:
                            String jsonProjection = request.getArguments();
                            Projection projection = gson.fromJson(jsonProjection, Projection.class);
                            ArrayList<Ticket> tickets = Controller.getInstance().getAllTicketsFromProjection(projection);
                            String jsonTickets = gson.toJson(tickets);
                            response.setResult(jsonTickets);
                            break;
                        case ADD_INVOICE:
                            Invoice addInvoice = (Invoice) request.getObject();
                            Controller.getInstance().addInvoice(addInvoice);
                            break;
                        case GET_ALL_INVOICES:
                            ArrayList<Invoice> allInvoices = Controller.getInstance().getAllInvoices();
                            response.setResultObject(allInvoices);
                            break;
                        case STORNO_INVOICE:
                            Invoice stornoInvoice = (Invoice) request.getObject();
                            Controller.getInstance().stornoInvoice(stornoInvoice);
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
        
        for(int i = 0;i<clients.length;i++){
            if(clients[i]==this){
                clients[i] = null;
                exits[i] = null;
            }
        }
    }

}
