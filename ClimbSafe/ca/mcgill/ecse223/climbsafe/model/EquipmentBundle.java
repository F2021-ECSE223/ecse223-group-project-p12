/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 29 "../../../../../../model.ump"
// line 125 "../../../../../../model.ump"
// line 141 "../../../../../../model.ump"
public class EquipmentBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentBundle Attributes
  private int discount;

  //EquipmentBundle Associations
  private List<Equipment> equipment;
  private ClimbingSeason climbingSeason;
  private Request request;
  private Assignment assignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentBundle(int aDiscount, ClimbingSeason aClimbingSeason, Request aRequest, Assignment aAssignment)
  {
    discount = aDiscount;
    equipment = new ArrayList<Equipment>();
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddRequest = setRequest(aRequest);
    if (!didAddRequest)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to request. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssignment = setAssignment(aAssignment);
    if (!didAddAssignment)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to assignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDiscount(int aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getDiscount()
  {
    return discount;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_GetOne */
  public Request getRequest()
  {
    return request;
  }
  /* Code from template association_GetOne */
  public Assignment getAssignment()
  {
    return assignment;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    if (aEquipment.indexOfEquipmentBundle(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipment.addEquipmentBundle(this);
      if (!wasAdded)
      {
        equipment.remove(aEquipment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (!equipment.contains(aEquipment))
    {
      return wasRemoved;
    }

    int oldIndex = equipment.indexOf(aEquipment);
    equipment.remove(oldIndex);
    if (aEquipment.indexOfEquipmentBundle(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipment.removeEquipmentBundle(this);
      if (!wasRemoved)
      {
        equipment.add(oldIndex,aEquipment);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClimbingSeason(ClimbingSeason aClimbingSeason)
  {
    boolean wasSet = false;
    if (aClimbingSeason == null)
    {
      return wasSet;
    }

    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = aClimbingSeason;
    if (existingClimbingSeason != null && !existingClimbingSeason.equals(aClimbingSeason))
    {
      existingClimbingSeason.removeEquipmentBundle(this);
    }
    climbingSeason.addEquipmentBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRequest(Request aRequest)
  {
    boolean wasSet = false;
    if (aRequest == null)
    {
      return wasSet;
    }

    Request existingRequest = request;
    request = aRequest;
    if (existingRequest != null && !existingRequest.equals(aRequest))
    {
      existingRequest.removeEquipmentBundle(this);
    }
    request.addEquipmentBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssignment(Assignment aAssignment)
  {
    boolean wasSet = false;
    if (aAssignment == null)
    {
      return wasSet;
    }

    Assignment existingAssignment = assignment;
    assignment = aAssignment;
    if (existingAssignment != null && !existingAssignment.equals(aAssignment))
    {
      existingAssignment.removeEquipmentBundle(this);
    }
    assignment.addEquipmentBundle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Equipment> copyOfEquipment = new ArrayList<Equipment>(equipment);
    equipment.clear();
    for(Equipment aEquipment : copyOfEquipment)
    {
      aEquipment.removeEquipmentBundle(this);
    }
    ClimbingSeason placeholderClimbingSeason = climbingSeason;
    this.climbingSeason = null;
    if(placeholderClimbingSeason != null)
    {
      placeholderClimbingSeason.removeEquipmentBundle(this);
    }
    Request placeholderRequest = request;
    this.request = null;
    if(placeholderRequest != null)
    {
      placeholderRequest.removeEquipmentBundle(this);
    }
    Assignment placeholderAssignment = assignment;
    this.assignment = null;
    if(placeholderAssignment != null)
    {
      placeholderAssignment.removeEquipmentBundle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "discount" + ":" + getDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "request = "+(getRequest()!=null?Integer.toHexString(System.identityHashCode(getRequest())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}