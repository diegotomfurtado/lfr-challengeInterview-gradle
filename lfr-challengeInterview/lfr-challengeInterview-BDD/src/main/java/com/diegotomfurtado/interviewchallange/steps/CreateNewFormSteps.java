package com.diegotomfurtado.interviewchallange.steps;

import org.junit.After;
import org.openqa.selenium.WebDriver;

import com.diegotomfurtado.interviewchallange.pages.LiferayConfirmationPage;
import com.diegotomfurtado.interviewchallange.pages.LiferayFormsPage;
import com.diegotomfurtado.interviewchallange.utils.SetUp;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateNewFormSteps {

	private WebDriver browser;
	LiferayFormsPage liferayFormsPage = new LiferayFormsPage(browser);
	LiferayConfirmationPage liferayConfirmationPage = new LiferayConfirmationPage(browser);

	@After
	public void teardown() {
		browser.quit();
		browser.close();
	}

	@Given("^a user is on home page$")
	public void aUserIsOnHomePage() throws Throwable {

		browser = SetUp.setUp();
	}

	@When("^a user type anything on \"([^\"]*)\" name field$")
	public void aUserTypeAnythingOnNameField(String name) throws Throwable {

		liferayFormsPage.fillANameOnField(name);
	}

	@When("^a user choose any date from calendar$")
	public void aUserChooseAnyDateFromCalendar() throws Throwable {

		liferayFormsPage.chooseActualDateFromCalendarIcon();
	}

	@When("^a user type anything on \"([^\"]*)\" boxText field$")
	public void aUserTypeAnythingOnBoxTextField(String boxTextField) throws Throwable {

		liferayFormsPage.fillBodyOnTextArea(boxTextField);
	}

	@When("^a user click on Submit button$")
	public void aUserClickOnSubmitButton() throws Throwable {

		liferayFormsPage.clickButtonToSubmitOnForm();
	}

	@Then("^a message will display to user$")
	public void aMessageWillDisplayToUser() throws Throwable {

		liferayConfirmationPage.returnSuccessMessageHeaderLocator();
	}

}
