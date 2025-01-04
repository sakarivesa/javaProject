import java.io.Console;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileOutputStream;


public class Util {
    private static List<Contact> database;
    private static String filepath = "Contacts.csv";
    private static Console c = System.console();

    public static void addContact() {
        String[] contactInfo = new String[6];

        contactInfo[0] = inputID();
        contactInfo[1] = inputName("First");
        contactInfo[2] = inputName("Last");
        contactInfo[3] = inputPhoneNum();
        contactInfo[4] = inputAddress();
        contactInfo[5] = inputEmail();

        //making an object and storing to database
        Contact newContact = new Contact(contactInfo);
        database.add(newContact);

        updateFile();
    }

    public static void removeContact() {
        boolean inValidInput = true;

        System.out.println("Number of line to remove?");

        while (inValidInput) {
            try {
                int index = Integer.parseInt(c.readLine());

                if (index > 0 && index <= database.size()) {
                    database.remove(index -1);
                    inValidInput = false;

                } else {
                    System.out.println("No index in contacts");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Number of line to remove?");
            }
        }
        updateFile();
    }

    public static void editContact() {
        boolean inValidInput = true;
        System.out.println("Select contact number (1-" + database.size() + ")");
        int index = 0;

        while (inValidInput) {
            try {
                index = Integer.parseInt(c.readLine());
                if (index <= database.size() && index > 0) {
                    index -= 1;
                    inValidInput = false;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        Contact contObject = database.get(index);
        System.out.println(contObject.firstName + " " + contObject.lastName + " selected.");

        System.out.println("Select field to update: id, name, surname, phone, address, email");

        inValidInput = true;
        while (inValidInput) {
            String field = c.readLine();

            switch (field) {
                case "id":
                    contObject.personalId = inputID();
                    inValidInput = false;
                    break;
                case "name":
                    contObject.firstName = inputName("First");
                    inValidInput = false;
                    break;
                case "surname":
                    contObject.lastName = inputName("Last");
                    inValidInput = false;
                    break;
                case "phone":
                    contObject.phoneNumber = inputPhoneNum();
                    inValidInput = false;
                    break;
                case "address":
                    contObject.address = inputAddress();
                    inValidInput = false;
                    break;
                case "email":
                    contObject.email = inputEmail();
                    inValidInput = false;
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        }
        updateFile();
    }

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

    public static void printDatabase() {

        System.out.println("");

        for (int i = 0; i < database.size(); i++) {
            System.out.print(i+1 + " ");
            System.out.println(database.get(i).getInfo());
        }
        System.out.println("");
    }

    public static String inputID() {
        System.out.println("Enter ID: ");

        boolean inValidInput = true;
        String id = "";

        while (inValidInput) {
            id = c.readLine();

            if (id.matches("\\d{6}[+-UVWXYABCDEF]{1}\\d{3}[A-Z0-9]{1}")) {
                inValidInput = false;
            } else {
                System.out.println("Invalid Finnish ID");
            }
        }
        return id;
    }

    public static String inputPhoneNum() {
        System.out.println("Enter phone number: ");

        boolean inValidInput = true;
        String phoneNum = "";

        while (inValidInput) {
            phoneNum = c.readLine();

            if (phoneNum.matches("[+]?\\d{7,13}")) {
                inValidInput = false;
            } else {
                System.out.println("Enter min. 7 numbers, exclude whitespaces.");
            }
        }
        return phoneNum;
    }

    public static String inputName(String firstOrLast) {
        System.out.println("Enter " + firstOrLast + " name: ");

        boolean inValidInput = true;
        String name = "";

        while (inValidInput) {
            name = c.readLine();

            if (name.matches("^[\\wöäåÖÄÅ]{2,24}$")) {
                inValidInput = false;
            } else {
                System.out.println("No special characters.");
            }
        }
        return name;
    }

    public static String inputAddress() {
        System.out.println("Enter address (optional): ");
        boolean inValidInput = true;
        String address = "";

        while (inValidInput) {
            address = c.readLine();

            if (address.matches("(^[^,]+\\s\\d+.{0,30}$)?")) {
                inValidInput = false;
            } else {
                System.out.println("Must have at least street and number. Cannot contain ','");
            }
        }
        return address;
    }

    public static String inputEmail() {
        System.out.println("Enter email (optional): ");
        boolean inValidInput = true;
        String email = "";

        while (inValidInput) {
            email = c.readLine();

            if (email.matches("(^[^,]+[@][^,]+$)?")) {
                inValidInput = false;
            } else {
                System.out.println("Must contain '@'. Cannot contain ','");
            }
        }
        return email;
    }

    public static void updateFile() {
        boolean append = false;

        for (int i = 0; i < database.size(); i++) {

            List<String> list = database.get(i).getInfo();
            String line = "";

            //convert list to string
            for (int j = 0; j < list.size(); j++) {
                line += list.get(j);

                if (j != list.size()-1) {
                    line += ",";
                }
            }
            //the first line overwrites previous file contents and following lines are appended
            writeToFile(filepath, line, append);
            append = true;
        }
    }

    public static void writeToFile(String filename, String line, boolean append) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename,append));
            bw.write(line + "\n");
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}