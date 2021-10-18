package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;
import java.sql.Date;
import org.junit.Assert;

import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.controller.*;
import ca.mcgill.ecse.climbsafe.application.*;

public class P12StepDefinitions {

  private ClimbSafe climbSafe;
  String error;

  @Given("the following ClimbSafe system exists: \\(p12)")
  public void the_following_climb_safe_system_exists_p12( io.cucumber.datatable.DataTable dataTable ) {
    
	List<List<String>> exClimbSafe = dataTable.asLists(String.class);
	climbSafe = ClimbSafeApplication.getClimbSafe();
    climbSafe.setStartDate(Date.valueOf(exClimbSafe.get(1).get(0)));
	climbSafe.setNrWeeks(Integer.parseInt(exClimbSafe.get(1).get(1)));
	climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(exClimbSafe.get(1).get(2)));
	resetClimbSafe();
  }

  @Given("the following guides exist in the system: \\(p12)")
  public void the_following_guides_exist_in_the_system_p12( io.cucumber.datatable.DataTable dataTable ) {
    List<List<String>> guideList = dataTable.asLists(String.class);
    for( int i = 1; i < guideList.size(); i++ ) {
    	climbSafe.addGuide(guideList.get(i).get(0), guideList.get(i).get(1), 
    			guideList.get(i).get(2), guideList.get(i).get(3));
    }
  }

  @Given("the following members exist in the system: \\(p12)")
  public void the_following_members_exist_in_the_system_p12( io.cucumber.datatable.DataTable dataTable ) {
    List<List<String>> memberList = dataTable.asLists(String.class);
    for( int i = 1; i < memberList.size(); i++ ) {
    	climbSafe.addMember(memberList.get(i).get(0), memberList.get(i).get(1), 
    			memberList.get(i).get(2), memberList.get(i).get(3), 
    			Integer.parseInt(memberList.get(i).get(4)), 
    			Boolean.parseBoolean(memberList.get(i).get(5)), 
    			Boolean.parseBoolean(memberList.get(i).get(6)));
    }
  }

  @When("the admin attempts to delete the guide account linked to the {string} \\(p12)")
  public void the_admin_attempts_to_delete_the_guide_account_linked_to_the_p12(String string){
    try {
	  ClimbSafeFeatureSet1Controller.deleteGuide(string);
    } catch( Exception e ) {
    	error = e.getMessage();
    }
  }

  @Then("the guide account linked to the {string} shall not exist in the system \\(p12)")
  public void the_guide_account_linked_to_the_shall_not_exist_in_the_system_p12(String string) {
    Assert.assertNull(findGuideFromEmail(string));
  }

  @Then("the number of guides in the system is {string} \\(p12)")
  public void the_number_of_guides_in_the_system_is_p12(String string) {
    Assert.assertEquals(Integer.parseInt(string), climbSafe.numberOfGuides());
  }

  @Then("the member account linked to the {string} shall exist in the system \\(p12)")
  public void the_member_account_linked_to_the_shall_exist_in_the_system_p12(String string) {
    Assert.assertNotNull(findMemberFromEmail(string));
  }

  @Then("the number of guides in the system is {int} \\(p12)")
  public void the_number_of_guides_in_the_system_is_p12(Integer int1) {
    Assert.assertEquals(int1, Integer.valueOf(climbSafe.numberOfGuides()));
  }
  
  // Reset the lists of objects used for this batch of test
  private void resetClimbSafe() {
	  int numberOfGuides = climbSafe.getGuides().size();
	  int numberOfMembers = climbSafe.getMembers().size();
	  int numberOfHotels = climbSafe.getHotels().size();
	  
	  for( int i = 0; i < numberOfGuides; i++) {
		  climbSafe.getGuides().get(0).delete();
	  }
	  for( int i = 0; i < numberOfMembers; i++) {
		  climbSafe.getMembers().get(0).delete();
	  }
	  for( int i = 0; i < numberOfHotels; i++) {
		  climbSafe.getHotels().get(0).delete();
	  }
  }
  
  public Guide findGuideFromEmail(String email){
	  List<Guide> guideList = ClimbSafeApplication.getClimbSafe().getGuides();
	  for( Guide g : guideList ) {
	    if(g.getEmail().equals(email)) {
	      return g;
	    }
	  }
	  return null;
	}
	
  public Member findMemberFromEmail(String email){
	  List<Member> memberList = ClimbSafeApplication.getClimbSafe().getMembers();
	  for( Member m : memberList ) {
	    if(m.getEmail().equals(email)) {
	      return m;
	    }
	  }
	  return null;
	}
	  
  public Hotel findHotelFromName(String name){
	  List<Hotel> hotelList = ClimbSafeApplication.getClimbSafe().getHotels();
	  for( Hotel h : hotelList ) {
	    if(h.getName().equals(name)) {
	      return h;
	    }
	  }
	  return null;
	}
}