package starterkit.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import starterkit.page.AbstractPageObject;

public class BookListPage extends AbstractPageObject {
	@FindBy(xpath = "//button[text()='Dodaj ksiazke']")
	private WebElement addBook;
	
	@FindBy(xpath = "//button[text()='Szukaj']")
	private WebElement search;
	
	@FindBy(xpath = "//input")
	private WebElement prefix;

	
	private List<WebElement> deleteButtons;

	@FindBy(xpath = "//button[text()='Edytuj']")
	private List<WebElement> editButtons;
	
	@FindBy(name = "message")
	private WebElement flashe;

	// edit modal elements
	private WebElement newTitle;
	
	private WebElement editBtn;
	private WebElement closeBtn;

	public BookListPage(WebDriver driver) {
		super(driver);
	}

	public void clickAddBtn() {
		addBook.click();
	}

	public void clickSearchBtn() {
		search.click();
	}

	public void clickDeleteBtn() {
		deleteButtons = driver.findElements(By.xpath("//button[text()='Usun']"));
		if (deleteButtons.size() != 0) {
			deleteButtons.get(0).click();
		}
	}

	public void setPrefix(String input) {
		prefix.sendKeys(input);
	}

	public int getBookTableRowCount() {
		return driver.findElements(By.xpath("//table/tbody/tr")).size();
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public void getModalElements() {
		newTitle = driver.switchTo().activeElement().findElement(By.name("newTitle"));
		editBtn = driver.switchTo().activeElement().findElement(By.xpath("//button[text()='Edit']"));
		closeBtn = driver.switchTo().activeElement().findElement(By.xpath("//button[text()='Close']"));
	}

	public void setNewTitle(String newTitle) {
		this.newTitle.sendKeys(newTitle);
	}

	public void clickEdit() {
		editBtn.click();
	}
	
	public boolean editBtnIsEnabled(){
		return editBtn.isEnabled();
	}
	public void clickClose() {
		closeBtn.click();
	}

	public void clickEditBtn() {
		if (editButtons.size() != 0) {
			editButtons.get(0).click();
		}
	}
	
	public boolean isMessageDisplayed(){
		return this.flashe.isDisplayed();
	}
}
