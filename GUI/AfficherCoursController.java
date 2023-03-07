package GUI;

import Entities.Cour;
import Service.Cours;
import Utils.Connexion;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AfficherCoursController implements Initializable{
   
    @FXML
    private Button btnafficher;

    @FXML
    private TableView<Cour> table;

    @FXML
    private TableColumn<Cour, Integer> id_cours;

    @FXML
    private TableColumn<Cour, String> nom_cours;

    @FXML
    private TableColumn<Cour, String> Description;

    @FXML
    private TableColumn<Cour, String> type;

    @FXML
    private TableColumn<Cour, Integer> age_Min;

    @FXML
    private TableColumn<Cour, Integer> age_Max;

    @FXML
    private TableColumn<Cour, String> disponibilité;
    
    
    @FXML
    private TableColumn<Cour, String> editCol;

    @FXML
    private Button btnacceuil;

    @FXML
    private Button btnajouter;

    
    @FXML
    private Button btnarchiver;
    
    @FXML
    private Button btntrimin;

     @FXML
    private TextField searchfield;
    
    
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    public ObservableList<Cour> Cours = FXCollections.observableArrayList();

    @FXML
    void AcceuilCours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AcceuilCours.fxml"));
        Parent root= loader.load();
        btnacceuil.getScene().setRoot(root);
    }


    @FXML
    void show() {
        // table.getItems().clear();
        table.getItems().clear();
        table.refresh();
        try {
            String requete ="SELECT * FROM cours  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               Cour c =new Cour();
                c.setId_cours(rs.getInt(1));
                c.setNom_cours(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setType(rs.getString(4));
                c.setAge_Min(rs.getInt(5));
                c.setAge_Max(rs.getInt(6));
                c.setDisponibilité(rs.getString(7));
                Cours.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        id_cours.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("id_cours"));

        nom_cours.setCellValueFactory(new PropertyValueFactory<Cour, String>("nom_cours"));

        Description.setCellValueFactory(new PropertyValueFactory<Cour, String>("Description"));

        type.setCellValueFactory(new PropertyValueFactory<Cour, String>("type"));
        
        age_Min.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("age_Min"));
        
        age_Max.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("age_Max"));
        
         disponibilité.setCellValueFactory(new PropertyValueFactory<Cour, String>("disponibilité"));
         
          Callback<TableColumn<Cour, String>, TableCell<Cour, String>> cellFoctory = (TableColumn<Cour, String> param) -> {
            // make cell containing buttons
            final TableCell<Cour, String> cell = new TableCell<Cour, String>() {
                
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                           Cour cou = null;

                            try {
                                cou = table.getSelectionModel().getSelectedItem();
                                
                                String requete= "UPDATE cours SET disponibilité='indisponible' WHERE id_cours ="+cou.getId_cours();
                                System.out.println(cou.getId_cours());
                                PreparedStatement pst = cnx.prepareStatement(requete);
                                pst.execute();
                                show();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherCoursController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                            Cour cou = null;
                            cou =table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/ModCours.fxml"));
                            try {
                                loader.load();

                                ModCoursController Ed = loader.getController();
                                //    Ed.setUpdate(true);
                                Ed.setTextField(cou.getNom_cours(), cou.getDescription(), cou.getType(), cou.getAge_Min(),cou.getAge_Max(),cou.getId_cours());
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                                show();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherCoursController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);

         
        
        // Ajouter les colonnes à la table view
        table.setItems(Cours);
    }

    @FXML
    void Afficher_Cours(ActionEvent event) {
        show();
    }
    
    
    
    @FXML
    void Ajouter_Cours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AjouterCours.fxml"));
        Parent root= loader.load();
        btnajouter.getScene().setRoot(root);
    }

    @FXML
    void Archiver_Cours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/ArchiveCours.fxml"));
        Parent root= loader.load();
        btnarchiver.getScene().setRoot(root);
    }
    
    @FXML
    private void handleTriNomButtonAction(ActionEvent event) {
   /* Cours coursService = new Cours();
    List<Cour> cours = Cours.afficher();
    cours.sort(Comparator.comparing(Cour::getNom_cours));
    table.setItems(FXCollections.observableArrayList(cours));*/


}

    
    
    
   /* private Cours se = new Cours();

    private void searchRec() {
         FilteredList<Cour> filteredData = new FilteredList<>(FXCollections.observableArrayList(se.afficher()),b->true);
         searchfield.textProperty().addListener((observable,oldValue,newValue)-> {
             filteredData.setPredicate(rec-> {
                 if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter = newValue.toLowerCase();
                if (rec.getNom_cours().toLowerCase().contains(lowerCaseFilter)) {
                 return true;
                } else if (rec.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (rec.getType().toLowerCase().contains(lowerCaseFilter)) {
                     return true;
                } else if (String.valueOf(rec.getAge_Min()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(rec.getAge_Max()).contains(lowerCaseFilter)) {
                   return true;
                } else if (rec.getDisponibilité().toLowerCase().contains(lowerCaseFilter)) {
                   return true;
                } else {
                 return false;
                }

             });
         });
         SortedList<Cour> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(table.comparatorProperty());
         table.setItems(sortedData); }
    /*@FXML
   private void searchRec(ActionEvent event) {
       Cours cou = new Cours();
         FilteredList<Cour> filteredData = new FilteredList<>(FXCollections.observableArrayList(cou.afficher()),b->true);
         searchfield.textProperty().addListener((observable,oldValue,newValue)-> {
             filteredData.setPredicate(rec-> {
                 if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter = newValue.toLowerCase();
                 if (rec.getNom_cours().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                 return true;
                 }else
                 return false ;
                 
             });
         });
         searchfield1.textProperty().addListener((observable,oldValue,newValue)-> {
             filteredData.setPredicate(rec-> {
                 if (newValue == null || newValue.isEmpty()){
                     return true;
                 }
                 String lowerCaseFilter = newValue.toLowerCase();
                 if (String.valueOf(rec.getAge_Min()).toLowerCase().indexOf(lowerCaseFilter)!= -1){
                 return true;
                 }else
                 return false ;
                 
             });
         });
         SortedList<Cour> sortedData = new SortedList<>(filteredData);
         sortedData.comparatorProperty().bind(table.comparatorProperty());
         table.setItems(sortedData); 
    }*/

   
  

   /* List<Cour> filteredList = Cours.stream()
        .filter(c -> nom.isEmpty() || c.getNom_cours().contains(nom))
        .filter(c -> description.isEmpty() || c.getDescription().contains(description))
        .filter(c -> Type.isEmpty() || c.getType().contains(Type))
        .filter(c -> Disponibilité.isEmpty() || c.getDisponibilité().contains(Disponibilité))
        .filter(c -> c.getAge_Min() >= ageMin && c.getAge_Max() <= ageMax)
        .collect(Collectors.toList());

// display filtered data in table
        table.setItems(FXCollections.observableArrayList(filteredList));*/


    @FXML
