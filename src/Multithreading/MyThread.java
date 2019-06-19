package Multithreading;

public class MyThread extends Thread {

    private float [] arr;

    MyThread(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        MultiMain.changeArrayForFormula(arr);
    }
}
