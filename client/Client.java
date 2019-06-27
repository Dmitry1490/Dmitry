package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    public Client() {
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("client.fxml"));
        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(root, 300.0D, 275.0D));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
