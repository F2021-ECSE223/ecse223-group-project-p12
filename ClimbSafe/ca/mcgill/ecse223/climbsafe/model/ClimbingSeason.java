/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.sql.Date;
import java.util.*;

// line 21 "../../../../../../model.ump"
// line 97 "../../../../../../model.ump"
public class ClimbingSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbingSeason Attributes
  private Date startDate;
  private Date endDate;

  //ClimbingSeason Associations
  private List<Assignment> assignments;
  private List<Visitor> member;
  private List<Visitor> guide;
  private NMC nMC;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbingSeason(Date aStartDate, Date aEndDate, NMC aNMC)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    assignments = new ArrayList<Assignment>();
    member = new ArrayList<Visitor>();
    guide = new ArrayList<Visitor>();
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create climbingSeason due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_GetMany */
  public Visitor getMember(int index)
  {
    Visitor aMember = member.get(index);
    return aMember;
  }

  public List<Visitor> getMember()
  {
    List<Visitor> newMember = Collections.unmodifiableList(member);
    return newMember;
  }

  public int numberOfMember()
  {
    int number = member.size();
    return number;
  }

  public boolean hasMember()
  {
    boolean has = member.size() > 0;
    return has;
  }

  public int indexOfMember(Visitor aMember)
  {
    int index = member.indexOf(aMember);
    return index;
  }
  /* Code from template association_GetMany */
  public Visitor getGuide(int index)
  {
    Visitor aGuide = guide.get(index);
    return aGuide;
  }

  public List<Visitor> getGuide()
  {
    List<Visitor> newGuide = Collections.unmodifiableList(guide);
    return newGuide;
  }

  public int numberOfGuide()
  {
    int number = guide.size();
    return number;
  }

  public boolean hasGuide()
  {
    boolean has = guide.size() > 0;
    return has;
  }

  public int indexOfGuide(Visitor aGuide)
  {
    int index = guide.indexOf(aGuide);
    return index;
  }
  /* Code from template association_GetOne */
  public NMC getNMC()
  {
    return nMC;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(int aStartWeek, int aDuration, int aPrice, Visitor aMember)
  {
    return new Assignment(aStartWeek, aDuration, aPrice, aMember, this);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    ClimbingSeason existingClimbingSeason = aAssignment.getClimbingSeason();
    boolean isNewClimbingSeason = existingClimbingSeason != null && !this.equals(existingClimbingSeason);
    if (isNewClimbingSeason)
    {
      aAssignment.setClimbingSeason(this);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a climbingSeason
    if (!this.equals(aAssignment.getClimbingSeason()))
    {
      assignments.remove(aAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMember()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Visitor addMember(String aUsername, String aName, String aPassword, int aEmergencyContact, ClimbingSeason aGuideSseason, Assignment aMemberAssignment)
  {
    return new Visitor(aUsername, aName, aPassword, aEmergencyContact, this, aGuideSseason, aMemberAssignment);
  }

  public boolean addMember(Visitor aMember)
  {
    boolean wasAdded = false;
    if (member.contains(aMember)) { return false; }
    ClimbingSeason existingMemberSeason = aMember.getMemberSeason();
    boolean isNewMemberSeason = existingMemberSeason != null && !this.equals(existingMemberSeason);
    if (isNewMemberSeason)
    {
      aMember.setMemberSeason(this);
    }
    else
    {
      member.add(aMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMember(Visitor aMember)
  {
    boolean wasRemoved = false;
    //Unable to remove aMember, as it must always have a memberSeason
    if (!this.equals(aMember.getMemberSeason()))
    {
      member.remove(aMember);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMemberAt(Visitor aMember, int index)
  {  
    boolean wasAdded = false;
    if(addMember(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMember()) { index = numberOfMember() - 1; }
      member.remove(aMember);
      member.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Visitor aMember, int index)
  {
    boolean wasAdded = false;
    if(member.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMember()) { index = numberOfMember() - 1; }
      member.remove(aMember);
      member.add(index, aMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMemberAt(aMember, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGuide()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Visitor addGuide(String aUsername, String aName, String aPassword, int aEmergencyContact, ClimbingSeason aMemberSeason, Assignment aMemberAssignment)
  {
    return new Visitor(aUsername, aName, aPassword, aEmergencyContact, aMemberSeason, this, aMemberAssignment);
  }

  public boolean addGuide(Visitor aGuide)
  {
    boolean wasAdded = false;
    if (guide.contains(aGuide)) { return false; }
    ClimbingSeason existingGuideSseason = aGuide.getGuideSseason();
    boolean isNewGuideSseason = existingGuideSseason != null && !this.equals(existingGuideSseason);
    if (isNewGuideSseason)
    {
      aGuide.setGuideSseason(this);
    }
    else
    {
      guide.add(aGuide);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGuide(Visitor aGuide)
  {
    boolean wasRemoved = false;
    //Unable to remove aGuide, as it must always have a guideSseason
    if (!this.equals(aGuide.getGuideSseason()))
    {
      guide.remove(aGuide);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGuideAt(Visitor aGuide, int index)
  {  
    boolean wasAdded = false;
    if(addGuide(aGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuide()) { index = numberOfGuide() - 1; }
      guide.remove(aGuide);
      guide.add(index, aGuide);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGuideAt(Visitor aGuide, int index)
  {
    boolean wasAdded = false;
    if(guide.contains(aGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuide()) { index = numberOfGuide() - 1; }
      guide.remove(aGuide);
      guide.add(index, aGuide);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGuideAt(aGuide, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setNMC(NMC aNewNMC)
  {
    boolean wasSet = false;
    if (aNewNMC == null)
    {
      //Unable to setNMC to null, as climbingSeason must always be associated to a nMC
      return wasSet;
    }
    
    ClimbingSeason existingClimbingSeason = aNewNMC.getClimbingSeason();
    if (existingClimbingSeason != null && !equals(existingClimbingSeason))
    {
      //Unable to setNMC, the current nMC already has a climbingSeason, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    NMC anOldNMC = nMC;
    nMC = aNewNMC;
    nMC.setClimbingSeason(this);

    if (anOldNMC != null)
    {
      anOldNMC.setClimbingSeason(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (assignments.size() > 0)
    {
      Assignment aAssignment = assignments.get(assignments.size() - 1);
      aAssignment.delete();
      assignments.remove(aAssignment);
    }
    
    while (member.size() > 0)
    {
      Visitor aMember = member.get(member.size() - 1);
      aMember.delete();
      member.remove(aMember);
    }
    
    while (guide.size() > 0)
    {
      Visitor aGuide = guide.get(guide.size() - 1);
      aGuide.delete();
      guide.remove(aGuide);
    }
    
    NMC existingNMC = nMC;
    nMC = null;
    if (existingNMC != null)
    {
      existingNMC.setClimbingSeason(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null");
  }
}