package Runners;

        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/feature_files/",
        plugin={"html:target/ReportsDestination", "pretty",}, tags={"@mirene"}, glue={"Step_definitions"})

public class TestRunner {
}
