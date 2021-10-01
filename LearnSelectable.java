package Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnSelectable {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/selectable/");
		driver.switchTo().frame(0);
		WebElement selElement1 = driver.findElement(By.xpath("//li[text()='Item 1']"));

		WebElement selElement2 = driver.findElement(By.xpath("//li[text()='Item 5']"));

		WebElement selElement3 = driver.findElement(By.xpath("//li[text()='Item 3']"));

		Actions action = new Actions(driver);
		action.moveToElement(selElement1).click().perform();
		Thread.sleep(300);
		action.moveToElement(selElement2).click().perform();
		Thread.sleep(300);
		action.moveToElement(selElement3).click().perform();
		driver.switchTo().defaultContent();
	}

}
