/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;
import java.sql.Date;

// line 12 "../../../../../../model.ump"
// line 107 "../../../../../../model.ump"
public class NMC
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NMC Attributes
  private int guideWeeklyRate;

  //NMC Associations
  private List<Equipment> equipment;
  private List<EquipmentBundle> equipmentBundles;
  private ClimbingSeason climbingSeason;
  private List<Hotel> hotels;
  private Administrator administrator;
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NMC(int aGuideWeeklyRate, Administrator aAdministrator, ClimbSafe aClimbSafe)
  {
    guideWeeklyRate = aGuideWeeklyRate;
    equipment = new ArrayList<Equipment>();
    equipmentBundles = new ArrayList<EquipmentBundle>();
    hotels = new ArrayList<Hotel>();
    if (aAdministrator == null || aAdministrator.getNMC() != null)
    {
      throw new RuntimeException("Unable to create NMC due to aAdministrator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    administrator = aAdministrator;
    if (aClimbSafe == null || aClimbSafe.getNMC() != null)
    {
      throw new RuntimeException("Unable to create NMC due to aClimbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbSafe = aClimbSafe;
  }

  public NMC(int aGuideWeeklyRate)
  {
    guideWeeklyRate = aGuideWeeklyRate;
    equipment = new ArrayList<Equipment>();
    equipmentBundles = new ArrayList<EquipmentBundle>();
    hotels = new ArrayList<Hotel>();
    administrator = new Administrator(this);
    climbSafe = new ClimbSafe(this);
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
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
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
    NMC existingNMC = aEquipment.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aEquipment.setNMC(this);
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
    //Unable to remove aEquipment, as it must always have a nMC
    if (!this.equals(aEquipment.getNMC()))
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
  public EquipmentBundle addEquipmentBundle(int aDiscount, MemberRequest aMemberRequest)
  {
    return new EquipmentBundle(aDiscount, this, aMemberRequest);
  }

  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundles.contains(aEquipmentBundle)) { return false; }
    NMC existingNMC = aEquipmentBundle.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aEquipmentBundle.setNMC(this);
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
    //Unable to remove aEquipmentBundle, as it must always have a nMC
    if (!this.equals(aEquipmentBundle.getNMC()))
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
    if (climbingSeason != null && !climbingSeason.equals(aNewClimbingSeason) && equals(climbingSeason.getNMC()))
    {
      //Unable to setClimbingSeason, as existing climbingSeason would become an orphan
      return wasSet;
    }

    climbingSeason = aNewClimbingSeason;
    NMC anOldNMC = aNewClimbingSeason != null ? aNewClimbingSeason.getNMC() : null;

    if (!this.equals(anOldNMC))
    {
      if (anOldNMC != null)
      {
        anOldNMC.climbingSeason = null;
      }
      if (climbingSeason != null)
      {
        climbingSeason.setNMC(this);
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
  public Hotel addHotel(String aName, String aAddress, int aStars, int aPricePerNight, Assignment aAssignment)
  {
    return new Hotel(aName, aAddress, aStars, aPricePerNight, this, aAssignment);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    NMC existingNMC = aHotel.getNMC();
    boolean isNewNMC = existingNMC != null && !this.equals(existingNMC);
    if (isNewNMC)
    {
      aHotel.setNMC(this);
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
    //Unable to remove aHotel, as it must always have a nMC
    if (!this.equals(aHotel.getNMC()))
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
      existingClimbingSeason.setNMC(null);
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
    ClimbSafe existingClimbSafe = climbSafe;
    climbSafe = null;
    if (existingClimbSafe != null)
    {
      existingClimbSafe.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "guideWeeklyRate" + ":" + getGuideWeeklyRate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "administrator = "+(getAdministrator()!=null?Integer.toHexString(System.identityHashCode(getAdministrator())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }
}