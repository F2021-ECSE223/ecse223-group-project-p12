package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet6Controller {

  public static void deleteEquipment(String name) throws InvalidInputException {
	  Equipment equipment = ClimbSafeApplication.getClimbSafe().findEquipmentFromName(name);
	  if(equipment == null) {
		  throw new InvalidInputException("Equipment does not exist");
	  }else equipment.delete();
  }
  
  public static void deleteEquipmentBundle(String name) throws InvalidInputException {
	  EquipmentBundle equipmentBundle = ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(name);
	  if(equipmentBundle == null) {
		  throw new InvalidInputException("Equipment Bundle does not exist");
	  }else equipmentBundle.delete();
  }

  public static List<TOAssignment> getAssignments() {
	  
	  var assignments = new ArrayList<TOAssignment>();
	  for (var assignment : ClimbSafeApplication.getClimbSafe().getAssignments()) {
		  var items = new ArrayList<BookedItem>();
		  items = (ArrayList<BookedItem>) assignment.getMember().getBookedItems();
		  int totalItemPrice = 0;
		  for (BookedItem item : items) {
			  if(item.getItem() instanceof Equipment) {
				  Equipment equ = (Equipment) item.getItem();
				  totalItemPrice += equ.getPricePerWeek()*(assignment.getEndWeek()-assignment.getStartWeek());
			  }else if(item.getItem() instanceof EquipmentBundle) {
				  EquipmentBundle bun = (EquipmentBundle) item.getItem();
				  totalItemPrice -= bun.getDiscount();
			  }
		  }
		  int priceGuidePerWeek = (assignment.getEndWeek()-assignment.getStartWeek())*ClimbSafeApplication.getClimbSafe().getPriceOfGuidePerWeek();
		  assignments.add(new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(), assignment.getGuide().getEmail(), assignment.getGuide().getName(), assignment.getHotel().getName(), assignment.getStartWeek(), assignment.getEndWeek(), priceGuidePerWeek, totalItemPrice));
	  }
	  
	  return null; }

}
