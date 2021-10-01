package Assignments;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnSortable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/sortable/");
		driver.switchTo().frame(0);
		List<WebElement> findElements = driver
				.findElements(By.xpath("//li[@class='ui-state-default ui-sortable-handle']"));
		System.out.print(findElements.size());
		WebElement target = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement dest = findElements.get(6);
		WebElement dest1 = findElements.get(3);
			Actions action = new Actions(driver);
		action.click(target).clickAndHold().moveToElement(dest).moveByOffset(0, 10).release().build().perform();
		action.click(target).clickAndHold().moveToElement(dest1).moveByOffset(0, 10).release().build().perform();
			driver.switchTo().defaultContent();
	}

}
