package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet6Controller {

/**
 * Finds an equpiment that is part of ClimbSafe and deletes it using the delte method found in the Equipment class
 * @param name - refers to the name of the equipment that needs to be deleted
 * @throws InvalidInputException - Thrown if the Equipment asked for does not exists
 */
	public static void deleteEquipment(String name) throws InvalidInputException {
		Equipment equipment = ClimbSafeApplication.getClimbSafe().findEquipmentFromName(name);	//Creates a pointer to the equipment asked for
		if(equipment == null) {	//if the equipment does not exist then throw exception
			throw new InvalidInputException("Equipment does not exist");
		}
		for(int i=0;i<ClimbSafeApplication.getClimbSafe().getBundleItems().size();i++) {	//Iterates through each Bundle in CLimbSafe
			if(ClimbSafeApplication.getClimbSafe().getBundleItem(i).getEquipment().equals(equipment)) {	//Checks to see if the equipment deleted is part of a bundle
				throw new InvalidInputException("The piece of equipment is in a bundle and cannot be deleted");	//if it is part of a bundle throw exception
			}
		}

		equipment.delete();	//removes equipment from ClimbSafe
	}

	/**
	 * Finds and deletes and Equipment Bundle that is part of ClimbSafe using the delete method found in the EquipmentBundle class
	 * @param name - referes to the name of the EquipmentBundle that needs to be deleted
	 */
	public static void deleteEquipmentBundle(String name) {
		EquipmentBundle equipmentBundle = ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(name);	//Creates a pointer to the EquipmentBundle asked for
		if(equipmentBundle == null) {	//if the equipment bundle does not exist, the function does nothing
		}else {	//if the bundle exists then it is deletd from CLimbSafe
			equipmentBundle.delete();
		}
	}

	/**
	 * This method, when called, returns a list of Assignment Transfer Objects that contain the necessary variables that the view needs to
	 * interact with the user
	 * @return - An ArrayList of TOAssignments that refer to a list of all assignments in ClimbSafe
	 */
	public static List<TOAssignment> getAssignments() {

		List<TOAssignment> assignments = new ArrayList<TOAssignment>();	//instantiates an ArrayList that will be returned containing TOAssignments
		for (Assignment assignment : ClimbSafeApplication.getClimbSafe().getAssignments()) {	//Iterates through all assignments in CLimbSafe
			int totalItemPrice = 0;	//total price of equipment
			double bundPrice = 0;	//Price of a specific bundle
			int stepPrice = 0;	//intermediate price for calculating
			double discount;	//price of bundle discount in currency
			for (BookedItem item : assignment.getMember().getBookedItems()) {	//Iterates through the booked items in an assignment to get its price
				if(item.getItem() instanceof Equipment) {	//If the bookedItem is an Equipment
					Equipment equ = (Equipment) item.getItem();	//creates an instance of specific equipment
					totalItemPrice += equipmentPrice(equ, item.getQuantity(), assignment.getEndWeek()-assignment.getStartWeek()+1);	//updates the totalPrice based on equipment price
				}else if(item.getItem() instanceof EquipmentBundle) {	//if bookedItem is a bundle
					EquipmentBundle bun = (EquipmentBundle) item.getItem();	//creates an instance of the EquipmentBundle
					for(BundleItem bunItem : bun.getBundleItems()) {	//iterates through equipments in the Bundle
						stepPrice += equipmentPrice(bunItem.getEquipment(), bunItem.getQuantity(), assignment.getEndWeek()-assignment.getStartWeek()+1);	//calculates price of specific equipment in bundle
					}
					if(assignment.getGuide() == null) {	//if assignment does not have a guide then bundle does not get a discount
						discount = 1;
					}else {
						discount = (double) (100-(double)bun.getDiscount())/100;	//calculates the discount based on the bundle
					}
					bundPrice = ((double) stepPrice)*discount;	//calculates the specific bundle price
					totalItemPrice += bundPrice*item.getQuantity();	//adds the bundle price to the totalPrice for equipment 
				}
			}
			String guideEmail;
			String guideName;
			String hotelName;
			int priceGuide = 0;
			if(assignment.getGuide() == null) {	//if the assignment does not have a guide
				guideEmail = null;
				guideName = null;
				priceGuide = 0;
			}else {	//if the member hired a guide
				guideEmail = assignment.getGuide().getEmail();	//sets guide email to String
				guideName = assignment.getGuide().getName();	//sets guide Name to String
				priceGuide = (assignment.getEndWeek()-assignment.getStartWeek()+1)*ClimbSafeApplication.getClimbSafe().getPriceOfGuidePerWeek();	//Calculates the total  price of a guide
			}
			if(assignment.getHotel() == null) {	//if the assignment does not have a booked hotel
				hotelName = null;
			}else {
				hotelName = assignment.getHotel().getName();	//sets hotel Name to String
			}
			assignments.add(new TOAssignment(assignment.getMember().getEmail(), assignment.getMember().getName(), guideEmail, guideName, hotelName, assignment.getStartWeek(), assignment.getEndWeek(), priceGuide, totalItemPrice));	//adds assignment from loop to the return List
		}
		//System.out.println(assignments.get(0).toString());System.out.println(assignments.get(1).toString());System.out.println(assignments.get(2).toString());System.out.println(assignments.get(3).toString());

		return assignments; }

	/**
	 * Used to calculate the Price of an equipment based on its price per week, amount of itself and the amount of weeks
	 * @param equ - The equipment that needs its rice calculated
	 * @param amount - the number of items booked
	 * @param time - the amounts of weeks the equipment is booked for
	 * @return - a int price
	 */
	private static int equipmentPrice(Equipment equ, int amount, int time) {
		int price = equ.getPricePerWeek()*time*amount; //calculates total price of equipment
		return price;
	}

}