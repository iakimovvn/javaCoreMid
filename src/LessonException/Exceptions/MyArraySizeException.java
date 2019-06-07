package LessonException.Exceptions;

public class MyArraySizeException extends Exception {
    public MyArraySizeException() {
        super("Был получен массив недопустимого размера");
    }
}
