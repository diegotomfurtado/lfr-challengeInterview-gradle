package com.diegotomfurtado.interviewchallenge.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.diegotomfurtado.interviewchallenge.pages.LiferayFormsPage;
import com.diegotomfurtado.interviewchallenge.utils.SetUp;

public class FieldDateTest {

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

	/*
	 * Test requirements from Liferay
	 */

	@Test
	public void shouldDisplayMessageErrorWhenDateAreaIsEmpty()
			throws InterruptedException {

		findCalendar("       ");
		liferayFormsPage
				.fillANameOnField("Static Test for field DATE validation.")
				.fillBodyOnTextArea("Static Test for field DATE validation.")
				.clickButtonToSubmitOnForm();

		assertEquals("This field is required.", liferayFormsPage.returnMessageErroFromDateArea());
		System.out.println(liferayFormsPage.returnMessageErroFromDateArea());
	}

	/*
	 * The correct validation is missing - the message is default for anything
	 */
	@Test
	public void shouldValidateAlphanumericInput()
			throws InterruptedException {

		findCalendar("testString");
		liferayFormsPage
				.fillANameOnField("Static Test for field DATE validation.")
				.fillBodyOnTextArea("Static Test for field DATE validation.")
				.clickButtonToSubmitOnForm();

		assertEquals("This field is required.", liferayFormsPage.returnMessageErroFromDateArea());
		
		System.out.println("Should have another type of message, like: Invalid Date");
	}
	

	/*
	 * There are BUGs: It's accepting different date formats However, I will not
	 * setup as bug, because I haven't all requirements necessary
	 */

	/*
	 * this test should fail purposely
	 */

	@Test
	public void shouldValidateNumbersInput()
			throws InterruptedException {
		
		findCalendar("1234567890");
		liferayFormsPage
		.fillANameOnField("Static Test for field DATE validation.")
		.fillBodyOnTextArea("Static Test for field DATE validation.")
		.clickButtonToSubmitOnForm();
		
		assertEquals("This field is required.", liferayFormsPage.returnMessageErroFromDateArea());
	}
	
	/*
	 * this test should fail purposely
	 */
	
	@Test  
	public void shouldValidateDifferentFormatDateInput()
			throws InterruptedException {

		findCalendar("08/01/167");
		String returnFeedback = liferayFormsPage
				.fillANameOnField("Static Test for field DATE validation.")
				.fillBodyOnTextArea("Static Test for field DATE validation.")
				.clickButtonToSubmitOnForm()
				.returnSuccessMessageHeaderLocator();

		assertTrue(returnFeedback, returnFeedback.isEmpty());

	}

	/*
	 * this test should fail purposely
	 */

	@Test 
	public void shouldValidateWhenTheNetworkIsDown()
			throws InterruptedException {

		findCalendar("testingNoNetwork");
		liferayFormsPage
				.fillANameOnField("Static Test for field DATE validation.")
				.fillBodyOnTextArea("Static Test for field DATE validation.")
				.clickButtonToSubmitOnForm();

		Assert.assertEquals("There was an error when trying to validate your form.",
				liferayFormsPage.returnErroMessageWithoutNetwork());
	}
	
	@Test
	public void testInputOnDateAreaFromCalendarIcon(){
		liferayFormsPage.chooseActualDateFromCalendarIcon();
		
	}

	public void findCalendar(String calendar) {
		liferayFormsPage.inputFakeDateToTestMethodStatic(calendar);
	}
	
}
