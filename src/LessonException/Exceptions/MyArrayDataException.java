package LessonException.Exceptions;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int firstSizeArray, int secondSizeArray) {
        super("Элемент массива ["+firstSizeArray+"]["+secondSizeArray+"]\tне является числом.");
    }
}
