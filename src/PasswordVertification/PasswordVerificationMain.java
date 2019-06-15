package PasswordVertification;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordVerificationMain {

    private static String isPasswordValid(String password){

        boolean isValid;
        String res;

        Pattern patternSymbols = Pattern.compile("^[A-z,0-9 ,_,\\-,<,>,\\.,\\,]{8,20}$");
        Matcher matcher = patternSymbols.matcher(password);
        isValid = matcher.matches();
        res = "Недопустимые символы/размер";

        if(isValid){
            Pattern patternIsNumber = Pattern.compile(".*\\d+.*");
            matcher = patternIsNumber.matcher(password);
            isValid = matcher.matches();
            res = "Не обнаружено чисел";

        }
        if(isValid){
            Pattern patternIsOneBig = Pattern.compile(".*[A-Z]+.*");
            matcher = patternIsOneBig.matcher(password);
            isValid = matcher.matches();
            res = "Не обнаружено заглавных букв";

        }
        if(isValid){
            Pattern patternIsOneBig = Pattern.compile(".*[_,\\-,<,>,\\.,\\,]+.*");
            matcher = patternIsOneBig.matcher(password);
            isValid = matcher.matches();
            res = "Не обнаружено специальных символов.";
        }
        if (isValid) res = password+"прошёл вертификацию";
        else res += " Пароль не подходит.";


        return res;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String inputPassword;
        System.out.println("Пароль.\nОт 8 до 20 символовю Можно использовать только Латинские буквы, цыфры,\n" +
                "и символы (<),(>),(.),(-),(_), должна быть хотябы одна Заглавная буква,\n" +
                "одна цыфра и один символ\nВведите пароль:");

        //while (true) {
            inputPassword = scanner.nextLine();
            System.out.println(isPasswordValid(inputPassword));
        //}
        scanner.close();
    }
}
