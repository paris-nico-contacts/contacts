import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactApp {
    static Path newPath = Paths.get("contacts.txt");
    private static ArrayList<Contact> contacts = new ArrayList<>();

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

    public static void readContacts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"));
        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public static void loadContacts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"));
        String line;
        String[] words = new String[2];
        String name;
        int number;
        while((line = reader.readLine()) != null) {
            words = (line.split(","));
            name = words[0];
            number = Integer.parseInt(words[1]);
            Contact contact = new Contact(name, number);
            contacts.add(contact);
        }
        reader.close();
    }

    public static void addContact() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add the name of your new contact: ");
        String name = sc.nextLine();
        System.out.println("Add the number of your new contact: ");
        int number = sc.nextInt();
        Contact newContact = new Contact(name, number);
        contacts.add(newContact);
        writeContacts();
    }


    public static void main(String[] args) throws IOException {
        loadContacts();

    }
}