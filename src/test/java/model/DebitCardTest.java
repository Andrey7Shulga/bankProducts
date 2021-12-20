package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DebitCardTest {

    private DebitCard debitCard;

    @BeforeEach
    void settingUP() {
        debitCard = new DebitCard("Petrov");
    }

    @ParameterizedTest
    @ValueSource(doubles = {3456.32, 0.13})
    void increase_ifDepositCard_balanceIsChangedAccordingly (Double sum) {
        Double balance = debitCard.getBalance();
        debitCard.deposit(sum);
        assertEquals(balance + sum, debitCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00, -10.13})
    void increase_ifDepositCardInvalid_exceptionIsThrownAndBalanceIsTheSame (Double sum) {
        Double balance = debitCard.getBalance();
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> debitCard.deposit(sum));
        assertEquals("Пополнение не может быть отрицательным или нулевым", ex.getMessage());
        assertEquals(balance, debitCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {456.32, 1000.54})
    void decrease_ifCreditCard_balanceIsChangedAccordingly (Double sum) {
        //увеличить баланс
        debitCard.deposit(1000.54);
        Double balance = debitCard.getBalance();
        //списание
        debitCard.credit(sum);
        assertEquals(balance - sum, debitCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00})
    void decrease_ifCreditCardInvalid_balanceIsTheSame (Double sum) {
        Double balance = debitCard.getBalance();
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> debitCard.credit(sum));
        assertEquals("Сумма снятия не может быть равна нулю, укажите другую сумму", ex.getMessage());
        assertEquals(balance, debitCard.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1500.00})
    void decrease_ifCreditCardInvalid_2_balanceIsTheSame (Double sum) {
        //увеличить баланс
        debitCard.deposit(1000.54);
        Double balance = debitCard.getBalance();
        //списание
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> debitCard.credit(sum));
        assertEquals("Сумма снятия больше баланса, укажите другую сумму", ex.getMessage());
        assertEquals(balance, debitCard.getBalance());
    }


}
