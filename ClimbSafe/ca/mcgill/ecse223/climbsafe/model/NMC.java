/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse223.climbsafe.model;
import java.util.*;

// line 4 "../../../../../../model.ump"
// line 78 "../../../../../../model.ump"
public class NMC
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NMC Associations
  private User user;
  private Hotel hotel;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NMC()
  {}

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }

  public boolean hasUser()
  {
    boolean has = user != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Hotel getHotel()
  {
    return hotel;
  }

  public boolean hasHotel()
  {
    boolean has = hotel != null;
    return has;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (user != null && !user.equals(aNewUser) && equals(user.getNMC()))
    {
      //Unable to setUser, as existing user would become an orphan
      return wasSet;
    }

    user = aNewUser;
    NMC anOldNMC = aNewUser != null ? aNewUser.getNMC() : null;

    if (!this.equals(anOldNMC))
    {
      if (anOldNMC != null)
      {
        anOldNMC.user = null;
      }
      if (user != null)
      {
        user.setNMC(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setHotel(Hotel aNewHotel)
  {
    boolean wasSet = false;
    if (hotel != null && !hotel.equals(aNewHotel) && equals(hotel.getNMC()))
    {
      //Unable to setHotel, as existing hotel would become an orphan
      return wasSet;
    }

    hotel = aNewHotel;
    NMC anOldNMC = aNewHotel != null ? aNewHotel.getNMC() : null;

    if (!this.equals(anOldNMC))
    {
      if (anOldNMC != null)
      {
        anOldNMC.hotel = null;
      }
      if (hotel != null)
      {
        hotel.setNMC(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
      existingUser.setNMC(null);
    }
    Hotel existingHotel = hotel;
    hotel = null;
    if (existingHotel != null)
    {
      existingHotel.delete();
      existingHotel.setNMC(null);
    }
  }

}