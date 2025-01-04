
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Console;


public class ContactsApp {
    public static void main(String[] args) {
        Console c = System.console();

        Util.readFile("Contacts.csv");
        Util.printDatabase();
        System.out.println("ContactsApp - Choose action");

        while (true) {

            System.out.println("Print: P, Add: A, Update: U, Remove: R, Exit: X");
            String command = c.readLine();

            if (command.equalsIgnoreCase("P")) {
                Util.printDatabase();

            } else if (command.equalsIgnoreCase("A")) {
                Util.addContact();
                System.out.println("New contact saved!");

            } else if (command.equalsIgnoreCase("U")) {
                Util.editContact();
                System.out.println("Edit successfull!");

            } else if (command.equalsIgnoreCase("R")) {
                Util.removeContact();
                System.out.println("Contact removed!");

            } else if(command.equalsIgnoreCase("X")) {
                System.out.println("App closed.");
                break;
            } else {
                System.out.println("Incorrect command");
            }
        }
    }
}
