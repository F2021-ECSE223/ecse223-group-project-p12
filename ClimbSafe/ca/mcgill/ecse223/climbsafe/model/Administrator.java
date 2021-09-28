/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 56 "../../../../../../model.ump"
// line 129 "../../../../../../model.ump"
public class Administrator
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Administrator Attributes
  private String username;
  private String password;

  //Administrator Associations
  private NMC nMC;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Administrator(NMC aNMC)
  {
    username = "admin@nmc.nt";
    password = "admin";
    if (aNMC == null || aNMC.getAdministrator() != null)
    {
      throw new RuntimeException("Unable to create Administrator due to aNMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    nMC = aNMC;
  }

  public Administrator(int aGuideWeeklyRateForNMC, ClimbSafe aClimbSafeForNMC)
  {
    username = "admin@nmc.nt";
    password = "admin";
    nMC = new NMC(aGuideWeeklyRateForNMC, this, aClimbSafeForNMC);
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
  public NMC getNMC()
  {
    return nMC;
  }

  public void delete()
  {
    NMC existingNMC = nMC;
    nMC = null;
    if (existingNMC != null)
    {
      existingNMC.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "nMC = "+(getNMC()!=null?Integer.toHexString(System.identityHashCode(getNMC())):"null");
  }
}