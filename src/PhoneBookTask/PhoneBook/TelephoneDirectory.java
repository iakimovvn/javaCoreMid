package PhoneBookTask.PhoneBook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TelephoneDirectory {
    private HashMap<String, HashSet<Long>> phoneTable;

    public TelephoneDirectory() {
        phoneTable = new HashMap<>();
    }

    private String showMobilePhones(String surName) {
        Long [] phonesArr = new Long[phoneTable.get(surName).size()];
        phoneTable.get(surName).toArray(phonesArr);

        StringBuilder res = new StringBuilder();
        for (Long phone : phonesArr) {
            res.append("\tтел.: ");
            res.append(phone);
            res.append("\n");
        }
        return res.toString();
    }

    public void add(String surName,Long ... phones){
        surName = surName.toUpperCase();
        if(phoneTable.containsKey(surName)){
                phoneTable.get(surName).addAll(Set.of(phones));
        }
        else{
            phoneTable.put(surName,new HashSet<>(Set.of(phones)));
        }
    }

    public String get(String surName){
        surName = surName.toUpperCase();
        return (phoneTable.containsKey(surName))?surName+":\n"+ showMobilePhones(surName) :
                "Контакта "+surName+", не обнаружено!\n";
    }
}
