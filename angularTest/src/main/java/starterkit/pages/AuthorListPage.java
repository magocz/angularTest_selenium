package starterkit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import starterkit.page.AbstractPageObject;

public class AuthorListPage extends AbstractPageObject {
	
	@FindBy(xpath = "//button[text()='Szukaj']")
	private WebElement search;
	
	@FindBy(xpath = "//input")
	private WebElement input;
	
	public AuthorListPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickSearchBtn(){
		this.search.click();
	}
	
	public void setInput(String name){
		this.input.sendKeys(name);
	}
	
	public int getAuthorTableRowCount() {
		return driver.findElements(By.xpath("//table/tbody/tr")).size();
	}
		
}
