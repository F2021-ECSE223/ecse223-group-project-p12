package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.application.*;

public class ClimbSafeFeatureSet2Controller {



public class ClimbSafeFeatureSet2Controller {
    static ClimbSafe climbSafe  = ClimbSafeApplication.getClimbSafe();


    public static void registerMember(String email, String password, String name,
                                      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
                                      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {

        emailIsValid(email);
        passwordIsValid(password);
        userIsAdmin(email);
        nameIsValid(name);
        emergencyContactIsValid(emergencyContact);
        validNrWeeks(nrWeeks);
        bookedItemIsValid(itemQuantities);

        Member newMember = new Member(email, password, name, emergencyContact, nrWeeks,guideRequired, hotelRequired, climbSafe);

        climbSafe.addMember(newMember);

    }


    public static void updateMember(String email, String newPassword, String newName,
                                    String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
                                    boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
            throws InvalidInputException {

        emailIsValid(email);
        passwordIsValid(newPassword);
        userIsAdmin(email);
        nameIsValid(newName);
        emergencyContactIsValid(newEmergencyContact);
        validNrWeeks(newNrWeeks);
        bookedItemIsValid(newItemQuantities);

        Member member = ClimbSafeApplication.getClimbSafe().findMemberFromEmail(email);
        if( member != null ) {
            member = new Member(email, newPassword, newName, newEmergencyContact, newNrWeeks,newGuideRequired, newHotelRequired, climbSafe);
        }
    }

    //Constraints:
    // email must not contain any spaces
//    not password.contains(" ")
//
//    // email must contain some characters (anything is allowed except @), a @, some characters, a dot, and some characters
//    // this is a simplified check sufficient for this application
//   email.indexOf("@") > 0 // index starts at zero
//           email.indexOf("@") = email.lastIndexOf("@")
//           email.indexOf("@") < email.lastIndexOf(".") - 1
//            email.lastIndexOf(".") < email.length() - 1

    public static String emailIsValid(String email) throws InvalidInputException {
        int countOfAt = 0;
        int lastIndexOfDot = 0;
        int indexOfAt = 0;
        int counterOfNotAt = 0;
        if (email.equals("")) throw new InvalidInputException("Your email address must not be empty");
        if (email == null) throw new InvalidInputException("Your email address must not be null");
        for (int i = 0; i < email.length(); i++) {
            char character = email.charAt(i);
            if (character == ' ') throw new InvalidInputException("The email must not contain any spaces");
            if (character == '.') lastIndexOfDot = i;
            if (character == '@') {
                indexOfAt = i;
                countOfAt++;
                if (countOfAt > 1)
                    throw new InvalidInputException("There must not be multiple @ characters in the email");
                if (i <= 0)
                    throw new InvalidInputException("The @ character must not be placed at the beginning of the email");
            }
            if (character != '@') {
                counterOfNotAt++;
            }
        }
        if (counterOfNotAt == email.length())
            throw new InvalidInputException("There must be an @ character in the email");
        if (indexOfAt >= lastIndexOfDot - 1)
            throw new InvalidInputException("The @ character must be before the last dot of the email");
        if (lastIndexOfDot >= email.length() - 1)
            throw new InvalidInputException("There must not be a dot at the very end of the email");
        if (email.equals("admin@nmc.nt")) throw new InvalidInputException("The email entered is not allowed for members ");

        return email;
    }

    // password must not be empty or null
    public static String passwordIsValid(String password) throws InvalidInputException {
        if (password.equals("")) throw new InvalidInputException("The password cannot be empty");
        if (password == null) throw new InvalidInputException("The password must not be null");
        return password;
    }

    //Is the user the Administrator ?
    public static void userIsAdmin(String email) {
        if (email.equals("admin@nmc.nt")) System.out.println("User is the Administrator");
    }

    // name must not be empty or null
    public static String nameIsValid(String name) throws InvalidInputException {
        if (name.equals("")) throw new InvalidInputException("The name cannot be empty");
        if (name == null) throw new InvalidInputException("The name must not be null");
        return name;
    }

    // emergencyContact must not be empty or null
    public static String emergencyContactIsValid(String emergencyContact) throws InvalidInputException {
        if (emergencyContact.equals("")) throw new InvalidInputException("The emergency contact cannot be empty");
        if (emergencyContact == null) throw new InvalidInputException("The emergency contact must not be null");
        return emergencyContact;
    }

    // number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season
    public static int validNrWeeks(int nrWeeks) throws InvalidInputException {
        if (nrWeeks < 0 || nrWeeks > climbSafe.getNrWeeks()) throw new InvalidInputException("The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season");
        return nrWeeks;
    }

    //    for each BookedItem:
    //    quantity must be greater than 0
    public static List<Integer> bookedItemIsValid(List<Integer> quantity) throws InvalidInputException {
        for(Integer q:quantity) {
            if (q<=0)  throw new InvalidInputException("The quantity of booked item must be greater than 0");
        }
        return quantity;
    }
}
