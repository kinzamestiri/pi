/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Seance;
import Service.Cours;
import Service.Seances;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

public class ModSeanceController implements Initializable {

    @FXML
    private TextField tfdate;
    
    @FXML
    private Button btnsuivant;

     @FXML
    private Button btnmod;
     
    @FXML
    private TextField timeInput1;

    @FXML
    private TextField timeInput2;
    
    @FXML
    private ComboBox <Integer> comb;

    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    public ObservableList<Seance> seance = FXCollections.observableArrayList();
    
    @FXML
    void Succés_Aj(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("SuccéesAjS.fxml"));
        Parent root= loader.load();
        btnsuivant.getScene().setRoot(root);
    }
    
    @FXML
    void modifier(ActionEvent event) {
    try {
        System.out.println("comb=" + comb);
         System.out.println("comb selection model=" + comb.getSelectionModel());
        String requete = "UPDATE seance SET date_seance=?, id_cours=?, horaire_debut=?, horaire_fin=? WHERE id_seance=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, tfdate.getText());
        pst.setInt(2, Integer.parseInt(comb.getSelectionModel().getSelectedItem().toString()));
        pst.setString(3, timeInput1.getText());
        pst.setString(4, timeInput2.getText());
        //pst.setInt(5, Integer.parseInt(seanceId.getText()));
        pst.executeUpdate();
        System.out.println("Seance modifié !");
        JOptionPane.showMessageDialog(null, "Modifié avec succès");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id_cours FROM cours");

        List<Integer> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getInt("id_cours"));
        }

        comb.setItems(FXCollections.observableArrayList(ids));
        comb.getSelectionModel().selectFirst();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }

    void setTextField(String date_seance, int id_cours, String horaire_debut, String horaire_fin, int id_seance) {
     
    tfdate.setText(date_seance);
    comb.getSelectionModel().select(id_cours);
    timeInput1.setText(horaire_debut);
    timeInput2.setText(horaire_fin);

    }
}

