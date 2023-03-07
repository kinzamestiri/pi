/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_dev;

import Entities.Cour;
import Entities.Seance;
import Service.Cours;
import Service.Iservicek;
import Service.Seances;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Mon Pc
 */
public class PI_DEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cour c1 = new Cour("dance classique","bbb","sport",5,12);
        Cour c2 = new Cour("music","ccc","art",12,18);
        Cour c3 = new Cour(6);

       Cours CoursService = new Cours ();
     // CoursService.ajouter(c2);
       // CoursService.supprimer(c3);
      // CoursService.modifier(c1);
        //System.out.println(CoursService.afficher());
         
      Seance s1 = new Seance("03/08/2001",18,"","");   
      Seance s2 = new Seance("22-08-2020",6,"18:50:00","18:50:00");
      Seances SeancesService = new Seances ();
     // SeancesService.ajouter(s1);
       //SeancesService.supprimer(s1);
      //SeancesService.modifier(s2);
     //  System.out.println(SeancesService.afficher());
    }}
    
    

