import java.io.Console;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * App for reading, creating, updating and removing Finnish contact information
 * @author Sakari Vesa
 * @version 1.0
 */

public class ContactsApp {
    /**
     * Stores contacts and contains the operations for handling them
     */

    private static List<Contact> database; //contact objects are stored here
    private static String filepath = "Contacts.csv";
    private static Console c = System.console();

    /**
     * Main loop handling the user commands for different actions
     * @param args none expected
     */
    public static void main(String[] args) {

        System.out.println("");
        System.out.println("-ContactsApp-");

        readFile(filepath);
        printDatabase();

        while (true) { //loop is broken when given exit command

            System.out.println("");
            System.out.println("Enter command: Print: P, Add: A, Update: U, Remove: R, Exit: X");
            String command = c.readLine();

            if (command.equalsIgnoreCase("P")) {
                printDatabase();

            } else if (command.equalsIgnoreCase("A")) {
                addContact();
                System.out.println("New contact saved!");

            } else if (command.equalsIgnoreCase("U")) {
                editContact();
                System.out.println("Edit successfull!");

            } else if (command.equalsIgnoreCase("R")) {
                removeContact();
                System.out.println("Contact removed!");

            } else if(command.equalsIgnoreCase("X")) {
                System.out.println("App closed.");
                break;
            } else {
                System.out.println("Incorrect command");
            }
        }
    }

    /**
     * Adds a new contact to the database
     * Calls the methods for inputting information
     */
    public static void addContact() {
        String[] contactInfo = new String[6];

        contactInfo[0] = inputID();
        contactInfo[1] = inputName("first");
        contactInfo[2] = inputName("last");
        contactInfo[3] = inputPhoneNum();
        contactInfo[4] = inputAddress();
        contactInfo[5] = inputEmail();

        //making new object and storing to database
        Contact newContact = new Contact(contactInfo);
        database.add(newContact);

        updateFile();
    }

