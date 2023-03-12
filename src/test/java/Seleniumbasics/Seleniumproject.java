package Seleniumbasics;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Seleniumproject {

public static void main(String[] args) {
				
				System.setProperty("webdriver.chrome.driver","D:\\Testing\\Libraries\\chromedriver.exe");
				WebDriver driver= new ChromeDriver();
				driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
				driver.manage().window().maximize();
				System.out.println("browser loaded");
				driver.close();
						
						

	}

}
