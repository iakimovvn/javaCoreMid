package ChatWindowJavaFX.client.Registration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;

public class RegistrationStage extends Stage {

    DataOutputStream out;

    public RegistrationStage(DataOutputStream out) {
        this.out = out;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("registration.fxml"));
            setTitle("Registration");
            Scene scene = new Scene(root,552,535);
            setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
