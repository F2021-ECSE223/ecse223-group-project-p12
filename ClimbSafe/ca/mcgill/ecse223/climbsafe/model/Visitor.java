/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 41 "../../../../../../model.ump"
// line 151 "../../../../../../model.ump"
// line 174 "../../../../../../model.ump"
// line 180 "../../../../../../model.ump"
public class Visitor
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Visitor> visitorsByUsername = new HashMap<String, Visitor>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Visitor Attributes
  private String username;
  private String name;
  private String password;
  private int emergencyContact;

  //Visitor Associations
  private MemberRequest memberRequest;
  private ClimbingSeason memberSeason;
  private ClimbingSeason guideSseason;
  private Assignment memberAssignment;
  private Assignment guideAssignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Visitor(String aUsername, String aName, String aPassword, int aEmergencyContact, ClimbingSeason aMemberSeason, ClimbingSeason aGuideSseason, Assignment aMemberAssignment)
  {
    name = aName;
    password = aPassword;
    emergencyContact = aEmergencyContact;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddMemberSeason = setMemberSeason(aMemberSeason);
    if (!didAddMemberSeason)
    {
      throw new RuntimeException("Unable to create member due to memberSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGuideSseason = setGuideSseason(aGuideSseason);
    if (!didAddGuideSseason)
    {
      throw new RuntimeException("Unable to create guide due to guideSseason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aMemberAssignment == null || aMemberAssignment.getMember() != null)
    {
      throw new RuntimeException("Unable to create Visitor due to aMemberAssignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    memberAssignment = aMemberAssignment;
  }

  public Visitor(String aUsername, String aName, String aPassword, int aEmergencyContact, ClimbingSeason aMemberSeason, ClimbingSeason aGuideSseason, int aStartWeekForMemberAssignment, int aDurationForMemberAssignment, int aPriceForMemberAssignment, ClimbingSeason aClimbingSeasonForMemberAssignment)
  {
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    name = aName;
    password = aPassword;
    emergencyContact = aEmergencyContact;
    boolean didAddMemberSeason = setMemberSeason(aMemberSeason);
    if (!didAddMemberSeason)
    {
      throw new RuntimeException("Unable to create member due to memberSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGuideSseason = setGuideSseason(aGuideSseason);
    if (!didAddGuideSseason)
    {
      throw new RuntimeException("Unable to create guide due to guideSseason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    memberAssignment = new Assignment(aStartWeekForMemberAssignment, aDurationForMemberAssignment, aPriceForMemberAssignment, this, aClimbingSeasonForMemberAssignment);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContact(int aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static Visitor getWithUsername(String aUsername)
  {
    return visitorsByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public int getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetOne */
  public MemberRequest getMemberRequest()
  {
    return memberRequest;
  }

  public boolean hasMemberRequest()
  {
    boolean has = memberRequest != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getMemberSeason()
  {
    return memberSeason;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getGuideSseason()
  {
    return guideSseason;
  }
  /* Code from template association_GetOne */
  public Assignment getMemberAssignment()
  {
    return memberAssignment;
  }
  /* Code from template association_GetOne */
  public Assignment getGuideAssignment()
  {
    return guideAssignment;
  }

  public boolean hasGuideAssignment()
  {
    boolean has = guideAssignment != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setMemberRequest(MemberRequest aNewMemberRequest)
  {
    boolean wasSet = false;
    if (memberRequest != null && !memberRequest.equals(aNewMemberRequest) && equals(memberRequest.getVisitor()))
    {
      //Unable to setMemberRequest, as existing memberRequest would become an orphan
      return wasSet;
    }

    memberRequest = aNewMemberRequest;
    Visitor anOldVisitor = aNewMemberRequest != null ? aNewMemberRequest.getVisitor() : null;

    if (!this.equals(anOldVisitor))
    {
      if (anOldVisitor != null)
      {
        anOldVisitor.memberRequest = null;
      }
      if (memberRequest != null)
      {
        memberRequest.setVisitor(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMemberSeason(ClimbingSeason aMemberSeason)
  {
    boolean wasSet = false;
    if (aMemberSeason == null)
    {
      return wasSet;
    }

    ClimbingSeason existingMemberSeason = memberSeason;
    memberSeason = aMemberSeason;
    if (existingMemberSeason != null && !existingMemberSeason.equals(aMemberSeason))
    {
      existingMemberSeason.removeMember(this);
    }
    memberSeason.addMember(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGuideSseason(ClimbingSeason aGuideSseason)
  {
    boolean wasSet = false;
    if (aGuideSseason == null)
    {
      return wasSet;
    }

    ClimbingSeason existingGuideSseason = guideSseason;
    guideSseason = aGuideSseason;
    if (existingGuideSseason != null && !existingGuideSseason.equals(aGuideSseason))
    {
      existingGuideSseason.removeGuide(this);
    }
    guideSseason.addGuide(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setGuideAssignment(Assignment aNewGuideAssignment)
  {
    boolean wasSet = false;
    if (aNewGuideAssignment == null)
    {
      Assignment existingGuideAssignment = guideAssignment;
      guideAssignment = null;
      
      if (existingGuideAssignment != null && existingGuideAssignment.getGuide() != null)
      {
        existingGuideAssignment.setGuide(null);
      }
      wasSet = true;
      return wasSet;
    }

    Assignment currentGuideAssignment = getGuideAssignment();
    if (currentGuideAssignment != null && !currentGuideAssignment.equals(aNewGuideAssignment))
    {
      currentGuideAssignment.setGuide(null);
    }

    guideAssignment = aNewGuideAssignment;
    Visitor existingGuide = aNewGuideAssignment.getGuide();

    if (!equals(existingGuide))
    {
      aNewGuideAssignment.setGuide(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    visitorsByUsername.remove(getUsername());
    MemberRequest existingMemberRequest = memberRequest;
    memberRequest = null;
    if (existingMemberRequest != null)
    {
      existingMemberRequest.delete();
      existingMemberRequest.setVisitor(null);
    }
    ClimbingSeason placeholderMemberSeason = memberSeason;
    this.memberSeason = null;
    if(placeholderMemberSeason != null)
    {
      placeholderMemberSeason.removeMember(this);
    }
    ClimbingSeason placeholderGuideSseason = guideSseason;
    this.guideSseason = null;
    if(placeholderGuideSseason != null)
    {
      placeholderGuideSseason.removeGuide(this);
    }
    Assignment existingMemberAssignment = memberAssignment;
    memberAssignment = null;
    if (existingMemberAssignment != null)
    {
      existingMemberAssignment.delete();
    }
    if (guideAssignment != null)
    {
      guideAssignment.setGuide(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "memberRequest = "+(getMemberRequest()!=null?Integer.toHexString(System.identityHashCode(getMemberRequest())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "memberSeason = "+(getMemberSeason()!=null?Integer.toHexString(System.identityHashCode(getMemberSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guideSseason = "+(getGuideSseason()!=null?Integer.toHexString(System.identityHashCode(getGuideSseason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "memberAssignment = "+(getMemberAssignment()!=null?Integer.toHexString(System.identityHashCode(getMemberAssignment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guideAssignment = "+(getGuideAssignment()!=null?Integer.toHexString(System.identityHashCode(getGuideAssignment())):"null");
  }
}