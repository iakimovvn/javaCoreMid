package PhoneBookTask;

import PhoneBookTask.PhoneBook.TelephoneDirectory;

public class PhoneBookMain {
    public static void main(String[] args) {
        TelephoneDirectory phoneBook = new TelephoneDirectory();
        phoneBook.add("Vladimir",89625957309L,423434324234L);
        System.out.println(phoneBook.get("vladimir"));
        System.out.println(phoneBook.get("Ivan"));
        phoneBook.add("vladimir",12321232L);
        System.out.println(phoneBook.get("vladimir"));
    }
}
