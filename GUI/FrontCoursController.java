/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Utils.Connexion;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class FrontCoursController implements Initializable {

    @FXML
    private FlowPane cardViewContainer;

    @FXML
    private ImageView imageView;

    private Connection connection;
     String query = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
     java.sql.Connection cnx = Connexion.getInstance().getCnx();
    public void initialize(URL location, ResourceBundle resources) {
        // Récupérer la connexion à la base de données
        connection = Connexion.getInstance().getCnx();
        
        // Récupérer les données des cours depuis la base de données
      try {
            String requete ="SELECT * FROM cours  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            int rowIndex = 0;
            while (rs.next()) {
                int id_cours = rs.getInt("id_cours");
                String nom_cours = rs.getString("nom_cours");
                String description = rs.getString("description");
                String type = rs.getString("type");
                int age_Min = rs.getInt("age_min");
                int age_Max = rs.getInt("age_max");
                String disponibilité= rs.getString("disponibilité");
              


                // Créer un CardView pour ce cours
                FlowPane cardView = new FlowPane();
                cardView.getStyleClass().add("card-view");

                Label nomCoursLabel = new Label(nom_cours);
                nomCoursLabel.getStyleClass().add("nom-cours");
                cardView.getChildren().add(nomCoursLabel);

                Label descriptionLabel = new Label(description);
                descriptionLabel.getStyleClass().add("description");
                cardView.getChildren().add(descriptionLabel);

                Label typeLabel = new Label(type);
                typeLabel.getStyleClass().add("type");
                cardView.getChildren().add(typeLabel);

                Label agelab = new Label(age_Min + " - " + age_Max + " ans");
                agelab.getStyleClass().add("age");
                cardView.getChildren().add(agelab);

                Label labdisp = new Label(disponibilité);
               labdisp.getStyleClass().add("disponibilité");
                cardView.getChildren().add(labdisp);

                // Charger l'image à partir de l'URL
               
                

                // Positionner le CardView dans la grille
                cardView.setLayoutX(20 + (rowIndex % 3) * 220);
                cardView.setLayoutY(20 + (rowIndex / 3) * 220);
                cardViewContainer.getChildren().add(cardView);

                rowIndex++;
            }

            rs.close();
            pst.close();
        } catch (SQLException e) {
        }
        
      
    }
}


/**
 * FXML Controller class
 *
 * @author Mon Pc
 */


    /**
     * Initializes the controller class.
     */
   
