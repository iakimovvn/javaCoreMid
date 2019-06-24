package ChatWindowJavaFX.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;


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
    private ScrollPane scrollPane;

    @FXML
    private Button btnLogin;


    @Override
    public void initialize(URL url, ResourceBundle resource) {
        try {
            socket =new Socket(IP_ADDRESS,PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            String str = in.readUTF();
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    inputToTextFlow(str+"\n");
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void inputToTextFlow(String msg){
        textFlow.getChildren().addAll(new Text(msg));
        scrollPane.setVvalue(1.0);


    }


    public void sendMsg(){
//        Text text = new Text(":\t"+textField.getText()+"\n");
//        Label  login = new Label(userName.getText());
//        login.getStyleClass().add("loginLabel");
//
//        textFlow.getChildren().addAll(new ImageView(avatarImg.getImage()),login,text);
//        scrollPane.setValue(1.0);

        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void makeBlueRedTheme(){
        loginTitle.getStyleClass().remove("loginTitleGreenYellow");
        loginTitle.getStyleClass().add("loginTitleBlueRed");
        contactsTitle.getStyleClass().remove("contactsGreenYellow");
        contactsTitle.getStyleClass().add("contactsTitleBlueRed");
        btnSend.getStyleClass().remove("sendButtonYellow");
        btnSend.getStyleClass().add("sendButtonRed");
}

    public void makeGreenYellowTheme(){
        loginTitle.getStyleClass().remove("loginTitleBlueRed");
        loginTitle.getStyleClass().add("loginTitleGreenYellow");
        contactsTitle.getStyleClass().remove("contactsTitleBlueRed");
        contactsTitle.getStyleClass().add("contactsGreenYellow");
        btnSend.getStyleClass().remove("sendButtonRed");
        btnSend.getStyleClass().add("sendButtonYellow");
    }

    public void clearChat(){
        textFlow.getChildren().clear();
    }

    public void login(){
        textFlow.getChildren().clear();
        textFlow.getStyleClass().remove("textFlowPassword");
        textFlow.getStyleClass().add("textFlow");
        btnSend.setDisable(false);
        textField.setDisable(false);
    }


}
