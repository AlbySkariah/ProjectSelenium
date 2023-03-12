package SeleniumwithTestNg;

import java.awt.AWTException;
import java.awt.Dimension;

import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Seleniumcommands {
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
	public void TC_001_verifyobsquraTitle() {
		driver.get("https://selenium.obsqurazone.com/index.php");
		String actualTitle = driver.getTitle();
		String expectedTitle = "Obsqura Testing";
		Assert.assertEquals(actualTitle, expectedTitle, "Invalid title found");

	}

	@Test
	public void TC_002_verifyTwoInputFieldMessage() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement A = driver.findElement(By.id("value-a"));
		A.sendKeys("6");
		WebElement B = driver.findElement(By.id("value-b"));
		B.sendKeys("2");
		WebElement total = driver.findElement(By.id("button-two"));
		total.click();
		WebElement totalAB = driver.findElement(By.id("message-two"));
		String actualResult = totalAB.getText();
		String expectedResult = "Total A + B : 8";
		Assert.assertEquals(actualResult, expectedResult, "Invalid result");
	}

	@Test
	public void TC_03_verifyEmptyFieldValidation() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement formsubmit = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/div/ul/li[5]/a"));
		formsubmit.click();
		WebElement submitbutton = driver
				.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/button"));
		submitbutton.click();
		WebElement firstname = driver
				.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[1]/div[1]/div[1]"));
		String actualmsg = firstname.getText();
		String expectedmsg = "Please enter First name.";
		Assert.assertEquals(actualmsg, expectedmsg, "Not displayed");
		WebElement lastname = driver
				.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[1]/div[2]/div[1]"));
		String actuallastname = lastname.getText();
		String expectedlastname = "Please enter Last name.";
		Assert.assertEquals(actuallastname, expectedlastname, "Not displayed");
		WebElement username = driver.findElement(
				By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[1]/div[3]/div/div[2]"));
		String actualusername = username.getText();
		String expectedusername = "Please choose a username.";
		Assert.assertEquals(actualusername, expectedusername);
		WebElement city = driver
				.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[1]/div[1]"));
		String actualcity = city.getText();
		String expectedcity = "Please provide a valid city.";
		Assert.assertEquals(actualcity, expectedcity);
		WebElement state = driver
				.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[2]/div[1]"));
		String actualstate = state.getText();
		String expectedstate = "Please provide a valid state.";
		Assert.assertEquals(actualstate, expectedstate);
		WebElement zip = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[3]/div[1]"));
		String actualzip = zip.getText();
		String expectedzip = "Please provide a valid zip.";
		Assert.assertEquals(actualzip, expectedzip);
		WebElement checkbox = driver
				.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[3]/div/div"));
		String actualcheckbox = checkbox.getText();
		String expectedcheckbox = "You must agree before submitting.";
		Assert.assertEquals(actualcheckbox, expectedcheckbox);

	}

	@Test
	public void TC_04_verifyEmptyStateCityfiled() {
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		WebElement formsubmit = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/div/ul/li[5]/a"));
		formsubmit.click();
		WebElement firstname = driver.findElement(By.xpath("//*[@id=\"validationCustom01\"]"));
		firstname.sendKeys("asha");
		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"validationCustom02\"]"));
		lastname.sendKeys("paniker");
		WebElement username = driver.findElement(By.xpath("//*[@id=\"validationCustomUsername\"]"));
		username.sendKeys("asha12");
		WebElement zip = driver.findElement(By.xpath("//*[@id=\"validationCustom03\"]"));
		zip.sendKeys("456987");
		WebElement submitbutton = driver
				.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/button"));
		submitbutton.click();
