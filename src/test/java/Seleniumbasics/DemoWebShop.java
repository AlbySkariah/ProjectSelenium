package Seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoWebShop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","D:\\Testing\\Libraries\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().deleteAllCookies();
		WebElement login=driver.findElement(By.className("ico-login"));
		login.click();
		WebElement email=driver.findElement(By.name("Email"));
		email.sendKeys("alby123@gmail.com");
		WebElement password=driver.findElement(By.name("Password"));
		password.sendKeys("asd123");
		WebElement loginbutton=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
		loginbutton.click();
		driver.close();
		//#Email
		//https://demo.guru99.com/test/newtours/
	
		

	}

}
