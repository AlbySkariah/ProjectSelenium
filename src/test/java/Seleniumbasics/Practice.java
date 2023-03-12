package Seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practice {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:\\Testing\\Libraries\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		driver.manage().window().maximize(); 
		driver.manage().deleteAllCookies();
		WebElement element=driver.findElement(By.xpath("//label[@for=\"inputEmail4\"]/following::input[@id=\"single-input-field\"]"));
		element.sendKeys("hello");
		

	}

}
