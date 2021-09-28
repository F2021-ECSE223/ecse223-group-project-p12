/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 29 "../../../../../../model.ump"
// line 118 "../../../../../../model.ump"
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
  private NMC nMC;
  private List<EquipmentBundle> equipmentBundles;
  private MemberRequest memberRequest;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aName, String aDescription, double aWeight, int aPrice, NMC aNMC, MemberRequest aMemberRequest)
  {
    name = aName;
    description = aDescription;
    weight = aWeight;
    price = aPrice;
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create equipment due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    equipmentBundles = new ArrayList<EquipmentBundle>();
    boolean didAddMemberRequest = setMemberRequest(aMemberRequest);
    if (!didAddMemberRequest)
    {
      throw new RuntimeException("Unable to create equipment due to memberRequest. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public NMC getNMC()
  {
    return nMC;
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
  public MemberRequest getMemberRequest()
  {
    return memberRequest;
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
      existingNMC.removeEquipment(this);
    }
    nMC.addEquipment(this);
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
      existingMemberRequest.removeEquipment(this);
    }
    memberRequest.addEquipment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    NMC placeholderNMC = nMC;
    this.nMC = null;
    if(placeholderNMC != null)
    {
      placeholderNMC.removeEquipment(this);
    }
    ArrayList<EquipmentBundle> copyOfEquipmentBundles = new ArrayList<EquipmentBundle>(equipmentBundles);
    equipmentBundles.clear();
    for(EquipmentBundle aEquipmentBundle : copyOfEquipmentBundles)
    {
      aEquipmentBundle.removeEquipment(this);
    }
    MemberRequest placeholderMemberRequest = memberRequest;
    this.memberRequest = null;
    if(placeholderMemberRequest != null)
    {
      placeholderMemberRequest.removeEquipment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "," +
            "weight" + ":" + getWeight()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "memberRequest = "+(getMemberRequest()!=null?Integer.toHexString(System.identityHashCode(getMemberRequest())):"null");
  }
}