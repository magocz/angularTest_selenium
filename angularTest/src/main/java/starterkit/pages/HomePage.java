package starterkit.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import starterkit.page.AbstractPageObject;


public class HomePage extends AbstractPageObject {
	@FindBy(linkText="Book List")
	private WebElement bookList;
	
	@FindBy(linkText="Author List")
	private WebElement authorList;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver.get("http://localhost:9721/workshop/#/main/welcome");
	}

	public BookListPage clickBookList() {
		bookList.click();
		return PageFactory.initElements(driver, BookListPage.class);
	}
	
	public AuthorListPage clickAuthorList() {
		authorList.click();
		return PageFactory.initElements(driver, AuthorListPage.class);
	}
	
	public AddBookPage clickAddBook() {
		bookList.click();
		PageFactory.initElements(driver, BookListPage.class).clickAddBtn();
		return PageFactory.initElements(driver, AddBookPage.class);
	}

}
