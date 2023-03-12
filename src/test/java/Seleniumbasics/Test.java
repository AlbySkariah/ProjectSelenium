package Seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:\\Testing\\Libraries\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		driver.manage().window().maximize(); 
		driver.manage().deleteAllCookies();
		WebElement A =driver.findElement(By.id("value-a"));
		A.sendKeys("6");
		WebElement B=driver.findElement(By.id("value-b"));
		B.sendKeys("2");
		WebElement total=driver.findElement(By.id("button-two"));
		total.click();
		WebElement totalAB=driver.findElement(By.id("message-two"));
		String totalfrmapl=totalAB.getText();
		String[]test=totalfrmapl.split(":");
		   String value=test[1].trim();
		   int value1=Integer.parseInt(value);
		   System.out.println("value " +value1);
		   if(value1==8) {
			   System.out.println("true");
		   }else {
			   System.out.println("false");
		   }
		   driver.close();
		   }
	}


