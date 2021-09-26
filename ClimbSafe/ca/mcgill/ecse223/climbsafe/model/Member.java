/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 41 "../../../../../../model.ump"
// line 115 "../../../../../../model.ump"
// line 151 "../../../../../../model.ump"
public class Member extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Member Attributes
  private int emergencyContact;

  //Member Associations
  private Request request;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Member(String aUsername, String aName, String aPassword, NMC aNMC, int aEmergencyContact)
  {
    super(aUsername, aName, aPassword, aNMC);
    emergencyContact = aEmergencyContact;
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmergencyContact(int aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public int getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetOne */
  public Request getRequest()
  {
    return request;
  }

  public boolean hasRequest()
  {
    boolean has = request != null;
    return has;
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setRequest(Request aNewRequest)
  {
    boolean wasSet = false;
    if (request != null && !request.equals(aNewRequest) && equals(request.getMember()))
    {
      //Unable to setRequest, as existing request would become an orphan
      return wasSet;
    }

    request = aNewRequest;
    Member anOldMember = aNewRequest != null ? aNewRequest.getMember() : null;

    if (!this.equals(anOldMember))
    {
      if (anOldMember != null)
      {
        anOldMember.request = null;
      }
      if (request != null)
      {
        request.setMember(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(int aStartWeek, int aDuration, int aPrice, ClimbingSeason aClimbingSeason)
  {
    return new Assignment(aStartWeek, aDuration, aPrice, this, aClimbingSeason);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    Member existingMember = aAssignment.getMember();
    boolean isNewMember = existingMember != null && !this.equals(existingMember);
    if (isNewMember)
    {
      aAssignment.setMember(this);
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
    //Unable to remove aAssignment, as it must always have a member
    if (!this.equals(aAssignment.getMember()))
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

  public void delete()
  {
    Request existingRequest = request;
    request = null;
    if (existingRequest != null)
    {
      existingRequest.delete();
    }
    for(int i=assignments.size(); i > 0; i--)
    {
      Assignment aAssignment = assignments.get(i - 1);
      aAssignment.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "request = "+(getRequest()!=null?Integer.toHexString(System.identityHashCode(getRequest())):"null");
  }
}