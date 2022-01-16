package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Main;

public class HomePage extends Main{ // create inheritance with Main class
	// 1. Create webElement -> @FindBy
	@FindBy(name="userName") // driver.findElement(By.name("userName"));
	WebElement userNameTxtField;
	@FindBy(name="password")
	WebElement passwordTxtField;
	@FindBy(name="submit")
	WebElement submitBtn;
	@FindBy(xpath="//input[@name='password']/following-sibling::span")
	WebElement loginErrorMsg;
	
	// 2. initialize webElement to the driver instance -> in constructor
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	// 3. methods -> Action for each webElement
	public void enterUserName(String username) {
		userNameTxtField.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordTxtField.sendKeys(password);
	}
	
	public void clickSubmitBtn() {
		submitBtn.click();
	}
	
	public String captureLoginErrorMsg() {
		String msg = loginErrorMsg.getText().trim();
		return msg;
	}

}
