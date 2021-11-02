/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;

// line 1 "../../../../../../ClimbSafeSM.ump"
// line 58 "../../../../../../ClimbSafeSM.ump"
// line 168 "../../../../../../model.ump"
// line 241 "../../../../../../model.ump"
public class Assignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private int startWeek;
  private int endWeek;

  //Assignment State Machines
  public enum Sm { Active, Cancelled }
  public enum SmActivePayment { Null, payment }
  public enum SmActivePaymentPayment { Null, unpaid, paid }
  public enum SmActiveGuideAssignment { Null, GuideAssignment }
  public enum SmActiveGuideAssignmentGuideAssignment { Null, Unassigned, Assigned }
  public enum SmActiveStatus { Null, status }
  public enum SmActiveStatusStatus { Null, NotStarted, Started, Finished }
  private Sm sm;
  private SmActivePayment smActivePayment;
  private SmActivePaymentPayment smActivePaymentPayment;
  private SmActiveGuideAssignment smActiveGuideAssignment;
  private SmActiveGuideAssignmentGuideAssignment smActiveGuideAssignmentGuideAssignment;
  private SmActiveStatus smActiveStatus;
  private SmActiveStatusStatus smActiveStatusStatus;

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
    setSmActivePayment(SmActivePayment.Null);
    setSmActivePaymentPayment(SmActivePaymentPayment.Null);
    setSmActiveGuideAssignment(SmActiveGuideAssignment.Null);
    setSmActiveGuideAssignmentGuideAssignment(SmActiveGuideAssignmentGuideAssignment.Null);
    setSmActiveStatus(SmActiveStatus.Null);
    setSmActiveStatusStatus(SmActiveStatusStatus.Null);
    setSm(Sm.Active);
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

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
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
    if (smActivePayment != SmActivePayment.Null) { answer += "." + smActivePayment.toString(); }
    if (smActivePaymentPayment != SmActivePaymentPayment.Null) { answer += "." + smActivePaymentPayment.toString(); }
    if (smActiveGuideAssignment != SmActiveGuideAssignment.Null) { answer += "." + smActiveGuideAssignment.toString(); }
    if (smActiveGuideAssignmentGuideAssignment != SmActiveGuideAssignmentGuideAssignment.Null) { answer += "." + smActiveGuideAssignmentGuideAssignment.toString(); }
    if (smActiveStatus != SmActiveStatus.Null) { answer += "." + smActiveStatus.toString(); }
    if (smActiveStatusStatus != SmActiveStatusStatus.Null) { answer += "." + smActiveStatusStatus.toString(); }
    return answer;
  }

  public Sm getSm()
  {
    return sm;
  }

  public SmActivePayment getSmActivePayment()
  {
    return smActivePayment;
  }

  public SmActivePaymentPayment getSmActivePaymentPayment()
  {
    return smActivePaymentPayment;
  }

  public SmActiveGuideAssignment getSmActiveGuideAssignment()
  {
    return smActiveGuideAssignment;
  }

  public SmActiveGuideAssignmentGuideAssignment getSmActiveGuideAssignmentGuideAssignment()
  {
    return smActiveGuideAssignmentGuideAssignment;
  }

  public SmActiveStatus getSmActiveStatus()
  {
    return smActiveStatus;
  }

  public SmActiveStatusStatus getSmActiveStatusStatus()
  {
    return smActiveStatusStatus;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    Sm aSm = sm;
    switch (aSm)
    {
      case Active:
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
    
    SmActivePaymentPayment aSmActivePaymentPayment = smActivePaymentPayment;
    switch (aSmActivePaymentPayment)
    {
      case unpaid:
        exitSmActivePaymentPayment();
        setSmActivePaymentPayment(SmActivePaymentPayment.paid);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean assign()
  {
    boolean wasEventProcessed = false;
    
    SmActiveGuideAssignmentGuideAssignment aSmActiveGuideAssignmentGuideAssignment = smActiveGuideAssignmentGuideAssignment;
    switch (aSmActiveGuideAssignmentGuideAssignment)
    {
      case Unassigned:
        exitSmActiveGuideAssignmentGuideAssignment();
        setSmActiveGuideAssignmentGuideAssignment(SmActiveGuideAssignmentGuideAssignment.Assigned);
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
    
    SmActiveStatusStatus aSmActiveStatusStatus = smActiveStatusStatus;
    switch (aSmActiveStatusStatus)
    {
      case NotStarted:
        exitSmActiveStatusStatus();
        setSmActiveStatusStatus(SmActiveStatusStatus.Started);
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
    
    SmActiveStatusStatus aSmActiveStatusStatus = smActiveStatusStatus;
    switch (aSmActiveStatusStatus)
    {
      case Started:
        exitSmActiveStatusStatus();
        setSmActiveStatusStatus(SmActiveStatusStatus.Finished);
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
      case Active:
        exitSmActivePayment();
        exitSmActiveGuideAssignment();
        exitSmActiveStatus();
        break;
    }
  }

  private void setSm(Sm aSm)
  {
    sm = aSm;

    // entry actions and do activities
    switch(sm)
    {
      case Active:
        if (smActivePayment == SmActivePayment.Null) { setSmActivePayment(SmActivePayment.payment); }
        if (smActiveGuideAssignment == SmActiveGuideAssignment.Null) { setSmActiveGuideAssignment(SmActiveGuideAssignment.GuideAssignment); }
        if (smActiveStatus == SmActiveStatus.Null) { setSmActiveStatus(SmActiveStatus.status); }
        break;
    }
  }

  private void exitSmActivePayment()
  {
    switch(smActivePayment)
    {
      case payment:
        exitSmActivePaymentPayment();
        setSmActivePayment(SmActivePayment.Null);
        break;
    }
  }

  private void setSmActivePayment(SmActivePayment aSmActivePayment)
  {
    smActivePayment = aSmActivePayment;
    if (sm != Sm.Active && aSmActivePayment != SmActivePayment.Null) { setSm(Sm.Active); }

    // entry actions and do activities
    switch(smActivePayment)
    {
      case payment:
        if (smActivePaymentPayment == SmActivePaymentPayment.Null) { setSmActivePaymentPayment(SmActivePaymentPayment.unpaid); }
        break;
    }
  }

  private void exitSmActivePaymentPayment()
  {
    switch(smActivePaymentPayment)
    {
      case unpaid:
        setSmActivePaymentPayment(SmActivePaymentPayment.Null);
        break;
      case paid:
        setSmActivePaymentPayment(SmActivePaymentPayment.Null);
        break;
    }
  }

  private void setSmActivePaymentPayment(SmActivePaymentPayment aSmActivePaymentPayment)
  {
    smActivePaymentPayment = aSmActivePaymentPayment;
    if (smActivePayment != SmActivePayment.payment && aSmActivePaymentPayment != SmActivePaymentPayment.Null) { setSmActivePayment(SmActivePayment.payment); }
  }

  private void exitSmActiveGuideAssignment()
  {
    switch(smActiveGuideAssignment)
    {
      case GuideAssignment:
        exitSmActiveGuideAssignmentGuideAssignment();
        setSmActiveGuideAssignment(SmActiveGuideAssignment.Null);
        break;
    }
  }

  private void setSmActiveGuideAssignment(SmActiveGuideAssignment aSmActiveGuideAssignment)
  {
    smActiveGuideAssignment = aSmActiveGuideAssignment;
    if (sm != Sm.Active && aSmActiveGuideAssignment != SmActiveGuideAssignment.Null) { setSm(Sm.Active); }

    // entry actions and do activities
    switch(smActiveGuideAssignment)
    {
      case GuideAssignment:
        if (smActiveGuideAssignmentGuideAssignment == SmActiveGuideAssignmentGuideAssignment.Null) { setSmActiveGuideAssignmentGuideAssignment(SmActiveGuideAssignmentGuideAssignment.Unassigned); }
        break;
    }
  }

  private void exitSmActiveGuideAssignmentGuideAssignment()
  {
    switch(smActiveGuideAssignmentGuideAssignment)
    {
      case Unassigned:
        setSmActiveGuideAssignmentGuideAssignment(SmActiveGuideAssignmentGuideAssignment.Null);
        break;
      case Assigned:
        setSmActiveGuideAssignmentGuideAssignment(SmActiveGuideAssignmentGuideAssignment.Null);
        break;
    }
  }

  private void setSmActiveGuideAssignmentGuideAssignment(SmActiveGuideAssignmentGuideAssignment aSmActiveGuideAssignmentGuideAssignment)
  {
    smActiveGuideAssignmentGuideAssignment = aSmActiveGuideAssignmentGuideAssignment;
    if (smActiveGuideAssignment != SmActiveGuideAssignment.GuideAssignment && aSmActiveGuideAssignmentGuideAssignment != SmActiveGuideAssignmentGuideAssignment.Null) { setSmActiveGuideAssignment(SmActiveGuideAssignment.GuideAssignment); }
  }

  private void exitSmActiveStatus()
  {
    switch(smActiveStatus)
    {
      case status:
        exitSmActiveStatusStatus();
        setSmActiveStatus(SmActiveStatus.Null);
        break;
    }
  }

  private void setSmActiveStatus(SmActiveStatus aSmActiveStatus)
  {
    smActiveStatus = aSmActiveStatus;
    if (sm != Sm.Active && aSmActiveStatus != SmActiveStatus.Null) { setSm(Sm.Active); }

    // entry actions and do activities
    switch(smActiveStatus)
    {
      case status:
        if (smActiveStatusStatus == SmActiveStatusStatus.Null) { setSmActiveStatusStatus(SmActiveStatusStatus.NotStarted); }
        break;
    }
  }

  private void exitSmActiveStatusStatus()
  {
    switch(smActiveStatusStatus)
    {
      case NotStarted:
        setSmActiveStatusStatus(SmActiveStatusStatus.Null);
        break;
      case Started:
        setSmActiveStatusStatus(SmActiveStatusStatus.Null);
        break;
      case Finished:
        setSmActiveStatusStatus(SmActiveStatusStatus.Null);
        break;
    }
  }

  private void setSmActiveStatusStatus(SmActiveStatusStatus aSmActiveStatusStatus)
  {
    smActiveStatusStatus = aSmActiveStatusStatus;
    if (smActiveStatus != SmActiveStatus.status && aSmActiveStatusStatus != SmActiveStatusStatus.Null) { setSmActiveStatus(SmActiveStatus.status); }
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
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }
}