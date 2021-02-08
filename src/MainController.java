import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final FilmDAO film = new FilmDAO(); // Data access layer object
    public Label lblMessage;
    public TableView tblFilms;
    Film thisFilm = null;

    public TextField txtRating;
    public Button btnRate;

    public void tableClicked(MouseEvent mouseEvent) throws IOException {
        this.thisFilm = (Film) tblFilms.getSelectionModel().getSelectedItem();

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            // Primary button click (usually left)
            // Copy the rating into the rating TextField if it is not -1
            int currentRating = thisFilm.getRatingValue();
            if (currentRating == -1) {
                txtRating.setText("");
            } else {
                txtRating.setText(String.valueOf(currentRating));
            }
        } else {
            // Secondary button click (usually right)
            // Pop up window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detail.fxml"));
            Parent root = loader.load();
            DetailController detailController = loader.getController();
            detailController.setFilm(thisFilm);
            String prevComments = thisFilm.getComments();
            Stage detailStage = new Stage();

            detailStage.setTitle("About this film...");
            detailStage.setScene(new Scene(root, 350, 200));

            detailStage.showAndWait();
            String currComments = detailController.getComments();
            thisFilm.setComments(currComments);

            // Persist this change if the comments were changed
            Boolean changed = !prevComments.equals(currComments);

            if (changed) film.update(thisFilm);
        }
    }

    public void rateFilm(ActionEvent actionEvent) {
        lblMessage.setText("");
        String strRating = txtRating.getText();
        try {
            int rating = Integer.parseInt(strRating);
            thisFilm.setRating(rating);
            film.update(thisFilm);
            tblFilms.refresh();
        } catch (NumberFormatException e) {
            lblMessage.setText("Failed to convert rating to an integer");
        } catch (NullPointerException e) {
            lblMessage.setText("No film has been selected");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create the columns with titles
        TableColumn<Film,String> filmName = new TableColumn<>("Name");
        TableColumn<Film,Integer> filmLength = new TableColumn<>("Length (mins)");
        // The rating is an integer but I want to display it as a string of stars
        TableColumn<Film,String> filmRating = new TableColumn<>("Rating");

        // Associate each column with the object property name
        // You must have created getters for these properties
        filmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        filmLength.setCellValueFactory(new PropertyValueFactory<>("lengthMins"));
        filmRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        // Add these columns to the table
        tblFilms.getColumns().add(filmName);
        tblFilms.getColumns().add(filmLength);
        tblFilms.getColumns().add(filmRating);

        // Load the data into the table
        Film[] allFilms = film.loadData();
        for (Film film: allFilms) {
            tblFilms.getItems().add(film);
        }

    }
}