void triParAgeMin(ActionEvent event) {
    // trier les cours par age minimum
    Cours.sort(Comparator.comparingInt(Cour::getAge_Min));
    // mettre à jour la table view
    table.setItems(Cours);
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
        
        FilteredList<Cour> filteredList = new FilteredList<>(Cours, p -> true);
        // Set the table view items to the filtered list
        table.setItems(filteredList);
        // Add a listener to the search field to filter the table view as the user types
        searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
        // Set the predicate (filter) for the filtered list based on the search query
         filteredList.setPredicate(cour -> {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        String lowerCaseQuery = newValue.toLowerCase();

        // Check if the course name or description contains the search query
        if (cour.getNom_cours().toLowerCase().contains(lowerCaseQuery)) {
            return true;
        } else if (cour.getDescription().toLowerCase().contains(lowerCaseQuery)) {
            return true;
        }else if (cour.getType().toLowerCase().contains(lowerCaseQuery)) {
            return true;
        }else if (String.valueOf(cour.getAge_Min()).toLowerCase().contains(lowerCaseQuery)) {
            return true;
        }else if (String.valueOf(cour.getAge_Max()).toLowerCase().contains(lowerCaseQuery)) {
            return true;
        }else if (cour.getDisponibilité().toLowerCase().contains(lowerCaseQuery)) {
            return true;
        }
        return false;
    });
});
         
    }
}
    
