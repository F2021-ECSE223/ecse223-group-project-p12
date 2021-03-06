package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.application.*;

public class ClimbSafeFeatureSet1Controller {

  /**
   * Method used to setup the climbsafe system instance with the specified data
   * 
   * @author Cédric Barré
   * @param startDate Date of the start of the climbing season
   * @param nrWeeks Number of weeks for which the climbing season will last
   * @param priceOfGuidePerWeek Price of the guides per weeks
   * @throws InvalidInputException -> nrWeeks < 0 -> priceOfGuidePerWeek < 0
   */
  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
    // No need to check date validity since creating a Date object already makes sure the date is
    // valid
    if (nrWeeks < 0)
      throw new InvalidInputException(
          "The number of climbing weeks must be greater than or equal to zero");
    if (priceOfGuidePerWeek < 0)
      throw new InvalidInputException(
          "The price of guide per week must be greater than or equal to zero");
    ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
    climbSafe.setStartDate(startDate);
    climbSafe.setNrWeeks(nrWeeks);
    climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
    ClimbSafePersistence.save();
  }

  /**
   * Method used to delete a specified member of the climbsafe system instance. The member to be
   * deleted is specified with an email address
   * 
   * @author Cédric Barré
   * @param email Email address of the member to be deleted
   */
  public static void deleteMember(String email) {
    Member member = ClimbSafeApplication.getClimbSafe().findMemberFromEmail(email);
    if (member != null) {
      member.delete();
      ClimbSafePersistence.save();
    }
  }

  /**
   * Method used to delete a specified guide of the climbsafe system instance. The guide to be
   * deleted is specified with an email address
   * 
   * @author Cédric Barré
   * @param email Email address of the guide to be deleted
   */
  public static void deleteGuide(String email) {
    Guide guide = ClimbSafeApplication.getClimbSafe().findGuideFromEmail(email);
    if (guide != null) {
      guide.delete();
      ClimbSafePersistence.save();
    }
  }

  /**
   * Method used to delete a specified hotel of the climbsafe system instance. The hotel to be
   * deleted is specified with a name
   * 
   * @author Cédric Barré
   * @param name Name of the hotel to be deleted
   */
  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {
    Hotel hotel = ClimbSafeApplication.getClimbSafe().findHotelFromName(name);
    if (hotel != null) {
      hotel.delete();
      ClimbSafePersistence.save();
    }
  }

}
