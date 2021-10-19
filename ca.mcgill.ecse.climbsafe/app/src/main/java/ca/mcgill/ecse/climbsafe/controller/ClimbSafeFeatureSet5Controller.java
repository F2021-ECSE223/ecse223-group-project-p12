package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

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
 * if equipment names list and equipment quantities list are not the same size
 */

  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames, List<Integer> equipmentQuantities) 
		  throws InvalidInputException {
	  boolean found = false ;
	  
	  /* discount constraints make sure the discount inputed is less than 100 (%) and more than 0 (%). */
	  if (discount<0) throw new InvalidInputException("Discount percentage must be bigger than or equal to 0");
	  if (discount > 100) throw new InvalidInputException("Discount must be less than or equal to 100");
	  
	  // equipment quantity constraints, first if statement makes sure size of equipmentNames is at least 2
	  if (equipmentNames.size() < 2) {
		  throw new InvalidInputException("Must have at least two  bundle items"); }
	  else {
		  for (int i = 0; i<equipmentNames.size(); i++) {
			    //second if statement makes sure that there are not two instances of the same equipment name
				if ((equipmentNames.get(i)).equals(equipmentNames.get(i+1)) {
					throw new InvalidInputException("Cannot have same equipment name twice");
					break ;
				}
			}
	  }
	  
	  // to make sure that all equipment names are valid
	  for (int i = 0; i<equipmentNames.size(); i++) {
		  for (int j= 0; j< getClimbSafe().getEquipment().size()) {
			  if (equipmentNames.get(i) == getClimbSafe().getEquipment().get(j).getName() ) found = true ;	  
		  }
		// if equipment name doesn't match any name from equipments list then  
		  if (found == false) throw new InputInvalidException("Make sure equipment name is valid name") ;
		  found = false ; //  re-intializing found to false to check if next name in the list is valid
	  }
	  
	  // if statement (in the for loop) makes sure there is at least one of every equipment item selected
	  for (Integer equipmentQuantity: equipmentQuantities) {
		  if (equipmentQuantity <= 0) throw new InvalidInputException("Must have at least a quantity of 1 for every equipment item selected");
	  }
	  // if statement makes sure lists have to be of the same size because every bundle item has a quantity
	  if (equipmentQuantities.size() != equipmentNames.size()) throw new InvalidInputException("Lists have to be of the same size");
	  //create new equipment bundle
	  EquipmentBundle equipmentBundle = new EquipmentBundle(name, discount, climbSafe);
      // add bundle to climbsafe
	  ClimbSafeApplication.getClimbSafe().addBundle(equipmentBundle);
	// create new bundle item and then add them to bundle
		  for (int i= 0; i< equipmentNames.size()) {
		      BundleItem bundleItem = new BundleItem(equipmentQuantities.get(i), climbSafe, equipmentBundle,
		    		  findEquipmentFromName(equipmentNames.get(i)));
		      equipmentBundle.add(bundleItem);
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
   * if equipment names list and equipment quantities list are not the same size
   */  
  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {
	  boolean found = false ; // to be used later to see if names are all valid
	  
	  /* discount constraints make sure the discount inputed is less than 100 (%) and more than 0 (%). */
	  if (discount<0) throw new InvalidInputException("Discount percentage must be bigger than or equal to 0");
	  
	  if (discount > 100) throw new InvalidInputException("Discount must be less than or equal to 100");
	  
	  // equipment quantity constraints, first if statement makes sure size of equipmentNames is at least 2
	  if (equipmentNames.size() < 2) {
		  throw new InvalidInputException("Must have at least two  bundle items"); }
	  else {
		  for (int i = 0; i<equipmentNames.size(); i++) {
			    if ()
			    //second if statement makes sure that there are not two instances of the same equipment name
				if ((equipmentNames.get(i)).equals(equipmentNames.get(i+1)) {
					throw new InvalidInputException("Cannot have same equipment name twice");
					break ;
				}
			}
	  }
	  // to make sure that all equipment names are valid
	  for (int i = 0; i<equipmentNames.size(); i++) {
		  for (int j= 0; j< getClimbSafe().getEquipment().size()) {
			  if (equipmentNames.get(i) == getClimbSafe().getEquipment().get(j).getName()) found = true ;	  
		  }
		// if equipment name doesn't match any name from equipments list then  
		  if (found == false) throw new InputInvalidException("Make sure equipment name is valid name") ;
		  found = false ; //  re-intializing found to false to check if next name in the list is valid
	  }
	  
	  // if statement (in the for loop) makes sure there is at least one of every equipment item selected
	  for (Integer equipmentQuantity: equipmentQuantities) {
		  if (equipmentQuantity <= 0) throw new InvalidInputException("Must have at least a quantity of 1 for every equipment item selected");
	  }
	  
	  // if statement makes sure lists have to be of the same size because every bundle item has a quantity
	  if (equipmentQuantities.size() != equipmentNames.size()) throw new InvalidInputException("Lists have to be of the same size");
	  
	  //finding old equipment bundle from oldName
	  EquipmentBundle equipmentBundle = ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(oldName);
	  //updating the equipment bundle 
	  if (equipmentBundle != null) {
		  equipmentBundle = new EquipmentBundle(newName, newDiscount, climbSafe); 
	  }
	  // create new bundle item and then add them to bundle
	  for (int i= 0; i< NewEquipmentNames.size()) {
	      BundleItem bundleItem = new BundleItem(NewEquipmentQuantities.get(i), climbSafe, equipmentBundle,
	    		  findEquipmentFromName(NewEquipmentNames.get(i)));
	      equipmentBundle.addBundleItem(bundleItem);
	  }
  } 
   
}