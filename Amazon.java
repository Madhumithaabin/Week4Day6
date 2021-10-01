package Assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		Actions action = new Actions(driver);
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro", Keys.ENTER);
		String price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		price = price.replaceAll("[\\D]", " ");
		price = price.trim();
		System.out.println("Price :" + price);
		String rating = driver.findElement(By.xpath("//span[@class='a-size-base']")).getText();
		System.out.println("Number of Rating :" + rating);
		WebElement fiveRating = driver.findElement(By.xpath("//span[@class='a-icon-alt']"));
		action.moveToElement(fiveRating).click().perform();
		String numRating = driver.findElement(By.xpath("//span[@class='a-size-base']/a[@class='a-link-normal']"))
				.getText();
		System.out.println("Number of Five Star Rating :" + numRating);
		WebElement firstPic = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		firstPic.click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winNew = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winNew.get(1));
		WebElement screenPic = driver.findElement(By.id("imgTagWrapperId"));
		File screenshotAs = screenPic.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snap/Amazon.png");
		FileUtils.copyFile(screenshotAs, dest);
		driver.findElement(By.id("add-to-cart-button")).click();

		String cartSubTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		cartSubTotal = cartSubTotal.replaceAll("[\\D]", " ");
		System.out.println(cartSubTotal);
		if (cartSubTotal.contains(price))
			System.out.println("Price is same :" + cartSubTotal);
		else
			System.out.println("Price is not same :" + cartSubTotal);

	}

}
