package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.model.*;

public class AssignmentController {

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
	 * @param m Member who has paid.
	 * @param authorizationCode Authorization code of the payment.
	 */
	public static void payTrip(Member m, String authorizationCode) {



	}

	/**
	 * @author Philippe Sarouphim Hochar
	 *
	 * This method starts all the trips of a given week.
	 *
	 * @param week Week in which to start trips.
	 */
	public static void startTrips(int week)
	{
		for(Assignment a: climbSafe.getAssignments())
			if(a.getStartWeek() == week)
				climbSafe.getAssignment(climbSafe.indexOfAssignment(a)).start();
	}

	/**
	 * @author Theo Ghanem
	 *
	 * This method finished the trip for a member
	 *
	 * @param m Member whose trip finished.
	 */
	public static void finishTrip(Member m)
	{
		
	}

	/**
	 * @author Chris Hatoum
	 * This method cancels the Trip for a member
	 * @param m Member whose trip got cancelled.
	 */
	public static void cancelTrip( Member m )
	{
		
	}
}
