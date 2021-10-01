package Assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nyakka {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get("https://www.nykaa.com/");
		WebElement brandElement = driver.findElement(By.xpath("//a[text()='brands']"));
		WebElement loreal = brandElement.findElement(By.xpath("(//div[@id='brandCont_Popular']//a)[5]"));
		Actions action = new Actions(driver);
		action.moveToElement(brandElement).moveToElement(loreal).click().perform();
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Title is verified" + title);
		} else {
			System.out.println("Title is not verified" + title);
		}
		driver.findElement(By.xpath("//span[@title='POPULARITY']")).click();
		WebElement custop = driver
				.findElement(By.xpath("//span[text()='customer top rated']/following::input[@id='3']"));
		action.moveToElement(custop).click().perform();
		driver.findElement(By.xpath("//div[@class='filter-sidebar__filter-title pull-left']")).click();
		driver.findElement(By.xpath("//span[@class='category-title-text']")).click();
		driver.findElement(By.xpath("//div[@class='category-filter-name']/span[text()='Hair Care']")).click();
		WebElement shampoo = driver.findElement(By.xpath("//span[@class='filter-name-count']/span"));
		action.moveToElement(shampoo).click().perform();
		driver.findElement(By.xpath("//div[text()='Concern']")).click();
		WebElement colorpro = driver
				.findElement(By.xpath("//span[@class='filter-name-count']/span[text()='Color Protection']"));
		action.moveToElement(colorpro).click().perform();
		WebElement filterApplied = driver.findElement(By.xpath("//span[text()='filters applied']"));
		if (filterApplied.isDisplayed()) {
			System.out.println("Filter is applied");
		} else {
			System.out.println("Filter is applied");
		}
		driver.findElement(By.xpath("//div[@class='card-img']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowNum = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowNum.get(1));
		driver.findElement(By.xpath("//span[text()='175ml']")).click();
		String price = driver.findElement(By.xpath("//span[@class='post-card__content-price-offer']")).getText();
		System.out.println("Price of the shampoo : " + price);
		driver.findElement(By.xpath("//button[text()='ADD TO BAG'] ")).click();
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		String grandTotal = driver.findElement(By.xpath("//div[@class='first-col']/div")).getText();
		System.out.println("Grand Total " + grandTotal);
		WebElement proceed = driver.findElement(By.xpath("//div[@class='second-col']/button"));
		action.moveToElement(proceed).click().perform();
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		String totalAmount = driver
				.findElement(By
						.xpath("(//div[@class='payment-details-tbl grand-total-cell prl20']/div[@class='value'])/span"))
				.getText();

		String grandAmount = grandTotal.replaceAll("[^\\d]", " ");
		;
		System.out.println(grandAmount);
		if (grandAmount.equals(totalAmount)) {
			System.out.println("Total amount is same as the grandtotal : " + totalAmount);
		} else {
			System.out.println("Total amount is not same as the grandtotal : " + totalAmount);
		}
		driver.close();
	}

}
