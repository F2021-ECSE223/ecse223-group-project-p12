package ca.mcgill.ecse.climbsafe.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet6Controller {

  /**
   * Finds an equipment that is part of ClimbSafe and deletes it using the delete method found in
   * the Equipment class
   * 
   * @param name - refers to the name of the equipment that needs to be deleted
   * @throws InvalidInputException - Thrown if the Equipment asked for does not exists
   * @author Zachary Godden
   */
  public static void deleteEquipment(String name) throws InvalidInputException {
    Equipment equipment = ClimbSafeApplication.getClimbSafe().findEquipmentFromName(name);
    // Creates a pointer to the equipment asked for
    if (equipment == null) {
      // if the equipment does not exist then throw exception
      throw new InvalidInputException("Equipment does not exist");
    }
    for (int i = 0; i < ClimbSafeApplication.getClimbSafe().getBundleItems().size(); i++) {
      // Iterates through each Bundle in CLimbSafe
      if (ClimbSafeApplication.getClimbSafe().getBundleItem(i).getEquipment().equals(equipment)) {
        // Checks to see if the equipment deleted is part of a bundle
        throw new InvalidInputException(
            "The piece of equipment is in a bundle and cannot be deleted");
        // if it is part of a bundle throw exception
      }
    }

    equipment.delete(); // removes equipment from ClimbSafe
    ClimbSafePersistence.save();
  }

  /**
   * Finds and deletes and Equipment Bundle that is part of ClimbSafe using the delete method found
   * in the EquipmentBundle class
   * 
   * @param name - referes to the name of the EquipmentBundle that needs to be deleted
   * @author Zachary Godden
   */
  public static void deleteEquipmentBundle(String name) {
    EquipmentBundle equipmentBundle =
        ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(name);
    // Creates a pointer to the EquipmentBundle asked for
    if (equipmentBundle == null) {
      // if the equipment bundle does not exist, the function does nothing
    } else { // if the bundle exists then it is deleted from CLimbSafe
      equipmentBundle.delete();
      ClimbSafePersistence.save();
    }
  }

  /**
   * This method, when called, returns a list of Assignment Transfer Objects that contain the
   * necessary variables that the view needs to interact with the user
   * 
   * @return - An ArrayList of TOAssignments that refer to a list of all assignments in ClimbSafe
   * @author Zachary Godden
   */
  public static List<TOAssignment> getAssignments() {

    List<TOAssignment> assignments = new ArrayList<TOAssignment>();
    // instantiates an ArrayList that will be returned containing TOAssignments
    for (Assignment a : ClimbSafeApplication.getClimbSafe().getAssignments()) {
      int equipmentCost = 0;
      for(BookedItem e: a.getMember().getBookedItems()){
        if(e.getItem() instanceof EquipmentBundle) equipmentCost += bundlePrice((EquipmentBundle) e.getItem(), e.getQuantity(), a.getEndWeek() - a.getStartWeek() + 1, a.hasGuide());
        else if(e.getItem() instanceof Equipment) equipmentCost += equipmentPrice((Equipment) e.getItem(), e.getQuantity(), a.getEndWeek() - a.getStartWeek() + 1);
      }
      int guideCost = a.hasGuide() ? ClimbSafeApplication.getClimbSafe().getPriceOfGuidePerWeek() * (a.getEndWeek() - a.getStartWeek() + 1) : 0;
      TOAssignment to = new TOAssignment(a.getMember().getEmail(), a.getMember().getName(), a.getGuide() == null ? "" : a.getGuide().getEmail(),
              a.getGuide() == null ? "" : a.getGuide().getName(), a.getHotel() == null ? "" : a.getHotel().getName(), a.getStartWeek(), a.getEndWeek(), guideCost, equipmentCost,
              a.getSmFullName(), a.getPaymentCode(), a.getMember().getRefund());
      assignments.add(to);
    }


    return assignments;
  }

  /**
   * Used to calculate the Price of an equipment based on its price per week, amount of itself and
   * the amount of weeks
   * 
   * @param equ - The equipment that needs its rice calculated
   * @param amount - the number of items booked
   * @param time - the amounts of weeks the equipment is booked for
   * @return - a int price
   * @author Zachary Godden
   */
  private static int equipmentPrice(Equipment equ, int amount, int time) {
    int price = equ.getPricePerWeek() * time * amount; // calculates total price of equipment
    return price;
  }

  private static int bundlePrice(EquipmentBundle bundle, int amount, int weeks, boolean guide){
    int price = 0;
    for(BundleItem i: bundle.getBundleItems()) price += i.getEquipment().getPricePerWeek() * i.getQuantity();
    price *= weeks;
    price *= amount;
    if(guide) price *= (1 - ((double) bundle.getDiscount()) / 100d);
    return price;
  }

}
