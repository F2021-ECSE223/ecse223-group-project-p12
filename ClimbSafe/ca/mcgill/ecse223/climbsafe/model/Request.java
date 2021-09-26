/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 65 "../../../../../../model.ump"
// line 192 "../../../../../../model.ump"
public class Request
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Request Attributes
  private int duration;

  //Request Associations
  private Member member;
  private Hotel hotel;
  private List<Equipment> equipment;
  private List<EquipmentBundle> equipmentBundles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Request(int aDuration, Member aMember)
  {
    duration = aDuration;
    boolean didAddMember = setMember(aMember);
    if (!didAddMember)
    {
      throw new RuntimeException("Unable to create request due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    equipment = new ArrayList<Equipment>();
    equipmentBundles = new ArrayList<EquipmentBundle>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public int getDuration()
  {
    return duration;
  }
  /* Code from template association_GetOne */
  public Member getMember()
  {
    return member;
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
  /* Code from template association_GetMany */
  public EquipmentBundle getEquipmentBundle(int index)
  {
    EquipmentBundle aEquipmentBundle = equipmentBundles.get(index);
    return aEquipmentBundle;
  }

  public List<EquipmentBundle> getEquipmentBundles()
  {
    List<EquipmentBundle> newEquipmentBundles = Collections.unmodifiableList(equipmentBundles);
    return newEquipmentBundles;
  }

  public int numberOfEquipmentBundles()
  {
    int number = equipmentBundles.size();
    return number;
  }

  public boolean hasEquipmentBundles()
  {
    boolean has = equipmentBundles.size() > 0;
    return has;
  }

  public int indexOfEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    int index = equipmentBundles.indexOf(aEquipmentBundle);
    return index;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMember(Member aNewMember)
  {
    boolean wasSet = false;
    if (aNewMember == null)
    {
      //Unable to setMember to null, as request must always be associated to a member
      return wasSet;
    }
    
    Request existingRequest = aNewMember.getRequest();
    if (existingRequest != null && !equals(existingRequest))
    {
      //Unable to setMember, the current member already has a request, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Member anOldMember = member;
    member = aNewMember;
    member.setRequest(this);

    if (anOldMember != null)
    {
      anOldMember.setRequest(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setHotel(Hotel aNewHotel)
  {
    boolean wasSet = false;
    if (hotel != null && !hotel.equals(aNewHotel) && equals(hotel.getRequest()))
    {
      //Unable to setHotel, as existing hotel would become an orphan
      return wasSet;
    }

    hotel = aNewHotel;
    Request anOldRequest = aNewHotel != null ? aNewHotel.getRequest() : null;

    if (!this.equals(anOldRequest))
    {
      if (anOldRequest != null)
      {
        anOldRequest.hotel = null;
      }
      if (hotel != null)
      {
        hotel.setRequest(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(String aName, String aDescription, double aWeight, int aPrice, ClimbingSeason aClimbingSeason, Assignment aAssignment)
  {
    return new Equipment(aName, aDescription, aWeight, aPrice, aClimbingSeason, this, aAssignment);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    Request existingRequest = aEquipment.getRequest();
    boolean isNewRequest = existingRequest != null && !this.equals(existingRequest);
    if (isNewRequest)
    {
      aEquipment.setRequest(this);
    }
    else
    {
      equipment.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a request
    if (!this.equals(aEquipment.getRequest()))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public EquipmentBundle addEquipmentBundle(int aDiscount, ClimbingSeason aClimbingSeason, Assignment aAssignment)
  {
    return new EquipmentBundle(aDiscount, aClimbingSeason, this, aAssignment);
  }

  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundles.contains(aEquipmentBundle)) { return false; }
    Request existingRequest = aEquipmentBundle.getRequest();
    boolean isNewRequest = existingRequest != null && !this.equals(existingRequest);
    if (isNewRequest)
    {
      aEquipmentBundle.setRequest(this);
    }
    else
    {
      equipmentBundles.add(aEquipmentBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipmentBundle, as it must always have a request
    if (!this.equals(aEquipmentBundle.getRequest()))
    {
      equipmentBundles.remove(aEquipmentBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentBundleAt(EquipmentBundle aEquipmentBundle, int index)
  {  
    boolean wasAdded = false;
    if(addEquipmentBundle(aEquipmentBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentBundles()) { index = numberOfEquipmentBundles() - 1; }
      equipmentBundles.remove(aEquipmentBundle);
      equipmentBundles.add(index, aEquipmentBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentBundleAt(EquipmentBundle aEquipmentBundle, int index)
  {
    boolean wasAdded = false;
    if(equipmentBundles.contains(aEquipmentBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipmentBundles()) { index = numberOfEquipmentBundles() - 1; }
      equipmentBundles.remove(aEquipmentBundle);
      equipmentBundles.add(index, aEquipmentBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentBundleAt(aEquipmentBundle, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Member existingMember = member;
    member = null;
    if (existingMember != null)
    {
      existingMember.setRequest(null);
    }
    Hotel existingHotel = hotel;
    hotel = null;
    if (existingHotel != null)
    {
      existingHotel.delete();
    }
    for(int i=equipment.size(); i > 0; i--)
    {
      Equipment aEquipment = equipment.get(i - 1);
      aEquipment.delete();
    }
    for(int i=equipmentBundles.size(); i > 0; i--)
    {
      EquipmentBundle aEquipmentBundle = equipmentBundles.get(i - 1);
      aEquipmentBundle.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "duration" + ":" + getDuration()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "member = "+(getMember()!=null?Integer.toHexString(System.identityHashCode(getMember())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotel = "+(getHotel()!=null?Integer.toHexString(System.identityHashCode(getHotel())):"null");
  }
}