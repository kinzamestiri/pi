package GUI;

import Entities.Cour;
import Entities.Seance;
import Service.Cours;
import Service.Seances;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mon Pc
 */
public class AjouterSeanceController implements Initializable{

    @FXML
    private Button btnsuivant;

    @FXML
    private Button btnajouter;

     @FXML
    private ComboBox comb;

    @FXML
    private TextField timeInput1;

    @FXML
    private TextField timeInput2;
    
   @FXML
    private TextField tfdate;


    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    public ObservableList<Seance> seance = FXCollections.observableArrayList();
    
    @FXML
    void Succés_Aj(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("SuccéesAjS.fxml"));
        Parent root= loader.load();
        btnsuivant.getScene().setRoot(root);
    }

    @FXML
    void ajouter(ActionEvent event) {
     
        Seances s = new Seances();
        String time1 = timeInput1.getText().trim();
        String time2 = timeInput2.getText().trim();
        String dateStr = tfdate.getText().trim();
    
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        dateFormat.parse(dateStr);
        } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "La date doit être au format yyyy/MM/dd");
        return;
    }
    
    if (!time1.matches("^\\d{2}:\\d{2}$") || !time2.matches("^\\d{2}:\\d{2}$")) {
        JOptionPane.showMessageDialog(null, "Les champs de temps doivent être au format HH:MM");
        return;
    }
    
    Seance p = new Seance(dateStr, Integer.parseInt(comb.getSelectionModel().getSelectedItem().toString()), time1, time2);
    s.ajouter(p);
    JOptionPane.showMessageDialog(null, "Ajout avec succès");
}

        
        /* Seances s= new Seances();       
        Seance p = new Seance(tfdate.getText(), Integer.parseInt(comb.getSelectionModel().getSelectedItem().toString()), timeInput1.getText(),timeInput2.getText());
        s.ajouter(p);
        JOptionPane.showMessageDialog(null, "Ajout avec succés");*/
    

/**
     * Initializes the controller class.
     */


    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_cours FROM cours");
            while (rs.next()) {
                comb.getItems().add(rs.getString("id_cours"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
    
    
}

    