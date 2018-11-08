package com.diegotomfurtado.interviewchallange.steps;

import static com.diegotomfurtado.interviewchallange.utils.SetUp.setUp;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.openqa.selenium.WebDriver;

import com.diegotomfurtado.interviewchallange.pages.LiferayFormsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckElementOnPageSteps {
	
	private WebDriver browser;
	String label;
	LiferayFormsPage liferayFormsPage = new LiferayFormsPage(browser);
	
	@After
	public void teardown() {
		browser.close();
		browser.quit();
	}
	
	@Given("^a user is on home page\\.$")
	public void aUserIsOnHomePage() throws Throwable {
		
		browser = setUp();
	}

	@When("^a user looks at the left side from the Forms page$")
	public void aUserLooksAtTheLeftSideFromTheFormsPage() throws Throwable {
		
		label = liferayFormsPage
				.isPartyRockLabelVisible();
	}

	@Then("^a user should see a message \"([^\"]*)\"$")
	public void aUserShouldSeeAMessage(String name) throws Throwable {

		assertThat(label, containsString(name));
	}
}
