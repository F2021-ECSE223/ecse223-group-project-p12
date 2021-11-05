package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;

public class AssignmentController {
			static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	/**
	 * @author Cedric Barre
	 *
	 * This method makes the pairing between trips and guides.
	 *
	 */
	public static void initiateAssignments() {
		
	}

	/**
	 * @author Habib Jarweh
	 *
	 * This method changes the payment status of a member to true.
	 *
	 * @param memberEmail Member who has paid.
	 * @param authorizationCode Authorization code of the payment.
	 */
	public static void payTrip(String memberEmail, String authorizationCode)
	throws InvalidInputException{



	}

	/**
	 * @author Philippe Sarouphim Hochar
	 *
	 * This method starts all the trips of a given week.
	 *
	 * @param week Week in which to start trips.
	 */
	public static void startTrips(int week) throws InvalidInputException{
		if(week > climbSafe.getNrWeeks() || week < 1) throw new InvalidInputException("Week number out of bounds");
		for(Assignment a: climbSafe.getAssignments())
			if(a.getStartWeek() == week)
				climbSafe.getAssignment(climbSafe.indexOfAssignment(a)).start();
	}

	/**
	 * @author Theo Ghanem
	 *
	 * This method finished the trip for a member
	 *
	 * @param memberEmail Member whose trip finished.
	 */
	public static void finishTrip(String memberEmail) throws InvalidInputException{
		
	}

	/**
	 * @author Chris Hatoum
	 * 
	 * This method cancels the Trip for a member
	 * @param memberEmail Member whose trip got cancelled.
	 */
	public static void cancelTrip(String memberEmail) throws InvalidInputException{
		Member member = climbSafe.findMemberFromEmail(memberEmail);
		if(member == null) throw new InvalidInputException("The member with " + memberEmail + "doesn't exist");
		else {
			member.getAssignment().cancel();
			}
		
	}
}
