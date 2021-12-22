package model;

import data.Cards;
import data.Currency;

public class DebitCard extends Cards {

    public DebitCard(String name) {
        super(Currency.RUB, name);
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
        return "DebitCard{" +
                "currency=" + currency +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
