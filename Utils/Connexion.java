/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mon Pc
 */
public class Connexion {
   /* private static Connexion Test;
    private static Connexion data;

    /*public static PreparedStatement prepareStatement(String requete) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    final String url = "jdbc:mysql://127.0.0.1:3306/arco";
    final String login="root";
    final String pwd="";
    Connection cnx;
    static Connexion instance;
    
    
    public Connexion() {
        
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connextion etablie !");
        } catch (SQLException ex) {
            System.out.println("Erreur de connextion");
            System.out.println(ex.getMessage());
        }
    }
    
    public static Connexion getInstance ()
    {
       if (instance == null)
            {
            instance = new Connexion();
            }
            return instance;
    }
    
     public Connection getCnx() {
        return cnx;
    }
      
    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }
    public static Connexion getConn() {
        return instance;
    }
    
}
