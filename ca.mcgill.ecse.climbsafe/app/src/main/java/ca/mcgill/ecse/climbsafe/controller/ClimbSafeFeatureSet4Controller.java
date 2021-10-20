package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

public class ClimbSafeFeatureSet4Controller {


import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
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
     * @param name Name of the equipment to add
     * @param weight Weight of the equipment to add (must be > 0)
     * @param pricePerWeek Price per week of the equipment to add (must be > 0)
     * @throws InvalidInputException If weight or price per week is negative.
     */
    public static void addEquipment(String name, int weight, int pricePerWeek) throws InvalidInputException {
        // Validate input
        if(weight < 0) throw new InvalidInputException("Weight can not be negative");
        if(pricePerWeek < 0) throw new InvalidInputException("Price can not be negative");

        // Once input is validated, add the new equipment to the application
        ClimbSafeApplication.getClimbSafe().addEquipment(name, weight, pricePerWeek);
    }

    /**
     * This method updates existing equipment.
     *
     * @param oldName Name of the equipment to update.
     * @param newName New name to give to the equipment.
     * @param newWeight New weight to give to the equipment.
     * @param newPricePerWeek New price per week to give to the equipment.
     * @throws InvalidInputException If the equipment does not exist, or the weight or the price per week is negative.
     */
    public static void updateEquipment(String oldName, String newName, int newWeight, int newPricePerWeek)
            throws InvalidInputException {
        // Validate input
        if(newWeight < 0) throw new InvalidInputException("Weight can not be negative");
        if(newPricePerWeek < 0) throw new InvalidInputException("Price per week can not be negative");

        // Once input is validated, look for equipment in the application
        Equipment equipment = findEquipmentByName(oldName);
        // If equipment does not exist in the application, throw an InvalidInputException
        if(equipment == null) throw new InvalidInputException("The equipment with name oldName does not exist");

        // Affect changes to said equipment
        equipment.setName(newName);
        equipment.setWeight(newWeight);
        equipment.setPricePerWeek(newPricePerWeek);
    }

    /**
     * This method searches the equipment list in the ClimbSafe application for equipment with specific name
     * and returns said equipment's reference, or <code>null</code> if none was found.
     *
     * @param name Name of the equipment ot look for.
     * @return Equipment with specified name, or <code>null</code> if none was found.
     */
    private static Equipment findEquipmentByName(String name){
        Equipment equipment = null; // Reference to return, null by default

        // Retrieve application's equipment list
        List<Equipment> equipmentList = ClimbSafeApplication.getClimbSafe().getEquipment();

        // Cycle through equipment of the application to find which one's name matches with input
        for(int i = 0; i < equipmentList.size(); i++)
            if(equipmentList.get(i).getName().equals(name)) {
                equipment = equipmentList.get(i);
                break;
        }

        // Return the equipment found, or null if none was found
        return equipment;
    }

}
