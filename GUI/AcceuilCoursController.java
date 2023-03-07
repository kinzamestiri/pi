/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author Mon Pc
 */


public class AcceuilCoursController implements Initializable {
    @FXML
    private Button btnCours;

    @FXML
    private Button btnseance;

    @FXML
    void AffListeCours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/AfficherCours.fxml"));
        Parent root= loader.load();
        btnCours.getScene().setRoot(root);
    }

    @FXML
    void AffListeSeance(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/AfficherSeance.fxml"));
        Parent root= loader.load();
        btnseance.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}



    
 