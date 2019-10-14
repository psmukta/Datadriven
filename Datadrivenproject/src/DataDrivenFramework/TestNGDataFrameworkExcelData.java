package DataDrivenFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataFrameworkExcelData {
	WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\ITTraining\\AllDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///C:/ITTraining/practice.html");
	}

	@Test(dataProvider="OurWebSiteData")
	public void OurWebSiteSignUP(String FN, String LN, String EM, String AEM, String PW, 
			String MN, String DY, String YR, String sex) {//these are local variable
		
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
	public Object[][]getData() throws IOException{
		 Object[][] object=null;
		 File f=new File("C:\\ITTraining\\FacebookData.xlsx");
		 FileInputStream fis=new FileInputStream(f);
		 
		 XSSFWorkbook wb=new XSSFWorkbook(fis);
		 
		 XSSFSheet sheet=wb.getSheet("Data2");
		 
		 int rowCount= sheet.getLastRowNum()-sheet.getFirstRowNum();
		 int colCount=9;
		 object=new Object[rowCount][colCount];
				 for (int i=0;i<=rowCount;i++) {
					 XSSFRow row=sheet.getRow(i+1);
					 
					 for(int j=0;j<=row.getLastCellNum();j++) {
						 object[i][j]=row.getCell(j).toString();
						 
					 }
				 }
				 return object;
		 
		
	}
	}
