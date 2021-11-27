/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.climbsafe.model;
import java.io.Serializable;

// line 110 "../../../../../ClimbSafePersistence.ump"
// line 1 "../../../../../ClimbSafeSM.ump"
// line 178 "../../../../../ClimbSafe.ump"
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
  public enum SmAssignedS1 { Null, s1 }
  public enum SmAssignedS1S1 { Null, NotStarted, Started, Finished, Cancelled }
  public enum SmAssignedS2 { Null, s2 }
  public enum SmAssignedS2S2 { Null, NotPaid, Paid }
  private Sm sm;
  private SmAssignedS1 smAssignedS1;
  private SmAssignedS1S1 smAssignedS1S1;
  private SmAssignedS2 smAssignedS2;
  private SmAssignedS2S2 smAssignedS2S2;

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
    setSmAssignedS1(SmAssignedS1.Null);
    setSmAssignedS1S1(SmAssignedS1S1.Null);
    setSmAssignedS2(SmAssignedS2.Null);
    setSmAssignedS2S2(SmAssignedS2S2.Null);
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
    if (smAssignedS1 != SmAssignedS1.Null) { answer += "." + smAssignedS1.toString(); }
    if (smAssignedS1S1 != SmAssignedS1S1.Null) { answer += "." + smAssignedS1S1.toString(); }
    if (smAssignedS2 != SmAssignedS2.Null) { answer += "." + smAssignedS2.toString(); }
    if (smAssignedS2S2 != SmAssignedS2S2.Null) { answer += "." + smAssignedS2S2.toString(); }
    return answer;
  }

  public Sm getSm()
  {
    return sm;
  }

  public SmAssignedS1 getSmAssignedS1()
  {
    return smAssignedS1;
  }

  public SmAssignedS1S1 getSmAssignedS1S1()
  {
    return smAssignedS1S1;
  }

  public SmAssignedS2 getSmAssignedS2()
  {
    return smAssignedS2;
  }

  public SmAssignedS2S2 getSmAssignedS2S2()
  {
    return smAssignedS2S2;
  }

  public boolean start()
  {
    boolean wasEventProcessed = false;
    
    SmAssignedS1S1 aSmAssignedS1S1 = smAssignedS1S1;
    switch (aSmAssignedS1S1)
    {
      case NotStarted:
        if (!(isPaid()))
        {
          exitSmAssignedS1S1();
        // line 8 "../../../../../ClimbSafeSM.ump"
          getMember().ban();
          setSmAssignedS1S1(SmAssignedS1S1.Cancelled);
          wasEventProcessed = true;
          break;
        }
        if (isPaid())
        {
          exitSmAssignedS1S1();
          setSmAssignedS1S1(SmAssignedS1S1.Started);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    SmAssignedS1S1 aSmAssignedS1S1 = smAssignedS1S1;
    switch (aSmAssignedS1S1)
    {
      case NotStarted:
        exitSmAssignedS1S1();
        setSmAssignedS1S1(SmAssignedS1S1.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        exitSmAssignedS1S1();
        setSmAssignedS1S1(SmAssignedS1S1.Cancelled);
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
    
    SmAssignedS1S1 aSmAssignedS1S1 = smAssignedS1S1;
    switch (aSmAssignedS1S1)
    {
      case Started:
        exitSmAssignedS1S1();
        setSmAssignedS1S1(SmAssignedS1S1.Finished);
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
    
    SmAssignedS2S2 aSmAssignedS2S2 = smAssignedS2S2;
    switch (aSmAssignedS2S2)
    {
      case NotPaid:
        exitSmAssignedS2S2();
        setSmAssignedS2S2(SmAssignedS2S2.Paid);
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
        exitSmAssignedS1();
        exitSmAssignedS2();
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
        if (smAssignedS1 == SmAssignedS1.Null) { setSmAssignedS1(SmAssignedS1.s1); }
        if (smAssignedS2 == SmAssignedS2.Null) { setSmAssignedS2(SmAssignedS2.s2); }
        break;
    }
  }

  private void exitSmAssignedS1()
  {
    switch(smAssignedS1)
    {
      case s1:
        exitSmAssignedS1S1();
        setSmAssignedS1(SmAssignedS1.Null);
        break;
    }
  }

  private void setSmAssignedS1(SmAssignedS1 aSmAssignedS1)
  {
    smAssignedS1 = aSmAssignedS1;
    if (sm != Sm.Assigned && aSmAssignedS1 != SmAssignedS1.Null) { setSm(Sm.Assigned); }

    // entry actions and do activities
    switch(smAssignedS1)
    {
      case s1:
        if (smAssignedS1S1 == SmAssignedS1S1.Null) { setSmAssignedS1S1(SmAssignedS1S1.NotStarted); }
        break;
    }
  }

  private void exitSmAssignedS1S1()
  {
    switch(smAssignedS1S1)
    {
      case NotStarted:
        setSmAssignedS1S1(SmAssignedS1S1.Null);
        break;
      case Started:
        setSmAssignedS1S1(SmAssignedS1S1.Null);
        break;
      case Finished:
        setSmAssignedS1S1(SmAssignedS1S1.Null);
        break;
      case Cancelled:
        setSmAssignedS1S1(SmAssignedS1S1.Null);
        break;
    }
  }

  private void setSmAssignedS1S1(SmAssignedS1S1 aSmAssignedS1S1)
  {
    smAssignedS1S1 = aSmAssignedS1S1;
    if (smAssignedS1 != SmAssignedS1.s1 && aSmAssignedS1S1 != SmAssignedS1S1.Null) { setSmAssignedS1(SmAssignedS1.s1); }
  }

  private void exitSmAssignedS2()
  {
    switch(smAssignedS2)
    {
      case s2:
        exitSmAssignedS2S2();
        setSmAssignedS2(SmAssignedS2.Null);
        break;
    }
  }

  private void setSmAssignedS2(SmAssignedS2 aSmAssignedS2)
  {
    smAssignedS2 = aSmAssignedS2;
    if (sm != Sm.Assigned && aSmAssignedS2 != SmAssignedS2.Null) { setSm(Sm.Assigned); }

    // entry actions and do activities
    switch(smAssignedS2)
    {
      case s2:
        if (smAssignedS2S2 == SmAssignedS2S2.Null) { setSmAssignedS2S2(SmAssignedS2S2.NotPaid); }
        break;
    }
  }

  private void exitSmAssignedS2S2()
  {
    switch(smAssignedS2S2)
    {
      case NotPaid:
        setSmAssignedS2S2(SmAssignedS2S2.Null);
        break;
      case Paid:
        setSmAssignedS2S2(SmAssignedS2S2.Null);
        break;
    }
  }

  private void setSmAssignedS2S2(SmAssignedS2S2 aSmAssignedS2S2)
  {
    smAssignedS2S2 = aSmAssignedS2S2;
    if (smAssignedS2 != SmAssignedS2.s2 && aSmAssignedS2S2 != SmAssignedS2S2.Null) { setSmAssignedS2(SmAssignedS2.s2); }
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

  // line 30 "../../../../../ClimbSafeSM.ump"
   private boolean isPaid(){
    if( this.getSmAssignedS2S2() == SmAssignedS2S2.Paid ) return true;
    return false;
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
  
  // line 113 "../../../../../ClimbSafePersistence.ump"
  private static final long serialVersionUID = 11L ;

  
}