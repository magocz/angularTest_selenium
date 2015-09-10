package starterkit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import starterkit.page.AbstractSelenium;
import starterkit.pages.AddBookPage;
import starterkit.pages.BookListPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchBookValidation extends AbstractSelenium {

	private BookListPage newBookListPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		newBookListPage = openStarterKit().clickBookList();
	}

	@Test
	public void test1_shouldSaveBook() throws InterruptedException {
		// given
		newBookListPage.setPrefix("");
		newBookListPage.clickSearchBtn();
		int rowCount = newBookListPage.getBookTableRowCount();
		// when
		newBookListPage.clickAddBtn();
		addBookInAddBookPage();
		newBookListPage.clickSearchBtn();
		// thne
		assertEquals((rowCount + 1), newBookListPage.getBookTableRowCount());
		assertTrue(!newBookListPage.hasError());
	}

	private void addBookInAddBookPage() {
		AddBookPage newAddBookPage = openStarterKit().clickAddBook();
		newAddBookPage.setTitle("Nowa ksiazka!");
		newAddBookPage.clickAddAuthorBtn();
		newAddBookPage.getModalElements();
		newAddBookPage.setFirstName("Janusz");
		newAddBookPage.setLastName("Kominiara");
		newAddBookPage.clickAddBtn();
		newAddBookPage.clickAddBookBtn();
	}

	@Test
	public void test2_shouldFindBookByTitle() throws InterruptedException {
		// when
		// shouldGoToAddBookAndSavBook();
		newBookListPage.setPrefix("Nowa ksiazka!");
		newBookListPage.clickSearchBtn();
		// thne
		assertTrue(2 <= newBookListPage.getBookTableRowCount());
		assertTrue(!newBookListPage.hasError());
	}

	@Test
	public void test3_shouldDeleteBook() throws InterruptedException {
		// given
		newBookListPage.clickSearchBtn();
		int rowNumber = newBookListPage.getBookTableRowCount();
		// when
		newBookListPage.clickDeleteBtn();
		newBookListPage.clickSearchBtn();
		// then
		assertEquals(rowNumber - 1, newBookListPage.getBookTableRowCount());
		assertTrue(!newBookListPage.hasError());
		assertTrue(newBookListPage.isMessageDisplayed());
	}

	@Test
	public void test4_shouldGoToAddAuthorUrl() {
		// whne
		newBookListPage.clickAddBtn();
		// then
		assertEquals("http://localhost:9721/workshop/#/books/add-book", newBookListPage.getUrl());
	}

	@Test
	public void test5_shouldEditTitle() throws InterruptedException {
		// whne
		newBookListPage.setPrefix("");
		newBookListPage.clickSearchBtn();
		newBookListPage.clickEditBtn();
		newBookListPage.getModalElements();
		newBookListPage.setNewTitle("nowyTytul");
		// then
		assertTrue(newBookListPage.editBtnIsEnabled());
		// when
		newBookListPage.clickEdit();
		// then
		assertTrue(!newBookListPage.hasError());
		assertTrue(newBookListPage.isMessageDisplayed());
	}

	@Test
	public void test6_shouldNotEditTitle() throws InterruptedException {
		// whne
		newBookListPage.setPrefix("");
		newBookListPage.clickSearchBtn();
		newBookListPage.clickEditBtn();
		newBookListPage.getModalElements();
		newBookListPage.setNewTitle("");
		// then
		assertTrue(!newBookListPage.editBtnIsEnabled());
		assertTrue(!newBookListPage.hasError());
	}
}
