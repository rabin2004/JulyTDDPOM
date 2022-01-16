package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Main;
import pageFactory.HomePage;
import pageFactory.LoginSuccessPage;

public class LoginFunctionalityTest extends Main{
	HomePage hp;
	LoginSuccessPage lsp;

	@BeforeMethod
	public void loadBrowser() {
		intilization();
		hp = new HomePage();
		lsp = new LoginSuccessPage();
	}
	
	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}
	
	@Test(dataProviderClass=dataPkg.LoginData.class , dataProvider="positiveLoginData")
	public void positiveLoginFunctionalityTest(String username, String password) {
		// Before TDD POM framework:
//		driver.findElement(By.name("userName")).sendKeys("tommy123");
//		driver.findElement(By.name("password")).sendKeys("");
//		driver.findElement(By.name("submit")).click();
	//	
//		WebElement loginErrorMsg = driver.findElement(By.xpath(""
//				+ "//input[@name='password']/following-sibling::span"));
		
		// With TDD POM framework
		hp.enterUserName(username);
		hp.enterPassword(password);
		hp.clickSubmitBtn();
		Assert.assertEquals(lsp.captureLoginSuccessMsg(), prop.getProperty("ExpectedLoginSuccessMsg"));
		Assert.assertEquals(lsp.captureLoginSuccessPageTitle(), prop.getProperty("ExpectedPageTitle"));
	}
	
	@Test(dataProviderClass=dataPkg.LoginData.class , dataProvider="negativeLoginData")
	public void negativeLoginFunctionalityTest(String username, String password) {
		hp.enterUserName(username);
		hp.enterPassword(password);
		hp.clickSubmitBtn();
		Assert.assertEquals(hp.captureLoginErrorMsg(), prop.getProperty("ExpectedLoginErrorMsg"));
	}

}
