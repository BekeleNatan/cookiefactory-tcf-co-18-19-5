package fr.unice.polytech.cod.ComportmentTesting;

import fr.unice.polytech.cod.Franchise;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class FeaturesTest{ // will run all features found on the classpath in the same package as this class
    static void initialiseStores(Franchise franchise, int nbrStore){
        for (int i =0 ; i < nbrStore ; i++){
            franchise.addStore("Store"+i);
        }
    }
}
