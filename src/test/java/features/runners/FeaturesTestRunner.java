package features.runners;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"pretty"}, dryRun = true, strict = false, monochrome = true,
        features = "src/test/resources/features",
        glue = "src/test/java/features/stepdefs")

public class FeaturesTestRunner { // will run all features found on the classpath in the same package as this class

}

