package fr.unice.polytech.cod;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class FeaturesTestRunner { // will run all features found on the classpath in the same package as this class
    public static void initialiseStores(Franchise franchise, int nbrStore) {

    }
}

