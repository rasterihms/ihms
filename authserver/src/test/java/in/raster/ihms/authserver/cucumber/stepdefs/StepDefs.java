package in.raster.ihms.authserver.cucumber.stepdefs;

import in.raster.ihms.authserver.AuthserverApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = AuthserverApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
