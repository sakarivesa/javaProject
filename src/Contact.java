
import java.util.List;
import java.util.Arrays;


/**
 * Stores the contact information of one person.
 * Personal ID, first name, last name, phone number, address and email address
 * @author Sakari Vesa
 */
public class Contact {

    public String personalId;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address = "";
    public String email = "";

    /**
     * constructor method, saves arguments as contact info
     * @param args String array of personal ID, first name, last name, address and email
     */
    public Contact(String[] args) {

        personalId = args[0];
        firstName = args[1];
        lastName = args[2];
        phoneNumber = args[3];
        address = args[4];
        email = args[5];
    }

    /**
     * @return a list of the contact information
     */
    public List<String> getInfo() {

        List<String> infoList = Arrays.asList(personalId, firstName, lastName,
        phoneNumber, address, email);
        return infoList;
    }
}
