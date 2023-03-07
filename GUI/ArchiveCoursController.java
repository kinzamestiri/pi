package GUI;

import Entities.Cour;
import Utils.Connexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class ArchiveCoursController implements Initializable{

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
    private Button btnliste;
    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    public ObservableList<Cour> Cours = FXCollections.observableArrayList();

    @FXML
    void AcceuilCours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
        Parent root= loader.load();
        btnacceuil.getScene().setRoot(root);
    }

    @FXML
    void show() {
        table.getItems().clear();
        table.refresh();
        try {
            String requete ="SELECT * FROM cours WHERE disponibilité='indisponible'";
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
       

        nom_cours.setCellValueFactory(new PropertyValueFactory<Cour, String>("nom_cours"));

        Description.setCellValueFactory(new PropertyValueFactory<Cour, String>("Description"));

        type.setCellValueFactory(new PropertyValueFactory<Cour, String>("type"));
        
        age_Min.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("age_Min"));
        
        age_Max.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("age_Max"));
        
       
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
                                
                                String requete= "DELETE FROM cours WHERE id_cours ="+cou.getId_cours();
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

                            try {
                                cou = table.getSelectionModel().getSelectedItem();
                                
                                String requete= "UPDATE cours SET disponibilité='disponible' WHERE id_cours ="+cou.getId_cours();
                                System.out.println(cou.getId_cours());
                                PreparedStatement pst = cnx.prepareStatement(requete);
                                pst.execute();
                                show();
                                
                            } catch (SQLException ex) {
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
    void liste_cours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/AfficherCours.fxml"));
        Parent root= loader.load();
        btnafficher.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       show();
    }

}
