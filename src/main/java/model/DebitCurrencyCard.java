package model;

import data.Cards;
import data.Currency;

public class DebitCurrencyCard extends Cards {

    public DebitCurrencyCard(Currency currency, String name) {
        super(currency, name);
        if (currency == Currency.RUB) {
            throw new IllegalArgumentException("Валютой вклада должна быть 'EUR' или 'USD'");
        }
    }

}
