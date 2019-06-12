package ArrayTask;

import java.util.*;

public class ListUniqueWordsMain {

    private static String [] arrayToLoverCase (String ... array){
        for (int i = 0; i <array.length ; i++) {
            array[i] = array[i].toLowerCase();
        }
        return array;
    }

    private static String arrayInfo(String ... wordsArr){
        StringBuilder res = new StringBuilder("Дан массив:\n");
        for (String word: wordsArr) {
            res.append(word);
            res.append("\t");
        }
        return res.toString();
    }

    private static String sortInfo(HashMap <String, Integer> hashMap){
        StringBuilder res = new StringBuilder("Результаты сортировки:\n");
        for (Map.Entry<String, Integer> o: hashMap.entrySet()) {
            res.append(o.getKey());
            res.append("\tповторилось\t");
            res.append(o.getValue());
            res.append(" раз(a)\n");
        }
        return res.toString();
    }

    private static void sortingWordOfArray (String ... stringWords){

        System.out.println(arrayInfo(stringWords));

        String [] workStringArray = arrayToLoverCase(stringWords);
        HashMap <String, Integer> selectedElements = new HashMap<>();

        for (String word: workStringArray) {
            if(selectedElements.containsKey(word)){
                Integer elementValue = selectedElements.get(word);
                selectedElements.put(word,++elementValue);
            }
            else selectedElements.put(word,1);
        }

        System.out.println(sortInfo(selectedElements));

    }

    public static void main(String[] args) {
        String [] crockery = new String[]{"Тарелка","Блюдо","вилка","Ложка","ТАРЕЛКА",
                "Тарелка","Вилка","Ложка","тарелка","Чайник",
                "Вилка","самовар","Нож","Самовар","Тарелка"};

        sortingWordOfArray(crockery);
    }
}
