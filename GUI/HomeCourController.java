package GUI;

import Entities.Cour;
import Service.Cours;
import Utils.Connexion;
import static com.microsoft.schemas.office.excel.STObjectType.Enum.table;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeCourController implements Initializable{

    static void setNotelabel(double moyenneAvis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private Label NomCourslab;

    @FXML
    private Label desclab;

    @FXML
    private Label typelab;

    @FXML
    private Label aminlab;

    @FXML
    private Label amaxlab;

    @FXML
    private Label displab;

    @FXML
    private TextField searchfield;

    @FXML
    private Button btnseance;

    @FXML
    private Button btndetails;
    
     @FXML
    private Button btntri;
     
     @FXML
    private VBox searchBox;
     
      @FXML
    private Button btnprec;

    @FXML
    private Button btnsuivant;
    
       @FXML
    private HBox hbox;
    
    private ObservableList<Cour> Cours = FXCollections.observableArrayList();
    private Cour selectedCour;
    private int currentIndex = 0;
     @FXML
    void show() {       
     try {
            Connection connection = Connexion.getInstance().getCnx();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cours WHERE disponibilité='indisponible'");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cours.add(new Cour(
                        
                        resultSet.getString("nom_cours"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getInt("age_min"),
                        resultSet.getInt("age_max"),
                        resultSet.getString("disponibilité")));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
/*     Cour cour = table.getSelectionModel().getSelectedItem();
     if (cour != null) {
    selectedCour = cour;
}
*/
    }
    
     @FXML
    void HomeSeance(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("HomeSeance.fxml"));
        Parent root= loader.load();
        btnseance.getScene().setRoot(root);
    }
    
     @FXML
    void triParAgeMin(ActionEvent event) {
        Collections.sort(Cours, Comparator.comparingInt(Cour::getAge_Min));
    }
    
@FXML
void showDetails() {
    if (!Cours.isEmpty()) {
        Cour cour = Cours.get(currentIndex);
        String ageRange = Integer.toString(cour.getAge_Min()) + " - " + Integer.toString(cour.getAge_Max());
        hbox.setVisible(true);
        searchBox.setVisible(true);
      aminlab.setText("Age : " + ageRange);
        desclab.setText(cour.getDescription());
        NomCourslab.setText(cour.getNom_cours());
        typelab.setText(cour.getType());
        displab.setText(String.format("Cours %d/%d", currentIndex + 1, Cours.size()));
    }
}

@FXML
void showNext(ActionEvent event) {
    if (currentIndex < Cours.size() - 1) {
        currentIndex++;
        showDetails();
    }
}

@FXML
void showPrevious(ActionEvent event) {
    if (currentIndex > 0) {
        currentIndex--;
        showDetails();
    }
}

@Override
public void initialize(URL location, ResourceBundle resources) {
    currentIndex = 0;
    show();
    if (!Cours.isEmpty()) {
        selectedCour = Cours.get(0); // Select the first Cour object by default
        NomCourslab.setText(selectedCour.getNom_cours());
        typelab.setText(selectedCour.getType());
        displab.setText(String.format("Cours %d/%d", currentIndex + 1, Cours.size()));
    }
}
}
  





