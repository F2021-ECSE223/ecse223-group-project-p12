package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

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
	  if(name.isEmpty()) throw new InvalidInputException("Equipment bundle name cannot be empty");
	  if (discount<0) throw new InvalidInputException("Discount must be at least 0");
	  if (discount > 100) throw new InvalidInputException("Discount must be no more than 100");
	  
	  // equipment quantity constraints, first if statement makes sure size of equipmentNames is at least 2
	  if (equipmentNames.size() < 2) {
		  throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment"); }
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
		  for (int j= 0; j< ClimbSafeApplication.getClimbSafe().getEquipment().size(); j++) {
			  if (equipmentNames.get(i).equals( ClimbSafeApplication.getClimbSafe().getEquipment().get(j).getName()) ) found = true ;	  
		  }
		// if equipment name doesn't match any name from equipments list then  
		  if (found == false) throw new InvalidInputException("Equipment "+ equipmentNames.get(i)+ " does not exist") ;
		  found = false ; //  re-intializing found to false to check if next name in the list is valid
	  }
	  
	  // if statement (in the for loop) makes sure there is at least one of every equipment item selected
	  for (Integer equipmentQuantity: equipmentQuantities) {
		  if (equipmentQuantity <= 0) throw new InvalidInputException("Each bundle item must have quantity greater than or equal to 1 ");
	  }
	  // if statement makes sure lists have to be of the same size because every bundle item has a quantity
	  if (equipmentQuantities.size() != equipmentNames.size()) throw new InvalidInputException("Lists have to be of the same size");
	  //create new equipment bundle
	  for (EquipmentBundle equipmentbundle: ClimbSafeApplication.getClimbSafe().getBundles()) {
		  if ( equipmentbundle.getName().equals(name)) throw new InvalidInputException("A bookable item called "+ name + " already exists");
	  }
	EquipmentBundle equipmentBundle = new EquipmentBundle(name, discount, climbSafe);
	  
      // add bundle to climbsafe
	  climbSafe.addBundle(equipmentBundle);
	
	// create new bundle item and then add them to bundle
		  for (int i= 0; i< equipmentNames.size(); i++) {
		      BundleItem bundleItem = new BundleItem(equipmentQuantities.get(i), climbSafe, equipmentBundle,
		    		  ClimbSafeApplication.getClimbSafe().findEquipmentFromName(equipmentNames.get(i)));
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
   * if equipment names list and equipment quantities list are not the same size
   */  
  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {
	  boolean found = false ; // to be used later to see if names are all valid
	  
	  if(oldName.isEmpty()) throw new InvalidInputException("Equipment bundle old name cannot be empty");
	  if(newName.isEmpty()) throw new InvalidInputException("Equipment bundle new name cannot be empty");
	  /* discount constraints make sure the discount inputed is less than 100 (%) and more than 0 (%). */
	  if (newDiscount<0) throw new InvalidInputException("Discount must be at least 0");
	  if (newDiscount>100) throw new InvalidInputException("Discount must be no more than 100");
	  
	  // equipment quantity constraints, first if statement makes sure size of equipmentNames is at least 2
	  if (newEquipmentNames.size()<2) {
		  throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment"); }
	  else {
		  for (int i = 0; i<newEquipmentNames.size(); i++) {
                for (int j = i +1; j<newEquipmentNames.size(); j++) {
                	if ((newEquipmentNames.get(i)).equals(newEquipmentNames.get(j)) )	{
                		throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment");
                	}
                }
			    //second if statement makes sure that there are not two instances of the same equipment name
//				if ((newEquipmentNames.get(i)).equals(newEquipmentNames.get(i+1)) ) {
//					throw new InvalidInputException("Cannot have same equipment name twice");
//					//break ;
//				}
			}
	  }
	  // to make sure that all equipment names are valid
	  for (int i = 0; i<newEquipmentNames.size(); i++) {
		  for (int j= 0; j< ClimbSafeApplication.getClimbSafe().getEquipment().size(); j++) {
			  if (newEquipmentNames.get(i).equals( ClimbSafeApplication.getClimbSafe().getEquipment().get(j).getName())) found = true ;	  
		  }
		// if equipment name doesn't match any name from equipments list then  
		  if (found == false) throw new InvalidInputException("Equipment "+ newEquipmentNames.get(i)+ " does not exist") ;
		  found = false ; //  re-intializing found to false to check if next name in the list is valid
	  }
	  
	  // if statement (in the for loop) makes sure there is at least one of every equipment item selected
	  for (Integer equipmentQuantity: newEquipmentQuantities) {
		  if (equipmentQuantity <= 0) throw new InvalidInputException("Each bundle item must have quantity greater than or equal to 1 ");
	  }
	  
	  // if statement makes sure lists have to be of the same size because every bundle item has a quantity
	  if (newEquipmentQuantities.size() != newEquipmentNames.size()) throw new InvalidInputException("Lists have to be of the same size");
	  
	  //finding old equipment bundle from oldName
	  EquipmentBundle equipmentBundle = ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(oldName);
	  //updating the equipment bundle 
	  for (EquipmentBundle equipmentbundle: ClimbSafeApplication.getClimbSafe().getBundles()) {
		  if ( equipmentbundle.getName().equals(newName)) throw new InvalidInputException("A bookable item called "+ newName + " already exists");
	  }
	  if (equipmentBundle != null) {
		  equipmentBundle = new EquipmentBundle(newName, newDiscount, climbSafe);
		  equipmentBundle.setName(newName);
		  equipmentBundle.setDiscount(newDiscount);
		  equipmentBundle.setClimbSafe(climbSafe) ;
	  }
	  else throw new InvalidInputException("The equipment bundle with name oldName does not exist");
	  
	  // create new bundle item and then add them to bundle
	  for (int i= 0; i< newEquipmentNames.size(); i++) {
	      BundleItem bundleItem = new BundleItem(newEquipmentQuantities.get(i), climbSafe, equipmentBundle,
	    		  ClimbSafeApplication.getClimbSafe().findEquipmentFromName(newEquipmentNames.get(i)));
	      equipmentBundle.addBundleItem(bundleItem);
	  }
	  
  } 
  
}