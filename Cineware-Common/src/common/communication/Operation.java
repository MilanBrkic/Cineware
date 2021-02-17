/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.communication;

import java.io.Serializable;

/**
 *
 * @author user
 */
public enum Operation implements Serializable{
    LOGIN,
    LOGOUT,
    EXIT_ALL,
    GET_COUNTRIES,
    GET_ALL_HALLS,
    GET_ALL_USERS,
    ADD_USER,
    UPDATE_USER,
    DELETE_USER,
    CHECK_PASSWORD,
    UPDATE_PASSWORD_ONLY,
    GET_ALL_DIRECTORS,
    ADD_DIRECTOR,
    UPDATE_DIRECTOR,
    DELETE_DIRECTOR,
    GET_ALL_ACTORS,
    ADD_ACTOR,
    UPDATE_ACTOR,
    DELETE_ACTOR,
    ADD_MOVIE,
    GET_ALL_MOVIES,
    UPDATE_MOVIE,
    DELETE_MOVIE,
    ADD_PROJECTION,
    GET_ALL_PROJECTIONS,
    UPDATE_PROJECTION,
    DELETE_PROJECTION,
    ADD_PRODUCT,
    GET_ALL_PRODUCTS,
    DELETE_PRODUCT,
    UPDATE_PRODUCT, 
    GET_TICKETS_FROM_PROJECTION,
    ADD_INVOICE,
    STORNO_INVOICE,
}
