package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	//przypadkiem pominąłem punkty 5 poprawiłem błedy ale już nie pamiętam w których testach były błedy

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	/*
	 * Testuje dodawanie i usuwanie planowanych płatności na koncie za pomocą metod addTimedPayment i removeTimedPayment.
	 * Sprawdza, czy dodana płatność istnieje po dodaniu oraz czy została usunięta po wywołaniu removeTimedPayment.
	 */
	@Test
	public void testAddRemoveTimedPayment() {
	testAccount.addTimedPayment("payment1", 2, 1, new Money(1000, SEK), SweBank, "Alice");
	assertTrue(testAccount.timedPaymentExists("payment1"));
	testAccount.removeTimedPayment("payment1");
	assertFalse(testAccount.timedPaymentExists("payment1"));
	}
	/*
	 * Testuje poprawność działania planowanych płatności na koncie za pomocą metody addTimedPayment.
	 * Sprawdza, czy po upływie określonej liczby ticków, stan konta docelowego jest zgodny
	 * z oczekiwanym saldem po wykonaniu planowanej płatności.
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
	testAccount.addTimedPayment("payment1", 2, 1, new Money(1000, SEK), SweBank, "Alice");
	testAccount.tick();
	testAccount.tick();
	assertEquals(new Money(1001000, SEK).getAmount(), SweBank.getBalance("Alice"));
	}
	/*
	 * Testuje poprawność dodawania środków na konto za pomocą metody deposit oraz poprawność stanu konta po depozycie.
	 */
	@Test
	public void testAddWithdraw() {
	testAccount.deposit(new Money(5000, SEK));
	assertEquals(new Money(10005000, SEK).toString(), testAccount.getBalance().toString());
	testAccount.withdraw(new Money(2000, SEK));
	assertEquals(new Money(10003000, SEK).toString(), testAccount.getBalance().toString());
	}
	/*
	 * Testuje poprawność pobierania stanu konta za pomocą metody getBalance.
	 * Sprawdza, czy stan konta jest zgodny z oczekiwanym po utworzeniu konta.
	 */
	@Test
	public void testGetBalance() {
		assertEquals(new Money(10000000, SEK).toString(), testAccount.getBalance().toString());
	}
}
