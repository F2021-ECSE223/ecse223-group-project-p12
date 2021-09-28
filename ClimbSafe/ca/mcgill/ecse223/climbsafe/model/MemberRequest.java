/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 49 "../../../../../../model.ump"
// line 159 "../../../../../../model.ump"
public class MemberRequest
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MemberRequest Attributes
  private boolean needsHotel;
  private boolean needsGuide;

  //MemberRequest Associations
  private List<Equipment> equipment;
  private List<EquipmentBundle> equipmentBundles;
  private Visitor visitor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MemberRequest(boolean aNeedsHotel, boolean aNeedsGuide, Visitor aVisitor)
  {
    needsHotel = aNeedsHotel;
    needsGuide = aNeedsGuide;
    equipment = new ArrayList<Equipment>();
    equipmentBundles = new ArrayList<EquipmentBundle>();
    boolean didAddVisitor = setVisitor(aVisitor);
    if (!didAddVisitor)
    {
      throw new RuntimeException("Unable to create memberRequest due to visitor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNeedsHotel(boolean aNeedsHotel)
  {
    boolean wasSet = false;
    needsHotel = aNeedsHotel;
    wasSet = true;
    return wasSet;
  }

  public boolean setNeedsGuide(boolean aNeedsGuide)
  {
    boolean wasSet = false;
    needsGuide = aNeedsGuide;
    wasSet = true;
    return wasSet;
  }

  public boolean getNeedsHotel()
  {
    return needsHotel;
  }

  public boolean getNeedsGuide()
  {
    return needsGuide;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isNeedsHotel()
  {
    return needsHotel;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isNeedsGuide()
  {
    return needsGuide;
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
  /* Code from template association_GetOne */
  public Visitor getVisitor()
  {
    return visitor;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(String aName, String aDescription, double aWeight, int aPrice, NMC aNMC)
  {
    return new Equipment(aName, aDescription, aWeight, aPrice, aNMC, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    MemberRequest existingMemberRequest = aEquipment.getMemberRequest();
    boolean isNewMemberRequest = existingMemberRequest != null && !this.equals(existingMemberRequest);
    if (isNewMemberRequest)
    {
      aEquipment.setMemberRequest(this);
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
    //Unable to remove aEquipment, as it must always have a memberRequest
    if (!this.equals(aEquipment.getMemberRequest()))
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
  public EquipmentBundle addEquipmentBundle(int aDiscount, NMC aNMC)
  {
    return new EquipmentBundle(aDiscount, aNMC, this);
  }

  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundles.contains(aEquipmentBundle)) { return false; }
    MemberRequest existingMemberRequest = aEquipmentBundle.getMemberRequest();
    boolean isNewMemberRequest = existingMemberRequest != null && !this.equals(existingMemberRequest);
    if (isNewMemberRequest)
    {
      aEquipmentBundle.setMemberRequest(this);
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
    //Unable to remove aEquipmentBundle, as it must always have a memberRequest
    if (!this.equals(aEquipmentBundle.getMemberRequest()))
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
  /* Code from template association_SetOneToOptionalOne */
  public boolean setVisitor(Visitor aNewVisitor)
  {
    boolean wasSet = false;
    if (aNewVisitor == null)
    {
      //Unable to setVisitor to null, as memberRequest must always be associated to a visitor
      return wasSet;
    }
    
    MemberRequest existingMemberRequest = aNewVisitor.getMemberRequest();
    if (existingMemberRequest != null && !equals(existingMemberRequest))
    {
      //Unable to setVisitor, the current visitor already has a memberRequest, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Visitor anOldVisitor = visitor;
    visitor = aNewVisitor;
    visitor.setMemberRequest(this);

    if (anOldVisitor != null)
    {
      anOldVisitor.setMemberRequest(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
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
    Visitor existingVisitor = visitor;
    visitor = null;
    if (existingVisitor != null)
    {
      existingVisitor.setMemberRequest(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "needsHotel" + ":" + getNeedsHotel()+ "," +
            "needsGuide" + ":" + getNeedsGuide()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "visitor = "+(getVisitor()!=null?Integer.toHexString(System.identityHashCode(getVisitor())):"null");
  }
}