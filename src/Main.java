import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("films.fxml"));
        primaryStage.setTitle("The Best Films");
        primaryStage.setScene(new Scene(root, 450, 350));
        primaryStage.show();

    }
}
