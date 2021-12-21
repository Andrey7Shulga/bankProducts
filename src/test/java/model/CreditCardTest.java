package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    private CreditCard creditCard;

    @BeforeEach
    void settingUP() {
        creditCard = new CreditCard("Petrov", 3.54, 1000.00);
    }

    @ParameterizedTest
    @ValueSource(doubles = {3456.32, 0.13})
    void increase_ifDepositCard_balanceIsChangedAccordingly (Double sum) {
        Double balance = creditCard.getBalance();
        creditCard.deposit(sum);
        assertEquals(balance + sum, creditCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00, -10.13})
    void increase_ifDepositCardInvalid_exceptionIsThrownAndBalanceIsTheSame (Double sum) {
        Double balance = creditCard.getBalance();
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> creditCard.deposit(sum));
        assertEquals("Пополнение не может быть отрицательным или нулевым", ex.getMessage());
        assertEquals(balance, creditCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {456.32, 2000.54})
    void decrease_ifCreditCard_balanceIsChangedAccordingly (Double sum) {
        //увеличить баланс
        creditCard.deposit(1000.54);
        Double balance = creditCard.getBalance();
        System.out.println(balance);
        //списание
        creditCard.credit(sum);
        assertEquals(balance - sum, creditCard.getBalance());
        System.out.println(creditCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00})
    void decrease_ifCreditCardInvalid_balanceIsTheSame (Double sum) {
        Double balance = creditCard.getBalance();
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> creditCard.credit(sum));
        assertEquals("Сумма снятия не может быть равна нулю, укажите другую сумму", ex.getMessage());
        assertEquals(balance, creditCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {2000.55})
    void decrease_ifCreditCardInvalid_2_balanceIsTheSame (Double sum) {
        //увеличить баланс
        creditCard.deposit(1000.54);
        Double balance = creditCard.getBalance();
        //списание
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> creditCard.credit(sum));
        assertTrue(ex.getMessage().contains("больше баланса"));
        assertTrue(ex.getMessage().contains("укажите другую сумму"));
        assertEquals(balance, creditCard.getBalance());
    }


}
