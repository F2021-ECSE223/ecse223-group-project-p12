/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 57 "../../../../../../model.ump"
// line 127 "../../../../../../model.ump"
public class Hotel
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Stars { One, Two, Three, Four, Five }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private String address;
  private Stars stars;

  //Hotel Associations
  private ClimbSafe climbSafe;
  private Assignment assignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, String aAddress, Stars aStars, ClimbSafe aClimbSafe, Assignment aAssignment)
  {
    name = aName;
    address = aAddress;
    stars = aStars;
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create hotel due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssignment = setAssignment(aAssignment);
    if (!didAddAssignment)
    {
      throw new RuntimeException("Unable to create hotel due to assignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setStars(Stars aStars)
  {
    boolean wasSet = false;
    stars = aStars;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public Stars getStars()
  {
    return stars;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public Assignment getAssignment()
  {
    return assignment;
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
      existingClimbSafe.removeHotel(this);
    }
    climbSafe.addHotel(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setAssignment(Assignment aNewAssignment)
  {
    boolean wasSet = false;
    if (aNewAssignment == null)
    {
      //Unable to setAssignment to null, as hotel must always be associated to a assignment
      return wasSet;
    }
    
    Hotel existingHotel = aNewAssignment.getHotel();
    if (existingHotel != null && !equals(existingHotel))
    {
      //Unable to setAssignment, the current assignment already has a hotel, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Assignment anOldAssignment = assignment;
    assignment = aNewAssignment;
    assignment.setHotel(this);

    if (anOldAssignment != null)
    {
      anOldAssignment.setHotel(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeHotel(this);
    }
    Assignment existingAssignment = assignment;
    assignment = null;
    if (existingAssignment != null)
    {
      existingAssignment.setHotel(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "stars" + "=" + (getStars() != null ? !getStars().equals(this)  ? getStars().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}