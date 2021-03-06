package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.application.*;

/**
 * This class is responsible for the implementation of the features relating to the members. Namely,
 * its purpose is to help with Registering members and Updating members.
 *
 * @author Theo Ghanem
 */

public class ClimbSafeFeatureSet2Controller {
  static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  /**
   * This method is used to register the member into the Climb Safe database
   * 
   * @param email |string| Member's email
   * @param password |string| Member's password
   * @param name |string| Member's name
   * @param emergencyContact |string| Member's emergency contact (phone number)
   * @param nrWeeks |int| Number of weeks the member wants to climb for
   * @param hotelRequired |Boolean| If the member requires a Hotel during his climbing weeks
   * @param itemNames |List<String>| Name of the items the member needs for his climb
   * @throws InvalidInputException If certain constraints aren't respected, the method throws an
   *         InvalidInputException
   * @author Theo Ghanem
   */

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {

    // makes sure all the constraints are respected.
    emailIsValid(email);
    passwordIsValid(password);
    userIsAdmin(email);
    nameIsValid(name);
    emergencyContactIsValid(emergencyContact);
    validNrWeeks(nrWeeks);
    itemNamesIsValid(itemNames);
    bookedItemQuanityIsValid(itemQuantities);

    Member newMember = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired,
        hotelRequired, climbSafe);

