import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static ArrayList<String> groceryList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("0: Çıkış");
            System.out.println("1: Eleman Ekle");
            System.out.println("2: Eleman Çıkar");
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Uygulama sonlandırılıyor...");
                    break;
                case 1:
                    scanner.nextLine(); // Buffer temizleme
                    System.out.println("Eklemek istediğiniz elemanları girin:");
                    String inputAdd = scanner.nextLine();
                    addItems(inputAdd);
                    break;
                case 2:
                    scanner.nextLine(); // Buffer temizleme
                    System.out.println("Çıkarmak istediğiniz elemanları girin:");
                    String inputRemove = scanner.nextLine();
                    removeItems(inputRemove);
                    break;
                default:
                    System.out.println("Geçersiz seçim! Lütfen terkar deneyin.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void addItems(String input) {
        String[] itemsToAdd = input.split(",\\s*"); //
        for (String item : itemsToAdd) {
            if (!checkItemIsInList(item)) {
                groceryList.add(item);
            }
        }
        printSorted();
    }

    public static void removeItems(String input) {
        String[] itemsToRemove = input.split(",\\s*"); //
        for (String item : itemsToRemove) {
            groceryList.removeIf(e -> e.equalsIgnoreCase(item));
        }
        printSorted();
    }

    public static boolean checkItemIsInList(String item) {
        return groceryList.contains(item);
    }

    public static void printSorted() {
        Set<String> uniqueItems = new HashSet<>(groceryList);
        groceryList.clear();
        groceryList.addAll(uniqueItems);
        Collections.sort(groceryList);
        System.out.println("Listenin Güncel Hali:");
        for (String item : groceryList) {
            System.out.println(item);
        }
    }
}

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            System.out.println("Bu kişi zaten listede var.");
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int foundPosition = findContact(oldContact);
        if (foundPosition < 0) {
            System.out.println(oldContact.getName() + " kişisi listede bulunamadı.");
            return false;
        }

        myContacts.set(foundPosition, newContact);
        System.out.println(oldContact.getName() + " kişisi " + newContact.getName() + " ile güncellendi.");
        return true;
    }

    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);
        if (foundPosition < 0) {
            System.out.println(contact.getName() + " kişisi listede bulunamadı.");
            return false;
        }

        myContacts.remove(foundPosition);
        System.out.println(contact.getName() + " kişisi listeden silindi.");
        return true;
    }

    private int findContact(Contact contact) {
        return myContacts.indexOf(contact);
    }

    private int findContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            if (contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return myContacts.get(position);
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (Contact contact : myContacts) {
            System.out.println(contact.getName() + " -> " + contact.getPhoneNumber());
        }
    }
}

public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Contact createContact(String name, String phoneNumber) {
        return new Contact(name, phoneNumber);
    }
}
