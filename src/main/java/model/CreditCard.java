package model;

import data.Cards;
import data.Currency;


public class CreditCard extends Cards {

    private double rate;
    private double limit;

    public CreditCard(String name, Double rate, Double limit) {
        super(Currency.RUB, name);
        this.rate = rate;
        this.limit = Math.max(limit, 0.00);
        super.balance = getBalance() + this.limit;
    }

    @Override
    public Double getBalance() {return super.getBalance();}

    @Override
    public void deposit(Double depositSum) {
        super.deposit(depositSum);
    }

    @Override
    public void credit(Double creditSum) {super.credit(creditSum);}

    public Double getDebtValue (int period) {
        period = Math.max(0, period);
        if (getBalance() < 0) {
            return getBalance() + getBalance()*rate*period/(365*100);
        }
        return 0.0;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = Math.max(limit, 0.00);
    }

    @Override
    public String toString() {
        return super.toString() + "CreditCard{" +
                "rate=" + rate +
                ", limit=" + limit +
                '}';
    }
}