    // Adds the ItemNames as BookableItems and then as a BookedItem associated with it's quantity
    for (int i = 0; i < itemNames.size(); i++) {
      BookableItem bookableItem = BookableItem.getWithName(itemNames.get(i));
      Integer quantity = itemQuantities.get(i);
      BookedItem bookedItem = new BookedItem(quantity, climbSafe, newMember, bookableItem);
    }
    climbSafe.addMember(newMember);
    ClimbSafePersistence.save();
  }



  /**
   * This method is used to update an existing member into the Climb Safe database
   * 
   * @param email |string| Member's email
   * @param password |string| Member's new password
   * @param name |string| Member's new name
   * @param emergencyContact |string| Member's new emergency contact (phone number)
   * @param nrWeeks |int| New number of weeks the member wants to climb for
   * @param hotelRequired |Boolean| If the member requires a Hotel during his climbing weeks
   * @param itemNames |List<String>| New name of the items the member needs for his climb
   * @throws InvalidInputException If certain constraints aren't respected, the method throws an
   *         InvalidInputException
   * @author Theo Ghanem
   */

  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {

    Member member = ClimbSafeApplication.getClimbSafe().findMemberFromEmail(email);
    if (member == null)
      throw new InvalidInputException("Member not found");

    // makes sure all the constraints are respected.
    passwordIsValid(newPassword);
    userIsAdmin(email);
    nameIsValid(newName);
    emergencyContactIsValid(newEmergencyContact);
    validNrWeeks(newNrWeeks);
    itemNamesIsValid(newItemNames);
    bookedItemQuanityIsValid(newItemQuantities);
    
    member.setPassword(newPassword);
    member.setName(newName);
    member.setEmergencyContact(newEmergencyContact);
    member.setNrWeeks(newNrWeeks);
    member.setGuideRequired(newGuideRequired);
    member.setHotelRequired(newHotelRequired);
    while( member.getBookedItems().size() != 0 ) {
    	member.getBookedItem(0).delete();
    }

    // Adds the newItemNames as BookableItems and then as a BookedItem associated with it's new
    // quantity
    for (int i = 0; i < newItemNames.size(); i++) {
      BookableItem bookableItem = BookableItem.getWithName(newItemNames.get(i));
      Integer quantity = newItemQuantities.get(i);
      BookedItem bookedItem = new BookedItem(quantity, climbSafe, member, bookableItem);
    }
    ClimbSafePersistence.save();
  }


  /**
   * Method for input validation. Makes sure all constraints are respected. If the constraints
   * aren't respected it throws the according InvalidInputException.
   * 
   * @param email |string| Email inputted by the user.
   * @throws InvalidInputException
   * @author Theo Ghanem
   */

  public static void emailIsValid(String email) throws InvalidInputException {
    int countOfAt = 0;
    int lastIndexOfDot = 0;
    int indexOfAt = 0;
    int counterOfNotAt = 0;
    if (email.equals(""))
      throw new InvalidInputException("Your email address must not be empty");
    if (email == null)
      throw new InvalidInputException("Your email address must not be null");
    for (int i = 0; i < email.length(); i++) {
      char character = email.charAt(i);
      if (character == ' ')
        throw new InvalidInputException("The email must not contain any spaces");
      if (character == '.')
        lastIndexOfDot = i;
      if (character == '@') {
        indexOfAt = i;
        countOfAt++;
        if (countOfAt > 1)
          throw new InvalidInputException("Invalid email");
        if (i <= 0)
          throw new InvalidInputException("Invalid email");
      }
      if (character != '@')
        counterOfNotAt++;
    }
    if (counterOfNotAt == email.length())
      throw new InvalidInputException("Invalid email");
    if (indexOfAt >= lastIndexOfDot - 1)
      throw new InvalidInputException("Invalid email");
    if (lastIndexOfDot >= email.length() - 1)
      throw new InvalidInputException("Invalid email");
    if (email.equals("admin@nmc.nt"))
      throw new InvalidInputException("The email entered is not allowed for members ");
    if (ClimbSafeApplication.getClimbSafe().findGuideFromEmail(email) != null)
      throw new InvalidInputException("A guide with this email already exists");
    if (ClimbSafeApplication.getClimbSafe().findMemberFromEmail(email) != null)
      throw new InvalidInputException("A member with this email already exists");
  }

  /**
   * Method for input validation. Makes sure all constraints are respected:
   * 
   * @param password |string| Password inputted by the user.
   * @throws InvalidInputException Password must not be empty or null
   * @author Theo Ghanem
   */

  private static void passwordIsValid(String password) throws InvalidInputException {
    if (password.equals(""))
      throw new InvalidInputException("The password cannot be empty");
    if (password == null)
      throw new InvalidInputException("The password must not be null");
  }

  /**
   * Method for that checks if the email inputted by the user is the Admin's email or not.
   * 
   * @param email |string| Email inputted by the user.
   * @author Theo Ghanem
   */

  private static void userIsAdmin(String email) {
    if (email.equals("admin@nmc.nt"))
      System.out.println("User is the Administrator");
  }

  /**
   * Method for input validation. Makes sure all constraints are respected:
   * 
   * @param name |string| Name inputted by the user.
   * @throws InvalidInputException Name must not be empty or null
   * @author Theo Ghanem
   */

  private static void nameIsValid(String name) throws InvalidInputException {
    if (name.equals(""))
      throw new InvalidInputException("The name cannot be empty");
    if (name == null)
      throw new InvalidInputException("The name must not be null");
  }

  /**
   * Method for input validation. Makes sure all constraints are respected:
   * 
   * @param emergencyContact |string| Emergency Contact's phone number inputted by the user.
   * @throws InvalidInputException Emergency Contact must not be empty or null
   * @author Theo Ghanem
   */

  private static void emergencyContactIsValid(String emergencyContact) throws InvalidInputException {
    if (emergencyContact.equals(""))
      throw new InvalidInputException("The emergency contact cannot be empty");
    if (emergencyContact == null)
      throw new InvalidInputException("The emergency contact must not be null");
  }

  /**
   * Method for input validation. Makes sure all constraints are respected:
   * 
   * @param nrWeeks |int| Number of weeks inputted by the user.
   * @throws InvalidInputException Number of weeks must be greater than zero and less than or equal
   *         to the number of climbing weeks in the climbing season
   * @author Theo Ghanem
   */

  private static void validNrWeeks(int nrWeeks) throws InvalidInputException {
    if (nrWeeks <= 0 || nrWeeks > climbSafe.getNrWeeks())
      throw new InvalidInputException(
          "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season");
  }


  /**
   * Method for input validation. Makes sure all constraints are respected:
   * 
   * @param itemNames |List<String>| Desired items inputted by the user.
   * @throws InvalidInputException The items must be bookable.
   * @author Theo Ghanem
   */

  private static void itemNamesIsValid(List<String> itemNames) throws InvalidInputException {
    for (String item : itemNames) {
      if (BookableItem.getWithName(item) == null)
        throw new InvalidInputException("Requested item not found");
    }
  }


  /**
   * Method for input validation. Makes sure all constraints are respected:
   * 
   * @param quantity |List<Integer>| Desired quantity of items inputted by the user.
   * @throws InvalidInputException For each BookedItem: quantity must be greater than 0
   * @author Theo Ghanem
   */

  private static void bookedItemQuanityIsValid(List<Integer> quantity) throws InvalidInputException {
    for (Integer q : quantity) {
      if (q <= 0)
        throw new InvalidInputException("The quantity of booked item must be greater than 0");
    }
  }
}
