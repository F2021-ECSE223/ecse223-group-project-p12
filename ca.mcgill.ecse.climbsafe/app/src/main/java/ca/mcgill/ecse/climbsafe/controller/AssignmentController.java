package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Assignment.Sm;

import java.util.ArrayList;
import java.util.List;

public class AssignmentController {
	
	static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

	/**
	 * @author Cedric Barre
	 *
	 * This method makes the pairing between trips and guides.
	 *
	 */
	public static void initiateAssignments() {
		List<Member> memberList = climbSafe.getMembers();
		
		for( Guide g : climbSafe.getGuides() ) {
			for( Member m : memberList ) {
				if ( m.getAssignment() == null ) {
					if( m.getGuideRequired() == true && ( climbSafe.getNrWeeks() - g.getBookings() ) >= m.getNrWeeks() ) {
						Assignment a = new Assignment((1 + g.getBookings()), ( g.getBookings() + m.getNrWeeks() ), m, climbSafe);
						a.setGuide( g );
						a.assign();
						g.setBookings( g.getBookings() + m.getNrWeeks() );
					}
					else if( m.getGuideRequired() == false ) {
						Assignment a = new Assignment(1, m.getNrWeeks(), m, climbSafe);
						a.assign();
					}
				}
				if( g.getBookings() == climbSafe.getNrWeeks() ) {
					break;
				}
			}
		}
		
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
	 * This method cancels the Trip for a member
	 * @param memberEmail Member whose trip got cancelled.
	 */
	public static void cancelTrip(String memberEmail) throws InvalidInputException{
		
	}
}
