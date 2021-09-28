/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 69 "../../../../../../model.ump"
// line 139 "../../../../../../model.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private int startWeek;
  private int duration;
  private int price;

  //Assignment Associations
  private Hotel hotel;
  private Visitor member;
  private Visitor guide;
  private ClimbingSeason climbingSeason;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aDuration, int aPrice, Visitor aMember, ClimbingSeason aClimbingSeason)
  {
    startWeek = aStartWeek;
    duration = aDuration;
    price = aPrice;
    if (aMember == null || aMember.getMemberAssignment() != null)
    {
      throw new RuntimeException("Unable to create Assignment due to aMember. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    member = aMember;
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create assignment due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Assignment(int aStartWeek, int aDuration, int aPrice, String aUsernameForMember, String aNameForMember, String aPasswordForMember, int aEmergencyContactForMember, ClimbingSeason aMemberSeasonForMember, ClimbingSeason aGuideSseasonForMember, ClimbingSeason aClimbingSeason)
  {
    startWeek = aStartWeek;
    duration = aDuration;
    price = aPrice;
    member = new Visitor(aUsernameForMember, aNameForMember, aPasswordForMember, aEmergencyContactForMember, aMemberSeasonForMember, aGuideSseasonForMember, this);
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create assignment due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getDuration()
  {
    return duration;
  }

  public int getPrice()
  {
    return price;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Visitor getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Visitor getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setHotel(Hotel aNewHotel)
  {
    boolean wasSet = false;
    if (hotel != null && !hotel.equals(aNewHotel) && equals(hotel.getAssignment()))
    {
      //Unable to setHotel, as existing hotel would become an orphan
      return wasSet;
    }

    hotel = aNewHotel;
    Assignment anOldAssignment = aNewHotel != null ? aNewHotel.getAssignment() : null;

    if (!this.equals(anOldAssignment))
    {
      if (anOldAssignment != null)
      {
        anOldAssignment.hotel = null;
      }
      if (hotel != null)
      {
        hotel.setAssignment(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setGuide(Visitor aNewGuide)
  {
    boolean wasSet = false;
    if (aNewGuide == null)
    {
      Visitor existingGuide = guide;
      guide = null;
      
      if (existingGuide != null && existingGuide.getGuideAssignment() != null)
      {
        existingGuide.setGuideAssignment(null);
      }
      wasSet = true;
      return wasSet;
    }

    Visitor currentGuide = getGuide();
    if (currentGuide != null && !currentGuide.equals(aNewGuide))
    {
      currentGuide.setGuideAssignment(null);
    }

    guide = aNewGuide;
    Assignment existingGuideAssignment = aNewGuide.getGuideAssignment();

    if (!equals(existingGuideAssignment))
    {
      aNewGuide.setGuideAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbingSeason(ClimbingSeason aClimbingSeason)
  {
    boolean wasSet = false;
    if (aClimbingSeason == null)
    {
      return wasSet;
    }

    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = aClimbingSeason;
    if (existingClimbingSeason != null && !existingClimbingSeason.equals(aClimbingSeason))
    {
      existingClimbingSeason.removeAssignment(this);
    }
    climbingSeason.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Hotel existingHotel = hotel;
    hotel = null;
    if (existingHotel != null)
    {
      existingHotel.delete();
    }
    Visitor existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.delete();
    }
    if (guide != null)
    {
      guide.setGuideAssignment(null);
    }
    ClimbingSeason placeholderClimbingSeason = climbingSeason;
    this.climbingSeason = null;
    if(placeholderClimbingSeason != null)
    {
      placeholderClimbingSeason.removeAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startWeek" + ":" + getStartWeek()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null");
  }
}