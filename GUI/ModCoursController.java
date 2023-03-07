package GUI;

import Entities.Cour;
import Service.Cours;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class ModCoursController implements Initializable{
     @FXML
    private TextField idcoursM;
    
    @FXML
    private TextField tfdispo;
     
    @FXML
    private Button btnsuivant;

    @FXML
    private Button btnmodifier;

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
       if (tfagemax.getText().isEmpty() || !tfagemax.getText().matches("[1-9]+")) {
        JOptionPane.showMessageDialog(null, "Le champ Age est obligatoire et doit contenire des chiffres.");
        return false;
    }
      
     return true;
}
    @FXML
    void Modifier_Cours(ActionEvent event) {
        if (verif()) {
        try {
            String requete = "UPDATE Cours SET nom_Cours=?,Description=?, type=?, age_Min=?,age_Max=? WHERE id_Cours= ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6,Integer.parseInt(idcoursM.getText()));
            pst.setString(1,tfnom.getText());
            pst.setString(2, tfdescription.getText());
            pst.setString(3, tftype.getText());
            pst.setInt(4,Integer.parseInt(tfagemin.getText()));
            pst.setInt(5,Integer.parseInt(tfagemax.getText()));

            pst.executeUpdate();
            System.out.println("Cours modifié !");

           JOptionPane.showMessageDialog(null, "Modifié avec succe");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


        }
    }



   @FXML
    void Succés_Mod(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("SuccésMod.fxml"));
        Parent root= loader.load();
        btnsuivant.getScene().setRoot(root);
    }

    void setTextField(String nom_cours, String Description,String type,int age_Min,int age_Max,int id_cours) {

        tfnom.setText(nom_cours);
        tfdescription.setText(Description);
        tftype.setText(type);
        tfagemin.setText(String.valueOf(age_Min));
        tfagemax.setText(String.valueOf(age_Max));
        idcoursM.setText(String.valueOf(id_cours));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }

    
}

