package DataDrivenFramework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenPractice {
	
	WebDriver driver;

	@BeforeTest
	public void OpenBrowsre() throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver", "C:\\\\ITTraining\\\\AllDriver\\\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Thread.sleep(4000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void WebDataWriting() throws IOException {
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		List<WebElement>rows=driver.findElements(By.xpath("html/body/div[6]/div[1]/div[1]/div[3]/div/table/tbody/tr"));
		int rowCount=rows.size();
		System.out.println("Total Rows are:"+rowCount);
		List<WebElement>Columns=driver.findElements(By.xpath("html/body/div[6]/div[1]/div[1]/div[3]/div/table/tbody/tr[1]/th"));
		int colCount=Columns.size();
		System.out.println("Total Columns are:"+colCount);
		FileOutputStream fos=new FileOutputStream ("C:\\ITTraining\\WebTableData\\W3Data.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("Data3");
		for(int i=1;i<=rowCount;i++) {
			XSSFRow excelrow=sheet.createRow(i);
			for(int j=1;j<=colCount;j++) {
				if (i==1) {
					WebElement value= driver.findElement(By.xpath("html/body/div[6]/div[1]/div[1]/div[3]/div/table/tbody/tr["+i+"]/th["+j+"]"));

					String a=value.getText();
					XSSFCell excelCell=excelrow.createCell(j);
					excelCell.setCellValue(a);

				}
				else {
					WebElement value=driver.findElement(By.xpath("html/body/div[6]/div[1]/div[1]/div[3]/div/table/tbody/tr["+i+"]/td["+j+"]"));
					String a=value.getText();
					XSSFCell excelCell=excelrow.createCell(j);
					excelCell.setCellValue(a);
				}


			}
		}
		wb.write(fos);
		fos.flush();
		fos.close();

	}
	@AfterTest
	public void CloseBrowser() {
		driver.close();

	}

}
