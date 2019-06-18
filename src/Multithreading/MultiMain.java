package Multithreading;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiMain {
    private static final int SIZE = 10_000_000;

    private static void firstFunction(){
        float [] arr = new float[SIZE];
        Arrays.fill(arr,1);
        long a = System.currentTimeMillis();
        changeArrayForFormula(arr);
        //System.currentTimeMillis(); // этот метод в пункте 5 задания. Но разве мы не вызываем его ниже? или не хотим
                                      // замерять время sout?
        System.out.println("Время исполнения однопоточной версии:"+(System.currentTimeMillis() - a));

    }

    private static void secondFunction(){
        int AMOUNT_FLOWS = 2;
        long time;
        float [] arr = new float[SIZE];
        Arrays.fill(arr,1);


        time = System.currentTimeMillis();

        ArrayList <float []> arrayList= arrayToPiecesInArrayList(arr,AMOUNT_FLOWS);

        MyThread [] arrayMyThread = new MyThread[AMOUNT_FLOWS];

        for (int i = 0; i <arrayMyThread.length; i++) {
            arrayMyThread[i] = new MyThread(arrayList.get(i));
        }

        for (MyThread myThread : arrayMyThread) {
            myThread.start();
            try {
                myThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        glueArrays(arr,arrayList);
        //System.currentTimeMillis();
        System.out.println("Время исполнения многопоточной версии:"+(System.currentTimeMillis() - time));
    }

    static void changeArrayForFormula(float [] arr){
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float)i / 5) * Math.cos(0.4f + (float)i / 2));
        }
    }

    private static ArrayList <float[]> arrayToPiecesInArrayList (float [] arr, int howManyPieces ){
        ArrayList <float[]> arrayList = new ArrayList<>();
        int h = SIZE / howManyPieces;

        for (int i = 0; i < howManyPieces ; i++) {
            if(SIZE %howManyPieces!=0 || i ==howManyPieces - 1){
                arrayList.add(new float[h+(SIZE %howManyPieces)]);
                System.arraycopy(arr,h*i,arrayList.get(i),0,h+(SIZE %howManyPieces));
            }else{
                arrayList.add(new float[h]);
                System.arraycopy(arr,h*i,arrayList.get(i),0,h);
            }


        }
        return arrayList;
    }

    private static void glueArrays (float [] arr, ArrayList<float[]> arrayList){
        int startArrElement = 0;
        for (float [] arrayFromList : arrayList) {
            System.arraycopy(arrayFromList,0,arr,startArrElement,arrayFromList.length);
            startArrElement+=arrayFromList.length;
        }
    }




    public static void main(String[] args) {
        firstFunction();
        secondFunction();

    }
}
