package ChatWindowJavaFX.sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;

import java.util.ArrayList;
public class Controller {



    @FXML
    private Label userName;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField;

    @FXML
    private HBox loginTitle;

    @FXML
    private Label contactsTitle;

    @FXML
    private Button btnSend;


    public void sendMsg(){
        String [] messageArr = transferLines(textField.getText().split(" "));
        for (String msg: messageArr) {
            textArea.appendText(msg+"\n");
        }
        textArea.appendText("\n");
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

    private String[] transferLines(String [] strArr){
        final int LINE_SIZE = 40;
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder stringBuilder= new StringBuilder(userName.getText()+":  ");
        for (String word: strArr) {
            if((stringBuilder.length()+word.length()) < LINE_SIZE){
                stringBuilder.append(" "+word);
            }
            else{
                arrayList.add(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.length());
                stringBuilder.append(word);
            }
        }
        arrayList.add(stringBuilder.toString());

        return arrayList.toArray(new String[arrayList.size()]);
    }



}
