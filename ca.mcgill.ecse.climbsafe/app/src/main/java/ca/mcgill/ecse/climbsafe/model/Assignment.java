/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 72 "../../../../../ClimbSafePersistence.ump"
// line 1 "../../../../../ClimbSafeSM.ump"
// line 176 "../../../../../ClimbSafe.ump"
public class Assignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String paymentCode;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum Sm { Assigned }
  public enum SmAssigned { Null, NotStarted, Paid, Started, Finished, Cancelled }
  private Sm sm;
  private SmAssigned smAssigned;

  //Assignment Associations
  private Member member;
  private Guide guide;
  private Hotel hotel;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(int aStartWeek, int aEndWeek, Member aMember, ClimbSafe aClimbSafe)
  {
    paymentCode = null;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create assignment due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create assignment due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setSmAssigned(SmAssigned.Null);
    setSm(Sm.Assigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPaymentCode(String aPaymentCode)
  {
    boolean wasSet = false;
    paymentCode = aPaymentCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public String getPaymentCode()
  {
    return paymentCode;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }

  public String getSmFullName()
  {
    String answer = sm.toString();
    if (smAssigned != SmAssigned.Null) { answer += "." + smAssigned.toString(); }
    return answer;
  }

  public Sm getSm()
  {
    return sm;
  }

  public SmAssigned getSmAssigned()
  {
    return smAssigned;
  }

  public boolean pay()
  {
    boolean wasEventProcessed = false;
    
    SmAssigned aSmAssigned = smAssigned;
    switch (aSmAssigned)
    {
      case NotStarted:
        exitSmAssigned();
        setSmAssigned(SmAssigned.Paid);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean start()
  {
    boolean wasEventProcessed = false;
    
    SmAssigned aSmAssigned = smAssigned;
    switch (aSmAssigned)
    {
      case NotStarted:
        exitSmAssigned();
        // line 8 "../../../../../ClimbSafeSM.ump"
        getMember().ban();
        setSmAssigned(SmAssigned.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        exitSmAssigned();
        setSmAssigned(SmAssigned.Started);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    SmAssigned aSmAssigned = smAssigned;
    switch (aSmAssigned)
    {
      case NotStarted:
        exitSmAssigned();
        setSmAssigned(SmAssigned.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        exitSmAssigned();
        setSmAssigned(SmAssigned.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        exitSmAssigned();
        setSmAssigned(SmAssigned.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finish()
  {
    boolean wasEventProcessed = false;
    
    SmAssigned aSmAssigned = smAssigned;
    switch (aSmAssigned)
    {
      case Started:
        exitSmAssigned();
        setSmAssigned(SmAssigned.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitSm()
  {
    switch(sm)
    {
      case Assigned:
        exitSmAssigned();
        break;
    }
  }

  private void setSm(Sm aSm)
  {
    sm = aSm;

    // entry actions and do activities
    switch(sm)
    {
      case Assigned:
        if (smAssigned == SmAssigned.Null) { setSmAssigned(SmAssigned.NotStarted); }
        break;
    }
  }

  private void exitSmAssigned()
  {
    switch(smAssigned)
    {
      case NotStarted:
        setSmAssigned(SmAssigned.Null);
        break;
      case Paid:
        setSmAssigned(SmAssigned.Null);
        break;
      case Started:
        setSmAssigned(SmAssigned.Null);
        break;
      case Finished:
        setSmAssigned(SmAssigned.Null);
        break;
      case Cancelled:
        setSmAssigned(SmAssigned.Null);
        break;
    }
  }

  private void setSmAssigned(SmAssigned aSmAssigned)
  {
    smAssigned = aSmAssigned;
    if (sm != Sm.Assigned && aSmAssigned != SmAssigned.Null) { setSm(Sm.Assigned); }
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }

  public boolean hasGuide()
  {
    boolean has = guide != null;
    return has;
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
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as assignment must always be associated to a member
      return wasSet;
    }
    
    Assignment existingAssignment = aNewMember.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setMember, the current member already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setAssignment(this);

    if (anOldMember != null)
    {
      anOldMember.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeAssignment(this);
    }
    if (aGuide != null)
    {
      aGuide.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setHotel(Hotel aHotel)
  {
    boolean wasSet = false;
    Hotel existingHotel = hotel;
    hotel = aHotel;
    if (existingHotel != null && !existingHotel.equals(aHotel))
    {
      existingHotel.removeAssignment(this);
    }
    if (aHotel != null)
    {
      aHotel.addAssignment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbSafe(ClimbSafe aClimbSafe)
  {
    boolean wasSet = false;
    if (aClimbSafe == null)
    {
      return wasSet;
    }

    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = aClimbSafe;
    if (existingClimbSafe != null && !existingClimbSafe.equals(aClimbSafe))
    {
      existingClimbSafe.removeAssignment(this);
    }
    climbSafe.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setAssignment(null);
    }
    if (guide != null)
    {
      Guide placeholderGuide = guide;
      this.guide = null;
      placeholderGuide.removeAssignment(this);
    }
    if (hotel != null)
    {
      Hotel placeholderHotel = hotel;
      this.hotel = null;
      placeholderHotel.removeAssignment(this);
    }
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "paymentCode" + ":" + getPaymentCode()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 75 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 8896099581655989380L ;

  
}