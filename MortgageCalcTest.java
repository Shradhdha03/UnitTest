import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/*
 * Program Name: MortgageCalcTest.java 
 * Author: Shradhdha Parsana 
 * Date: Feb 28,2018 8:17:24 AM 
 * Description:
 */
public class MortgageCalcTest
{

	static MortgageCalc mcalc;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		mcalc = new MortgageCalc();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		mcalc = null;
	}

	// **********All Valid********
	@Test
	public void testValidAll() throws MortgageException
	{

		double expected = Math.round(468.4031529867958);
		double actual = Math.round(mcalc.calculateMonthlyPayment(10000, 2, 11.50));
		assertEquals(expected, actual);
	}

	// **********LoanAmount********
	@Test(expected = MortgageException.class)
	public void testInvalidLoanAmountZero() throws MortgageException
	{
		Math.round(mcalc.calculateMonthlyPayment(0, 2, 11.50));
	}

	@Test(expected = MortgageException.class)
	public void testInvalidLoanAmountNull() throws MortgageException
	{
		Math.round(mcalc.calculateMonthlyPayment((Integer) null, 2, 11.50));
	}

	@Test(expected = MortgageException.class)
	public void testInvalidLoanAmountNagative() throws MortgageException
	{
		Math.round(mcalc.calculateMonthlyPayment(-10000, 2, 11.50));
	}

	// **********TermInYears*********
	@Test(expected = Exception.class)
	public void testInvalidTermInYearsZero() throws Exception
	{
		Math.round(mcalc.calculateMonthlyPayment(10000, 0, 11.50));
	}

	@Test(expected = Exception.class)
	public void testInvalidTermInYearsNull() throws Exception
	{
		Math.round(mcalc.calculateMonthlyPayment(10000, (Integer) null, 11.50));
	}

	@Test(expected = Exception.class)
	public void testInvalidTermInYearsNagative() throws Exception
	{
		Math.round(mcalc.calculateMonthlyPayment(10000, -10, 11.50));
	}

	// **********InterestRate*********
	@Test(expected = Exception.class)
	public void testInvalidInterestRateNull() throws Exception
	{
		Math.round(mcalc.calculateMonthlyPayment(10000, 10, (Double) null));
	}

	@Test(expected = Exception.class)
	public void testInvalidInterestRateNagative() throws Exception
	{
		Math.round(mcalc.calculateMonthlyPayment(10000, 10, -11.50));
	}

	// **********TermInYears and InterestRate*********
	@Test(expected = ArithmeticException.class)
	public void testInvalidTermInYearsInterestRateZero() throws ArithmeticException, MortgageException
	{
		Math.round(mcalc.calculateMonthlyPayment(10000, 0, 0));
	}

}
