package features.runners;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = "")

public class FeaturesRunnerTest { // will run all features found on the classpath in the same package as this class

}

