package ChatWindowJavaFX.client.Registration;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegController {

    @FXML
    Label errMsg;

    @FXML
    TextField loginField;

    @FXML
    TextField passField;

    @FXML
    TextField rePassField;

    @FXML
    TextField nickField;

    @FXML
    TextField controlWord;

    @FXML
    Button regBtn;

    public void toRegistration(){
        if(!isTextFieldValid(loginField.getText()) || !isTextFieldValid(nickField.getText())
                || !isTextFieldValid(controlWord.getText())){
            errMsg.setText("Заполните все поля(4-20 символов)");
        } else if(!isPasswordValidSymbols(passField.getText())){
            errMsg.setText("Пароль неверного формата A-z,0-1,_,-");
        } else if(!isPasswordBigLetter(passField.getText())){
            errMsg.setText("Должны быть заглавные буквыт");
        } else if (!isPasswordNumbers(passField.getText())) {
            errMsg.setText("Должны быть цифры");
        } else if (!passField.getText().equals(rePassField.getText())) {
            errMsg.setText("Пароли не совпадают");
        } else {

        }

    }

    private static boolean isTextFieldValid(String str){

            Pattern patternSymbols = Pattern.compile("^[A-z,0-9,_,\\-]{4,20}$");
            Matcher matcher = patternSymbols.matcher(str);
           return matcher.matches();
    }

    private static boolean isPasswordValidSymbols(String password) {
        Pattern patternSymbols = Pattern.compile(
                "^[A-z,0-9,_,\\-]{6,20}$");
        Matcher matcher = patternSymbols.matcher(password);
        return matcher.matches();
    }

    private static boolean isPasswordBigLetter(String password){
        Pattern patternSymbols = Pattern.compile(
                ".*[A-Z]+.*");
        Matcher matcher = patternSymbols.matcher(password);
        return matcher.matches();
    }

    private static boolean isPasswordNumbers(String password){
        Pattern patternSymbols = Pattern.compile(
                ".*\\d+.*");
        Matcher matcher = patternSymbols.matcher(password);
        return matcher.matches();
    }

}
