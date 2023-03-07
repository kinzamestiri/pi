import Utils.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class HomeCoursController implements Initializable {
/*
   @FXML
private FlowPane cardViewContainer;


    private Connection connection;*/

    public void initialize(URL location, ResourceBundle resources) {
    /*    // Récupérer la connexion à la base de données
        connection = Connexion.getInstance().getCnx();

        // Récupérer les données des cours depuis la base de données
        String query = "SELECT * FROM cour";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int rowIndex = 0;
            while (rs.next()) {
                int id_cours = rs.getInt("id_cours");
                String nom_cours = rs.getString("nom_cours");
                String description = rs.getString("description");
                String type = rs.getString("type");
                int age_Min = rs.getInt("age_min");
                int age_Max = rs.getInt("age_max");
                String disponibilite = rs.getString("disponibilite");

                // Créer un CardView pour ce cours
                FlowPane cardView = new FlowPane();
                cardView.getStyleClass().add("card-view");

                Label nomCoursLabel = new Label(nom_cours);
                nomCoursLabel.getStyleClass().add("nom-cours");
                cardView.getChildren().add(nomCoursLabel);

                Label descriptionLabel = new Label(description);
                descriptionLabel.getStyleClass().add("description");
                cardView.getChildren().add(descriptionLabel);

                Label typeLabel = new Label(type);
                typeLabel.getStyleClass().add("type");
                cardView.getChildren().add(typeLabel);

                Label ageLabel = new Label(age_Min + " - " + age_Max + " ans");
                ageLabel.getStyleClass().add("age");
                cardView.getChildren().add(ageLabel);


                Label disponibiliteLabel = new Label(disponibilite);
                disponibiliteLabel.getStyleClass().add("disponibilite");
                cardView.getChildren().add(disponibiliteLabel);

                // Positionner le CardView dans la grille
                cardView.setLayoutX(20 + (rowIndex % 3) * 220);
                cardView.setLayoutY(20 + (rowIndex / 3) * 220);
                cardViewContainer.getChildren().add(cardView);

                rowIndex++;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
