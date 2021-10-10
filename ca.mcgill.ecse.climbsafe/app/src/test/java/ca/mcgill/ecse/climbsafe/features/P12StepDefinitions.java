package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;
import java.sql.Date;
import org.junit.Assert;

import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.controller.*;

public class P12StepDefinitions {

  private ClimbSafe climbSafe;

  @Given("the following ClimbSafe system exists: \\(p12)")
  public void the_following_climb_safe_system_exists_p12(List<String> arguments) {
    String[] ymd = arguments.get(0).split("-");
    @SuppressWarnings("deprecation")
    Date date = new Date(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]));
    climbSafe = new ClimbSafe(date, Integer.parseInt(arguments.get(1)), Integer.parseInt(arguments.get(2)));
  }

  @Given("the following guides exist in the system: \\(p12)")
  public void the_following_guides_exist_in_the_system_p12(List<String> arguments) {
    if (climbSafe == null) throw new NullPointerException();
    climbSafe.addGuide(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3));
  }

  @Given("the following members exist in the system: \\(p12)")
  public void the_following_members_exist_in_the_system_p12(List<String> arguments) {
    if (climbSafe == null) throw new NullPointerException();
    climbSafe.addMember(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3),
            Integer.parseInt(arguments.get(4)), Boolean.parseBoolean(arguments.get(5)), Boolean.parseBoolean(arguments.get(6)));
  }

  @When("the admin attempts to delete the guide account linked to the {string} \\(p12)")
  public void the_admin_attempts_to_delete_the_guide_account_linked_to_the_p12(String string){
    Guide g = findGuideFromEmail(string);
    if( g == null) {
      return;
    }
    ClimbSafeFeatureSet1Controller.deleteGuide(g);

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

  private Guide findGuideFromEmail(String email){
    List<Guide> guideList = climbSafe.getGuides();
    for( Guide g : guideList ) {
      if(g.getEmail().equals(email)) {
        return g;
      }
    }
    return null;
  }

  private Member findMemberFromEmail(String email){
    List<Member> memberList = climbSafe.getMembers();
    for( Member m : memberList ) {
      if(m.getEmail().equals(email)) {
        return m;
      }
    }
    return null;
  }
}