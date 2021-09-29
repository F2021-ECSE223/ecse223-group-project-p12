/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 32 "../../../../../../model.ump"
// line 116 "../../../../../../model.ump"
// line 162 "../../../../../../model.ump"
public class EquipmentBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentBundle Attributes
  private int discount;

  //EquipmentBundle Associations
  private List<Equipment> equipment;
  private ClimbSafe climbSafe;
  private MemberRequest memberRequest;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentBundle(int aDiscount, ClimbSafe aClimbSafe, MemberRequest aMemberRequest, Equipment... allEquipment)
  {
    discount = aDiscount;
    equipment = new ArrayList<Equipment>();
    boolean didAddEquipment = setEquipment(allEquipment);
    if (!didAddEquipment)
    {
      throw new RuntimeException("Unable to create EquipmentBundle, must have at least 2 equipment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddClimbSafe = setClimbSafe(aClimbSafe);
    if (!didAddClimbSafe)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to climbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMemberRequest = setMemberRequest(aMemberRequest);
    if (!didAddMemberRequest)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to memberRequest. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }
  /* Code from template association_GetOne */
  public MemberRequest getMemberRequest()
  {
    return memberRequest;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfEquipmentValid()
  {
    boolean isValid = numberOfEquipment() >= minimumNumberOfEquipment();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 2;
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
  /* Code from template association_AddMStarToMany */
  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (!equipment.contains(aEquipment))
    {
      return wasRemoved;
    }

    if (numberOfEquipment() <= minimumNumberOfEquipment())
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
  /* Code from template association_SetMStarToMany */
  public boolean setEquipment(Equipment... newEquipment)
  {
    boolean wasSet = false;
    ArrayList<Equipment> verifiedEquipment = new ArrayList<Equipment>();
    for (Equipment aEquipment : newEquipment)
    {
      if (verifiedEquipment.contains(aEquipment))
      {
        continue;
      }
      verifiedEquipment.add(aEquipment);
    }

    if (verifiedEquipment.size() != newEquipment.length || verifiedEquipment.size() < minimumNumberOfEquipment())
    {
      return wasSet;
    }

    ArrayList<Equipment> oldEquipment = new ArrayList<Equipment>(equipment);
    equipment.clear();
    for (Equipment aNewEquipment : verifiedEquipment)
    {
      equipment.add(aNewEquipment);
      if (oldEquipment.contains(aNewEquipment))
      {
        oldEquipment.remove(aNewEquipment);
      }
      else
      {
        aNewEquipment.addEquipmentBundle(this);
      }
    }

    for (Equipment anOldEquipment : oldEquipment)
    {
      anOldEquipment.removeEquipmentBundle(this);
    }
    wasSet = true;
    return wasSet;
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
      existingClimbSafe.removeEquipmentBundle(this);
    }
    climbSafe.addEquipmentBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMemberRequest(MemberRequest aMemberRequest)
  {
    boolean wasSet = false;
    if (aMemberRequest == null)
    {
      return wasSet;
    }

    MemberRequest existingMemberRequest = memberRequest;
    memberRequest = aMemberRequest;
    if (existingMemberRequest != null && !existingMemberRequest.equals(aMemberRequest))
    {
      existingMemberRequest.removeEquipmentBundle(this);
    }
    memberRequest.addEquipmentBundle(this);
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
    ClimbSafe placeholderClimbSafe = climbSafe;
    this.climbSafe = null;
    if(placeholderClimbSafe != null)
    {
      placeholderClimbSafe.removeEquipmentBundle(this);
    }
    MemberRequest placeholderMemberRequest = memberRequest;
    this.memberRequest = null;
    if(placeholderMemberRequest != null)
    {
      placeholderMemberRequest.removeEquipmentBundle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "discount" + ":" + getDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "memberRequest = "+(getMemberRequest()!=null?Integer.toHexString(System.identityHashCode(getMemberRequest())):"null");
  }
}