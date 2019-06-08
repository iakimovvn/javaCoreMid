package LessonException;

import LessonException.Exceptions.*;

public class MainClass {

    private static int sumElementsOfArray(String [][] arrayOfNumbers) throws MyArraySizeException, MyArrayDataException {
        int sumElements = 0;

        if(arrayOfNumbers.length!=4 || arrayOfNumbers[0].length!=4) throw new MyArraySizeException();
        for (int i = 0; i <arrayOfNumbers.length ; i++) {
            for (int j = 0; j <arrayOfNumbers[i].length ; j++) {
                try {
                    sumElements +=Integer.parseInt(arrayOfNumbers[i][j]);
                }catch(NumberFormatException e){
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        return sumElements;
    }


    public static void main(String[] args) {
        try{
            System.out.println(sumElementsOfArray(new String[][]{
                    {"2","3","4","5"},
                    {"2","3","wvjk","5"},
                    {"2","3","4","5"},
                    {"2","3","4","5"}}
                    )
            );
        } catch (MyArraySizeException es){
            es.printStackTrace();
        }
        catch (MyArrayDataException ed){
            ed.printStackTrace();
        }

    }
}
