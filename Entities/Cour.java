/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

/**
 *
 * @author Mon Pc
 */
public class Cour {
    
    private int id_cours;
    private String nom_cours;
    private String Description;
    private String type;
    private int age_Min;
    private int age_Max;
    private String disponibilité;
    //private double rating;
    
      public Cour( String nom_cours) {
        this.nom_cours= nom_cours;}
    /*  public double getRating() {
    return rating;
}

public void setRating(double rating) {
    this.rating = rating;
}*/

   public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge_Min(int age_Min) {
        this.age_Min = age_Min;
    }

    public void setAge_Max(int age_Max) {
        this.age_Max = age_Max;
    }

    public void setDisponibilité(String disponibilité) {
        this.disponibilité = disponibilité;
    }

    
    public int getId_cours() {
        return id_cours;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public String getDescription() {
        return Description;
    }

    public String getType() {
        return type;
    }

    public int getAge_Min() {
        return age_Min;
    }

    public int getAge_Max() {
        return age_Max;
    }

    public String getDisponibilité() {
        return disponibilité;
    }
        
      public Cour(){
          
      }
     
    public Cour( String nom_cours, String Description, String type, int age_Min, int age_Max) {
        this.nom_cours = nom_cours;
        this.Description = Description;
        this.type = type;
        this.age_Min = age_Min;
        this.age_Max = age_Max; 
    }

    public Cour(int id_cours, String nom_cours, String Description, String type, int age_Min, int age_Max, String disponibilité) {
        this.id_cours = id_cours;
        this.nom_cours = nom_cours;
        this.Description = Description;
        this.type = type;
        this.age_Min = age_Min;
        this.age_Max = age_Max;
        this.disponibilité = disponibilité;
    }

  
    

    public Cour(String nom_cours, String Description, String type, int age_Min, int age_Max, String disponibilité) {
        this.nom_cours = nom_cours;
        this.Description = Description;
        this.type = type;
        this.age_Min = age_Min;
        this.age_Max = age_Max;
        this.disponibilité = disponibilité;
    }
    
   


    public Cour(int id_cours) {
        this.id_cours = id_cours;
    }

    @Override
    public String toString() {
        return "Cour\n" + " nom_cours=" + nom_cours + ", Description=" + Description + ", type=" + type + ", age_Min=" + age_Min + ", age_Max=" + age_Max + ", disponibilit\u00e9=" + disponibilité + '}';
    }

      public String toStringG() {
        return   id_cours + "" ;
    }


    }
       



