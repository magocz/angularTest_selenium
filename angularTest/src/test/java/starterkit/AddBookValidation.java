package starterkit;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.sql.Driver;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import starterkit.page.AbstractSelenium;
import starterkit.pages.AddBookPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddBookValidation extends AbstractSelenium {
	private AddBookPage newAddBookPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		newAddBookPage = openStarterKit().clickAddBook();
	}

	@Test
	public void shouldShowAllert_NoAuthors() throws InterruptedException {
		// when
		newAddBookPage.setTitle("title");
		newAddBookPage.clickAddBookBtn();
		// then
		assertEquals("Lista autorow jest pusta!", newAddBookPage.getAlert().getText());
		newAddBookPage.getAlert().accept();
		assertTrue(!newAddBookPage.hasError());
	}

	@Test
	public void shouldShowAllert_NoTitle() throws InterruptedException {
		// when
		newAddBookPage.setTitle("");
		newAddBookPage.clickAddBookBtn();
		// then
		assertEquals("Musisz podac tytul ksiazki!", newAddBookPage.getAlert().getText());
		newAddBookPage.getAlert().accept();
		assertTrue(!newAddBookPage.hasError());
	}

	@Test
	public void shouldShowAllert_ToLongTitle() throws InterruptedException {
		// when
		newAddBookPage.setTitle("asdfghjkl;asdfghjkl;asdfghjkl;asdfghjkl;asdfghjkl;asdfghjkl;");
		newAddBookPage.clickAddBookBtn();
		// then
		assertEquals("Tytul nie moze posiadac wiecej niz 50 znakow!", newAddBookPage.getAlert().getText());
		newAddBookPage.getAlert().accept();
		assertTrue(!newAddBookPage.hasError());
	}

	@Test
	public void shouldShowModalAndAddAuthor() throws InterruptedException, AWTException {
		// when
		newAddBookPage.setTitle("nowytytul");
		newAddBookPage.clickAddAuthorBtn();
		// then
		assertTrue(newAddBookPage.modalShouldByDisplayed());

		// when
		newAddBookPage.getModalElements();
		newAddBookPage.setFirstName("Jan");
		newAddBookPage.setLastName("Kowalski");
		newAddBookPage.clickAddBtn();
		// then
		assertTrue(!newAddBookPage.hasError());
		assertEquals(2, newAddBookPage.getAuthorTableRowCount());
	}

	@Test
	public void shouldShowModalAndDontAddAuthor() throws InterruptedException, AWTException {
		// when
		newAddBookPage.setTitle("nowy tytul");
		newAddBookPage.clickAddAuthorBtn();
		// then
		assertTrue(newAddBookPage.modalShouldByDisplayed());

		// when
		newAddBookPage.getModalElements();
		newAddBookPage.setFirstName("Jan");
		newAddBookPage.setLastName("Kowalski");
		newAddBookPage.clickCloseBtn();
		// then
		assertTrue(!newAddBookPage.hasError());
		assertEquals(1, newAddBookPage.getAuthorTableRowCount());
	}

	@Test
	public void shouldSaveBook_AndShowSucesMessage() throws InterruptedException, AWTException {
		// when
		shouldShowModalAndAddAuthor();
		newAddBookPage.clickAddBookBtn();
		// then
		assertTrue(!newAddBookPage.hasError());
		assertTrue(newAddBookPage.isFlashDisplayed());
	}
}
