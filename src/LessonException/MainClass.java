package LessonException;

import LessonException.Exceptions.*;

public class MainClass {

    private static boolean isNumber (String str){
        boolean res = true;
        for(char c : str.toCharArray()){
            if(!Character.isDigit(c)) res = false;
        }
        return res;
    }

    private static int sumElementsOfArray(String [][] arrayOfNumbers) throws MyArraySizeException, MyArrayDataException {
        int sumElements = 0;

        if(arrayOfNumbers.length!=4 || arrayOfNumbers[0].length!=4) throw new MyArraySizeException();
        for (int i = 0; i <arrayOfNumbers.length ; i++) {
            for (int j = 0; j <arrayOfNumbers[i].length ; j++) {
                if(!isNumber(arrayOfNumbers[i][j])) throw new MyArrayDataException(i,j);
                sumElements +=Integer.parseInt(arrayOfNumbers[i][j]);
            }
        }
        return sumElements;
    }


    public static void main(String[] args) {
        try{
            System.out.println(sumElementsOfArray(new String[][]{
                    {"2","3","4","5"},
                    {"6","3","4","5"},
                    {"2","4","4","1"},
                    {"4","1","1","5"}}
                    )
            );
        } catch (MyArraySizeException | MyArrayDataException e){
            e.printStackTrace();
        }
    }
}
