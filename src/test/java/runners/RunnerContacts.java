package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = "stepdefinitions",
        features = "src/test/resources/features/pets.feature",
        tags = "@getpetbyid"
)
public class RunnerContacts {
}