/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Utils.Connexion;
import static java.awt.PageAttributes.MediaType.C;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import Entities.Cour;
import Entities.Seance;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Mon Pc
 */
public class Seances  implements Iservicek <Seance>{
java.sql.Connection cnx = Connexion.getInstance().getCnx();

    
    @Override
    public void ajouter(Seance s) {
        try {
            String requete = "INSERT INTO seance (date_seance,id_cours,horaire_debut,horaire_fin) VALUES (?,?,?,?)";
            PreparedStatement pst1 = cnx.prepareStatement(requete);
          
            pst1.setString(1,s.getDate_seance());
            pst1.setInt(2,s.getId_cours());
           // pst1.setInt(3,s.getId_salle());
           // pst1.setInt(4,s.getId_coach());
            pst1.setString(3,s.getHoraire_debut());
            pst1.setString(4,s.getHoraire_fin());
            pst1.executeUpdate();
            System.out.println("Seance ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Seance s) {
         try {
         String requete = "DELETE FROM seance WHERE id_seance=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,s.getId_seance());
            pst.executeUpdate();
            System.out.println("Seance supprimée avec succés  ! \n");
        } 
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression \n" + ex.getMessage());
        }
    }

    @Override
    public void modifier(Seance s) {
        try { 
            String requete = "UPDATE seance SET date_seance=?,id_cours=?,horaire_debut=?,horaire_fin=? WHERE id_seance= ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7,s.getId_seance());
            pst.setString(1,s.getDate_seance());
            pst.setInt(2,s.getId_cours());
           // pst.setInt(3,s.getId_salle());
            //pst.setInt(4,s.getId_coach());
            pst.setString(3,s.getHoraire_debut());
            pst.setString(4,s.getHoraire_fin());
            pst.executeUpdate();
            System.out.println("Seance modifié avec succés \n ");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la modification \n " + ex.getMessage());
        }
    }

   /* @Override
    public List<Seance> afficher() {
        ArrayList<Seance> seances = new ArrayList<>();
        String requete = "Select * from seance";

        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                seances.add(new Seance( rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getInt (4),rs.getInt(5),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }
        return seances;
}*/
    
    @Override
    public List<Seance> afficher() {
        List<Seance> seances = new ArrayList<>();
        try {
            String qry ="SELECT * FROM `seance` ";
             PreparedStatement pst = cnx.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Seance c =new Seance();
                c.setId_seance(rs.getInt(1));
                c.setDate_seance(rs.getString(2));
                c.setId_cours(rs.getInt(3));
               // c.setId_salle(rs.getInt(4));
               // c.setId_coach(rs.getInt(5));
                c.setHoraire_debut(rs.getString(4));
                c.setHoraire_fin(rs.getString(5));
                c.setAvis(rs.getInt(6));


                
                seances.add(c);
            }
            return seances;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return seances;
        
    }
    public List<Seance> afficherS() {
     
        List<Seance> seances = new ArrayList<>();
        try {
            String qry ="SELECT id_cours FROM `seance` ";
             PreparedStatement pst = cnx.prepareStatement(qry);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Seance c =new Seance();
                c.setId_seance(rs.getInt(1));
                seances.add(c);
            }
            return seances;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return seances;
        
       
}
    }
    

