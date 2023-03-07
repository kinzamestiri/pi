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

public class SuccésModController implements Initializable{

    @FXML
    private Button btnafficher;

    @FXML
    private Button btnacceuil;

    @FXML
    void acceuil_cours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AcceuilCours.fxml"));
        Parent root= loader.load();
        btnacceuil.getScene().setRoot(root);
    }

    @FXML
    void afficher_cours(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherCours.fxml"));
        Parent root= loader.load();
        btnafficher.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

}
