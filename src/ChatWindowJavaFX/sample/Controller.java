package ChatWindowJavaFX.sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
public class Controller {


    @FXML
    private ImageView avatarImg;

    @FXML
    private Label userName;

    @FXML
    private TextFlow textFlow;

    @FXML
    private TextField textField;

    @FXML
    private HBox loginTitle;

    @FXML
    private Label contactsTitle;

    @FXML
    private Button btnSend;

    @FXML
    private ScrollPane sp;


    public void sendMsg(){
        Text text = new Text(":\t"+textField.getText()+"\n");
        Label  login = new Label(userName.getText());
        login.getStyleClass().add("loginLabel");

        textFlow.getChildren().addAll(new ImageView(avatarImg.getImage()),login,text);
        sp.setVvalue(1.0);

        textField.clear();
        textField.requestFocus();
    }

    public void makeBlueRedTheme(){
        loginTitle.getStyleClass().add("loginTitleBlueRed");
        contactsTitle.getStyleClass().add("contactsBlueRed");
        btnSend.getStyleClass().add("sendButtonRed");
    }

    public void makeGreenYellowTheme(){
        loginTitle.getStyleClass().add("loginTitleGreenYellow");
        contactsTitle.getStyleClass().add("contactsGreenYellow");
        btnSend.getStyleClass().add("sendButtonYellow");
    }

    public void clearChat(){
        textFlow.getChildren().clear();
    }

}
