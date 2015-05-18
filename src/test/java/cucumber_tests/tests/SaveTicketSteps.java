package cucumber_tests.tests;

import app.DatabaseTalkie;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;

/**
 * Created by Netwave on 18/05/2015.
 */
public class SaveTicketSteps
{
    DatabaseTalkie dbt = new DatabaseTalkie();
    HashMap<String, String> hm = new HashMap<String, String>();
    boolean res;
    String to_retrieve;
    String retrieved;

    @Given("^a ticket with name '(.+)', data '(.+)', password '(.+)'$")
    public void a_ticket_with_name_cucumber_test_data_cucumber_data_password_cucumber(final String name, final String data, final String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        hm.put("name", name);
        hm.put("data", data);
        hm.put("pwd", password);
        to_retrieve = data;
    }

    @When("^the user save$")
    public void the_user_save() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        res = dbt.store(hm.get("name"), hm.get("data"), hm.get("pwd"));
    }

    @When("^the user retrieves with name '(.+)', password '(.+)'$")
    public void the_user_retrieves_with_name_cucumber_test_password_cucumber(final String name, final String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        retrieved = dbt.retrieve(name, password);
    }

    @Then("^the user gets the data stored$")
    public void the_user_gets_the_data_stored() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        assert retrieved.equals(to_retrieve);
    }

}
