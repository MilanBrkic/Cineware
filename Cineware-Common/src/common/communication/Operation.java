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
    DELETE_DIRECTOR
}
