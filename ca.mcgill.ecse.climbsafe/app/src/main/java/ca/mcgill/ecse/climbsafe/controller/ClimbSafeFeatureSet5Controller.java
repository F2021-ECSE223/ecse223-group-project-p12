package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

/**
 * This class is responsible for the implementation of the features relating to the equipment bundle.
 * Namely, its purpose is to help with the creation and updating of equipment bundles.
 *
 * @author Habib Jarweh
 */

public class ClimbSafeFeatureSet5Controller {
	
static ClimbSafe climbSafe  = ClimbSafeApplication.getClimbSafe();	

/**
 * This method adds an equipment bundle to the climb safe application.
 *
 * @param name Name of the equipment bundle to add
 * @param discount Discount of the equipment bundle to add (must be >= 0, <= 100 )
 * @param equipmentNames List of the names of the equipments we want to add to the the bundle (must be
 *  at least two names in the list of equipment names, can't have same name more than once, 
 * and names have to be valid equipment items)
 * @param equipmentQuantities List of the quantities of each equipment we want in our bundle ( quantity must be > 0)
 * @throws InvalidInputException if 
 * discount is less than 0 or more than 100
 * if list size is less than 2
 * if an equipment name is listed more than once
 * if equipment name listed is not a valid equipment name
 * if we don't have at least a quantity of 1 of every equipment name listed
 */

  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames, List<Integer> equipmentQuantities) 
		  throws InvalidInputException {
	  
	  //makes sure all the constraints are respected.
      nameIsValid(name);
      discountIsValid(discount);
      namesAreValid(equipmentNames);
      quantitiesAreValid(equipmentQuantities);
      bookableItemIsValid1(climbSafe.getEquipment(), name);
      bookableItemIsValid2(climbSafe.getBundles(), name);
	  
	  //create new equipment bundle
	  EquipmentBundle equipmentBundle = new EquipmentBundle(name, discount, climbSafe);
      // add bundle to climb safe
	  climbSafe.addBundle(equipmentBundle);
	  
	  // create new bundle items and then add them to bundle
	  for (int i= 0; i< equipmentNames.size(); i++) {
		  BundleItem bundleItem = new BundleItem(equipmentQuantities.get(i), climbSafe, equipmentBundle, 
		    		  climbSafe.findEquipmentFromName(equipmentNames.get(i)));
		  equipmentBundle.addBundleItem(bundleItem);
		  }	  
  }

  
  /**
   * This method updates an existing equipment bundle.
   *
   * @param name Name of the equipment bundle to add
   * @param discount Discount of the equipment bundle to add (must be >= 0, <= 100 )
   * @param equipmentNames List of the names of the equipments we want to add to the the bundle (must be
   *  at least two names in the list of equipment names, can't have same name more than once, 
   * and names have to be valid equipment items)
   * @param equipmentQuantities List of the quantities of each equipment we want in our bundle ( quantity must be > 0)
   * @throws InvalidInputException if 
   * discount is less than 0 or more than 100
   * if list size is less than 2
   * if an equipment name is listed more than once
   * if equipment name listed is not a valid equipment name
   * if we don't have at least a quantity of 1 of every equipment name listed
   */  
  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount, List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {
	  
	  //makes sure all the constraints are respected.
	  nameIsValid(oldName);
	  nameIsValid(newName);
	  discountIsValid(newDiscount);
	  namesAreValid(newEquipmentNames);
	  quantitiesAreValid(newEquipmentQuantities);
      bookableItemIsValid1(climbSafe.getEquipment(), newName);
      //if the bundle we want to update has the same name, then we don't check if there already
      //exists a bundle with the name newName, since we know there does exist
      if(!oldName.equals(newName)) {
    	  bookableItemIsValid2(climbSafe.getBundles(), newName);  
      }
	  
	  //finding old equipment bundle from oldName
      EquipmentBundle equipmentBundle = climbSafe.findEquipmentBundleFromName(oldName);
      // constraint checks that there does exist an equipment bundle with name oldName
      oldEquipmentBundleIsValid(equipmentBundle, oldName);

	  //updating the equipment bundle
	  equipmentBundle.delete();
	  equipmentBundle.setName(newName);
	  equipmentBundle.setDiscount(newDiscount);
	  equipmentBundle.setClimbSafe(climbSafe);
	  
	  // create new bundle items and then add them to bundle
	  for (int i= 0; i< newEquipmentNames.size(); i++) {
	      BundleItem bundleItem = new BundleItem(newEquipmentQuantities.get(i), climbSafe, equipmentBundle,
	    		  climbSafe.findEquipmentFromName(newEquipmentNames.get(i)));
	      equipmentBundle.addBundleItem(bundleItem);
	  }

       
  } 
  
  /**
   * Method for input validation. Makes sure all constraints are respected:
   * @param name |String| name of equipment bundle inputed by user.
   * @throws InvalidInputException if no name is inputed
   * @author Habib Jarweh
   */  
