package model;

import data.Cards;
import data.Currency;

public class DebitCard extends Cards {

    public DebitCard(String name) {
        super(Currency.RUB, name);
    }

}
