package GUI;

import Entities.Cour;
import Service.Cours;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class AjouterCoursController implements Initializable{
    
     @FXML
    private Button btnsuivant;
     
    @FXML
    private Button btnajouter;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tftype;

    @FXML
    private TextField tfagemin;

    @FXML
    private TextField tfagemax;
    
    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    
    public ObservableList<Cour> Cours = FXCollections.observableArrayList();
    
    private boolean verif() {
    // Add your verification logic here, for example:
    if (tfnom.getText().isEmpty() || !tfnom.getText().matches("[a-zA-Z]+")) {
        JOptionPane.showMessageDialog(null, "Le champ nom est obligatoire et doit contenir uniquement des lettres de l'alphabet.");
        return false;
    }
    if (tfdescription.getText().isEmpty()  || !tfdescription.getText().matches("[a-zA-Z]+")) {
        JOptionPane.showMessageDialog(null, "Le champ description est obligatoire et et doit contenir uniquement des lettres de l'alphabet.");
   return false;
    }
    if (tftype.getText().isEmpty()  || !tftype.getText().matches("[a-zA-Z]+")) {
        JOptionPane.showMessageDialog(null, "Le champ type est obligatoire et et doit contenir uniquement des lettres de l'alphabet.");
   return false;
    }
   
      if (tfagemin.getText().isEmpty() || !tfagemin.getText().matches("[1-9]+")) {
        JOptionPane.showMessageDialog(null, "Le champ Age est obligatoire et doit contenire des chiffres.");
        return false;
    }
       if (tfagemax.getText().isEmpty() || !tfagemax.getText().matches("\\d+")) {
        JOptionPane.showMessageDialog(null, "Le champ Age est obligatoire et doit contenire des chiffres.");
        return false;
    }
     return true;
}

    @FXML
    void ajouter(ActionEvent event) {
         Cours s= new Cours();
         if (verif()) {
        Cour p = new Cour(tfnom.getText(),tfdescription.getText(), tftype.getText(),Integer.parseInt(tfagemin.getText()), Integer.parseInt(tfagemax.getText()));
        s.ajouter(p);
        JOptionPane.showMessageDialog(null, "Ajout avec succés");
}
         
    }
    @FXML
    void Succés_Aj(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("SuccésAj.fxml"));
        Parent root= loader.load();
        btnsuivant.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}


