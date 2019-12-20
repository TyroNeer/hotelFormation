package org.hotel;

import  org.junit.Assert.*;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.hotel.ENavigateur;
import org.hotel.OutilTechnique;

public class TestDragAndDrop {


	WebDriver driver;
	PageBooking page_booking;
	int n_snapshot=1;

	@Before
	public void setup() {
		driver = OutilTechnique.choisirNavigateur(ENavigateur.chrome);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	//	@After
	//	public void tearDown()
	//	{
	//		driver.quit();
	//	}


	@Test
	public void test() throws Exception {
		driver.get("http://localhost/TutorialHtml5HotelPhp/");
		assertEquals("HTML5 Hotel Room Booking (JavaScript/PHP)", driver.getTitle());

		page_booking = PageFactory.initElements(driver, PageBooking.class);

		Actions action = new Actions (driver);

		action.dragAndDrop(page_booking.resaCell, page_booking.target).build().perform();
		verifMessage("Update successful");

		action.moveToElement(page_booking.resaCell).build().perform();
		action.moveToElement(page_booking.croix_delete).click().build().perform();

		verifMessage("Deleted");
		


	}

	public void verifMessage(String message) throws Exception {
		Thread.sleep(1000);	
		assertTrue(page_booking.message_action.isDisplayed());
		assertTrue(page_booking.message_action.getText().contains(message));
		OutilTechnique.takeSnapShot(driver, ".\\src\\test\\"+message+"_"+n_snapshot+".png");
		Thread.sleep(7000);
		assertFalse(page_booking.message_action.isDisplayed());
		n_snapshot++;
	}
}
