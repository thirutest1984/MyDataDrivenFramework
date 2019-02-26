package datadrivenframework.tests;

import org.testng.annotations.Test;

import datadrivenframework.utils.ReadExcelFile;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class DataDrivenExampleTest {

  public WebDriver driver;
	
  @Test(dataProvider = "testData")
  public void dataDrivenExampleTestCase(String username, String password) {
	  driver.findElement(By.name("userName")).sendKeys(username);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.name("login")).click();
	  Assert.assertEquals(driver.getTitle(),"Sign-on: Mercury Tours");
	  System.out.println("Login failed.");
  }
  
  @BeforeMethod
  public void setUp() {
	  String chromepath = System.getProperty("user.dir")+"\\src\\datadrivenframework\\resources\\chromedriver.exe";
	  System.setProperty("webdriver.chrome.driver", chromepath);
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("http://newtours.demoaut.com");
  }

  @AfterMethod
  public void tearDown() {
	  driver.quit();
  }


  @DataProvider(name="testData")
  public Object[][] testDataInput() {
	  ReadExcelFile config = new ReadExcelFile(System.getProperty("user.dir")+"\\src\\datadrivenframework\\resources\\testDataExcel.xlsx");
	  int rows = config.getRowCount(0);
	  
	  Object[][] credentials = new Object[rows][2];
	  for(int i=0;i<rows;i++) {
		  credentials[i][0] = config.getData(0, i, 0);
		  credentials[i][1] = config.getData(0, i, 1);
	  }
	  return credentials;
    }
}
