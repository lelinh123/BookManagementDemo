package com.cmc.demo.cucumber.controller.command;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/cmc/demo/cucumber/controller/feature/command")
public class ControllerCommandTest {

}
