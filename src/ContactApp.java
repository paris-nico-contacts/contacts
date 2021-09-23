import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ContactApp {
    static Path newPath = Paths.get("contacts.txt");

    public static void createFile() throws IOException {
        try {
            Files.createFile(newPath);
        } catch (FileAlreadyExistsException e) {

        }
    }



    public static void writeContacts() throws IOException {
        Contact charles = new Contact("Charles", 210222222);
        Contact sara = new Contact("Sara", 123442424);
//        ArrayList<Contact> contacts = new ArrayList<>();
        List<Contact> contacts = new ArrayList<>();
        contacts.add(charles);
        contacts.add(sara);
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

    public void readFileAndOutput (Path pathToFile) {
        List<String> linesInTheFile = new ArrayList<>();
        try {
            linesInTheFile = Files.readAllLines(pathToFile);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        for (String line : linesInTheFile){
            System.out.println(line);
        }
    }

    public static void main(String[] args) throws IOException {
        createFile();
        writeContacts();
    }
}
