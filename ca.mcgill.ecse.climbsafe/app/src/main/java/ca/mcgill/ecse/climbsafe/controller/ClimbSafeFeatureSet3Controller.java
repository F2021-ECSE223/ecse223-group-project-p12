package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Guide;


/**
 * This class is responsible for the implementation of the features relating to the guide.
 * Namely, registering a new guide and updating a guide 
 *
 * @author Chris Hatoum
 */


public class ClimbSafeFeatureSet3Controller {
	
/**
 * This method register the guide into the database
 * @param email
 * @param password
 * @param name
 * @param emergencyContact
 * @throws InvalidInputException
 */
  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {

        emailValid(email);
        passwordValid(password);
        nameValid(name);
        emergencyContactValid(emergencyContact);

    ClimbSafeApplication.getClimbSafe().addGuide(new Guide(email, password, name, emergencyContact,ClimbSafeApplication.getClimbSafe()));

  }


/**
 * This method updates the information of a given guide
 * @param email
 * @param newPassword
 * @param newName
 * @param newEmergencyContact
 * @throws InvalidInputException
 */
public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {

   // If the guide does not exist yet then we can't update his information.So we check first if he exists
    // then we delete the old guide(so the old information of the guide) and add a guide with the new information

	emailValid(email);
    passwordValid(newPassword);
    nameValid(newName);
    emergencyContactValid(newEmergencyContact);
    
    Guide guide = ClimbSafeApplication.getClimbSafe().findGuideFromEmail(email);
    if(guide!= null){
      ClimbSafeApplication.getClimbSafe().addGuide(new Guide(email, newPassword, newName, newEmergencyContact, ClimbSafeApplication.getClimbSafe()));
    }
  }

/**
 * This method checks if the email provided is with accordance with the given constraints
 * @param email
 * @throws InvalidInputException
 */
  public static void emailValid(String email) throws InvalidInputException {
	  for(int i=0 ; i<email.length(); i++) {
		  // Constraint for the email containing spaces
		  if (email.charAt(i)== ' ') throw new InvalidInputException("The email must not contain any spaces");
		  
		  else if(email.charAt(i)== '@') {
			  // Constraint for The @ character cannot be at the beginning of the email
			  if(i== 0) throw new InvalidInputException("The @ character cannot be at the beginning of the email");
			  
			  // Constraint for There cannot exist more than one @ character
			  if(email.indexOf("@") != email.lastIndexOf("@")) throw new InvalidInputException("There cannot exist more than one @ character");
		  }  
	  }
	  // Constraint for the index of @ being after the last .
	  if (email.indexOf("@") > email.lastIndexOf(".")) throw new InvalidInputException("The index of @ cannot be after the last .");
	  
	  // Constraint for the last . being at the end of the email
	  if(email.lastIndexOf(".") >= email.length()-1) throw new InvalidInputException("There must not be a dot at the very end of the email");
	  
	  //Constraint for the input email of the guide being the one of the administrator
	  if(email.equals("admin@nmc.nt")) throw new InvalidInputException("This email is reserved for administrators only");
  }

/**
 * This method checks if the password is valid given the constraints 
 * @param password
 * @throws InvalidInputException
 */
  public static void passwordValid(String password) throws InvalidInputException{

	  if (password == null) throw new InvalidInputException("The password cannot be null");
	  if (password.equals("")) throw new InvalidInputException("The password cannot be empty");  
  }

 /**
  * This method checks if the name is valid given the constraints
  * @param name
  * @throws InvalidInputException
  */
public static void nameValid(String name) throws InvalidInputException{

	if (name == null) throw new InvalidInputException("The name cannot be null");
	if (name.equals("")) throw new InvalidInputException("The name cannot be empty");	 
  }

/**
 * This method checks if the emergency contact is valid given the constraints
 * @param emergencyContact
 * @throws InvalidInputException
 */
public static void emergencyContactValid(String emergencyContact) throws InvalidInputException{

	
    if (emergencyContact == null) throw new InvalidInputException("The emergency contact cannot be null");
	if (emergencyContact.equals("")) throw new InvalidInputException("The emergency contact cannot be empty");
	}
}


