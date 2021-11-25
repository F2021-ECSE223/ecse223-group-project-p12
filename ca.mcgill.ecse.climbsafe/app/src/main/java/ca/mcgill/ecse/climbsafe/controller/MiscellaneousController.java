package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;

public class MiscellaneousController {

	static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	
	public static int getSeasonNumberOfWeeks() {
		return climbSafe.getNrWeeks();
	}
}
