package model;

import data.Currency;
import org.junit.jupiter.api.BeforeEach;
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
    void increase_ifDepositCard_balanceIsChangedAccordingly (Double sum) {
        Double balance = deposit.getBalance();
        deposit.deposit(sum);
        assertEquals(balance + sum, deposit.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00, -10.13})
    void increase_ifDepositCardInvalid_exceptionIsThrownAndBalanceIsTheSame (Double sum) {
        Double balance = deposit.getBalance();
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> deposit.deposit(sum));
        assertEquals("Пополнение не может быть отрицательным или нулевым", ex.getMessage());
        assertEquals(balance, deposit.getBalance());
    }

//    @ParameterizedTest
//    @ValueSource(doubles = {456.32, 2000.54})
//    void decrease_ifCreditCard_balanceIsChangedAccordingly (Double sum) {
//        //увеличить баланс
//        deposit.deposit(1000.54);
//        Double balance = deposit.getBalance();
//        System.out.println(balance);
//        //списание
//        deposit.credit(sum);
//        assertEquals(balance - sum, deposit.getBalance());
//        System.out.println(deposit.getBalance());
//    }
//
//    @ParameterizedTest
//    @ValueSource(doubles = {0.00})
//    void decrease_ifCreditCardInvalid_balanceIsTheSame (Double sum) {
//        Double balance = creditCard.getBalance();
//        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> creditCard.credit(sum));
//        assertEquals("Сумма снятия не может быть равна нулю, укажите другую сумму", ex.getMessage());
//        assertEquals(balance, creditCard.getBalance());
//    }
//
//    @ParameterizedTest
//    @ValueSource(doubles = {3000.55, 2000.55})
//    void decrease_ifCreditCardInvalid_2_balanceIsTheSame (Double sum) {
//        //увеличить баланс
//        creditCard.deposit(1000.54);
//        Double balance = creditCard.getBalance();
//        //списание
//        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> creditCard.credit(sum));
//        assertTrue(ex.getMessage().contains("больше баланса"));
//        assertTrue(ex.getMessage().contains("укажите другую сумму"));
//        assertEquals(balance, creditCard.getBalance());
//    }
//
//    @ParameterizedTest
//    @MethodSource("positiveDebtCalculation")
//    void getDebt_ifCreditCard_DebtIsCalculated (Double sum, int period, String result) {
//        //списать с карты
//        creditCard.credit(sum);
//        //Расчет
//        BigDecimal debt = creditCard.getDebtValue(period);
//        assertEquals(new BigDecimal(result), debt);
//    }
//
//    private static Stream<Arguments> positiveDebtCalculation() {
//        return Stream.of(
//                Arguments.of(1000.00, 365, "100.00"),
//                Arguments.of(800.00, 180, "39.45"),
//                Arguments.of(500.00, 0, "0.00")
//        );
//    }
//
//    @ParameterizedTest
//    @ValueSource(doubles = {0.00})
//    void getDebt_ifCreditCardInvalid_balanceIsTheSame (Double sum) {
//        Double balance = creditCard.getBalance();
//        //взять кредит
//        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> creditCard.credit(sum));
//        assertTrue(ex.getMessage().contains("Сумма снятия не может быть равна нулю, укажите другую сумму"));
//        assertEquals(balance, creditCard.getBalance());
//    }
//
//    @ParameterizedTest
//    @ValueSource(doubles = {1000.01, 3000})
//    void getDebt_ifCreditCardInvalid_2_balanceIsTheSame (Double sum) {
//        Double balance = creditCard.getBalance();
//        //списание
//        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> creditCard.credit(sum));
//        assertTrue(ex.getMessage().contains("больше баланса"));
//        assertTrue(ex.getMessage().contains("укажите другую сумму"));
//        assertEquals(balance, creditCard.getBalance());
//    }


}
