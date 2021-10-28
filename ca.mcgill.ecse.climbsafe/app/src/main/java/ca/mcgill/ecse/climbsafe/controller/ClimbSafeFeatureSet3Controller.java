package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Guide;

/**
 * This class is responsible for the implementation of the features relating to the guide. Namely,
 * registering a new guide and updating a guide.
 *
 * @author Chris Hatoum
 */
public class ClimbSafeFeatureSet3Controller {

  /**
   * This method registers the guide into the database.
   * 
   * @author Chris Hatoum
   * 
   * @param email Guide's email
   * @param password Guide's password
   * @param name Guide's name
   * @param emergencyContact Guide's emergency contact
   * @throws InvalidInputException If constraints are not respected, the method will throw the
   *         corresponding InvalidInputException
   */
  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {

    // Check if our input is with accordance with the given constraints
    emailValid(email);
    passwordValid(password);
    nameValid(name);
    emergencyContactValid(emergencyContact);

    // If so, add a new guide with the given input
    ClimbSafeApplication.getClimbSafe().addGuide(
        new Guide(email, password, name, emergencyContact, ClimbSafeApplication.getClimbSafe()));
  }

  /**
   * This method updates the information of a given guide.
   * 
   * @author Chris Hatoum
   * 
   * @param email
   * @param newPassword Guide's new password
   * @param newName Guide's new name
   * @param newEmergencyContact Guide's new emergency contact
   * @throws InvalidInputException If constraints are not respected, the method will throw an
   *         InvalidInputException
   */
  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {

    Guide guide = ClimbSafeApplication.getClimbSafe().findGuideFromEmail(email);
    if (guide == null)
      throw new InvalidInputException("Guide not found");

    // Makes sure all the constraints are respected. If they are we set the new input as the guide's
    // input
    // (ie: if newPassword is valid, we set the password of the guide to be the newPassword)

    passwordValid(newPassword);
    nameValid(newName);
    emergencyContactValid(newEmergencyContact);

    guide.setPassword(newPassword);
    guide.setName(newName);
    guide.setEmergencyContact(newEmergencyContact);
  }

  /**
   * This method checks if the email provided is with accordance with the given constraints.
   * 
   * @author Chris Hatoum
   * 
   * @param email The email inputed
   * @throws InvalidInputException If constraints are not respected, the method will throw the
   *         corresponding InvalidInputException
   */
  private static void emailValid(String email) throws InvalidInputException {

    if (email == null)
      throw new InvalidInputException("Password cannot be empty");
    if (email.equals(""))
      throw new InvalidInputException("Email cannot be empty");

    for (int i = 0; i < email.length(); i++) {
      // Constraint for the email containing spaces
      if (email.charAt(i) == ' ')
        throw new InvalidInputException("Email must not contain any spaces");

      else if (email.charAt(i) == '@') {

        // Constraint for "The @ character cannot be at the beginning of the email"
        if (i == 0)
          throw new InvalidInputException("Invalid email");

        // Constraint for "There cannot exist more than one @ character"
        if (email.indexOf("@") != email.lastIndexOf("@"))
          throw new InvalidInputException("Invalid email");
      }
    }
    // Constraint for the index of @ being after the last
    if (email.indexOf("@") > email.lastIndexOf("."))
      throw new InvalidInputException("Invalid email");

    // Constraint for the last . being at the end of the email
    if (email.lastIndexOf(".") >= email.length() - 1)
      throw new InvalidInputException("Invalid email");

    // Constraint for the .being right after the @ sign
    if (email.indexOf("@") == email.indexOf(".") - 1)
      throw new InvalidInputException("Invalid email");

    // Constraint for the input email of the guide being the one of the administrator
    if (email.equals("admin@nmc.nt"))
      throw new InvalidInputException("Email cannot be admin@nmc.nt");
    if (ClimbSafeApplication.getClimbSafe().findGuideFromEmail(email) != null)
      throw new InvalidInputException("Email already linked to a guide account");
    if (ClimbSafeApplication.getClimbSafe().findMemberFromEmail(email) != null)
      throw new InvalidInputException("Email already linked to a member account");
  }

  /**
   * This method checks if the password is valid given the constraints.
   * 
   * @author Chris Hatoum
   * 
   * @param password The password inputed
   * @throws InvalidInputException Password must not be empty or null
   */
  private static void passwordValid(String password) throws InvalidInputException {

    if (password == null)
      throw new InvalidInputException("Password cannot be empty");
    if (password.equals(""))
      throw new InvalidInputException("Password cannot be empty");
  }

  /**
   * This method checks if the name is valid given the constraints.
   * 
   * @author Chris Hatoum
   * 
   * @param name The name inputed
   * @throws InvalidInputException Name must not be empty or null
   */
  private static void nameValid(String name) throws InvalidInputException {

    if (name == null)
      throw new InvalidInputException("Name cannot be empty");
    if (name.equals(""))
      throw new InvalidInputException("Name cannot be empty");
  }

  /**
   * This method checks if the emergency contact is valid given the constraints.
   * 
   * @author Chris Hatoum
   * 
   * @param emergencyContact The emergencyContact inputed
   * @throws InvalidInputException EmergencyContact must not be empty or null
   */
  private static void emergencyContactValid(String emergencyContact) throws InvalidInputException {

    if (emergencyContact == null)
      throw new InvalidInputException("Emergency contact cannot be empty");
    if (emergencyContact.equals(""))
      throw new InvalidInputException("Emergency contact cannot be empty");
  }
}


