package ChatWindowJavaFX.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatWindowMain extends Application {

    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("sample.fxml"));
        controller = loader.getController();

        primaryStage.setTitle("Universal CHAT");
        primaryStage.setScene(new Scene(root,600,575));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent ->{
            controller.dispose();
            Platform.exit();
            System.exit(0);
                }
                );
    }


    public static void main(String[] args) {
        launch(args);
    }

}
