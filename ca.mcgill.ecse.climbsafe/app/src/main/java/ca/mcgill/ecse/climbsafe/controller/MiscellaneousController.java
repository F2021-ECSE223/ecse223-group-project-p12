package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.sql.Date;
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

	public static List<String> getEquipmentBundleNamesList(){
		ArrayList<String> names = new ArrayList<>();
		for(EquipmentBundle b: climbSafe.getBundles()) names.add(b.getName());
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
	
	public static String getMemberStatus(String memberEmail ) {
		Member m = climbSafe.findMemberFromEmail(memberEmail);
		return m.getSmFullName();
	}


	public static Date getSeasonStartDate(){
		return climbSafe.getStartDate();
	}

	public static int getPriceOfGuide() {
		return climbSafe.getPriceOfGuidePerWeek();
	}

	public static String getAbsolutePathRelativeToApp(String path){
		String relativePath = "";
		if(Paths.get(".").normalize().toAbsolutePath().endsWith("app"))
			relativePath += "./";
		else if(Paths.get(".").normalize().toAbsolutePath().endsWith("ca.mcgill.ecse.climbsafe")) {
			relativePath += "./app/";
		}
		else
			relativePath += "./ca.mcgill.ecse.climbsafe/app/";
		relativePath += path;
		return Paths.get(relativePath).toAbsolutePath().normalize().toString();
	}
	
	public static void deleteAllAssignments() {
		List<Assignment> assignmentList = climbSafe.getAssignments();
		List<Guide> guideList = climbSafe.getGuides();
		while( assignmentList.size() != 0 ) {
			assignmentList.get(0).delete();
		}
		for( Guide g : guideList ) {
			g.setBookings(0);
		}
		ClimbSafePersistence.save();
	}
}
