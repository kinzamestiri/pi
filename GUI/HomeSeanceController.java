package GUI;

import Entities.Cour;
import Entities.Seance;
import Service.Cours;
import Utils.Connexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeSeanceController implements Initializable {

  
    @FXML
    private Label NomCourslab;
@FXML
    private Label datelabel;

    @FXML
    private Button btndetails;

    @FXML
    private VBox searchBox;

    @FXML
    private Button btnprec;

    @FXML
    private Button btnsuivant;

    @FXML
    private Label displab;

    @FXML
    private HBox hbox;

    @FXML
    private Label hdlabel;

    @FXML
    private Label hflabel;
    
    
    @FXML
    private Label notelabel;
    
    @FXML
    private Button btncours;

    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    private ObservableList<Seance> Seances = FXCollections.observableArrayList();
    private Seance selectedSeance;
    private int currentIndex = 0;
    
    
     @FXML
    void HomeCours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("HomeCour.fxml"));
        Parent root= loader.load();
        btncours.getScene().setRoot(root);
    }

    @FXML
    void show() {       
      try {
            Connection connection = Connexion.getInstance().getCnx();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM seance ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Seances.add(new Seance(
                        
                        resultSet.getInt("id_seance"),
                        resultSet.getString("date_seance"),
                        resultSet.getInt("id_cours"),
                        resultSet.getString("horaire_debut"),
                        resultSet.getString("horaire_fin"),
                        resultSet.getInt("Avis")));
                        } 
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    
    @FXML
    void showDetails() throws SQLException {
        
        System.out.println();

        if (!Seances.isEmpty()) {
        Seance seance = Seances.get(currentIndex);

        String HourRange = seance.getHoraire_debut() + " - " + seance.getHoraire_fin();
        hbox.setVisible(true);
        searchBox.setVisible(true);
        hdlabel.setText("Horaire : " + HourRange);
        datelabel.setText(seance.getDate_seance());
        NomCourslab.setText(Integer.toString(seance.getId_cours()));
notelabel.setText(Integer.toString(selectedSeance.getAvis()+1));
        
        
           // notelabel.setText("Note : " + moyenne + "/5");
          //  seance.setAvis(moyenne);
           //double moyenneAvis = calculerMoyenneAvis();
        displab.setText(String.format("Cours %d/%d", currentIndex + 1,Seances.size()));
    }
    
}


    @FXML
    void showNext(ActionEvent event) throws SQLException {
       if (currentIndex < Seances.size() - 1) {
        currentIndex++;
        showDetails();
    }}
    
    @FXML
    void showPrevious(ActionEvent event) throws SQLException {
         if (currentIndex > 0) {
        currentIndex--;
        showDetails();
    
    }
    }

   private double calculerMoyenneAvis() {
    int totalRatings = 0;
    int numSeances = Seances.size();

    for (Seance seance : Seances) {
        totalRatings += seance.getAvis();
    }

    if (numSeances == 0) {
        return 0;
    } else {
        return (double) totalRatings / numSeances;
    }
}

    @Override
public void initialize(URL location, ResourceBundle resources) {
    currentIndex = 0;
    show();
    if (!Seances.isEmpty()) {
        
        selectedSeance = Seances.get(currentIndex); // Select the first Seance object by default
        NomCourslab.setText(String.valueOf(selectedSeance.getId_cours()));
        datelabel.setText(selectedSeance.getDate_seance());
notelabel.setText(Integer.toString(selectedSeance.getAvis()+1));
        displab.setText(String.format("Séance %d/%d", currentIndex + 1, Seances.size()));
    }
}


}