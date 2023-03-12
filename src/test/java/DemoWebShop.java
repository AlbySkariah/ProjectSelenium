import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
//import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class DemoWebShop {
	WebDriver driver;

	public void testIntiatilse(String browser) {

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Libraries\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			try {
				throw new Exception("Invalid browser");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@BeforeMethod
	public void setUp() {
		testIntiatilse("chrome");

	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
		}
//	        driver.close();
		driver.quit();
	}
	@Test
	public void TC_001_verifyObsquraTitle() {
        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();                 /** Get title**/
        String expectedTitle = "Obsqura Testing";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }
	@Test
	public void TC_002_verifyLogin() {
		driver.get("https://demowebshop.tricentis.com/");
		WebElement loinMenu = driver.findElement(By.className("ico-login"));
        loinMenu.click();
        String mailId ="alby123@gmail.com";
		WebElement email=driver.findElement(By.name("Email"));
		email.sendKeys(mailId);
		WebElement password=driver.findElement(By.name("Password"));
		password.sendKeys("asd123");
		WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        loginButton.click();
        WebElement userAccount = driver.findElement(By.xpath("(//a[@class='account'])[1]"));
        String actMail = userAccount.getText();
        Assert.assertEquals(mailId,actMail ,"Login failed");
	}
//	@Test
//	public void TC_003_verifyRegistration() {
//		driver.get("https://demowebshop.tricentis.com/");
//		WebElement reg1 = driver.findElement(By.xpath("//a[@class='ico-register']"));
//        reg1.click();
//        List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
//        selectGender("F", gender);
//        WebElement firstName = driver.findElement(By.id("FirstName"));
//        firstName.sendKeys("alby");
//        WebElement lastName = driver.findElement(By.id("LastName"));
//        lastName.sendKeys("skariah");
//        WebElement emailField = driver.findElement(By.id("Email"));
//        String email = "alby@gmail.com";
//        emailField.sendKeys(email);
//        WebElement passwordField = driver.findElement(By.id("Password"));
//        passwordField.sendKeys("alby123");
//        WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
//        passwordConfirm.sendKeys("alby123");
//        WebElement register = driver.findElement(By.id("register-button"));
//        register.click();
//        WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
//        String actualEmail = userAccount.getText();
//        Assert.assertEquals(actualEmail, email, "Registration failed");
//	}
@Test
public void selectGender(String gen, List<WebElement> gender) {
    for (int i = 0; i < gender.size(); i++) {
        String genderValue = gender.get(i).getAttribute("value");
        if (genderValue.equals(gen)) {
            gender.get(i).click();

        }
    }
}
}

