package dataPkg;

import org.testng.annotations.DataProvider;

public class LoginData {
	
	@DataProvider
	public Object[][] positiveLoginData() {
		Object[][] data = {	{"test", "123"},
							{"test12", "123"},
							{"test123", "123"}};
		return data;
	}
	
	@DataProvider
	public Object[][] negativeLoginData() {
		Object[][] data = {	{"tommy123", "12345"},
							{"johnny123", "123"},
							{"Micheal1234", "123"}};
		return data;
	}

}
