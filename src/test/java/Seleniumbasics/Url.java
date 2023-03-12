package Seleniumbasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Url {

	public static void main(String[] args) {
		/*System.setProperty("webdriver.chrome.driver","D:\\Testing\\Libraries\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        System.out.println("Url loaded in chrome");
        
        System.setProperty("webdriver.gecko.driver", "D:\\Testing\\Libraries\\geckodriver-v0.32.0-win64\\geckodriver.exe" );
		WebDriver driver1=new FirefoxDriver();
		driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		 System.out.println("Url loaded in firefox");
		*/ 
		 System.setProperty("webdriver.edge.driver","D:\\Testing\\Libraries\\edge\\edgedriver_win64\\edgedriver.exe");
		 WebDriver driver2=new EdgeDriver();
		 driver2.get("https://selenium.obsqurazone.com/simple-form-demo.php");
		 System.out.println("Url loaded in edge");
	}

}
