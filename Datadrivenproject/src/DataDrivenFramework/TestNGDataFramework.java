package DataDrivenFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataFramework {
	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\ITTraining\\AllDriver\\chromedriver.exe");
		//System.setProperty("webdriver.ie.driver", "");
		//driver=new InternetExplorerDriver();
		driver = new ChromeDriver();
		//driver=new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test(dataProvider="OurWebSiteData")
	public void OurWebSiteSignUP(String FN, String LN, String EM, String AEM, String PW, 
			String MN, String DY, String YR, String sex) {
		driver.get("file:///C:/ITTraining/practice.html");
		driver.findElement(By.name("FName")).sendKeys(FN);
		driver.findElement(By.id("LN")).sendKeys(LN);
		driver.findElement(By.xpath(".//*[@id='E_m']")).sendKeys(EM);
		driver.findElement(By.xpath(".//*[@id='AE_m']")).sendKeys(AEM);
		driver.findElement(By.className("CPass")).sendKeys(PW);

		Select monthSelect=new Select(driver.findElement(By.id("MN")));
		monthSelect.selectByVisibleText(MN);

		Select daySelect=new Select(driver.findElement(By.name("day")));
		daySelect.selectByVisibleText(DY);

		Select yearSelect=new Select(driver.findElement(By.xpath(".//*[@id='YR']")));
		yearSelect.selectByVisibleText(YR);
		if(sex.equalsIgnoreCase("Female")) {
		driver.findElement(By.xpath("html/body/input[6]")).click();
		}
		else {
			driver.findElement(By.xpath("html/body/input[7]")).click();
		}

	}

	@AfterTest
	public void CloseBrowser() {
		driver.close();
		driver.quit();

	}

	@DataProvider(name="OurWebSiteData")
	public Object[][]getData(){
		return new Object[][] {
			{"Parveen","Sultana","parveen@yahoo.com","parveen@yahoo.com","xyz123","Apr","Sun","1993","Female"},
			{"Areeb","Islam","asdf@yahoo.com","asdf@yhaoo.com","ret123","Jan","Sat","1995","Famle"},
			{"Parveen","Sultana","parveen@yahoo.com","parveen@yahoo.com","xyz123","Apr","Sun","1993","Female"},
			{"Areeb","Islam","asdf@yahoo.com","asdf@yhaoo.com","ret123","Jan","Sat","1995","Male"}
		};
	}
}