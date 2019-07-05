package ChatWindowJavaFX.client;

import ChatWindowJavaFX.client.Registration.RegistrationStage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
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

    private String login = null;
    private String password = null;


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

    @FXML
    Circle circleIsInNet;

    @FXML
    TextFlow clientFlow;

    @FXML
    VBox rootVBox;

    @FXML
    VBox vBoxChatPanel;



    public void connect() {
        try {
            socket =new Socket(IP_ADDRESS,PORT);
            this.isAuthorized = false;

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            String str = in.readUTF();
                            if(str.startsWith("/serverclosed")) break;
                            if(str.startsWith("/authok")) {
                                setAuthorized(true);
                                String[] nickArr = str.split(" ");
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        userName.setText(nickArr[1]);
                                        circleIsInNet.setStyle("-fx-fill: green");
                                    }
                                });

                                break;
                            }else{
                                login = null;
                                password = null;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        labelNotIdentification.setText(str);                                    }
                                });

                            }

                        }
                        while(isAuthorized) {
//                            String str;
//
//                            while(true){
//                                if(!socket.isInputShutdown()){
//                                    try {
//                                        str = in.readUTF();
//                                        break;
//                                    }catch(EOFException e){
//                                        inputToTextFlow("Разрыв соединения");
//                                    }
//                                }else {
//                                    try {
//                                        Thread.sleep(1000);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }

                            String str = in.readUTF();
                            if (str.startsWith("/serverclosed")) break;
                            if (str.startsWith("/clientlist")) {
                                String[] tokens = str.split(" ");
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                     //   clientFlow.getChildren().clear();
                                        for (int i = 1; i < tokens.length; i++) {
                                            Hyperlink nickHyper = makeHyperlinkLogin(tokens[i]);

                                            clientFlow.getChildren().addAll(nickHyper,new Text("\n"));
                                        }
                                    }
                                });

                            } else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        inputToTextFlow(str + "\n");
                                    }
                                });
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //setAuthorized(false);
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
                    rootVBox.getChildren().clear();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatPanel.fxml"));
                    try {
                        rootVBox.getChildren().add(fxmlLoader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

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
        login = loginField.getText();
        password = passwordField.getText();
        try {
            out.writeUTF("/auth " + login + " " + password);
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Hyperlink makeHyperlinkLogin(String nickNameStr){
        Hyperlink nickname = new Hyperlink(nickNameStr);
        nickname.setStyle("StyleClass: hyperlink-nickname");

        nickname.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                textField.setText("/w "+makeLogin(nickNameStr)+" ");
                textField.requestFocus();

            }
        });
        return nickname;
    }



    private void inputToTextFlow(String msg){
        String[] msgArr = msg.split(" ");
        msg = makeMessageFromArray(msgArr,1,msgArr.length-1);
        Hyperlink nickName = makeHyperlinkLogin(msgArr[0]);
        textFlow.getChildren().addAll(nickName,new Text(msg));
        scrollPane.setVvalue(1.0);
    }

    private String makeLogin(String login){
        char [] charLogin = login.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        if(charLogin[charLogin.length-1]==':'){
            for (int i = 0; i <charLogin.length-1 ; i++) {

                stringBuilder.append(charLogin[i]);
            }
            login = stringBuilder.toString();
        }
        return login;
    }


    private String makeMessageFromArray(String [] arr, int from, int to){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = from; i <= to; i++) {
            stringBuilder.append(arr[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
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

    public void dispose(){
        try {
            if(out != null) {
                System.out.println("Close");
                out.writeUTF("/end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registration(){
        RegistrationStage rs = new RegistrationStage(out);
        rs.show();
    }

}
