/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;
import java.sql.Date;

// line 8 "../../../../../../model.ump"
// line 77 "../../../../../../model.ump"
// line 97 "../../../../../../model.ump"
public class ClimbSafe
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Stars { One, Two, Three, Four, Five }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbSafe Attributes
  private int guideWeeklyRate;

  //ClimbSafe Associations
  private List<Equipment> equipment;
  private List<EquipmentBundle> equipmentBundles;
  private ClimbingSeason climbingSeason;
  private List<Hotel> hotels;
  private Administrator administrator;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbSafe(Administrator aAdministrator)
  {
    guideWeeklyRate = 0;
    equipment = new ArrayList<Equipment>();
    equipmentBundles = new ArrayList<EquipmentBundle>();
    hotels = new ArrayList<Hotel>();
    if (aAdministrator == null || aAdministrator.getClimbSafe() != null)
    {
      throw new RuntimeException("Unable to create ClimbSafe due to aAdministrator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    administrator = aAdministrator;
  }

  public ClimbSafe()
  {
    guideWeeklyRate = 0;
    equipment = new ArrayList<Equipment>();
    equipmentBundles = new ArrayList<EquipmentBundle>();
    hotels = new ArrayList<Hotel>();
    administrator = new Administrator(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGuideWeeklyRate(int aGuideWeeklyRate)
  {
    boolean wasSet = false;
    guideWeeklyRate = aGuideWeeklyRate;
    wasSet = true;
    return wasSet;
  }

  public int getGuideWeeklyRate()
  {
    return guideWeeklyRate;
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
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }

  public boolean hasClimbingSeason()
  {
    boolean has = climbingSeason != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
  }
  /* Code from template association_GetOne */
  public Administrator getAdministrator()
  {
    return administrator;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipment()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(String aName, String aDescription, double aWeight, int aPrice, MemberRequest aMemberRequest)
  {
    return new Equipment(aName, aDescription, aWeight, aPrice, this, aMemberRequest);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    ClimbSafe existingClimbSafe = aEquipment.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aEquipment.setClimbSafe(this);
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
    //Unable to remove aEquipment, as it must always have a climbSafe
    if (!this.equals(aEquipment.getClimbSafe()))
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
  public EquipmentBundle addEquipmentBundle(int aDiscount, MemberRequest aMemberRequest, Equipment... allEquipment)
  {
    return new EquipmentBundle(aDiscount, this, aMemberRequest, allEquipment);
  }

  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundles.contains(aEquipmentBundle)) { return false; }
    ClimbSafe existingClimbSafe = aEquipmentBundle.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aEquipmentBundle.setClimbSafe(this);
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
    //Unable to remove aEquipmentBundle, as it must always have a climbSafe
    if (!this.equals(aEquipmentBundle.getClimbSafe()))
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setClimbingSeason(ClimbingSeason aNewClimbingSeason)
  {
    boolean wasSet = false;
    if (climbingSeason != null && !climbingSeason.equals(aNewClimbingSeason) && equals(climbingSeason.getClimbSafe()))
    {
      //Unable to setClimbingSeason, as existing climbingSeason would become an orphan
      return wasSet;
    }

    climbingSeason = aNewClimbingSeason;
    ClimbSafe anOldClimbSafe = aNewClimbingSeason != null ? aNewClimbingSeason.getClimbSafe() : null;

    if (!this.equals(anOldClimbSafe))
    {
      if (anOldClimbSafe != null)
      {
        anOldClimbSafe.climbingSeason = null;
      }
      if (climbingSeason != null)
      {
        climbingSeason.setClimbSafe(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(String aName, String aAddress, Stars aStars)
  {
    return new Hotel(aName, aAddress, aStars, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    ClimbSafe existingClimbSafe = aHotel.getClimbSafe();
    boolean isNewClimbSafe = existingClimbSafe != null && !this.equals(existingClimbSafe);
    if (isNewClimbSafe)
    {
      aHotel.setClimbSafe(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a climbSafe
    if (!this.equals(aHotel.getClimbSafe()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (equipment.size() > 0)
    {
      Equipment aEquipment = equipment.get(equipment.size() - 1);
      aEquipment.delete();
      equipment.remove(aEquipment);
    }
    
    while (equipmentBundles.size() > 0)
    {
      EquipmentBundle aEquipmentBundle = equipmentBundles.get(equipmentBundles.size() - 1);
      aEquipmentBundle.delete();
      equipmentBundles.remove(aEquipmentBundle);
    }
    
    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = null;
    if (existingClimbingSeason != null)
    {
      existingClimbingSeason.delete();
      existingClimbingSeason.setClimbSafe(null);
    }
    while (hotels.size() > 0)
    {
      Hotel aHotel = hotels.get(hotels.size() - 1);
      aHotel.delete();
      hotels.remove(aHotel);
    }
    
    Administrator existingAdministrator = administrator;
    administrator = null;
    if (existingAdministrator != null)
    {
      existingAdministrator.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "guideWeeklyRate" + ":" + getGuideWeeklyRate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "administrator = "+(getAdministrator()!=null?Integer.toHexString(System.identityHashCode(getAdministrator())):"null");
  }
}