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
  public enum Sm { s }
  public enum SmSS1 { Null, s1 }
  public enum SmSS1S1 { Null, Assigned, Started, Finished, Cancelled }
  public enum SmSS2 { Null, s2 }
  public enum SmSS2S2 { Null, NotPaid, Paid }
  private Sm sm;
  private SmSS1 smSS1;
  private SmSS1S1 smSS1S1;
  private SmSS2 smSS2;
  private SmSS2S2 smSS2S2;

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
    setSmSS1(SmSS1.Null);
    setSmSS1S1(SmSS1S1.Null);
    setSmSS2(SmSS2.Null);
    setSmSS2S2(SmSS2S2.Null);
    setSm(Sm.s);
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
    if (smSS1 != SmSS1.Null) { answer += "." + smSS1.toString(); }
    if (smSS1S1 != SmSS1S1.Null) { answer += "." + smSS1S1.toString(); }
    if (smSS2 != SmSS2.Null) { answer += "." + smSS2.toString(); }
    if (smSS2S2 != SmSS2S2.Null) { answer += "." + smSS2S2.toString(); }
    return answer;
  }

  public Sm getSm()
  {
    return sm;
  }

  public SmSS1 getSmSS1()
  {
    return smSS1;
  }

  public SmSS1S1 getSmSS1S1()
  {
    return smSS1S1;
  }

  public SmSS2 getSmSS2()
  {
    return smSS2;
  }

  public SmSS2S2 getSmSS2S2()
  {
    return smSS2S2;
  }

  public boolean start()
  {
    boolean wasEventProcessed = false;
    
    SmSS1S1 aSmSS1S1 = smSS1S1;
    switch (aSmSS1S1)
    {
      case Assigned:
        if (!(isPaid()))
        {
          exitSmSS1S1();
        // line 8 "../../../../../ClimbSafeSM.ump"
          getMember().ban();
          setSmSS1S1(SmSS1S1.Cancelled);
          wasEventProcessed = true;
          break;
        }
        if (isPaid())
        {
          exitSmSS1S1();
          setSmSS1S1(SmSS1S1.Started);
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
    
    SmSS1S1 aSmSS1S1 = smSS1S1;
    switch (aSmSS1S1)
    {
      case Assigned:
        exitSmSS1S1();
        setSmSS1S1(SmSS1S1.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        exitSmSS1S1();
        setSmSS1S1(SmSS1S1.Cancelled);
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
    
    SmSS1S1 aSmSS1S1 = smSS1S1;
    switch (aSmSS1S1)
    {
      case Started:
        exitSmSS1S1();
        setSmSS1S1(SmSS1S1.Finished);
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
    
    SmSS2S2 aSmSS2S2 = smSS2S2;
    switch (aSmSS2S2)
    {
      case NotPaid:
        exitSmSS2S2();
        setSmSS2S2(SmSS2S2.Paid);
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
      case s:
        exitSmSS1();
        exitSmSS2();
        break;
    }
  }

  private void setSm(Sm aSm)
  {
    sm = aSm;

    // entry actions and do activities
    switch(sm)
    {
      case s:
        if (smSS1 == SmSS1.Null) { setSmSS1(SmSS1.s1); }
        if (smSS2 == SmSS2.Null) { setSmSS2(SmSS2.s2); }
        break;
    }
  }

  private void exitSmSS1()
  {
    switch(smSS1)
    {
      case s1:
        exitSmSS1S1();
        setSmSS1(SmSS1.Null);
        break;
    }
  }

  private void setSmSS1(SmSS1 aSmSS1)
  {
    smSS1 = aSmSS1;
    if (sm != Sm.s && aSmSS1 != SmSS1.Null) { setSm(Sm.s); }

    // entry actions and do activities
    switch(smSS1)
    {
      case s1:
        if (smSS1S1 == SmSS1S1.Null) { setSmSS1S1(SmSS1S1.Assigned); }
        break;
    }
  }

  private void exitSmSS1S1()
  {
    switch(smSS1S1)
    {
      case Assigned:
        setSmSS1S1(SmSS1S1.Null);
        break;
      case Started:
        setSmSS1S1(SmSS1S1.Null);
        break;
      case Finished:
        setSmSS1S1(SmSS1S1.Null);
        break;
      case Cancelled:
        setSmSS1S1(SmSS1S1.Null);
        break;
    }
  }

  private void setSmSS1S1(SmSS1S1 aSmSS1S1)
  {
    smSS1S1 = aSmSS1S1;
    if (smSS1 != SmSS1.s1 && aSmSS1S1 != SmSS1S1.Null) { setSmSS1(SmSS1.s1); }
  }

  private void exitSmSS2()
  {
    switch(smSS2)
    {
      case s2:
        exitSmSS2S2();
        setSmSS2(SmSS2.Null);
        break;
    }
  }

  private void setSmSS2(SmSS2 aSmSS2)
  {
    smSS2 = aSmSS2;
    if (sm != Sm.s && aSmSS2 != SmSS2.Null) { setSm(Sm.s); }

    // entry actions and do activities
    switch(smSS2)
    {
      case s2:
        if (smSS2S2 == SmSS2S2.Null) { setSmSS2S2(SmSS2S2.NotPaid); }
        break;
    }
  }

  private void exitSmSS2S2()
  {
    switch(smSS2S2)
    {
      case NotPaid:
        setSmSS2S2(SmSS2S2.Null);
        break;
      case Paid:
        setSmSS2S2(SmSS2S2.Null);
        break;
    }
  }

  private void setSmSS2S2(SmSS2S2 aSmSS2S2)
  {
    smSS2S2 = aSmSS2S2;
    if (smSS2 != SmSS2.s2 && aSmSS2S2 != SmSS2S2.Null) { setSmSS2(SmSS2.s2); }
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
    if( this.getSmSS2S2() == SmSS2S2.Paid ) return true;
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