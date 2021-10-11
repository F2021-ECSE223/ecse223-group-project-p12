package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.application.*;

public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
	  if( nrWeeks < 0 ) throw new InvalidInputException("Number of climbing weeks must be bigger than 0");
	  if( priceOfGuidePerWeek < 0 ) throw new InvalidInputException("Price of guide per week must be bigger than 0");
	  ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	  climbSafe.setStartDate(startDate);
	  climbSafe.setNrWeeks(nrWeeks);
	  climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  }

  public static void deleteMember(String email) {
	  Member member = ClimbSafeApplication.getClimbSafe().findMemberFromEmail(email);
	  if( member != null ) {
		  member.delete();
	  }
  }

  public static void deleteGuide(String email) {
	  Guide guide = ClimbSafeApplication.getClimbSafe().findGuideFromEmail(email);
	  if( guide != null) {
		  guide.delete();
	  }
  }

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {
	  Hotel hotel = ClimbSafeApplication.getClimbSafe().findHotelFromName(name);
	  if( hotel != null ) {
		  hotel.delete();
	  }
  }

}
