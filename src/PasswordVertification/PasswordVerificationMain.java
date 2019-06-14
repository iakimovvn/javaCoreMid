package PasswordVertification;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordVerificationMain {

    private static boolean isPasswordValid(String password){

        boolean res;

        Pattern patternSymbols = Pattern.compile("^[A-z,0-9,_,\\-,<,>,\\.,\\,]{8,20}$");
        Matcher matcher = patternSymbols.matcher(password);
        res = matcher.matches();

        if(res){
            Pattern patternIsNumber = Pattern.compile(".*\\d+.*");
            matcher = patternIsNumber.matcher(password);
            res = matcher.matches();
        }
        if(res){
            Pattern patternIsOneBig = Pattern.compile(".*[A-Z]+.*");
            matcher = patternIsOneBig.matcher(password);
            res = matcher.matches();

        }
        if(res){
            Pattern patternIsOneBig = Pattern.compile(".*[_,\\-,<,>,\\.,\\,]+.*");
            matcher = patternIsOneBig.matcher(password);
            res = matcher.matches();

        }


        return res;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String inputPassword;

        System.out.println("Введите пароль:");
        inputPassword = scanner.nextLine();

        System.out.println((isPasswordValid(inputPassword))?inputPassword+"\t-прошёл вертификацию."
                :inputPassword+"\t-не подходит");


        scanner.close();

    }
}
