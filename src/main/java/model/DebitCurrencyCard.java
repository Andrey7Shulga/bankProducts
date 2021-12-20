package model;

import data.Credit;
import data.Currency;
import data.MainProduct;

public class DebitCurrencyCard extends MainProduct implements Credit {

    public DebitCurrencyCard(Currency currency, String name) {
        if (currency == Currency.RUB) {
            throw new IllegalArgumentException("Валютой вклада должна быть 'EUR' или 'USD'");
        }
            super.currency = currency;
            super.name = name;
    }

    @Override
    public void deposit(Double depositSum) {
        super.deposit(depositSum);
    }

    @Override
    public void credit(Double creditSum) {
        if (creditSum <= getBalance()) {
            setBalance(getBalance()-creditSum);
        } else {
            throw new IllegalArgumentException("Сумма снятия больше баланса, укажите другую сумму");
        }
    }

}
