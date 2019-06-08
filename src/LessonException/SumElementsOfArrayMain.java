package LessonException;

import LessonException.Exceptions.*;

public class SumElementsOfArrayMain {

    private static boolean isNumber (String str){
        boolean res = true;

        for(char c : str.toCharArray()){
            if(!Character.isDigit(c)) {
                res = false;
                break;
            }
        }
        return res;
    }

    private static int sumElementsOfArray(String [][] arrayOfNumbers) throws MyArraySizeException, MyArrayDataException {

        final int SIZE_SQUARE_ARRAY = 4;
        int sumElements = 0;

        if(arrayOfNumbers.length!= SIZE_SQUARE_ARRAY || arrayOfNumbers[0].length!=SIZE_SQUARE_ARRAY)
            throw new MyArraySizeException();

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
