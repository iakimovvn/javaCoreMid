package ArrayTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class ListUniqueWordsMain {

    private static String [] arrayToLoverCase (String ... array){
        for (int i = 0; i <array.length ; i++) {
            array[i] = array[i].toLowerCase();
        }
        return array;
    }

    private static void sortingWordOfArray (String ... crockery){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arrayToLoverCase(crockery)));
        System.out.println(arrayList);

        Iterator <String> iterator = arrayList.iterator();

        HashMap <String, Integer> selectedElements = new HashMap<>();
        selectedElements.put(iterator.next(), 1);
        while(iterator.hasNext()){
            String element = iterator.next();

            if(selectedElements.containsKey(element)){
                Integer number = selectedElements.get(element);
                selectedElements.put(element,++number);
            }
            else selectedElements.put(element,1);
        }
        System.out.println(selectedElements);
    }


    public static void main(String[] args) {
        String [] crockery = new String[]{"Тарелка","Блюдо","вилка","Ложка","ТАРЕЛКА",
                "Тарелка","Вилка","Ложка","тарелка","Чайник",
                "Вилка","самовар","Нож","Самовар","Тарелка"};

        sortingWordOfArray(crockery);


    }
}
