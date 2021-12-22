package model;

import data.CloseProduct;
import data.Currency;
import data.MainProduct;

import java.util.Objects;

public class Deposit extends MainProduct implements CloseProduct {

    private boolean isActive;

    public Deposit(Currency currency, String name) {
        super(currency, name, 0.00);
        this.isActive = true;
    }

    @Override
    public void deposit(Double depositSum) {
        if(!getActive()) {
            throw new IllegalArgumentException("Нельзя пополнить закрытый вклад");
        }
        super.deposit(depositSum);
    }

    @Override
    public Double getBalance() {return super.getBalance();}

    public boolean getActive() {
        return isActive;
    }

    @Override
    public void closeProduct() {
        if (getBalance() > 0) {
            this.balance = 0.0;
        }
        isActive = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deposit)) return false;
        if (!super.equals(o)) return false;
        Deposit deposit = (Deposit) o;
        return getActive() == deposit.getActive();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getActive());
    }

    @Override
    public String toString() {
        return "Deposit:" + super.toString();
    }
}
