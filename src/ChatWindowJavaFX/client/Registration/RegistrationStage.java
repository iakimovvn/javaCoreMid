package ChatWindowJavaFX.client.Registration;

import ChatWindowJavaFX.client.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;

public class RegistrationStage extends Stage {
    public RegistrationStage() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("registration.fxml"));
            setTitle("Registration");
            Scene scene = new Scene(root,600,575);
            setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
