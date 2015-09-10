package starterkit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import starterkit.page.AbstractSelenium;
import starterkit.pages.AuthorListPage;

public class SearchAuthorValidation extends AbstractSelenium{
	private AuthorListPage newAuthorListPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		newAuthorListPage = openStarterKit().clickAuthorList();
	}
	
	@Test
	public void test1_shouldFindAuthors() throws InterruptedException {
		// given
		newAuthorListPage.setInput("");
		// when
		// then
		assertTrue((newAuthorListPage.getAuthorTableRowCount() > 1));
	}
	
	@Test
	public void test2_shouldFindAuthorByFirstName() throws InterruptedException {
		// given
		newAuthorListPage.setInput("Jan");
		// when
		// then
		assertEquals(3, newAuthorListPage.getAuthorTableRowCount());
	}
	
	@Test
	public void test3_shouldFindAuthorByLastName() throws InterruptedException {
		// given
		newAuthorListPage.setInput("Nowak");
		// when
		// then
		assertEquals(2, newAuthorListPage.getAuthorTableRowCount());
	}
}
