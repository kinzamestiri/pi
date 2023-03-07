/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Service.Cours;
import Utils.Connexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import Entities.Cour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 *
 * @author Mon Pc
 */

public class Cours  implements Iservicek <Cour> {

   

  

    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    @Override
        public void ajouter(Cour c) {
        try {
            String requete = "INSERT INTO Cours (nom_cours,Description,type,age_Min,age_Max,disponibilité) VALUES (?,?,?,?,?,'disponible')";
            PreparedStatement pst1 = cnx.prepareStatement(requete);
            pst1.setString(1,c.getNom_cours());
            pst1.setString(2, c.getDescription());
            pst1.setString(3, c.getType());
            pst1.setInt(4,c.getAge_Min());
            pst1.setInt(5,c.getAge_Max());
            
            pst1.executeUpdate();
            System.out.println("cours ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Cour c) {
        try {
         String requete = "UPDATE Cours SET disponibilité='indisponible' WHERE id_Cours= ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,c.getId_cours());
            pst.executeUpdate();
            System.out.println("Cours supprimée avec succés  ! \n");
        } 
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression \n" + ex.getMessage());
        }
    }

    @Override
    public void modifier(Cour c) {
        try { 
            String requete = "UPDATE Cours SET nom_Cours=?,Description=?, type=?, age_Min=?,age_Max=?,disponibilité=? WHERE id_Cours= ?";;
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7,c.getId_cours());
            pst.setString(1,c.getNom_cours());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getType());
            pst.setInt(4,c.getAge_Min());
            pst.setInt(5,c.getAge_Max());
            pst.setString(6, c.getDisponibilité());
            pst.executeUpdate();
            System.out.println("Cours modifié avec succés \n ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modification \n " + ex.getMessage());
        }
    }

  
    @Override
    public List<Cour> afficher() {
        List<Cour> cours = new ArrayList<>();
        try {
            String qry ="SELECT * FROM `cours` ";
             PreparedStatement pst = cnx.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Cour c =new Cour();
              //  c.setId_cours(rs.getInt(1));
                c.setNom_cours(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setType(rs.getString(4));
                c.setAge_Min(rs.getInt(5));
                c.setAge_Max(rs.getInt(6));
                c.setDisponibilité(rs.getString(7));

                
                cours.add(c);
            }
            return cours;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cours;
        
    }

    public List<Cour> afficherCo() {
     
        List<Cour> cours = new ArrayList<>();
        try {
            String qry ="SELECT id_cours FROM `cours` ";
             PreparedStatement pst = cnx.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Cour c =new Cour();
               c.setId_cours(rs.getInt(1));   
               cours.add(c);
            }
            return cours;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cours;
        
       
}
    
    
   /* public List<Cour> TriNom() {
    List<Cour> cours = afficher();
    Collections.sort(cours, new Comparator<Cour>() {
        @Override
        public int compare(Cour c1, Cour c2) {
            return c1.getNom_cours().compareTo(c2.getNom_cours());
        }
    });
    return cours;
}

    /*  public List<Cour> TriNom() {
    List<Cour> coursList = afficher();
    Collections.sort(coursList, Comparator.comparing(Cour::getNom_cours).reversed());
    return coursList;
}

public static Comparator<Cour> CourComparator = (Cour c1, Cour c2) -> {
    String nom1 = c1.getNom_cours();
    String nom2 = c2.getNom_cours();
    return nom1.compareTo(nom2);
};
/*public  List<Cour> triNom() {

return this.afficher().stream().sorted((c1, c2) -> c2.getNom_cours().compareTo(c1.getNom_cours())).collect(Collectors.toList());
}
  
//    

public int getIdCours(String type_cours) {
    try {
        PreparedStatement pst = cnx.prepareStatement("SELECT * FROM cour WHERE type_cours = ?");
        pst.setString(1, type_cours);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            return rs.getInt("id_cours");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
    }
    return -1;
}
 public ArrayList<Cour> rechercheCoursParNom(String nom_cours) {
    ArrayList<Cour> cours = new ArrayList<>();
    String requete = "SELECT * FROM cour WHERE nom_cours = ?";
    try {
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, nom_cours);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            cours.add(new Cour(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
    }
    return cours;
}*/




}
