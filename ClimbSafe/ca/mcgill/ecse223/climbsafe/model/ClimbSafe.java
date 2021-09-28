/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;

// line 4 "../../../../../../model.ump"
// line 83 "../../../../../../model.ump"
// line 92 "../../../../../../model.ump"
public class ClimbSafe
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClimbSafe Associations
  private NMC nMC;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClimbSafe(NMC aNMC)
  {
    if (aNMC == null || aNMC.getClimbSafe() != null)
    {
      throw new RuntimeException("Unable to create ClimbSafe due to aNMC. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    nMC = aNMC;
  }

  public ClimbSafe(int aGuideWeeklyRateForNMC, Administrator aAdministratorForNMC)
  {
    nMC = new NMC(aGuideWeeklyRateForNMC, aAdministratorForNMC, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
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

}