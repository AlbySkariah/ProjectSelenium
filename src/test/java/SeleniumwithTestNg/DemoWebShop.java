package SeleniumwithTestNg;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DemoWebShop {
	WebDriver driver;

	public void testIntiatilse(String browser) {

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Testing\\Libraries\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Testing\\Libraries\\geckodriver.exe");
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
	@Parameters({"browser","base_url"})
	public void setUp(String browserName,String url) {
		testIntiatilse(browserName);
		driver.get(url);

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
	@Test(priority=1,enabled=true,description="verify title for obsqura",groups= {"Regression"})
	public void TC_001_verifyObsquraTitle() {
        driver.get("https://selenium.obsqurazone.com/index.php");
        String actualTitle = driver.getTitle();                 /** Get title**/
        String expectedTitle = "Obsqura Testing";
        System.out.println("testing............");
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    }
	@Test(priority=2,enabled=true,description="verify login",groups= {"Regression","Sanity"})
	public void TC_002_verifyLogin() {
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
@Test(priority=3,enabled=true,description="verify title from excel sheet",groups= {"Smoke"})
public void TC_004_verifyTitleFromExcelSheet() throws IOException {
    String actualTitle = driver.getTitle();                 /** Get title**/
    String excelPath = "\\src\\test\\resources\\TestData.xlsx";
    String sheetName = "Home";
    String expTitle = ExcelUtility.readStringData(excelPath, sheetName, 0,1);
    Assert.assertEquals(actualTitle, expTitle ,"Invalid data in the table");
}
@Test(dataProvider="InvalidCredentials")
public void TC_006_verifyLoginWithInvalidDatas(String userName, String pWord) {
    WebElement loinButton = driver.findElement(By.className("ico-login"));
    loinButton.click();
    WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
    email.sendKeys(userName);
    WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
    WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
    loginButton.click();
    WebElement errorMessage = driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
    String actualMessage = errorMessage.getText();
    String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.";
    Assert.assertEquals(expectedMessage, actualMessage, "Invalid error message");
    
}
@DataProvider(name="InvalidCredentials")
public Object[][] userCredentials() {
	Object[][] data = {{"alby1234@gmail.com" ,"asd123"},{"alby123@gmail.com" ,"asd12345"},{"alby12345@gmail.com","qwer12"}};
	return data;
}
@Test
public void TC_011_verifyRegisterPageFromExcelSheetAndMailAsRandomGenrator() throws IOException {
    String actualTitle = driver.getTitle();
    List<ArrayList<String>> data = ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "Register");
    String expectedTitle = data.get(1).get(6);
    Assert.assertEquals(actualTitle, expectedTitle, "Invalid Title found");
    WebElement reg1 = driver.findElement(By.xpath("//a[@class='ico-register']"));
    reg1.click();
    List<WebElement> gender = driver.findElements(By.xpath("//input[@name='Gender']"));
    selectGender("F", gender);
    WebElement firstName = driver.findElement(By.id("FirstName"));
    String fName= data.get(1).get(1);
    firstName.sendKeys(fName);
    WebElement lastName = driver.findElement(By.id("LastName"));
    String lName = data.get(1).get(2);
    lastName.sendKeys(lName);
    WebElement emailField = driver.findElement(By.id("Email"));
    String emailID = RandomDataUtility.getRandomEmail();
    emailField.sendKeys(emailID);
    WebElement passwordField = driver.findElement(By.id("Password"));
    String pword= data.get(1).get(4);
    passwordField.sendKeys(pword);
    WebElement passwordConfirm = driver.findElement(By.id("ConfirmPassword"));
    String confirmPword= data.get(1).get(4);
    passwordConfirm.sendKeys(confirmPword);
    WebElement register = driver.findElement(By.id("register-button"));
    register.click();
    WebElement userAccount = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
    String actualEmail = userAccount.getText();
    Assert.assertEquals(actualEmail, emailID, "Registration failed");
}
@Test
@Parameters({"uName","pWord"})
public void TC_010_verifyLoginWithValidDatasUsingParameters(String userName, String pWord) {
    WebElement loinButton = driver.findElement(By.className("ico-login"));
    loinButton.click();
    WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
    email.sendKeys(userName);
    WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
    password.sendKeys(pWord);
    WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
    loginButton.click();
    WebElement logoutButton = driver.findElement(By.className("ico-logout"));
    logoutButton.click();
}
}


