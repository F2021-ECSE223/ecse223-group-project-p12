package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;

/**
 * This class is responsible for the implementation of the features relating to the equipment.
 * Namely, its purpose is to help with the creation and updating of equipment.
 *
 * @author Philippe Sarouphim Hochar
 */
public class ClimbSafeFeatureSet4Controller {

  /**
   * This method adds an equipment item to the climb safe application.
   *
   * @author Philippe Sarouphim Hochar
   *
   * @param name Name of the equipment to add
   * @param weight Weight of the equipment to add (must be > 0)
   * @param pricePerWeek Price per week of the equipment to add (must be > 0)
   * @throws InvalidInputException If weight or price per week is negative.
   */
  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {
    // Validate input
    if (name.isEmpty())
      throw new InvalidInputException("The name must not be empty");
    if (weight <= 0)
      throw new InvalidInputException("The weight must be greater than 0");
    if (pricePerWeek < 0)
      throw new InvalidInputException("The price per week must be greater than or equal to 0");
    if (ClimbSafeApplication.getClimbSafe().findEquipmentFromName(name) != null)
      throw new InvalidInputException("The piece of equipment already exists");
    if (ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(name) != null)
      throw new InvalidInputException("The equipment bundle already exists");

    // Once input is validated, add the new equipment to the application
    ClimbSafeApplication.getClimbSafe().addEquipment(name, weight, pricePerWeek);
  }

  /**
   * This method updates existing equipment.
   *
   * @author Philippe Sarouphim Hochar
   *
   * @param oldName Name of the equipment to update.
   * @param newName New name to give to the equipment.
   * @param newWeight New weight to give to the equipment.
   * @param newPricePerWeek New price per week to give to the equipment.
   * @throws InvalidInputException If the equipment does not exist, or the weight or the price per
   *         week is negative.
   */
  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
    // Validate input
    if (oldName.isEmpty())
      throw new InvalidInputException("The piece of equipment does not exist");
    if (newName.isEmpty())
      throw new InvalidInputException("The name must not be empty");
    if (!newName.equals(oldName)
        && ClimbSafeApplication.getClimbSafe().findEquipmentFromName(newName) != null)
      throw new InvalidInputException("The piece of equipment already exists");
    if (ClimbSafeApplication.getClimbSafe().findEquipmentBundleFromName(newName) != null)
      throw new InvalidInputException("An equipment bundle with the same name already exists");
    if (ClimbSafeApplication.getClimbSafe().findEquipmentFromName(oldName) == null)
      throw new InvalidInputException("The piece of equipment does not exist");
    if (newWeight <= 0)
      throw new InvalidInputException("The weight must be greater than 0");
    if (newPricePerWeek < 0)
      throw new InvalidInputException("The price per week must be greater than or equal to 0");

    // Remove old equipment from application
    ClimbSafeApplication.getClimbSafe().getEquipment(findEquipmentIndexByName(oldName)).delete();

    // Add updated equipment to application
    addEquipment(newName, newWeight, newPricePerWeek);

  }

  /**
   * This method searches the equipment list in the ClimbSafe application for equipment with
   * specific name and returns said equipment's index, or -1 if none was found.
   *
   * @author Philippe Sarouphim Hochar
   *
   * @param name Name of the equipment to look for.
   * @return Index of equipment with specified name, or -1 if none was found.
   */
  private static int findEquipmentIndexByName(String name) {
    int index = -1; // Index to return, -1 by default

    // Retrieve application's equipment list
    List<Equipment> equipmentList = ClimbSafeApplication.getClimbSafe().getEquipment();

    // Cycle through equipment of the application to find which one's name matches with input
    for (int i = 0; i < equipmentList.size(); i++)
      if (equipmentList.get(i).getName().equals(name)) {
        index = i;
        break;
      }

    // Return the index of the equipment found, or -1 if none was found
    return index;
  }

}