private static void nameIsValid(String name) throws InvalidInputException {
	//makes sure name is not empty (or null)
	if(name.isEmpty()) throw new InvalidInputException("Equipment bundle name cannot be empty");
}

/**
 * Method for input validation. Makes sure all constraints are respected:
 * @param discount |int| discount inputed by user.
 * @throws InvalidInputException discount has to be between 0 and a 100, no more no less
 * @author Habib Jarweh
 */
private static void discountIsValid(int discount) throws InvalidInputException {
	/* discount constraints make sure the discount inputed is less than 100 (%) and more than 0 (%). */
	if (discount<0) throw new InvalidInputException("Discount must be at least 0");
	if (discount > 100) throw new InvalidInputException("Discount must be no more than 100");
}

/**
 * Method for input validation. Makes sure all constraints are respected:
 * @param equipmentNames |List<String>| Desired equipment names inputted by the user.
 * @throws InvalidInputException there should be at least two different equipment names inputed.
 * @throws InvalidInputException if the equipment name inputed is not a valid equipment
 * @author Habib Jarweh
 */
private static void namesAreValid(List<String> equipmentNames) throws InvalidInputException {
	boolean found = false ;
	
	// equipment quantity constraints, first if statement makes sure size of equipmentNames is at least 2
    if (equipmentNames.size() < 2) throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment"); 
    else {
	    for (int i = 0; i<equipmentNames.size(); i++) {
	    	for (int j = i +1; j<equipmentNames.size(); j++) {
	    		if ((equipmentNames.get(i)).equals(equipmentNames.get(j)) )	{
	    			throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment");
	            }
	         }
	     }
    }
    // to make sure that all equipment names are valid
	for (int i = 0; i<equipmentNames.size(); i++) {
		for (int j= 0; j< climbSafe.getEquipment().size(); j++) {
			if (equipmentNames.get(i).equals( climbSafe.getEquipment().get(j).getName()) ) found = true ;	  
	    }
		// if equipment name doesn't match any name from equipments list then  
		if (found == false) throw new InvalidInputException("Equipment "+ equipmentNames.get(i)+ " does not exist");
		found = false ; //  re-intializing found to false to check if next name in the list is valid
    }

}

/**
 * Method for input validation. Makes sure all constraints are respected:
 * @param equipmentQuantities |List<Integer>| desired quantities of equipment inputed by user.
 * @throws InvalidInputException each quantity cannot be less than 0.
 * @author Habib Jarweh
 */
private static void quantitiesAreValid(List<Integer> equipmentQuantities) throws InvalidInputException {
	  // if statement (in the for loop) makes sure there is at least one of every equipment item selected
	  for (Integer equipmentQuantity: equipmentQuantities) {
		  if (equipmentQuantity <= 0) throw new InvalidInputException("Each bundle item must have quantity greater than or equal to 1 ");
	  }	
}

/**
 * Method for input validation. Makes sure all constraints are respected:
 * @param equipments |List<Equipment>| the list of equipment in climbSafe is going to be inputed here.
 * @param name |String| name is going to be inputed in the parameter name, or newName in the case of updateEquipmentBundle
 * this method checks that there isn't already an equipment item with the name name
 * @throws InvalidInputException there already exists a bookable item or an equipment in this case with the name name
 * @author Habib Jarweh
 */
private static void bookableItemIsValid1(List<Equipment> equipments, String name) throws InvalidInputException {
	  for (Equipment equipment: equipments) {
		  if (equipment.getName().equals(name)) throw new InvalidInputException("A bookable item called "+ name + " already exists");
	  }	
}

/**
 * Method for input validation. Makes sure all constraints are respected:
 * @param bundles |List<EquipmentBundle>| the list of equipment bundles in climbSafe is going to be inputed here.
 * @param name |String| name is going to be inputed in the parameter name, or newName in the case of updateEquipmentBundle
 * this method checks that there isn't already a bundle with the name name
 * @throws InvalidInputException there already exists a bookable item or a bundle in this case with the name name
 * @author Habib Jarweh
 */
private static void bookableItemIsValid2(List<EquipmentBundle> bundles, String name) throws InvalidInputException {
	  for (EquipmentBundle equipmentbundle: bundles) {
		  if ( equipmentbundle.getName().equals(name)) throw new InvalidInputException("A bookable item called "+ name + " already exists");
	  }
}

/**
 * Method for input validation. Makes sure all constraints are respected:
 * @param equipmentbundle |EquipmentBundle| bundle that we want to update, which we get using oldName.
 * @param name |String| oldName is going to be inputed in the parameter name
 * this method finds the old equipment bundle from oldName
 * @throws InvalidInputException bundle with the name oldName does not exist
 * @author Habib Jarweh
 */
private static void oldEquipmentBundleIsValid(EquipmentBundle equipmentbundle, String name) throws InvalidInputException {
	//finding old equipment bundle from oldName
	  if (equipmentbundle == null) throw new InvalidInputException("Equipment bundle " + name + " does not exist");
}

}