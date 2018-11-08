package com.diegotomfurtado.interviewchallenge.tests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.diegotomfurtado.interviewchallenge.pages.LiferayFormsPage;
import com.diegotomfurtado.interviewchallenge.utils.SetUp;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InputInformation.csv")

public class CreateNewFormTest { 

	private WebDriver browser;
	LiferayFormsPage liferayFormsPage = new LiferayFormsPage(browser);

	@Before
	public void setUpOpenBrowser() {
		browser = SetUp.setUp();
	}

	@After
	public void teardown() {
		browser.close();
	}

	@Test
	public void testToValidateIfContainsPartyRockLabel() {

		String labelName = liferayFormsPage.isPartyRockLabelVisible();
		assertThat(labelName, containsString("party rock"));
	}
	
	@Test
	public void testToSendAFormWithoutFieldsFilled() throws InterruptedException {
		
		liferayFormsPage.clickButtonToSubmitOnForm();
		
		assertTrue("This field is required.".contains(liferayFormsPage.returnMessageErroFromNameArea()));
		
		assertTrue("This field is required.".contains(liferayFormsPage.returnMessageErroFromDateArea()));
		
		assertTrue("This field is required.".contains(liferayFormsPage.returnMessageTextErroMessage()));
	}
	
	@Test
	public void testToValidateEndToEndProcess(@Param(name = "name") String name,
			@Param(name = "textArea") String textArea)
			throws InterruptedException {

		String returnFeedback = liferayFormsPage
				.fillANameOnField(name)
				.chooseActualDateFromCalendarIcon()
				.fillBodyOnTextArea(textArea)
				.clickButtonToSubmitOnForm()
				.returnSuccessMessageHeaderLocator();

		assertEquals("Informações enviadas", returnFeedback);
	}

}
