import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ContactApp {
    static Path newPath = Paths.get("contacts.txt");
    public static ArrayList<Contact> contacts = new ArrayList<>();

    public static void createFile() throws IOException {
        try {
            Files.createFile(newPath);
        } catch (FileAlreadyExistsException e) {

        }
    }



    public static void writeContacts() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt"));
        for (Contact contact: contacts) {
            if (contacts.indexOf(contact) != contacts.size() - 1) {
                writer.write(contact.getName() + "," + contact.getNumber() + "\n");
            } else {
                writer.write(contact.getName() + "," + contact.getNumber());
            }
        }
        writer.close();
    }

    public static void printContacts() throws IOException {
        System.out.println();
        int length = 0;
        for (Contact contact: contacts) {
            if (contact.getName().length() > length) {
                length = contact.getName().length();
            }
        }
        String wordName = "Name";
        String phoneNumber = "Phone number";
        String output = "%-" + length + "s | %-12s |\n";
        System.out.printf(output, wordName, phoneNumber);
        System.out.println("-".repeat(length + 17));
        for (Contact contact: contacts) {
            String numString = String.valueOf(contact.getNumber());
            if (numString.length() == 7) {
                numString = numString.substring(0,3) + "-" + numString.substring(3, numString.length());
            } else {
                numString = numString.substring(0,3) + "-" + numString.substring(3,6) + "-" + numString.substring(6, numString.length());
            }
//            String output = "%-" + length + "s | %-12s |\n";
            System.out.printf(output, contact.getName(), numString);
        }
    }

    public static void loadContacts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"));
        String line;
        String[] words = new String[2];
        String name;
        long number;
        while((line = reader.readLine()) != null) {
            words = (line.split(","));
            name = words[0];
            number = Long.parseLong(words[1]);
            Contact contact = new Contact(name, number);
            contacts.add(contact);
        }
        reader.close();
    }

    public static void addContact() throws IOException {
        int index = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Add the name of your new contact: ");
        String name = sc.nextLine();


        for (Contact contact: contacts) {
            if (Objects.equals(name, contact.getName())) {
                System.out.printf("There's already a contact named %s. Do you want to overwrite it? (Yes/No)", contact.getName());
                String input = sc.nextLine();
                if (input == "Yes" | input.equalsIgnoreCase("y")) {
                    index = contacts.indexOf(contact);
                    contacts.remove(contacts.get(index));
                }
            }
        }
        System.out.println("Add the number of your new contact: ");
        long number = sc.nextLong();
        Contact newContact = new Contact(name, number);
        contacts.add(newContact);
        writeContacts();
    }


    public static void searchContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of your contact: ");
        String name = sc.nextLine();
        for (Contact contact : contacts) {
            if (Objects.equals(name, contact.getName())) {
                System.out.println("Matched contacts: ");
                System.out.printf("%s | %d\n", contact.getName(), contact.getNumber());
            }
        }
    }

    public static void deleteContact() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the contact you want to delete: ");
        String name = sc.nextLine();
        int index = -1;
        for(Contact contact : contacts) { 
            
            if (Objects.equals(name, contact.getName())) {
                index = contacts.indexOf(contact);
            }
            
        }
        try {
            contacts.remove(contacts.get(index));
        } catch (Exception e) {

        }

        writeContacts();
    }

    public static void runContactApp() throws IOException {
        loadContacts();
        System.out.println("Welcome to the Contact App");
        boolean goOn = true;
        do {
            System.out.println("1. View contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            Scanner sc = new Scanner(System.in);
            int response = sc.nextInt();
            switch (response) {
                case 1: printContacts();
                    break;
                case 2: addContact();
                    break;
                case 3: searchContact();
                    break;
                case 4: deleteContact();
                    break;
                case 5: goOn = false;
                    break;
            }
        } while (goOn == true);
    }


    public static void main(String[] args) throws IOException {
        runContactApp();
    }
}
