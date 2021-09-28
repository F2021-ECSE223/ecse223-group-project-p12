/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 36 "../../../../../../model.ump"
// line 123 "../../../../../../model.ump"
// line 169 "../../../../../../model.ump"
public class EquipmentBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EquipmentBundle Attributes
  private int discount;

  //EquipmentBundle Associations
  private List<Equipment> equipment;
  private NMC nMC;
  private MemberRequest memberRequest;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EquipmentBundle(int aDiscount, NMC aNMC, MemberRequest aMemberRequest)
  {
    discount = aDiscount;
    equipment = new ArrayList<Equipment>();
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create equipmentBundle due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public NMC getNMC()
  {
    return nMC;
  }
  /* Code from template association_GetOne */
  public MemberRequest getMemberRequest()
  {
    return memberRequest;
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
  public boolean setNMC(NMC aNMC)
  {
    boolean wasSet = false;
    if (aNMC == null)
    {
      return wasSet;
    }

    NMC existingNMC = nMC;
    nMC = aNMC;
    if (existingNMC != null && !existingNMC.equals(aNMC))
    {
      existingNMC.removeEquipmentBundle(this);
    }
    nMC.addEquipmentBundle(this);
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
    NMC placeholderNMC = nMC;
    this.nMC = null;
    if(placeholderNMC != null)
    {
      placeholderNMC.removeEquipmentBundle(this);
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
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "memberRequest = "+(getMemberRequest()!=null?Integer.toHexString(System.identityHashCode(getMemberRequest())):"null");
  }
}