//   	WebElement city=driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[1]/div[1]"));
//    String actualcity=city.getText();
//    String expectedcity="Please provide a valid city.";
//    Assert.assertEquals(actualcity, expectedcity);
		WebElement state = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div[2]/form/div[2]/div[2]/div[1]"));
		String actualstate = state.getText();
		String expectedstate = "Please provide a valid state.";
		Assert.assertEquals(actualstate, expectedstate);
	}

	@Test
	public void TC_04_verifysuccessfulformsubmission() {
		driver.get("https://selenium.obsqurazone.com/form-submit.php");
		WebElement firstNameFiled = driver.findElement(By.xpath("//input[@id='validationCustom01']"));
		firstNameFiled.sendKeys("alby");
		WebElement lastNameFiled = driver.findElement(By.xpath("//input[@id='validationCustom02']"));
		lastNameFiled.sendKeys("Skariah");
		WebElement userNameFiled = driver.findElement(By.xpath("//input[@id='validationCustomUsername']"));
		userNameFiled.sendKeys("alby123");
		WebElement cityFiled = driver.findElement(By.xpath("//input[@id='validationCustom03']"));
		cityFiled.sendKeys("kochi");
		WebElement stateFiled = driver.findElement(By.xpath("//input[@id='validationCustom04']"));
		stateFiled.sendKeys("Ernakulam");
		WebElement zipFiled = driver.findElement(By.xpath("//input[@id='validationCustom05']"));
		zipFiled.sendKeys("45687");
		WebElement checkbox = driver.findElement(By.xpath("//input[@class='form-check-input']"));
		checkbox.click();
		WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submit.click();
	}

	@Test
	public void TC_05_verifyQuitAndClose() {
		driver.get("https://demo.guru99.com/popup.php");
		WebElement clickHere = driver.findElement(By.xpath("//a[text()='Click Here']"));
		clickHere.click();
	}

	@Test

	public void TC_06_verifyNavigateTo() {
		driver.navigate().to("https://demowebshop.tricentis.com");
	}

	@Test
	public void TC_07_verifyRefresh() {
		driver.get("https://demowebshop.tricentis.com");
		WebElement emailFiled = driver.findElement(By.xpath("//input[@id='newsletter-email']"));
		emailFiled.sendKeys("asd@gmail.com");
		driver.navigate().refresh();

	}
	@Test
	public void TC_08_verifyForwardAndBackward() throws InterruptedException {
		driver.get("https://demowebshop.tricentis.com");
		WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
		login.click();
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().forward();
	}
	@Test
	public void TC_09_isDisplayed() {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement submitField = driver.findElement(By.xpath("//input[@id='subject']"));
		submitField.sendKeys("Selenium");
		boolean status=submitField.isDisplayed();
		System.out.println(status);
		Assert.assertTrue(status, "Submit field is not dispalyed");
	}
	@Test
	public void TC_10_isSelected() {
		driver.get("https://selenium.obsqurazone.com/check-box-demo.ph");
		WebElement checkbox= driver.findElement(By.xpath("//input[@id='gridCheck']"));
		boolean statusBeforeClick=checkbox.isSelected();
		System.out.println(statusBeforeClick);
		Assert.assertFalse(statusBeforeClick, "checkbox is selected");
		checkbox.click();
		boolean statusAfterClick=checkbox.isSelected();
		Assert.assertTrue(statusAfterClick, "checkbox is not selected");	
	}
	@Test
	public void TC_11_isEnabled()throws InterruptedException {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
        WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
        boolean status = submitButton.isEnabled();
        System.out.println(status);
        Assert.assertTrue(status, "submit button not enabled");
        org.openqa.selenium.Point point = submitButton.getLocation();
        System.out.println(point.x + "," + point.y);
        org.openqa.selenium.Dimension dim = submitButton.getSize();
        System.out.println(dim.height + "," + dim.width);
        String backgroundColor = submitButton.getCssValue("background-color");
        System.out.println(backgroundColor);
        WebElement element = driver.findElement(By.tagName("input"));
        System.out.println(element);
        List<WebElement> elements = driver.findElements(By.tagName("input"));
        System.out.println(elements);
        submitButton.submit();
	}
	@Test
	public void TC_12_veriyLogin() throws InterruptedException{
		driver.get("https://phptravels.com/demo/");
		  WebElement firstNameField = driver.findElement(By.xpath("//input[@name=\"first_name\"]"));
		  firstNameField.sendKeys("Virat");
		  WebElement lastNameField = driver.findElement(By.xpath("//input[@name=\"last_name\"]"));
		  lastNameField.sendKeys("kohli");
		  WebElement businessNameField = driver.findElement(By.xpath("//input[@name=\"business_name\"]"));
		  businessNameField.sendKeys("VK company");
		  WebElement emailField = driver.findElement(By.xpath("//input[@name=\"email\"]"));
		  emailField.sendKeys("vk@gmail.com");
		  WebElement num1= driver.findElement(By.xpath("//span[@id=\"numb1\"]"));
		 String value= num1.getText();
		 int i=Integer.parseInt(value); 
		// System.out.println("value = " + value);
		 WebElement num2= driver.findElement(By.xpath("//span[@id=\"numb2\"]"));
		 String value1= num1.getText();
		 int j=Integer.parseInt(value1); 
		 int sum=i+j;
		 String s= Integer.toString(sum);
		 WebElement resultFiled= driver.findElement(By.xpath("//input[@id=\"number\"]"));
		 resultFiled.sendKeys(s);
		 Thread.sleep(3000);
		 WebElement submitButton=driver.findElement(By.xpath("//button[@id=\"demo\"]"));
		 submitButton.click();  
	}
	@Test
	public void TC_13() throws InterruptedException {
		driver.get("https://selenium.obsqurazone.com/ajax-form-submit.php");
		WebElement subjectField=driver.findElement(By.xpath("//input[@id='subject']"));
		subjectField.sendKeys("selenium");
		WebElement descriptionField=driver.findElement(By.xpath("//textarea[@id='description']"));
		descriptionField.sendKeys("Automation Testing");
		subjectField.clear();
		String classValue =subjectField.getAttribute("class");
         System.out.println("value" + classValue);
        String tagValue= subjectField.getTagName();
        System.out.println("tagValue" + tagValue);
        subjectField.sendKeys("selenium testing");
        WebElement submitButton=driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]"));
        submitButton.click();
        Thread.sleep(2000);
        WebElement msg=driver.findElement(By.xpath("//div[@class=\"my-2\"]"));
        String actualmsg=msg.getText();
        String expectedmsg="Form has been submitted successfully!";
        Assert.assertEquals(actualmsg, expectedmsg);
	}
	@Test
	public void TC_014_VerifyTheMessageDisplayedInNewTab() {
		driver.get("https://demoqa.com/browser-windows");
		WebElement newTabButton=driver.findElement(By.xpath("//button[@id='tabButton']"));
		newTabButton.click();
		driver.navigate().to("https://demoqa.com/sample");
		WebElement text=driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
		String actualmsg=text.getText();
		String expectedmsg="This is a sample page";
		Assert.assertEquals(actualmsg,expectedmsg ,"Text is invalid");
	}
	@Test
	public void TC_015_VerifyTheMessageDisplayedInNewWindow() {
		driver.get("https://demoqa.com/browser-windows");
		String parentWindow=driver.getWindowHandle();
		System.out.println(parentWindow);
		WebElement newWindowButton=driver.findElement(By.xpath("//button[@id='windowButton']"));
		newWindowButton.click();
		Set<String> handles=driver.getWindowHandles();
		System.out.println(handles);
		Iterator<String> handleIds=handles.iterator();
		while(handleIds.hasNext()) {
			String childWindow=handleIds.next();
			if(!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				WebElement text=driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
				String actualmsg=text.getText();
				String expectedmsg="This is a sample page";
				Assert.assertEquals(actualmsg,expectedmsg ,"Text is invalid");
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}
	@Test
	public void TC_016_VerifySimpleAlert(){
	driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
	WebElement clickButton=driver.findElement(By.xpath("//button[@class='btn btn-success']"));
	clickButton.click();
	Alert alert=driver.switchTo().alert();
	String alertText=alert.getText();
	System.out.println(alertText);
	alert.accept();
	}
	@Test
	public void TC_017_VerifyConfirmAlert(){
	driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
	WebElement clickButton=driver.findElement(By.xpath("//button[@class='btn btn-warning']"));
	clickButton.click();
	Alert alert=driver.switchTo().alert();
	String alertText=alert.getText();
	System.out.println(alertText);
	alert.dismiss();
	}
	@Test
	public void TC_018_VerifyPromptAlert(){
	driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
	WebElement clickButton=driver.findElement(By.xpath("//button[@class='btn btn-warning']"));
	clickButton.click();
	Alert alert=driver.switchTo().alert();
	
}
	@Test
	public void TC_019_VerifyTextInAFrame(){
	driver.get("https://demoqa.com/frames");
	List<WebElement> frame=driver.findElements(By.tagName("iframe"));
	int numberOfFrames=frame.size();
	System.out.println("text" + numberOfFrames);
	//driver.switchTo().frame(3);
//	driver.switchTo().frame("frame1");
	WebElement frameElement=driver.findElement(By.id("frame1Wrapper"));
	driver.switchTo().parentFrame();
	driver.switchTo().defaultContent();
	}
	@Test
	public void TC_20_VerifyRightClick(){
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		WebElement rightClickButton=driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
		Actions action= new Actions(driver);
		action.contextClick(rightClickButton).build().perform();
	}
	@Test
	public void TC_21_VerifyTheMessageDisplayed(){
		driver.get("https://demoqa.com/browser-windows");
		String parentWindow=driver.getWindowHandle();
		WebElement newWindowButton=driver.findElement(By.xpath("//button[@id='messageWindowButton\']"));
		newWindowButton.click();
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> handleIds=handles.iterator();
		while(handleIds.hasNext()) {
			String childWindow=handleIds.next();
			if(!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				WebElement text=driver.findElement(By.xpath("/html/body/text()"));
				String actualmsg=driver.getPageSource();
				String expectedmsg="Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";
				Assert.assertEquals(actualmsg,expectedmsg ,"Text is invalid");
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		driver.close();
	}
	
	@Test
	public void TC_22_VerifyDoubleClick(){
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		WebElement doubleClickButton=driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		Actions action= new Actions(driver);
		action.doubleClick(doubleClickButton).build().perform();
		Alert alert=driver.switchTo().alert();
		alert.accept();
	}
	@Test
	public void TC_23_VerifyMouseOver(){
		driver.get("https://demoqa.com/menu/");
		WebElement mouseover=driver.findElement(By.xpath("//a[text()='Main Item 1']"));
		Actions action= new Actions(driver);
		action.moveToElement(mouseover).build().perform();
	}
	@Test
	public void TC_24_VerifyDragAndDrop(){
		driver.get("https://demoqa.com/droppable");
		WebElement dragMe=driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement dropHere=driver.findElement(By.xpath("//div[@class='simple-drop-container']/child::div[@id='droppable']"));
		Actions action =new Actions(driver);
		action.dragAndDrop(dragMe,dropHere).build().perform();
	}
	@Test
	public void TC_25_VerifyDragAndDropBy(){
		driver.get("https://demoqa.com/dragabble");
		WebElement dragMe=driver.findElement(By.xpath("//div[@id='dragBox']"));
		Actions action =new Actions(driver);
		action.dragAndDropBy(dragMe,100,100).build().perform();
	}
	@Test
	public void TC_26_VerifyClickHoldAndResize(){
		driver.get("https://demoqa.com/resizable");
		WebElement dragBox=driver.findElement(By.xpath("//div[@id='resizableBoxWithRestriction']"));
		Actions action=new Actions(driver);
		action.clickAndHold(dragBox).build().perform();
		action.dragAndDropBy(dragBox,150,150).build().perform();
	}
	@Test
	public void TC_25_VerifyFindElements() {
		driver.get("https://selenium.obsqurazone.com/radio-button-demo.php");
		List<WebElement> genders =driver.findElements(By.xpath("//input[@name='student-gender']"));
	System.out.println("test---------" + genders);
	for(int i =0;i<genders.size();i++) {
		String gender=genders.get(i).getAttribute("value");
		if(gender.equals("Male")) {
			genders.get(i).click();
		}
	}  
	}
	@Test
	public void TC_26_VerifyValuesInDropDown() {
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		WebElement countryDropDown=driver.findElement(By.xpath("//select[@name='country']"));
		List<String> expectedDropDownList=new ArrayList<String>();
		expectedDropDownList.add("ALBANIA");
		expectedDropDownList.add("ALGERIA");
		expectedDropDownList.add("AMERICAN SAMOA");
		expectedDropDownList.add("ANDORRA");
		List<String> actualDropDownList=new ArrayList<String>();
		Select select = new Select(countryDropDown);
		List<WebElement> dropDownOptions=select.getOptions();
		for(int i=0;i<4;i++) {
			actualDropDownList.add(dropDownOptions.get(i).getText());
		}
		System.out.println(actualDropDownList);
		Assert.assertEquals(actualDropDownList,expectedDropDownList ,"Text is invalid");
		//select.selectByVisibleText("INDIA");
		//select.selectByIndex(10);
		select.selectByValue("ARGENTINA");
	}
	@Test
	public void TC_27_VerifyFileUpload() {
		driver.get("https://demo.guru99.com/test/upload/");
		WebElement chooseFileName= driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
		chooseFileName.sendKeys("D:\\testfile\\document.txt");
		WebElement radioButton= driver.findElement(By.xpath("//input[@id='terms']"));
		radioButton.click();
		WebElement submitFile= driver.findElement(By.xpath("//button[@id='submitbutton']"));
		submitFile.click();
	}
	@Test
	public void TC_29_VerifyClickAndSendUsingJavascriptExecutor() {
	driver.get("https://demowebshop.tricentis.com/");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("document.getElementById('newsletter-email').value='test@text.com'");
	 js.executeScript("document.getElementById('newsletter-subscribe-button').click()");
	}
@Test
public void TC_30_VerifyWaitInSelenium(){
	driver.get("https://demowebshop.tricentis.com/");
	/*page load wait*/
	 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	 /*implicit wait */
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 /* explicit wait */
	 WebElement emailField=driver.findElement(By.xpath("//input[@id='newsletter-email']"));
	 emailField.sendKeys("test@gmail.com");
	 WebElement subscribeButton=driver.findElement(By.xpath("//input[@id='newsletter-subscribe-button']"));
	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	 wait.until(ExpectedConditions.visibilityOf(subscribeButton));
	 /*Fluent wait*/
	 FluentWait fWait=new FluentWait<WebDriver> (driver);
	 fWait.withTimeout(Duration.ofSeconds(10));
	 fWait.pollingEvery(Duration.ofSeconds(1));
	 fWait.until(ExpectedConditions.visibilityOf(subscribeButton));
	 subscribeButton.click();
}
@Test
public void TC_31_VerifyScrollDownOfAWebpage(){
	driver.get("https://demo.guru99.com/test/guru99home/");
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,1000)");	
}
@Test
public void TC_32_VerifyScrollInToViewOffAWebelement(){
	driver.get("https://demo.guru99.com/test/guru99home/");
	WebElement linuxText=driver.findElement(By.linkText("Linux"));
	JavascriptExecutor js =(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView();",linuxText);
			}
@Test
public void TC_33_VerifyScrollToTheBottomofpage() {
	driver.get("https://demo.guru99.com/test/guru99home/");
	JavascriptExecutor js =(JavascriptExecutor)driver;
	js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
}
@Test
public void TC_34_VerifyHorizontalScroll() {
	driver.get("https://demo.guru99.com/test/guru99home/");
	WebElement VBScriptText=driver.findElement(By.linkText("VBScript"));
	JavascriptExecutor js =(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView();",VBScriptText);
}


@Test
public void TC_35_MethodInSelectClass() {
	driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
    WebElement multiSelectDrpdown = driver.findElement(By.xpath("//select[@name='multipleselect[]']"));
    Select select = new Select(multiSelectDrpdown);
    boolean status = select.isMultiple();
    System.out.println(status);
    select.selectByVisibleText("Performance Testing");
    select.selectByVisibleText("Manual Testing");
    List<WebElement> selectedOptions = select.getAllSelectedOptions();
    for (int i = 0; i < selectedOptions.size(); i++) {
        System.out.println(selectedOptions.get(i).getText());
    }
    select.deselectAll();
}
@Test
public void TC_36_VerifyMultipleDropDowns() {
	driver.get("https://selenium.obsqurazone.com/drag-drop.php");
	WebElement drag1=driver.findElement(By.xpath("//span[text()='Draggable n°1']"));
//	WebElement drag2=driver.findElement(By.xpath("//span[text()='Draggable n°2']"));
//	WebElement drag3=driver.findElement(By.xpath("//span[text()='Draggable n°3']"));
//	WebElement drag4=driver.findElement(By.xpath("//span[text()='Draggable n°4']"));
	WebElement dropHere=driver.findElement(By.xpath("//div[@id='mydropzone']"));
	Actions action =new Actions(driver);
	action.dragAndDrop(drag1,dropHere).build().perform();
//	action.dragAndDrop(drag2,dropHere).build().perform();
//	action.dragAndDrop(drag3,dropHere).build().perform();
//	action.dragAndDrop(drag4,dropHere).build().perform();
}
@Test
public void TC_36_VerifyTables() throws IOException {
	driver.get("https://www.w3schools.com/html/html_tables.asp");
	List<WebElement> rowElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
	List<WebElement> columnElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td"));
	List<ArrayList<String>> actGridData=TableUtility.get_Dynamic_TwoDimension_TableElemnts(rowElements, columnElements);
	List<ArrayList<String>> expGridData=ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "Table");
	Assert.assertEquals(actGridData, expGridData,"Invalid data found in the table");
}
@Test
public void TC_36_VerifyValueInTheTable() throws IOException {
	driver.get("https://www.w3schools.com/html/html_tables.asp");
	List<WebElement> rowElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr"));
	List<WebElement> columnElements=driver.findElements(By.xpath("//table[@id='customers']//tbody//tr//td"));
	List<ArrayList<String>> actGridData=TableUtility.get_Dynamic_TwoDimension_TableElemnts(rowElements, columnElements);
	List<ArrayList<String>> expGridData=ExcelUtility.excelDataReader("\\src\\test\\resources\\TestData.xlsx", "Table");
	Assert.assertEquals(actGridData, expGridData,"Invalid data found in the table");
	System.out.println(actGridData);
	for(int i=0;i<actGridData.size();i++) {
		if(actGridData.get(i).get(0).equals("Island Trading")) {
			for(int j=1;j<actGridData.get(i).size();i++) {
				System.out.println(actGridData.get(i).get(j));
			}
		}
	}
}
@Test
public void TC_36_VerifyFileUploadUsingRobotClass() throws InterruptedException, AWTException {
	driver.get("https://www.foundit.in/seeker/registration");
	StringSelection s=new StringSelection("D:\\testfile\\document.txt");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
	WebElement chooseFile=driver.findElement(By.xpath("//span[text()='Choose CV']"));
	chooseFile.click();
	Thread.sleep(2000);
	Robot r= new Robot();
	r.keyPress(KeyEvent.VK_ENTER);
	r.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(2000);
	r.keyPress(KeyEvent.VK_CONTROL);
    r.keyPress(KeyEvent.VK_V);
    r.keyRelease(KeyEvent.VK_CONTROL);
    r.keyRelease(KeyEvent.VK_V);
    Thread.sleep(2000);
    r.keyPress(KeyEvent.VK_ENTER);
    r.keyRelease(KeyEvent.VK_ENTER);
}

}



