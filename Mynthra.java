package Assignments;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.*;

public class Mynthra {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		Actions action = new Actions(driver);
		driver.get("https://www.myntra.com/");
		WebElement men = driver.findElement(By.xpath("//a[text()='Men']"));
		action.moveToElement(men).perform();
		driver.findElement(By.xpath("//a[text()='Jackets']")).click();
		String count = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		count = count.replaceAll("[^\\d]", " ");
		count = count.trim();
		System.out.println("Total count of Item :" + count);
		String firstCat = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String secondCat = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		firstCat = firstCat.replaceAll("[^\\d]", " ");
		secondCat = secondCat.replaceAll("[^\\d]", " ");
		firstCat = firstCat.trim();
		secondCat = secondCat.trim();

		System.out.print(firstCat);
		System.out.print(secondCat);
		int result = Integer.parseInt(secondCat) + Integer.parseInt(firstCat);
		String countCat = String.valueOf(result);
		System.out.print(countCat);
		if (count.equals(countCat)) {
			System.out.println("Total count is same and verified");
		} else
			System.out.println("Total count is not same");

		driver.findElement(By.xpath("//label[text()='Jackets']")).click();
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");
		WebElement duke = driver.findElement(By.xpath("//input[@value='Duke']"));
		action.moveToElement(duke).click().perform();
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		List<WebElement> brandName = driver.findElements(By.xpath("(//div[@class='filter-summary-filter'])[2]"));
		for (int i = 0; i < brandName.size(); i++) {
			if (brandName.get(i).getText().contains("Duke")) {
				System.out.println("All coats are brand of duke");
			} else {
				System.out.println("All coats are not brand of duke");
			}

		}
		WebElement sort = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		action.moveToElement(sort).perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<WebElement> options = sort.findElements(By.tagName("li"));
		for (WebElement option : options) {
			if (option.getText().equals("Better Discount")) {
				option.click(); 
				break;
			}
		}
		String price = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Price :" + price);
		driver.findElement(By.xpath("//picture[@class='img-responsive']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowNew = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowNew.get(1));
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snap/mynthra.png");
		FileUtils.copyFile(screenshotAs, dest);
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		driver.close();
	}

}
