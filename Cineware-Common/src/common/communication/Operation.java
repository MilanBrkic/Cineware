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
    LOGIN,
    LOGOUT
}
