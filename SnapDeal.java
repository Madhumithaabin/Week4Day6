package Assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		Actions action = new Actions(driver);
		driver.get("https://www.snapdeal.com/");
		WebElement men = driver.findElement(By.linkText("Men's Fashion"));
		action.moveToElement(men).perform();
		WebElement shoe = driver.findElement(By.xpath("//span[text()='Sports Shoes']"));
		action.moveToElement(shoe).click().perform();

		String count = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		String countShoe = count.replaceAll("[\\D]", " ");
		System.out.println("Number of shoes" + countShoe);
		WebElement trainingShoe = driver.findElement(By.xpath("//div[text()='Training Shoes']"));
		action.moveToElement(trainingShoe).click().perform();
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		WebElement sortPrice = driver.findElement(By.xpath("//ul[@class='sort-value']/li[@data-index='1']"));
		action.moveToElement(sortPrice).click().perform();
		WebElement fromPrice = driver.findElement(By.name("fromVal"));
		WebElement toPrice = driver.findElement(By.name("toVal"));
		String fromP = fromPrice.getAttribute("value");
		String toP = toPrice.getAttribute("value");
		int fromVal = Integer.parseInt(fromP);
		int toVal = Integer.parseInt(toP);

		if (toVal > fromVal) {
			System.out.println("Price is sorted correctly");
		} else {
			System.out.println("Price is not sorted correctly");

		}

		/*
		 * WebElement sliderFrom =
		 * driver.findElement(By.xpath("(//div[@class='filter-inner']/div /a)[1]"));
		 * action.dragAndDropBy(sliderFrom, 48, 0).perform();
		 * 
		 * String fromValue =
		 * driver.findElement(By.xpath("//span[@class='from-price-text']")).getAttribute
		 * ("value"); System.out.print(fromValue); WebElement sliderTo =
		 * driver.findElement(By.xpath("(//div[@class='filter-inner']/div /a)[2]"));
		 * action.dragAndDropBy(sliderTo, -50, 0).perform();
		 */
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		WebElement color = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
		action.moveToElement(color).click().perform();
		if (driver.findElement(By.className("filter-clear")).isDisplayed()) {

			System.out.println("Filters applied");
		} else {
			System.out.println("Filters not applied");
		}
		WebElement firstPic = driver.findElement(By.xpath("//picture[@class='picture-elem']"));
		action.moveToElement(firstPic).perform();
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
		String cost = driver.findElement(By.xpath("//span[@class='strikee ']")).getText();
		String discount = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Cost : " + cost);
		System.out.println("Discount : " + discount);
		WebElement shoeImg = driver.findElement(By.xpath("//img[@class='cloudzoom']"));
		File screenshotAs = shoeImg.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snap/show.png");
		FileUtils.copyFile(screenshotAs, dest);
		driver.findElement(By.xpath("//div[@class='close close1 marR10']/i")).click();
		driver.close();

	}

}
