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
 * 
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
  
  
  public static void passwordValid(String password) throws InvalidInputException{
//    // password must not be empty or null
//    password <> ""
//    password <> null
	  if (password == null) throw new InvalidInputException("The password cannot be null");
	  if (password.equals("")) throw new InvalidInputException("The password cannot be empty");  
  }
  
public static void nameValid(String name) throws InvalidInputException{
//	for each NamedUser:
//		   // name must not be empty or null
//		   name <> ""
//		   name <> null
	if (name == null) throw new InvalidInputException("The name cannot be null");
	if (name.equals("")) throw new InvalidInputException("The name cannot be empty");	 
  }

public static void emergencyContactValid(String emergencyContact) throws InvalidInputException{
//	  // emergencyContact must not be empty or null
//	   emergencyContact <> ""
//	   emergencyContact <> null
	
    if (emergencyContact == null) throw new InvalidInputException("The emergency contact cannot be null");
	if (emergencyContact.equals("")) throw new InvalidInputException("The emergency contact cannot be empty");
	}
}


