/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 44 "../../../../../../model.ump"
// line 110 "../../../../../../model.ump"
// line 146 "../../../../../../model.ump"
public class Guide extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Attributes
  private int emergencyContact;

  //Guide Associations
  private Assignment assignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aUsername, String aPassword, NMC aNMC, int aEmergencyContact, Assignment aAssignment)
  {
    super(aUsername, aPassword, aNMC);
    emergencyContact = aEmergencyContact;
    boolean didAddAssignment = setAssignment(aAssignment);
    if (!didAddAssignment)
    {
      throw new RuntimeException("Unable to create guide due to assignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  public Assignment getAssignment()
  {
    return assignment;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAssignment(Assignment aNewAssignment)
  {
    boolean wasSet = false;
    if (aNewAssignment == null)
    {
      //Unable to setAssignment to null, as guide must always be associated to a assignment
      return wasSet;
    }
    
    Guide existingGuide = aNewAssignment.getGuide();
    if (existingGuide != null && !equals(existingGuide))
    {
      //Unable to setAssignment, the current assignment already has a guide, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Assignment anOldAssignment = assignment;
    assignment = aNewAssignment;
    assignment.setGuide(this);

    if (anOldAssignment != null)
    {
      anOldAssignment.setGuide(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Assignment existingAssignment = assignment;
    assignment = null;
    if (existingAssignment != null)
    {
      existingAssignment.setGuide(null);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}