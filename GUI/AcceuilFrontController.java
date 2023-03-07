package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class AcceuilFrontController implements Initializable {

    @FXML
    private Button btnCours;

    @FXML
    private Button btnseance;

    @FXML
    void AffListeCours(ActionEvent event) throws IOException {
       FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/ListeCours.fxml"));
        Parent root= loader.load();
       btnCours.getScene().setRoot(root);
    }

    @FXML
    void AffListeSeance(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/ListeSeance.fxml"));
        Parent root= loader.load();
        btnseance.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }

}

 


    /**
     * Initializes the controller class.
     */
   