    /**
     * Asks the user for the number of contact to remove
     * and removes the contact from the database
     */
    public static void removeContact() {
        boolean inValidInput = true;

        while (inValidInput) {
            System.out.print("Number of line to remove?: ");

            try {
                int index = Integer.parseInt(c.readLine());

                if (index > 0 && index <= database.size()) {
                    database.remove(index -1); //subtract 1 because list index starts at 0
                    inValidInput = false;

                } else {
                    System.out.println("No index in contacts");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        updateFile(); // Update file to match database
    }

    /**
     * Asks the user for the contact and field to edit,
     * calls the input function for the chosen field and updates the information
     */
    public static void editContact() {
        boolean inValidInput = true;
        int index = 0;

        while (inValidInput) {
            System.out.print("Select contact number (1-" + database.size() + "): ");

            try {
                index = Integer.parseInt(c.readLine());

                if (index <= database.size() && index > 0) {
                    index -= 1; // list index starts at 0
                    inValidInput = false;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        Contact contObject = database.get(index);
        System.out.println(contObject.firstName + " " + contObject.lastName + " selected.");

        inValidInput = true;
        while (inValidInput) {

            System.out.print("Select field to update:" +
            " id, name, surname, phone, address, email: ");

            String field = c.readLine();

            if (field.equalsIgnoreCase("id")) {
                contObject.personalId = inputID();
                inValidInput = false;

            } else if (field.equalsIgnoreCase("name")) {
                contObject.firstName = inputName("first");
                inValidInput = false;

            } else if (field.equalsIgnoreCase("surname")) {
                contObject.lastName = inputName("last");
                inValidInput = false;

            } else if (field.equalsIgnoreCase("phone")) {
                contObject.phoneNumber = inputPhoneNum();
                inValidInput = false;

            } else if (field.equalsIgnoreCase("address")) {
                contObject.address = inputAddress();
                inValidInput = false;

            } else if (field.equalsIgnoreCase("email")) {
                contObject.email = inputEmail();
                inValidInput = false;

            } else {
                System.out.println("Incorrect input");
            }
        }
        updateFile();
    }

    /**
     * Reads file and makes a contact object from each line,
     * stores objects in the database list
     * @param fileName name of the file to be read
     */
    public static void readFile(String fileName) {

        List<Contact> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                Contact contactForList = new Contact(values);
                records.add(contactForList);
            }
            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        database = records;
    }

    /**
     * Prints the contacts from the database for the user to see
     */
    public static void printDatabase() {

        System.out.println("");

        for (int i = 0; i < database.size(); i++) {

            List<String> listForPrint = database.get(i).getInfo();

    // this could be done with a for-loop but the code seemed more readable this way
            System.out.println((i + 1) + " ID: " + listForPrint.get(0));
            System.out.println("  Name: " + listForPrint.get(1));
            System.out.println("  Surname: " + listForPrint.get(2));
            System.out.println("  Phone: " + listForPrint.get(3));
            System.out.println("  Address: " + listForPrint.get(4));
            System.out.println("  Email: " + listForPrint.get(5));
            System.out.println("");
        }
    }

    /**
     * Asks the user for a Finnish ID and checks the input using regex
     * @return personal ID
     */
    public static String inputID() {

        boolean inValidInput = true;
        String id = "";

        while (inValidInput) {
            System.out.print("Enter ID: ");
            id = c.readLine();

            if (id.matches("\\d{6}[+-UVWXYABCDEF]{1}\\d{3}[A-Z0-9]{1}")) {
                inValidInput = false;
            } else {
                System.out.println("Invalid Finnish ID.");
            }
        }
        return id;
    }

    /**
     * Asks the user for a phone number and checks the input using regex
     * @return phone number
     */
    public static String inputPhoneNum() {

        boolean inValidInput = true;
        String phoneNum = "";

        while (inValidInput) {
            System.out.print("Enter phone number: ");
            phoneNum = c.readLine();

            if (phoneNum.matches("[+]?\\d{7,13}")) {
                inValidInput = false;
            } else {
                System.out.println("Enter min. 7 numbers, exclude spaces.");
            }
        }
        return phoneNum;
    }

    /**
     * Asks the user for a name and checks the input using regex
     * @param firstOrLast this string is printed when asking for the name
     * @return first or last name
     */
    public static String inputName(String firstOrLast) {

        boolean inValidInput = true;
        String name = "";

        while (inValidInput) {
            System.out.print("Enter " + firstOrLast + " name: ");
            name = c.readLine();

            if (name.matches("[a-zA-ZöäåÖÄÅ-]{2,24}")) {
                inValidInput = false;
            } else {
                System.out.println("Incorrect name input.");
            }
        }
        return name;
    }

    /**
     * Asks the user for an address containing the street, house number, optional apartment code,
     * five-digit postal code and place.
     * Checks the input using regex. Input can be skipped by pressing return
     * @return an address
     */
    public static String inputAddress() {

        boolean inValidInput = true;
        String address = "";

        while (inValidInput) {
            System.out.print("Enter address (optional): ");
            address = c.readLine();

            if (address.matches("([a-zA-ZöäåÖÄÅ-]+ \\d+ ([A-Z] [0-9]+ )?\\d{5} [\\wöäåÖÄÅ-]+)?")) {

                if (address.equals("")) { //empty input replaced with space
                    address = " ";
                }
                inValidInput = false;
            } else {
                System.out.println("Enter street, house number, postcode and place.");
            }
        }
        return address;
    }

    /**
     * Asks the user for an email address and checks the input using regex
     * @return email address
     */
    public static String inputEmail() {
        boolean inValidInput = true;
        String email = "";

        while (inValidInput) {
            System.out.print("Enter email (optional): ");
            email = c.readLine();

            if (email.matches("([\\w.-]+[@][\\w.-]+)?")) {

                if (email.equals("")) { //empty input replaced with space
                    email = " ";
                }
                inValidInput = false;
            } else {
                System.out.println("Must contain '@'. Cannot contain ',' or nordic characters");
            }
        }
        return email;
    }

    /**
     * goes through each contact in database and parses lists into
     * Strings to store in file
     */
    public static void updateFile() {
        boolean append = false;

        for (int i = 0; i < database.size(); i++) {

            List<String> list = database.get(i).getInfo();
            String line = "";

            //convert list to string
            for (int j = 0; j < list.size(); j++) {
                line += list.get(j);

                if (j != list.size() - 1) {
                    line += ",";
                }
            }

            //the first line overwrites previous file contents and following lines are appended
            writeToFile(filepath, line, append);
            append = true;
        }
         //updating file after deleting the last contact, previously couldn't overwrite with empty line
        if (database.size() == 0) {
            writeToFile(filepath, " ", append);
        }
    }

    /**
     * Writes a line to a file
     * @param filename name of the comma delimited .csv file
     * @param line line to write in file
     * @param append option for overwriting or appending the previous file contents
     */
    public static void writeToFile(String filename, String line, boolean append) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, append));
            bw.write(line + "\n");
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
