/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 57 "../../../../../../model.ump"
// line 121 "../../../../../../model.ump"
// line 156 "../../../../../../model.ump"
public class Hotel
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hotel Attributes
  private String name;
  private String address;
  private int stars;
  private int pricePerNight;

  //Hotel Associations
  private NMC nMC;
  private Assignment assignment;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hotel(String aName, String aAddress, int aStars, int aPricePerNight, NMC aNMC, Assignment aAssignment)
  {
    name = aName;
    address = aAddress;
    stars = aStars;
    pricePerNight = aPricePerNight;
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create hotel due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setStars(int aStars)
  {
    boolean wasSet = false;
    stars = aStars;
    wasSet = true;
    return wasSet;
  }

  public boolean setPricePerNight(int aPricePerNight)
  {
    boolean wasSet = false;
    pricePerNight = aPricePerNight;
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

  public int getStars()
  {
    return stars;
  }

  public int getPricePerNight()
  {
    return pricePerNight;
  }
  /* Code from template association_GetOne */
  public NMC getNMC()
  {
    return nMC;
  }
  /* Code from template association_GetOne */
  public Assignment getAssignment()
  {
    return assignment;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setNMC(NMC aNewNMC)
  {
    boolean wasSet = false;
    if (aNewNMC == null)
    {
      //Unable to setNMC to null, as hotel must always be associated to a nMC
      return wasSet;
    }
    
    Hotel existingHotel = aNewNMC.getHotel();
    if (existingHotel != null && !equals(existingHotel))
    {
      //Unable to setNMC, the current nMC already has a hotel, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    NMC anOldNMC = nMC;
    nMC = aNewNMC;
    nMC.setHotel(this);

    if (anOldNMC != null)
    {
      anOldNMC.setHotel(null);
    }
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
    NMC existingNMC = nMC;
    nMC = null;
    if (existingNMC != null)
    {
      existingNMC.setHotel(null);
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
            "address" + ":" + getAddress()+ "," +
            "stars" + ":" + getStars()+ "," +
            "pricePerNight" + ":" + getPricePerNight()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null");
  }
}