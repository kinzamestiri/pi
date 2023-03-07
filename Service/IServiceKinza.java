/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.SQLDataException;
import java.util.List;

/**
 *
 * @author Mon Pc
 * @param <T>
 */
public interface IServiceKinza <T>{
    void add(T t) throws SQLDataException;
    void delete(T t) throws SQLDataException;
    void update(T t) throws SQLDataException;
    List <T> afficher()throws SQLDataException;
    
}
