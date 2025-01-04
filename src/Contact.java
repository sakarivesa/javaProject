
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Contact {

    public String personalId;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address = "";
    public String email = "";

    public Contact(String[] args) {

        personalId = args[0];
        firstName = args[1];
        lastName = args[2];
        phoneNumber = args[3];
        address = args[4];
        email = args[5];
    }

    public List<String> getInfo() {

        List<String> infoList = Arrays.asList(personalId, firstName, lastName,
        phoneNumber, address, email);
        return infoList;
    }
}
