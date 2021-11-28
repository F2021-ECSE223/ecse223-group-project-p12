package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;

import java.util.ArrayList;
import java.util.List;

public class MiscellaneousController {

	static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	public static List<Equipment> getEquipmentList(){
		return climbSafe.getEquipment();
	}

	public static List<String> getEquipmentNamesList(){
		ArrayList<String> names = new ArrayList<String>();
		for(Equipment e: climbSafe.getEquipment()) names.add(e.getName());
		return names;
	}

	public static String[] getEquipmentNamesArray(){
		String[] names = new String[climbSafe.getEquipment().size()];
		for(int i = 0; i < climbSafe.getEquipment().size(); i++)
			names[i] = climbSafe.getEquipment(i).getName();
		return names;
	}

	public static String[] getEquipmentBundleNamesArray(){
		String[] names = new String[climbSafe.getBundles().size()];
		for(int i = 0; i < climbSafe.getBundles().size(); i++)
			names[i] = climbSafe.getBundle(i).getName();
		return names;
	}

	public static int getSeasonNumberOfWeeks() {
		return climbSafe.getNrWeeks();
	}
}
