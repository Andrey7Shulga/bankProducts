package model;

import data.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {

    private Deposit deposit;

    @BeforeEach
    void settingUP() {
        deposit = new Deposit(Currency.RUB, "Sidorov");
    }

    @ParameterizedTest
    @ValueSource(doubles = {3456.32, 0.13})
    void increase_ifDepositDeposit_balanceIsChangedAccordingly (Double sum) {
        Double balance = deposit.getBalance();
        deposit.deposit(sum);
        assertEquals(balance + sum, deposit.getBalance());
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.00, -10.13})
    void increase_ifDepositInvalid_exceptionIsThrownAndBalanceIsTheSame (Double sum) {
        Double balance = deposit.getBalance();
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> deposit.deposit(sum));
        assertEquals("Пополнение не может быть отрицательным или нулевым", ex.getMessage());
        assertEquals(balance, deposit.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {5000.32})
    void close_ifCloseDeposit_balanceIsNullAndNotActive (Double sum) {
        //увеличить баланс
        deposit.deposit(sum);
        //закрытие
        deposit.closeProduct();
        assertEquals(0.0, deposit.getBalance());
        assertFalse(deposit.getActive());
    }

    @Test
    void close2_ifCloseDeposit_balanceIsNullAndNotActive () {
        //закрытие депозита с нулевым балансом
        deposit.closeProduct();
        assertEquals(0.0, deposit.getBalance());
        assertFalse(deposit.getActive());
    }

    @Test
    void increaseBalance_ifDepositIsClosed_balanceIsNull () {
        //закрытие депозита
        deposit.closeProduct();
        //пополнение закрытого депозита
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> deposit.deposit(3000.0));
        assertAll (
                () -> assertEquals("Нельзя пополнить закрытый вклад", ex.getMessage()),
                () -> assertEquals(0.0, deposit.getBalance()),
                () -> assertFalse(deposit.getActive())
        );
    }
}
