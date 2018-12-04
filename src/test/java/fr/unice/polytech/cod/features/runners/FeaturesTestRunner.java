package fr.unice.polytech.cod.features.runners;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"pretty"  ,"html:Folder_Name" ,
        "json:Folder_Name/cucumber.json" ,
        "junit:Folder_Name/cucumber.xml",}, dryRun = true, strict = false, monochrome = true,
        features = "src/test/resources/fr/unice/polytech/cod/features",
        glue = "src/test/java/fr/unice/polytech/cod/features/stepdefs")


public class FeaturesTestRunner { // will run all features found on the classpath in the same package as this class

}

