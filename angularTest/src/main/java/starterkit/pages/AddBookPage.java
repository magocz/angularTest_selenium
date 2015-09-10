package starterkit.pages;

import javax.xml.xpath.XPath;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import starterkit.page.AbstractPageObject;

public class AddBookPage extends AbstractPageObject {

	@FindBy(xpath = "//button[text()='Add author']")
	private WebElement addAuthor;

	@FindBy(xpath = "//button[text()='Add book']")
	private WebElement addBook;

	private Alert alert;
	
	@FindBy(xpath = "//input")
	private WebElement title;
	
	@FindBy(name = "message")
	private WebElement flashe;

	// modal elements
	private WebElement firstName;
	private WebElement lastName;
	private WebElement addBtn;
	private WebElement closeBtn;

	public AddBookPage(WebDriver driver) {
		super(driver);
	}

	public void clickAddBookBtn() {
		addBook.click();
	}

	public void clickAddAuthorBtn() {
		addAuthor.click();
	}

	public boolean modalShouldByDisplayed() {
		return driver.switchTo().activeElement().isDisplayed();
	}

	public int getAuthorTableRowCount() {
		return driver.findElements(By.xpath("//table/tbody/tr")).size();
	}

	public void getModalElements() {
		closeBtn = driver.switchTo().activeElement().findElement(By.xpath("//button[text()='Close']"));
		addBtn = driver.switchTo().activeElement().findElement(By.xpath("//button[text()='Add']"));
		firstName = driver.switchTo().activeElement().findElement(By.name("firstName"));
		lastName = driver.switchTo().activeElement().findElement(By.name("lastName"));
	}

	public Alert getAlert() {
		alert = driver.switchTo().alert();
		return alert;
	}

	public void setTitle(String title) {
		this.title.sendKeys(title);
	}

	public void setFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}

	public void clickAddBtn() {
		addBtn.click();
	}

	public void clickCloseBtn() {
		closeBtn.click();
	}
	
	public boolean isFlashDisplayed() {
		return flashe.isDisplayed();
	}

}
