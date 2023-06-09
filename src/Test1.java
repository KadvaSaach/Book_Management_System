import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class Test1 {

	Login log = new Login();			//login page obj
	booksinfo bks = new booksinfo();	//booksinfo page obj
	boolean a = false;					//1st test case username
	boolean z = false;					//2nd test case book no
	boolean q = false;					//3rd test case year
	boolean g = false;					//4th test case title
	
	// 1st test case login page user name
	@Test
	void test1() throws NoSuchFieldException, SecurityException {
		assertEquals("", a, log.str("123"));			//username doesn't accept numeric value
	}
	
	// 2nd test case booksinfo page book no
	@Test
	void test2() throws NoSuchFieldException, SecurityException {
		assertEquals("", z, bks.bnvalid("asd") );		//book no only numeric values are allowed
	}
	
	//3rd test case booksifo page year
	@Test
	void test3() throws NoSuchFieldException, SecurityException {
		assertEquals("",q , bks.YearValid("qwe") );    //year only numeric values are allowed
	}
	
	//4th test case booksifo page title 
	@Test
	void test4() throws NoSuchFieldException, SecurityException {
		assertEquals("",g , bks.TitleValid("@asd%") );	//title only alphanumeric vales are allowed special characters are not allowed
	}
}
