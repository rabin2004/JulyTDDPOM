package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Main {
	public static WebDriver driver;
	public static Properties prop;
	
	public void intilization() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\User\\eclipse-workspace\\JulyTDDPOM\\"
					+ "src\\propertiesPkg\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(prop.getProperty("chromeKey"), prop.getProperty("chromePath"));
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("internet explorer") || browserName.equalsIgnoreCase("ie")) {
			System.setProperty(prop.getProperty("IEKey"), prop.getProperty("IEPath"));
			driver = new InternetExplorerDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
			System.setProperty(prop.getProperty("FFKey"), prop.getProperty("FFPath"));
			driver = new FirefoxDriver();
		}
		else {
			System.err.println(browserName.toUpperCase()+" "+"is not supported!");
		}
		
		// outside cross browser testing - common steps
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
	}
	
	public void tearDown() {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("C:\\Users\\User\\eclipse-workspace\\JulyTDDPOM\\src\\"
					+ "Screenshots\\pageClick.png"));
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}

}
