package com.diegotomfurtado.interviewchallenge.tests;

import static org.junit.Assert.assertTrue;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.diegotomfurtado.interviewchallenge.pages.LiferayFormsPage;
import com.diegotomfurtado.interviewchallenge.utils.SetUp;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InputInformation.csv")

public class FieldBoxMessageTest {

	private WebDriver browser;
	LiferayFormsPage liferayFormsPage = new LiferayFormsPage(browser);

	@Before
	public void setUpOpenBrowser() throws Exception{
		browser = SetUp.setUp();
	}

	@After
	public void teardown() throws Exception{
		browser.close();
	}

	@Test
	public void shouldDisplayMessageErrorWhenBoxAreaIsEmpty()
			throws InterruptedException {

		liferayFormsPage
				.fillANameOnField("Static Test for field boxMessage validation.")
				.chooseActualDateFromCalendarIcon()
				.fillBodyOnTextArea("            ")
				.clickButtonToSubmitOnForm();

		assertTrue("This field is required.".contains(liferayFormsPage.returnMessageTextErroMessage()));
		System.out.println(liferayFormsPage.returnMessageTextErroMessage());
	}

	@Test
	public void shouldValidateAlphanumericInput(
			@Param(name = "textArea") String textArea)
			throws InterruptedException {

		liferayFormsPage.fillBodyOnTextArea(textArea);
	}

	@Test
	@Ignore
	public void shouldValidateLoadTestAtBoxMessage(
			@Param(name = "textArea") String textArea)
			throws InterruptedException {

		liferayFormsPage
				.fillANameOnField("Static Test for field boxMessage validation.")
				.chooseActualDateFromCalendarIcon()
				.fillBodyOnTextArea(textArea)
				.clickButtonToSubmitOnForm();
	}

}
