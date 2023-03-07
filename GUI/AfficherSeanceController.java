package GUI;

import Entities.Cour;
import Entities.Seance;
import Service.Seances;
import Utils.Connexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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

public class AfficherSeanceController implements Initializable{

    @FXML
    private Button btnafficher;

    @FXML
    private TableView<Seance> table;

    @FXML
    private TableColumn<Seance, String> date_seance;

    @FXML
    private TableColumn<Seance, Integer> id_cours;

    @FXML
    private TableColumn<Seance, String> horaire_debut;

    @FXML
    private TableColumn<Seance, String> horaire_fin;

    @FXML
    private TableColumn<Seance, String> editCol;
    
    @FXML
    private TableColumn<Seance, Integer> id_seance;

    @FXML
    private Button btnCours;

    @FXML
    private Button btnajouter;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    public ObservableList<Seance> Seances = FXCollections.observableArrayList();
    
  @FXML
    void show() {
        // table.getItems().clear();
        table.getItems().clear();
        table.refresh();
        try {
            String requete ="SELECT * FROM seance  ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
               Seance s =new Seance();
                s.setId_seance(rs.getInt(1));
                s.setDate_seance(rs.getString(2));
                s.setId_cours(rs.getInt(3));
                s.setHoraire_debut(rs.getString(4));
                s.setHoraire_fin(rs.getString(5));
                Seances.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        id_seance.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("id_seance"));

       date_seance.setCellValueFactory(new PropertyValueFactory<Seance, String>("date_seance"));

    id_cours.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("id_cours"));

    horaire_debut.setCellValueFactory(new PropertyValueFactory<Seance, String>("horaire_debut"));
    
    horaire_fin.setCellValueFactory(new PropertyValueFactory<Seance, String>("horaire_fin"));

         //add cell of button edit 
         Callback<TableColumn<Seance, String>, TableCell<Seance, String>> cellFoctory = (TableColumn<Seance, String> param) -> {
            // make cell containing buttons
            final TableCell<Seance, String> cell = new TableCell<Seance, String>() {
                
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
                          Seance cou = null;

                          cou = table.getSelectionModel().getSelectedItem();
                          String requete= "DELETE FROM seance WHERE id_seance=?";
                          System.out.println(cou.getId_seance());
                          try {
                              PreparedStatement pst = cnx.prepareStatement(requete);
                              pst.setInt(1, cou.getId_seance());
                              pst.execute();
                          } catch (SQLException ex) {
                              System.out.println("Error executing query: " + ex.getMessage());
                          }
                          //PreparedStatement pst = cnx.prepareStatement(requete);
                          //pst.execute();
                          show();
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            Seance cou = null;
                            cou = table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/ModSeance.fxml"));
                            try {
                                loader.load();
                            
                            ModSeanceController Ed = loader.getController();
                        //    Ed.setUpdate(true);
                            Ed.setTextField(cou.getDate_seance(),cou.getId_cours(),cou.getHoraire_debut(),cou.getHoraire_fin(),cou.getId_seance());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            show();
                             } catch (IOException ex) {
                                Logger.getLogger(AfficherSeanceController.class.getName()).log(Level.SEVERE, null, ex);
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
        table.setItems(Seances);
    }

    
    
    @FXML
    void AfficherCours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherCours.fxml"));
        Parent root= loader.load();
        btnCours.getScene().setRoot(root);
    }
    
    @FXML
    void Afficher_Seance(ActionEvent event) {
        show();
    }

    @FXML
    void Ajouter_Seance(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AjouterSeance.fxml"));
        Parent root= loader.load();
        btnajouter.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
    }

}
