package model;

import data.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DebitCurrencyCardTest {

    private DebitCurrencyCard debitCurrencyCard;


    @Test
    void createCardWithWrongCurrency_exceptionCall() {
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> new DebitCurrencyCard(Currency.RUB, "Smite"));
        assertEquals("Валютой вклада должна быть 'EUR' или 'USD'", ex.getMessage());
    }

    @ParameterizedTest
    @MethodSource("differentCurrencyAndSum")
    void increase_ifDepositCard_balanceIsChangedAccordingly (DebitCurrencyCard card, Double sum) {
        debitCurrencyCard = card;
        Double balance = debitCurrencyCard.getBalance();
        debitCurrencyCard.deposit(sum);
        assertEquals(balance + sum, debitCurrencyCard.getBalance());
    }

    private static Stream<Arguments> differentCurrencyAndSum() {
        return Stream.of(
            Arguments.of(new DebitCurrencyCard(Currency.EUR, "Smite"), 3456.32),
            Arguments.of(new DebitCurrencyCard(Currency.USD, "Smite"), 3456.32)
        );
    }

    @ParameterizedTest
    @MethodSource("differentCurrencyAndSumInvalid")
    void increase_ifDepositCardInvalid_exceptionIsThrownAndBalanceIsTheSame (DebitCurrencyCard card, Double sum) {
        debitCurrencyCard = card;
        Double balance = debitCurrencyCard.getBalance();
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> debitCurrencyCard.deposit(sum));
        assertEquals("Пополнение не может быть отрицательным или нулевым", ex.getMessage());
        assertEquals(balance, debitCurrencyCard.getBalance());
    }

    private static Stream<Arguments> differentCurrencyAndSumInvalid() {
        return Stream.of(
                Arguments.of(new DebitCurrencyCard(Currency.EUR, "Smite"), 0.00),
                Arguments.of(new DebitCurrencyCard(Currency.USD, "Smite"), 0.00),
                Arguments.of(new DebitCurrencyCard(Currency.EUR, "Smite"), -10.13),
                Arguments.of(new DebitCurrencyCard(Currency.USD, "Smite"), -10.13)
        );
    }

    @ParameterizedTest
    @MethodSource("differentCurrencyAndSumDecrease")
    void decrease_ifCreditCard_balanceIsChangedAccordingly (DebitCurrencyCard card, Double sum) {
        debitCurrencyCard = card;
        //увеличить баланс
        debitCurrencyCard.deposit(1000.54);
        Double balance = debitCurrencyCard.getBalance();
        //списание
        debitCurrencyCard.credit(sum);
        assertEquals(balance - sum, debitCurrencyCard.getBalance());
    }

    private static Stream<Arguments> differentCurrencyAndSumDecrease() {
        return Stream.of(
                Arguments.of(new DebitCurrencyCard(Currency.EUR, "Smite"), 456.32),
                Arguments.of(new DebitCurrencyCard(Currency.USD, "Smite"), 1000.54),
                Arguments.of(new DebitCurrencyCard(Currency.EUR, "Smite"), 456.32),
                Arguments.of(new DebitCurrencyCard(Currency.USD, "Smite"), 1000.54)
        );
    }

    @ParameterizedTest
    @MethodSource("differentCurrencyAndSum2")
    void decrease_ifCreditCardInvalid_balanceIsTheSame (DebitCurrencyCard card, Double sum, String text) {
        debitCurrencyCard = card;
        //увеличить баланс
        debitCurrencyCard.deposit(1000.54);
        Double balance = debitCurrencyCard.getBalance();
        //списание
        Throwable ex = assertThrowsExactly(IllegalArgumentException.class, () -> debitCurrencyCard.credit(sum));
        assertEquals(text, ex.getMessage());
        assertEquals(balance, debitCurrencyCard.getBalance());
    }

    private static Stream<Arguments> differentCurrencyAndSum2() {
        return Stream.of(
                Arguments.of(new DebitCurrencyCard(Currency.EUR, "Smite"), 0.00,
                        "Сумма снятия не может быть равна нулю, укажите другую сумму"),
                Arguments.of(new DebitCurrencyCard(Currency.USD, "Smite"), 0.00,
                        "Сумма снятия не может быть равна нулю, укажите другую сумму"),
                Arguments.of(new DebitCurrencyCard(Currency.EUR, "Smite"), 1500.00,
                        "Сумма снятия больше баланса, укажите другую сумму"),
                Arguments.of(new DebitCurrencyCard(Currency.USD, "Smite"), 1500.00,
                        "Сумма снятия больше баланса, укажите другую сумму")
        );
    }

}
