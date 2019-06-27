package ChatWindowJavaFX.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    private boolean isAuthorized;


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

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    Label labelNotIdentification;

    @FXML
    VBox loginPasswordPanel;



    public void connect() {
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
                            if(str.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            }else{
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        labelNotIdentification.setText(str);                                    }
                                });

                            }

                        }
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
                        setAuthorized(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setAuthorized(boolean isAuthorized){
        this.isAuthorized = isAuthorized;
        if(isAuthorized){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    textFlow.getChildren().clear();
                }
            });
            loginPasswordPanel.setVisible(false);
            textFlow.getStyleClass().remove("textFlowPassword");
            textFlow.getStyleClass().add("textFlow");
            btnSend.setDisable(false);
            textField.setDisable(false);
        }else{
            //textFlow.getChildren().clear();
            loginPasswordPanel.setVisible(true);
            textFlow.getStyleClass().remove("textFlow");
            textFlow.getStyleClass().add("textFlowPassword");
            btnSend.setDisable(true);
            textField.setDisable(true);

        }
    }

    public void tryToAuth() {
        if(socket == null || socket.isClosed()) {
            connect();
        }
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void inputToTextFlow(String msg){
        String[] msgArr = msg.split(" ");
        msg = makeMessageFromArray(msgArr,1,msgArr.length-1);
        Hyperlink nickname = new Hyperlink(msgArr[0]);
        nickname.setStyle("StyleClass: hyperlink-nickname");

        nickname.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                textField.setText("/w "+makeLogin(msgArr[0])+" ");
                textField.requestFocus();

            }
        });
        textFlow.getChildren().addAll(nickname,new Text(msg));
        scrollPane.setVvalue(1.0);
    }

    private String makeLogin(String loginWithPoints){
        char [] charLogin = loginWithPoints.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <charLogin.length-1 ; i++) {
            stringBuffer.append(charLogin[i]);
        }
        return stringBuffer.toString();
    }


    private String makeMessageFromArray(String [] arr, int from, int to){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = from; i <= to; i++) {
            stringBuffer.append(arr[i]);
        }
        return stringBuffer.toString();
    }


    public void sendMsg(){

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


}
