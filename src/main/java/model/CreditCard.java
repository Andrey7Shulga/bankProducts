package model;

import data.Cards;
import data.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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

    public BigDecimal getDebtValue (int period) {
        period = Math.max(0, period);
        double delta = getLimit() - getBalance();
        if (delta > 0.00) {
            BigDecimal bd = new BigDecimal(delta*rate*period/(365*100));
            return bd.setScale(2, RoundingMode.FLOOR);
        }
        return null;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        if (!super.equals(o)) return false;
        CreditCard that = (CreditCard) o;
        return Double.compare(that.getRate(), getRate()) == 0 && Double.compare(that.getLimit(), getLimit()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRate(), getLimit());
    }


    @Override
    public String toString() {
        return super.toString() + "CreditCard{" +
                "rate=" + rate +
                ", limit=" + limit +
                '}';
    }
}
