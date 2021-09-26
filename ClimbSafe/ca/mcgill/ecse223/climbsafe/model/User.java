/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 34 "../../../../../../model.ump"
// line 104 "../../../../../../model.ump"
// line 146 "../../../../../../model.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByUsername = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;
  private String name;
  private String password;

  //User Associations
  private NMC nMC;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, String aName, String aPassword, NMC aNMC)
  {
    name = aName;
    password = aPassword;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddNMC = setNMC(aNMC);
    if (!didAddNMC)
    {
      throw new RuntimeException("Unable to create user due to nMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      usersByUsername.remove(anOldUsername);
    }
    usersByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUsername(String aUsername)
  {
    return usersByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public NMC getNMC()
  {
    return nMC;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setNMC(NMC aNewNMC)
  {
    boolean wasSet = false;
    if (aNewNMC == null)
    {
      //Unable to setNMC to null, as user must always be associated to a nMC
      return wasSet;
    }
    
    User existingUser = aNewNMC.getUser();
    if (existingUser != null && !equals(existingUser))
    {
      //Unable to setNMC, the current nMC already has a user, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    NMC anOldNMC = nMC;
    nMC = aNewNMC;
    nMC.setUser(this);

    if (anOldNMC != null)
    {
      anOldNMC.setUser(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByUsername.remove(getUsername());
    NMC existingNMC = nMC;
    nMC = null;
    if (existingNMC != null)
    {
      existingNMC.setUser(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null");
  }
}