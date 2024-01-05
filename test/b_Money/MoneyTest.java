package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest
	{
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

	@Before
	public void setUp() throws Exception
		{
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
		}


	/*
	* funckja wywołuje funkcje @assertEquals()
	* która sprawdza poprawność działania funckji
	* zwracającej ilość pieniędzy
	*/
	@Test
	public void testGetAmount()
		{
		assertEquals(10000, (int) SEK100.getAmount());
		assertEquals(1000, (int) EUR10.getAmount());
		assertEquals(20000, (int) SEK200.getAmount());
		assertEquals(2000, (int) EUR20.getAmount());
		assertEquals(0, (int) SEK0.getAmount());
		assertEquals(0, (int) EUR0.getAmount());
		assertEquals(-10000, (int) SEKn100.getAmount());
		}

	/*
	* funkcja sprawdza działanie działanie
	* funkcji getCurrency któa zwracaaklase Currency danej pieniedzy
	 */
	@Test
	public void testGetCurrency()
		{

		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(SEK, SEK200.getCurrency());
		assertEquals(EUR, EUR20.getCurrency());
		assertEquals(SEK, SEK0.getCurrency());
		assertEquals(EUR, EUR0.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());

		}

	/*
	 * funckja sprawdza działanie działania funkcji toString
	 * która zwraca sformatowaną reprezentację pieniędzy.
	 */
	@Test
	public void testToString()
		{
		assertEquals("100,00 SEK", SEK100.toString());
		assertEquals("20,00 EUR", EUR20.toString());
		assertEquals("-100,00 SEK", SEKn100.toString());
		assertEquals("0,00 EUR", EUR0.toString());

		}

	/*
	* Test sprawdza poprawność obliczania wartości uniwersalnej
	*/
	@Test
	public void testGlobalValue()
		{
		assertEquals(1500, (int) SEK100.universalValue(), 0.001);
		assertEquals(1500, (int) EUR10.universalValue(), 0.001);
		assertEquals(3000, (int) SEK200.universalValue(), 0.001);
		assertEquals(3000, (int) EUR20.universalValue(), 0.001);
		assertEquals(0, (int) SEK0.universalValue(), 0.001);
		assertEquals(0, (int) EUR0.universalValue(), 0.001);
		assertEquals(-1500, (int) SEKn100.universalValue(), 0.001);
		}

	/*
	 *  Test sprawdza poprawność działania metody `equalsMoney`
	 * porównującej obiekty Money dla różnych kwot i walut.
	 */
	@Test
	public void testEqualsMoney()
		{

		assertTrue(SEK100.equals(new Money(10000, SEK)));
		assertFalse(EUR20.equals(new Money(2500, EUR)));
		assertTrue(SEK0.equals(EUR0));
		assertFalse(SEKn100.equals(new Money(-1000, SEK)));
		}

	/*
	 * Test sprawdza poprawność działania metody `add`
	 * dla dodawania dwóch obiektów Money o różnych kwotach i walutach.
		 */
	@Test
	public void testAdd()
		{

		Money result1 = SEK100.add(EUR10);
		assertEquals(10100, (int) result1.getAmount());
		assertEquals(SEK, result1.getCurrency());
		}

	/*
	 * Test sprawdza poprawność działania metody `sub`
	 * dla odejmowania dwóch obiektów Money o różnych kwotach i walutach.
	 */
	@Test
	public void testSub()
		{

		Money result1 = SEK200.sub(SEK100);
		assertEquals(10000, (int) result1.getAmount());
		assertEquals(SEK, result1.getCurrency());

		Money result2 = EUR20.sub(EUR10);
		assertEquals(1000, (int) result2.getAmount());
		assertEquals(EUR, result2.getCurrency());
		}

	/*
	* Testuje, czy metoda isZero zwraca poprawnie true dla obiektu Money
	* o wartości zero (SEK0) i false dla obiektów o wartościach różnych od zera (EUR20, SEKn100).
	 */
	@Test
	public void testIsZero()
		{
		assertTrue(SEK0.isZero());
		assertFalse(EUR20.isZero());
		assertFalse(SEKn100.isZero());
		}
	/*
	 * Testuje poprawność działania metody negate, sprawdzając czy obiekty Money
	 * są poprawnie negowane i czy zmieniają walutę oraz wartość na przeciwną.
	 */
	@Test
	public void testNegate()
		{
		Money negated1 = SEK100.negate();
		assertEquals(-10000, (int) negated1.getAmount());
		assertEquals(SEK, negated1.getCurrency());

		Money negated2 = EUR20.negate();
		assertEquals(-2000, (int) negated2.getAmount());
		assertEquals(EUR, negated2.getCurrency());
		}
	/*
	 * Testuje porównywanie obiektów Money za pomocą metody compareTo.
	 * Sprawdza, czy porównania zwracają poprawne wyniki dla różnych przypadków.
	 */
	@Test
	public void testCompareTo()
		{
		assertTrue(SEK100.compareTo(SEK200) < 0);
		assertTrue(EUR20.compareTo(EUR10) > 0);
		assertEquals(0, SEK0.compareTo(EUR0));
		assertTrue(SEKn100.compareTo(SEK0) < 0);
		}
	}