package Seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browsercommands {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:\\Testing\\Libraries\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		driver.manage().window().maximize(); //maximising the browser window
		System.out.println("window maximised");
		driver.manage().deleteAllCookies(); // to dele cookies
		String url =driver.getCurrentUrl(); // to get url
		System.out.println("Url is " +url);
		 String title=driver .getTitle();// to get title
		 System.out.println("title is " +title);
		String page= driver.getPageSource();
		System.out.println("page source " +page);
		WebElement msg=driver.findElement(By.id("single-input-field"));
		msg.sendKeys("Hello");
		WebElement submit=driver.findElement(By.className("btn btn-primary"));
		submit.click();
		
		driver.close(); //to close the browser
          
	}

}
//https://demowebshop.tricentis.com/