/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 22 "../../../../../../model.ump"
// line 99 "../../../../../../model.ump"
// line 136 "../../../../../../model.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private String name;
  private String description;
  private double weight;
  private int price;

  //Equipment Associations
  private ClimbingSeason climbingSeason;
  private List<EquipmentBundle> equipmentBundles;
  private Request request;
  private Assignment assignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aName, String aDescription, double aWeight, int aPrice, ClimbingSeason aClimbingSeason, Request aRequest, Assignment aAssignment)
  {
    name = aName;
    description = aDescription;
    weight = aWeight;
    price = aPrice;
    boolean didAddClimbingSeason = setClimbingSeason(aClimbingSeason);
    if (!didAddClimbingSeason)
    {
      throw new RuntimeException("Unable to create equipment due to climbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    equipmentBundles = new ArrayList<EquipmentBundle>();
    boolean didAddRequest = setRequest(aRequest);
    if (!didAddRequest)
    {
      throw new RuntimeException("Unable to create equipment due to request. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssignment = setAssignment(aAssignment);
    if (!didAddAssignment)
    {
      throw new RuntimeException("Unable to create equipment due to assignment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(double aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public double getWeight()
  {
    return weight;
  }

  public int getPrice()
  {
    return price;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
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
  public Request getRequest()
  {
    return request;
  }
  /* Code from template association_GetOne */
  public Assignment getAssignment()
  {
    return assignment;
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
      existingClimbingSeason.removeEquipment(this);
    }
    climbingSeason.addEquipment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipmentBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasAdded = false;
    if (equipmentBundles.contains(aEquipmentBundle)) { return false; }
    equipmentBundles.add(aEquipmentBundle);
    if (aEquipmentBundle.indexOfEquipment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aEquipmentBundle.addEquipment(this);
      if (!wasAdded)
      {
        equipmentBundles.remove(aEquipmentBundle);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeEquipmentBundle(EquipmentBundle aEquipmentBundle)
  {
    boolean wasRemoved = false;
    if (!equipmentBundles.contains(aEquipmentBundle))
    {
      return wasRemoved;
    }

    int oldIndex = equipmentBundles.indexOf(aEquipmentBundle);
    equipmentBundles.remove(oldIndex);
    if (aEquipmentBundle.indexOfEquipment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aEquipmentBundle.removeEquipment(this);
      if (!wasRemoved)
      {
        equipmentBundles.add(oldIndex,aEquipmentBundle);
      }
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
      existingRequest.removeEquipment(this);
    }
    request.addEquipment(this);
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
      existingAssignment.removeEquipment(this);
    }
    assignment.addEquipment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClimbingSeason placeholderClimbingSeason = climbingSeason;
    this.climbingSeason = null;
    if(placeholderClimbingSeason != null)
    {
      placeholderClimbingSeason.removeEquipment(this);
    }
    ArrayList<EquipmentBundle> copyOfEquipmentBundles = new ArrayList<EquipmentBundle>(equipmentBundles);
    equipmentBundles.clear();
    for(EquipmentBundle aEquipmentBundle : copyOfEquipmentBundles)
    {
      aEquipmentBundle.removeEquipment(this);
    }
    Request placeholderRequest = request;
    this.request = null;
    if(placeholderRequest != null)
    {
      placeholderRequest.removeEquipment(this);
    }
    Assignment placeholderAssignment = assignment;
    this.assignment = null;
    if(placeholderAssignment != null)
    {
      placeholderAssignment.removeEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "," +
            "weight" + ":" + getWeight()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "request = "+(getRequest()!=null?Integer.toHexString(System.identityHashCode(getRequest())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}