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

    @Override
    public Double getBalance() {return super.getBalance();}

    @Override
    public void deposit(Double depositSum) {super.deposit(depositSum);}

    @Override
    public void credit(Double creditSum) {super.credit(creditSum);}

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "DebitCurrencyCard{" +
                "currency=" + currency +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
