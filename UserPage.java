package com.ibm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.test.BaseTest;

public class UserPage extends BaseTest{
		
		@FindBy(id="pnum2")
		WebElement userEle;

		@FindBy(id="pword2")
		WebElement passEle;
		
		@FindBy(id="mem_login")
		WebElement loginEle;
		WebDriverWait wait;
		WebDriver driver;
		
		public UserPage(WebDriver driver,WebDriverWait wait) {
			PageFactory.initElements(driver, this);
			this.driver=driver;
			this.wait=wait;
		}

		public void userName(String user)
		{
			userEle.sendKeys();
		}
		public void enterPassword(String password)
		{
			passEle.sendKeys(password);
		}
		public void clickOnLogin()
		{
			loginEle.click();
		}

}
