import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankAccountTest
{
	BankAccount bn = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		bn = new BankAccount("Shradhdha Parsana");
	}

	@After
	public void tearDown() throws Exception
	{
	}

	// Testing Deposit
	@Test
	public void depositValid()
	{
		assertTrue(bn.deposit(300.00));
	}

	@Test
	public void depositInvalidNull()
	{
		assertFalse(bn.deposit(null));
	}

	@Test
	public void depositInvalidNagative()
	{
		assertFalse(bn.deposit(-300.00));
	}

	@Test
	public void depositInvalidZero()
	{
		assertFalse(bn.deposit(0.00));
	}

	// Testing Customer Name
	@Test
	public void getCustomerValid()
	{
		assertEquals("Shradhdha Parsana", bn.getCustomerName());
	}

	// Testing Withdraw
	@Test
	public void withDrawValid()
	{
		assertTrue(bn.withDraw(bn.getBalance()));
	}

	@Test
	public void withDrawInvalidNull()
	{
		assertFalse(bn.withDraw(null));
	}

	@Test
	public void withDrawInvalidNagative()
	{
		assertFalse(bn.withDraw(-50.00));
	}

	@Test
	public void withDrawInvalidZero()
	{
		assertFalse(bn.withDraw(0.00));
	}

	@Test
	public void withDrawInvalidMoreThanBalance()
	{
		assertTrue(bn.withDraw(bn.getBalance() + 50));
	}


	@Test(expected = Exception.class)
	public void openAccountWithNull() throws FileNotFoundException, Exception
	{
		bn = new BankAccount(null);
	}

	@Test(expected = Exception.class)
	public void openAccountWithEmptyString() throws FileNotFoundException, Exception
	{
		bn = new BankAccount("");
	}
}
