/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;
import java.sql.Date;

// line 49 "../../../../../../model.ump"
// line 99 "../../../../../../model.ump"
// line 151 "../../../../../../model.ump"
public class Administrator extends User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Administrator theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Attributes
  private String username;
  private String password;

  //Administrator Associations
  private ClimbingSeason climbingSeason;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private Administrator(String aUsername, String aPassword, NMC aNMC)
  {
    super(aUsername, aPassword, aNMC);
    username = "admin@nmc.nt";
    password = "admin";
  }

  public static Administrator getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new Administrator();
    }
    return theInstance;
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
  /* Code from template attribute_Set_subclass */
  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    if (!canSetUsername) { return false; }
      wasSet = super.setUsername(aUsername);
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
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }

  public boolean hasClimbingSeason()
  {
    boolean has = climbingSeason != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setClimbingSeason(ClimbingSeason aNewClimbingSeason)
  {
    boolean wasSet = false;
    if (climbingSeason != null && !climbingSeason.equals(aNewClimbingSeason) && equals(climbingSeason.getAdministrator()))
    {
      //Unable to setClimbingSeason, as existing climbingSeason would become an orphan
      return wasSet;
    }

    climbingSeason = aNewClimbingSeason;
    Administrator anOldAdministrator = aNewClimbingSeason != null ? aNewClimbingSeason.getAdministrator() : null;

    if (!this.equals(anOldAdministrator))
    {
      if (anOldAdministrator != null)
      {
        anOldAdministrator.climbingSeason = null;
      }
      if (climbingSeason != null)
      {
        climbingSeason.setAdministrator(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = null;
    if (existingClimbingSeason != null)
    {
      existingClimbingSeason.delete();
      existingClimbingSeason.setAdministrator(null);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "climbingSeason = "+(getClimbingSeason()!=null?Integer.toHexString(System.identityHashCode(getClimbingSeason())):"null");
  }
}