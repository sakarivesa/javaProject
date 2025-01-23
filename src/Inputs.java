import java.io.Console;

/**
 * This class is used for getting the user inputs for different
 * fields
 * @author Sakari Vesa
 */
public class Inputs {

    private static Console c = System.console();

    /**
     * Asks the user for a Finnish ID and checks the input using regex
     * @return personal ID
     */
    public static String inputID() {

        boolean inValidInput = true;
        String id = "";

        while (inValidInput) {
            System.out.print("Enter personal ID: ");
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

                if (address.equals("")) { //empty input replaced with '-'
                    address = "-";
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

                if (email.equals("")) { //empty input replaced with '-'
                    email = "-";
                }
                inValidInput = false;
            } else {
                System.out.println("Must contain '@'. Cannot contain ',' or nordic characters");
            }
        }
        return email;
    }
}
