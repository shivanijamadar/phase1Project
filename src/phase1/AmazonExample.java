package phase1;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonExample {

	public static void main(String[] args) {
		System.setProperty("webdriver,chrome.driver", "chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		//Launch Amazon
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		//Enter search string
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Samsung");
		
		//Click search
		WebElement goButton = driver.findElement(By.xpath("//input[@value='Go']"));
		goButton.click();

		List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> prices = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		//Print total number of products
		System.out.println("Total number of products are: "+products.size());

		//Print all products and prices
		for(int i=0;i<products.size();i++) {
			System.out.println(products.get(i).getText()+ " - "+prices.get(i).getText());
		}
		
		//Get title on parent window
		String parentWindowTitle = products.get(0).getText();
		
		//Select first product
		products.get(0).click();
		
		String parentWin = driver.getWindowHandle();
		Set<String> allWins = driver.getWindowHandles();
		//Switch to new window
		for (String win : allWins) {
			if(!win.equals(parentWin)) {
				driver.switchTo().window(win);
			}
		}
		
		//Get title on child window
		WebElement childHeader = driver.findElement(By.id("productTitle"));
		String childWindowTitle = childHeader.getText();
		
		//Validate titles 
			if(parentWindowTitle.equals(childWindowTitle)) {
				System.out.print("Product title is matching");
			}
			else {
				System.out.print("Product title is not matching");
			}
	}

}
