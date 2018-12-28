package com.ibm.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.pages.UserPage;
import com.ibm.utilities.ExcelUtil;
import com.ibm.utilities.PropertiesFileHandler;

//jars would be in the link
// https://drive.google.com/open?id=1Oa7AzFN8nQapvatjRToU_bwDgpf18QKI
public class BaseTest extends ExcelUtil {
	WebDriverWait wait;
	WebDriver driver;
	//Use properties to solve the simple test cases. 
	/*@Test
	public void testcase1() throws IOException
	{
		String file="./TestData/magentodata.properties";
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
	}*/
	
	//excel data using dataprovider
	//run and check	
	//sends data in excel one by one
   /* @Test(dataProvider = "data")
    public void testcase2(String user, String pwd){
    		System.out.println(user);
    		System.out.println(pwd);
    }

    @DataProvider(name="data")
    public Object[][] data() throws IOException {
        return ExcelUtil.DataTable("./TestData/TestData.xlsx","LoginData");
    }
    */
	 
    @Test(dataProvider="CategoryDatas")
    
    public void testcase6(String name, String tagTitle, String sortNo) throws InterruptedException{
    	
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
		driver.get("https://atozgroceries.com/admin");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Login login = new Login(driver, wait);
		
		login.enterEmailAddress("demo@atozgroceries.com");
		login.enterPassword("456789");
		login.clickOnLogin();
		
		WebElement systemEle=driver.findElement(By.linkText("Catalog"));
		systemEle.click();
		
		WebElement usersEle=driver.findElement(By.linkText("Categories"));
		usersEle.click();
		
		Thread.sleep(3000);
		WebElement newUserEle=driver.findElement(By.cssSelector("a.btn.btn-primary"));
		newUserEle.click();

		WebElement categoryName=driver.findElement(By.name("name"));
		categoryName.sendKeys(name);
		
		WebElement metaTagName=driver.findElement(By.name("tag_title"));
		metaTagName.sendKeys(tagTitle);
		
		WebElement sortOrder=driver.findElement(By.name("sort"));
		sortOrder.sendKeys(sortNo);
		
		WebElement statusEle=driver.findElement(By.name("status"));
		Select select=new Select(statusEle);
		select.selectByVisibleText("Enabled");
		
		WebElement toTopEle=driver.findElement(By.id("toTop"));
		toTopEle.click();
		Thread.sleep(3000);
		WebElement saveEle=driver.findElement(By.cssSelector("button.btn.btn-primary"));
		saveEle.click();
		
		Assert.assertTrue(driver.findElement(By.linkText("oats")).isDisplayed());
		
		WebElement logOutEle=driver.findElement(By.cssSelector("i.fa.fa-sign-out"));
		logOutEle.click();
		
		driver.get("https://atozgroceries.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		UserPage user = new UserPage(driver, wait);
		
		WebElement loginEle=driver.findElement(By.linkText("Login"));
		loginEle.click();
		
		user.userName("7675058941");
		user.enterPassword("456789");
		user.clickOnLogin();
		
		WebElement categoryEle=driver.findElement(By.cssSelector("#categories-menu > ul.menu > li.menu-item > span.click-categories.flaticon-bars"));
		categoryEle.click();
		
		WebElement moreCategoryEle=driver.findElement(By.cssSelector("li.menu-item > div.category-drop-list.category-drop-list-show > div.category-drop-list-inner > span.more-categories.open-cate"));
		moreCategoryEle.click();
		
		Assert.assertTrue(driver.findElement(By.linkText("oats")).isDisplayed());
		
    }
  
    @DataProvider(name="CategoryDatas")
    public Object[][] categoryData() throws IOException {
        return ExcelUtil.DataTable("./TestData/TestData.xlsx","CategoryDatas");
    }

}
