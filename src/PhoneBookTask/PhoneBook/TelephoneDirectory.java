package PhoneBookTask.PhoneBook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TelephoneDirectory {
    private HashMap<String, Set<Long>> phoneTable;

    public TelephoneDirectory() {
        phoneTable = new HashMap<>();
    }

    public void add(String surName,Long ... phones){
        surName = surName.toUpperCase();
        if(phoneTable.containsKey(surName)){
            for (Long phone : phones) {
                phoneTable.get(surName).add(phone);
            }
        }
        else{
            phoneTable.put(surName,new HashSet<>(Set.of(phones)));
        }
    }

    public String get(String surName){
        surName = surName.toUpperCase();
        return (phoneTable.containsKey(surName))?surName+": "+ phoneTable.get(surName)
                : "Контакта "+surName+", не обнаружено!";
    }
}
