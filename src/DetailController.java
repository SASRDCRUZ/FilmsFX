import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DetailController {

    public Label lblFilmName;
    public Label lblFilmLength;
    public Label lblFilmRating;
    public TextArea txtComments;
    public Button btnSave;

    private String comments = "";

    public void saveComments(ActionEvent actionEvent) {
        comments = txtComments.getText();
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    public String getComments() {
        return this.comments;
    }

    public void setFilm(Film thisFilm) {
        lblFilmName.setText("Film:\t" + thisFilm.getName());
        lblFilmLength.setText("Length (mins):\t" + thisFilm.getLengthMins());
        lblFilmRating.setText("Rating:\t" + thisFilm.getRating());
        txtComments.setText(thisFilm.getComments());
    }
}
