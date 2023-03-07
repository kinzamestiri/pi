package GUI;

import APIs.ToXLSCour;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import Entities.Cour;
import Service.Cours;
import Utils.Connexion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
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
import javafx.scene.control.Cell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.textfield.CustomTextField;

public class ListeCoursController implements Initializable {
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
    private Button btnacceuil;
    
    @FXML
    private Button btnseance;
      
    @FXML
    private Button exportToXLS;
   
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;


    java.sql.Connection cnx = Connexion.getInstance().getCnx();
    public ObservableList<Cour> Cours = FXCollections.observableArrayList();
    
    @FXML
    void AcceuilCours(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AcceuilCours.fxml"));
        Parent root= loader.load();
        btnacceuil.getScene().setRoot(root);
    }
    
      @FXML
    void ListeSeance(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("ListeSeance.fxml"));
        Parent root= loader.load();
         btnseance.getScene().setRoot(root);
    }

    @FXML
    void show() {
        // table.getItems().clear();
        table.getItems().clear();
        table.refresh();
        try {
            String requete = "SELECT * FROM cours ";

            //String requete ="SELECT * FROM cours  ";
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
       
 // Ajouter les colonnes à la table view
        table.setItems(Cours);
         
    }

    
    @FXML
    private void exportToXLS(ActionEvent event) {
    ToXLSCour exporter = new ToXLSCour();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    fileChooser.setTitle("Export Cour Data to XLS");
    FileChooser.ExtensionFilter xlsFilter = new FileChooser.ExtensionFilter("XLS Files (.xls)", ".xls");
    fileChooser.getExtensionFilters().add(xlsFilter);
    Window stage = null;
    File selectedFile = fileChooser.showSaveDialog(stage);
    if (selectedFile != null) {
        exporter.exportToXLS(table, selectedFile);
    }
}

 @Override
public void initialize(URL location, ResourceBundle resources) {
    show();
}

}

