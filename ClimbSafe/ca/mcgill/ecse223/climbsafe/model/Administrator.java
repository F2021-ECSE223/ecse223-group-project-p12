/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 52 "../../../../../../model.ump"
// line 122 "../../../../../../model.ump"
public class Administrator
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Attributes
  private String username;
  private String password;

  //Administrator Associations
  private ClimbSafe climbSafe;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(ClimbSafe aClimbSafe)
  {
    username = "admin@nmc.nt";
    password = "admin";
    if (aClimbSafe == null || aClimbSafe.getAdministrator() != null)
    {
      throw new RuntimeException("Unable to create Administrator due to aClimbSafe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbSafe = aClimbSafe;
  }

  public Administrator()
  {
    username = "admin@nmc.nt";
    password = "admin";
    climbSafe = new ClimbSafe(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public ClimbSafe getClimbSafe()
  {
    return climbSafe;
  }

  public void delete()
  {
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
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbSafe = "+(getClimbSafe()!=null?Integer.toHexString(System.identityHashCode(getClimbSafe())):"null");
  }
}