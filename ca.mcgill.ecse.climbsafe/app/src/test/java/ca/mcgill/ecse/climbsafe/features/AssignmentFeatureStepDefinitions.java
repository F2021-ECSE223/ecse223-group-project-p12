package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.esce.climbsafe.controller.*;
import org.junit.Assert;

public class AssignmentFeatureStepDefinitions {

  private String error = "";

  ClimbSafeApplication climbSafe = null;

  /**
   * @author Cedric Barre, Theo Ghanem, Zachary Godden, Chris Hatoum, Habib Jarweh, Philippe
   *    *         Sarouphim Hochar
   *
   * This method implements the given clause: "Given the following ClimbSafe system exists".
   *
   *
   *@param dataTable Cucumber data table.
   */
  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> exClimbSafe = dataTable.asMaps(String.class, String.class);
    climbSafe = ClimbSafeApplication.getClimbSafe();

    climbSafe.setStartDate(Date.valueOf(exClimbSafe.get(0).get("startDate")));
    climbSafe.setNrWeeks(Integer.parseInt(exClimbSafe.get(0).get("nrWeeks")));
    climbSafe.setPriceOfGuidePerWeek(Integer.parseInt(exClimbSafe.get(0).get("priceOfGuidePerWeek")));
  }

  /**
   *
   * @param dataTable
   */
  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> equipmentList = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> e : equipmentList) {
      climbSafe.addEquipment(e.get("name"), e.get("weight"), e.get("pricePerWeek"));
    }
  }

  /**
   * @author Habib Jarweh.
   *
   * This method implement the given clause: "Given the following equipment bundles exist in the system".
   * It adds all of the equipment bundles to the application.
   *
   * @param dataTable Cucumber data table containg equipment bundles.
   */
  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> equipmentBundleList = dataTable.asMaps(String.class, String.class);
    // Add each equipment bundle to the application
    for (Map<String, String> e : equipmentBundleList) {
      climbSafe.addBundle(e.get("name"), e.get("discount"));
    }
  }

  /**
   * @author Philippe Sarouphim Hochar.
   *
   * This method implement the given clause: "Given the following guides exist in the system".
   * It adds all of the guides to the application.
   *
   * @param dataTable Cucumber data table containg guides.
   */
  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    // Parse data table to map list
    List<Map<String, String>> guideList = dataTable.asMaps(String.class, String.class);

    // Add each guide to the application
    for (Map<String, String> g : guideList) {
      climbSafe.addGuide(g.get("email"), g.get("password"), g.get("name"), g.get("emergencyContact"));
    }
  }

  /**
   * @author Habib Jarweh.
   *
   * This method implement the given clause: "Given the following members exist in the system".
   * It adds all of the members to the application.
   *
   * @param dataTable Cucumber data table containg members.
   */
  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> memberList = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> m : memberList) {
      climbSafe.addMember(m.get("email"), m.get("password"), m.get("name"),
              m.get("emergencyContact"), Integer.parseInt(m.get("nrWeeks")),
              Boolean.parseBoolean(m.get("guideRequired")),
              Boolean.parseBoolean(m.get("hotelRequired")));
    }
  }

  /**
   * @author Cedric Barre.
   *
   * This method implements the cucumber When clause: "When the administrator attempts to initiate
   * the assignment process.
   * It calls the assignment initiation routine in the Assignment controller class.
   *
   */
  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    AssignmentController.initiateAssignments();
  }

  /**
   * @author Cedric Barre
   *
   * This method implements the cucumber When clause: "the following assignments shall exist in
   * the system:"
   * It makes sure the specified assignments exist in the system by directly verifying the
   * model
   *
   * @param dataTable
   */
  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> assignmentList = dataTable.asMaps(String.class, String.class);
    for ( Map<String, String> a : assignmentLists) {
      Assert.assertTrue(assignmentExists(
              climbSafe.findMemberFromEmail(a.get("memberEmail")),
              climbSafe.findGuideFromEmail("guideEmail"),
              a.get("startWeek"),
              a.get("endWeek")));
    }
  }


  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /** @author Habib Jarweh
   *
   * Here, we make sure the amount of assignments in the system is the same as the number specified
   *
   * @param string Expected number of assignments in the climbsafe system instance in the form of a
   * string
   */
  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    Assert.assertEquals(Integer.parseInt(string), climbSafe.numberOfAssignments());
  }

  /**
   * @author Chris Hatoum
   * @param string
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {

    assertTrue(error.startsWith(string));
  }

  /**
   * @author Theo Ghanem
   *
   * This method implements the cucumber Given clause: "the following assignments exist in the system:".
   * It confirms the existence of an assignment.
   *
   * @param dataTable
   */
  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> assignmentsList = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> a : assignmentsList) {
      climbSafe.addEquipment(a.get("memberEmail"), a.get("guideEmail"), a.get("startWeek"), a.get("endWeek"));
    }
  }

  }

  /**
   * @author Philippe Sarouphim Hochar.
   *
   * This method implements the cucumber When clause: "When the administrator attempts to confirm
   * payment for {string} using authorization code {string}".
   * It confirms the payment of a given member, with an authorization code.
   *
   * @param string Name of the member who paid.
   * @param string2 Authorization code of the payment.
   */
  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String string, String string2){
    AssignmentController.payTrip(string, string2);
  }

  /**
   * @author Philippe Sarouphim Hochar.
   *
   * This method implements the cucumber Then clause: "Then the assignment for {string} shall
   * record the authorization code {string}".
   * It compares the member's assignment's payment's authorization code with the given one.
   *
   * @param string Member's email
   * @param string2 Authorization code
   */
  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String string,
      String string2) {
    assertEquals(string2,
            climbSafe.findMemberFromEmail(string).getAssignment().getAuthorizationCode());
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Philippe Sarouphim Hochar.
   *
   * This method implements the cucumber Then clause: "Then the member account with the email
   * {string} does not exist."
   * It checks if the given member exists in the system.
   *
   * @param string Member email
   */
  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String string) {
    assertNull(climbSafe.findMemberFromEmail(string));
  }

  /**
   * @author Chris Hatoum
   *
   * @param string
   */
  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String string) {
    Assert.assertEquals(Integer.parseInt(string), climbSafe.numberOfMembers());

  }

  /**
   * @author Theo Ghanem
   *
   * This method implements the cucumber When clause: "the error {string} shall be raised"
   * It throws the corresponding error message
   *
   * @param string Error message
   */
  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String string) {
    throw new Exception(string);
  }

  /**
   * @author Habib Jarweh
   *
   * This method implements the cucumber When clause: "the administrator attempts to cancel the trip for {string}"
   *
   * @param string Name of the member who cancelled
   *.
   */
  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String string) {
    // Write code here that turns the phrase above into concrete actions
    AssignmentController.cancelTrip(string);
  }

  /**
   * @author Chris Hatoum
   * @param string
   */
  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String string) {
    // Write code here that turns the phrase above into concrete actions

    throw new io.cucumber.java.PendingException();
  }

  /**
   *
   * This method implements the cucumber When clause: "the administrator attempts to cancel the trip for {string}".
   *
   * @author Chris Hatoum
   * @param string
   */
  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String string) {
    AssignmentController.finishTrip(string);
  }

  /**
   *
   *This method implement the cucumber Given clause: "the member with "<email>" is banned"
   * This method bans that member
   *
   * @author Zachary Godden
   * @param string - member's email
   */
  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String string) {
    climSafe.findMemberFromEmail(string).ban();
  }

  /**
   * @author Theo Ghanem not complete
   *
   * @param string
   * @param string2
   */
  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String string, String string2) {

    throw new io.cucumber.java.PendingException();
  }
  /**
   * @author Chris Hatoum
   * @param string
   */
  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String string) {

    AssignmentController.startTrips(Integer.valueOf(string));
  }

  /**
   * @author Philippe Sarouphim Hochar.
   *
   * This method implement the cucumber Given clause: "Given the member with {string} has cancelled
   * their trip".
   * It cancels the trip of a given member.
   *
   * @param string Member email
   */
  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String string) {
    climbSafe.findMemberFromEmail(string).getAssignment().cancel();
  }

  /**
   * @author Theo Ghanem
   *
   * This method implements the cucumber Given clause: ""
   *
   * @param string Member's email
   *
   */
  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String string) {


    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Cedric Barre
   *
   * This method is used to check if a specified assignment exists in the system
   *
   * @param m
   * @param g
   * @param startWeek
   * @param endWeek
   * @return
   */
  private boolean assignmentExists( Member m, Guide g, int startWeek, int endWeek ) {
    List<Assignment> allAssignments = climbSafe.getAssignments();
    for( Assignment a : allAssignments ) {
      if( a.getMember().equals(m) && a.getGuide().equals(g)
              && a.getStartWeek() == startWeek  && a.getEndWeek() == endWeek ) {
        return true;
      }
    }
    return false;
  }
}
