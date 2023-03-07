/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
/**
 *
 * @author Mon Pc
 */
public class Seance {

    public static Object stream() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id_seance;
    private String date_seance;
    private int id_cours;
    //private int id_salle;
  //  private int id_coach;
    private String horaire_debut;
    private String horaire_fin;
    private int Avis;
    

  

  

    public int getId_seance() {
        return id_seance;
    }

    public String getDate_seance() {
        return date_seance;
    }

    public int getId_cours() {
        return id_cours;
    }

  /*  public int getId_salle() {
        return id_salle;
    }

    public int getId_coach() {
        return id_coach;
    }*/
    public String getHoraire_debut() {
        return horaire_debut;
    }

    public String getHoraire_fin() {
        return horaire_fin;
    }

    public int getAvis() {
        return Avis;
    }

    public void setAvis(int Avis) {
        this.Avis = Avis;
    }

   

    
    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public void setDate_seance(String date_seance) {
        this.date_seance = date_seance;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

  /*  public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }*/

    public void setHoraire_debut(String horaire_debut) {
        this.horaire_debut = horaire_debut;
    }

    public void setHoraire_fin(String horaire_fin) {
        this.horaire_fin = horaire_fin;
    }

  /*  public void setAvis(int Avis) {
        this.Avis= Avis;
    }
*/
     
     
      
    public Seance() {
    }

    public Seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public Seance(int id_seance, String date_seance, int id_cours, String horaire_debut, String horaire_fin) {
        this.id_seance = id_seance;
        this.date_seance = date_seance;
        this.id_cours = id_cours;
        this.horaire_debut = horaire_debut;
        this.horaire_fin = horaire_fin;
    }

    public Seance(String date_seance, int id_cours, String horaire_debut, String horaire_fin) {
        this.date_seance = date_seance;
        this.id_cours = id_cours;
        this.horaire_debut = horaire_debut;
        this.horaire_fin = horaire_fin;
    }

    public Seance(int id_seance, String date_seance, int id_cours, String horaire_debut, String horaire_fin, int Avis) {
        this.id_seance = id_seance;
        this.date_seance = date_seance;
        this.id_cours = id_cours;
        this.horaire_debut = horaire_debut;
        this.horaire_fin = horaire_fin;
        this.Avis = Avis;
    }


    

  

    @Override
    public String toString() {
        return "Seance{" + "id_seance=" + id_seance + ", date_seance=" + date_seance + ", id_cours=" + id_cours + ", horaire_debut=" + horaire_debut + ", horaire_fin=" + horaire_fin + '}';
    }
    
   


    
 
  
    

   
   
    
    
    
}
