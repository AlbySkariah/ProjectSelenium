package Seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:\\Testing\\Libraries\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://demo.guru99.com/test/newtours/");
		driver.manage().window().maximize(); 
		driver.manage().deleteAllCookies();
		WebElement register=driver.findElement(By.linkText("REGISTER"));
		register.click();
		WebElement firstname=driver.findElement(By.name("firstName"));
		firstname.sendKeys("Bob");
		WebElement lastname=driver.findElement(By.name("lastName"));
		lastname.sendKeys("Sam");
		WebElement phone=driver.findElement(By.name("phone"));
		phone.sendKeys("45618975");
		WebElement email=driver.findElement(By.name("userName"));
		email.sendKeys("alby123@gmail.com");
		WebElement address=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/input"));
		address.sendKeys("sdghhyuu");
		WebElement city=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td[2]/input"));
          city.sendKeys("kochi");
          WebElement state=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/input"));
	state.sendKeys("Kerala");
	 WebElement postalcode=driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[10]/td[2]/input"));
	 postalcode.sendKeys("45689");
		 WebElement username=driver.findElement(By.cssSelector(" #email"));
		 username.sendKeys("Bobsam12345");
		 WebElement password=driver.findElement(By.cssSelector(" body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(14) > td:nth-child(2) > input[type=password]"));
		 password.sendKeys("12345");
		 WebElement condfirmpassword=driver.findElement(By.cssSelector(" body > div:nth-child(5) > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(5) > td > form > table > tbody > tr:nth-child(15) > td:nth-child(2) > input[type=password]"));
		 condfirmpassword.sendKeys("12345");
		 WebElement submit=driver.findElement(By.name("submit"));
		 submit.click();
		 driver.close();
		
	}
	
	
}
