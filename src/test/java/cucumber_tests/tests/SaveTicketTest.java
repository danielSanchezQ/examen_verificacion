package cucumber_tests.tests;


import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import org.springframework.boot.SpringApplication;

/**
 * Created by Netwave on 18/05/2015.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/cucumber_tests/features/SaveTicket.feature",
                 format = "pretty")
public class SaveTicketTest
{

}
