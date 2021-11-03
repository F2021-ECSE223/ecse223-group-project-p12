/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;

// line 1 "../../../../../../ClimbSafeSM.ump"
// line 60 "../../../../../../ClimbSafeSM.ump"
// line 168 "../../../../../../model.ump"
// line 239 "../../../../../../model.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String paymentCode;
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum Sm { Unassigned, Assigned, Cancelled }
  public enum SmAssignedPayment { Null, Payment }
  public enum SmAssignedPaymentPayment { Null, Unpaid, Paid }
  public enum SmAssignedStatus { Null, Status }
  public enum SmAssignedStatusStatus { Null, NotStarted, Started, Finished }
  private Sm sm;
  private SmAssignedPayment smAssignedPayment;
  private SmAssignedPaymentPayment smAssignedPaymentPayment;
  private SmAssignedStatus smAssignedStatus;
  private SmAssignedStatusStatus smAssignedStatusStatus;

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
    setSmAssignedPayment(SmAssignedPayment.Null);
    setSmAssignedPaymentPayment(SmAssignedPaymentPayment.Null);
    setSmAssignedStatus(SmAssignedStatus.Null);
    setSmAssignedStatusStatus(SmAssignedStatusStatus.Null);
    setSm(Sm.Unassigned);
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
    if (smAssignedPayment != SmAssignedPayment.Null) { answer += "." + smAssignedPayment.toString(); }
    if (smAssignedPaymentPayment != SmAssignedPaymentPayment.Null) { answer += "." + smAssignedPaymentPayment.toString(); }
    if (smAssignedStatus != SmAssignedStatus.Null) { answer += "." + smAssignedStatus.toString(); }
    if (smAssignedStatusStatus != SmAssignedStatusStatus.Null) { answer += "." + smAssignedStatusStatus.toString(); }
    return answer;
  }

  public Sm getSm()
  {
    return sm;
  }

  public SmAssignedPayment getSmAssignedPayment()
  {
    return smAssignedPayment;
  }

  public SmAssignedPaymentPayment getSmAssignedPaymentPayment()
  {
    return smAssignedPaymentPayment;
  }

  public SmAssignedStatus getSmAssignedStatus()
  {
    return smAssignedStatus;
  }

  public SmAssignedStatusStatus getSmAssignedStatusStatus()
  {
    return smAssignedStatusStatus;
  }

  public boolean assign()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case Unassigned:
        setSm(Sm.Assigned);
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
    
    Sm aSm = sm;
    switch (aSm)
    {
      case Unassigned:
        setSm(Sm.Cancelled);
        wasEventProcessed = true;
        break;
      case Assigned:
        exitSm();
        setSm(Sm.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pay()
  {
    boolean wasEventProcessed = false;
    
    SmAssignedPaymentPayment aSmAssignedPaymentPayment = smAssignedPaymentPayment;
    switch (aSmAssignedPaymentPayment)
    {
      case Unpaid:
        exitSmAssignedPaymentPayment();
        setSmAssignedPaymentPayment(SmAssignedPaymentPayment.Paid);
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
    
    SmAssignedStatusStatus aSmAssignedStatusStatus = smAssignedStatusStatus;
    switch (aSmAssignedStatusStatus)
    {
      case NotStarted:
        exitSmAssignedStatusStatus();
        setSmAssignedStatusStatus(SmAssignedStatusStatus.Started);
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
    
    SmAssignedStatusStatus aSmAssignedStatusStatus = smAssignedStatusStatus;
    switch (aSmAssignedStatusStatus)
    {
      case Started:
        exitSmAssignedStatusStatus();
        setSmAssignedStatusStatus(SmAssignedStatusStatus.Finished);
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
        exitSmAssignedPayment();
        exitSmAssignedStatus();
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
        if (smAssignedPayment == SmAssignedPayment.Null) { setSmAssignedPayment(SmAssignedPayment.Payment); }
        if (smAssignedStatus == SmAssignedStatus.Null) { setSmAssignedStatus(SmAssignedStatus.Status); }
        break;
    }
  }

  private void exitSmAssignedPayment()
  {
    switch(smAssignedPayment)
    {
      case Payment:
        exitSmAssignedPaymentPayment();
        setSmAssignedPayment(SmAssignedPayment.Null);
        break;
    }
  }

  private void setSmAssignedPayment(SmAssignedPayment aSmAssignedPayment)
  {
    smAssignedPayment = aSmAssignedPayment;
    if (sm != Sm.Assigned && aSmAssignedPayment != SmAssignedPayment.Null) { setSm(Sm.Assigned); }

    // entry actions and do activities
    switch(smAssignedPayment)
    {
      case Payment:
        if (smAssignedPaymentPayment == SmAssignedPaymentPayment.Null) { setSmAssignedPaymentPayment(SmAssignedPaymentPayment.Unpaid); }
        break;
    }
  }

  private void exitSmAssignedPaymentPayment()
  {
    switch(smAssignedPaymentPayment)
    {
      case Unpaid:
        setSmAssignedPaymentPayment(SmAssignedPaymentPayment.Null);
        break;
      case Paid:
        setSmAssignedPaymentPayment(SmAssignedPaymentPayment.Null);
        break;
    }
  }

  private void setSmAssignedPaymentPayment(SmAssignedPaymentPayment aSmAssignedPaymentPayment)
  {
    smAssignedPaymentPayment = aSmAssignedPaymentPayment;
    if (smAssignedPayment != SmAssignedPayment.Payment && aSmAssignedPaymentPayment != SmAssignedPaymentPayment.Null) { setSmAssignedPayment(SmAssignedPayment.Payment); }
  }

  private void exitSmAssignedStatus()
  {
    switch(smAssignedStatus)
    {
      case Status:
        exitSmAssignedStatusStatus();
        setSmAssignedStatus(SmAssignedStatus.Null);
        break;
    }
  }

  private void setSmAssignedStatus(SmAssignedStatus aSmAssignedStatus)
  {
    smAssignedStatus = aSmAssignedStatus;
    if (sm != Sm.Assigned && aSmAssignedStatus != SmAssignedStatus.Null) { setSm(Sm.Assigned); }

    // entry actions and do activities
    switch(smAssignedStatus)
    {
      case Status:
        if (smAssignedStatusStatus == SmAssignedStatusStatus.Null) { setSmAssignedStatusStatus(SmAssignedStatusStatus.NotStarted); }
        break;
    }
  }

  private void exitSmAssignedStatusStatus()
  {
    switch(smAssignedStatusStatus)
    {
      case NotStarted:
        setSmAssignedStatusStatus(SmAssignedStatusStatus.Null);
        break;
      case Started:
        setSmAssignedStatusStatus(SmAssignedStatusStatus.Null);
        break;
      case Finished:
        setSmAssignedStatusStatus(SmAssignedStatusStatus.Null);
        break;
    }
  }

  private void setSmAssignedStatusStatus(SmAssignedStatusStatus aSmAssignedStatusStatus)
  {
    smAssignedStatusStatus = aSmAssignedStatusStatus;
    if (smAssignedStatus != SmAssignedStatus.Status && aSmAssignedStatusStatus != SmAssignedStatusStatus.Null) { setSmAssignedStatus(SmAssignedStatus.Status); }
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
}