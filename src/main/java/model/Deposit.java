package model;

import data.CloseProduct;
import data.Currency;
import data.MainProduct;

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
    public String toString() {
        return "Deposit:" + super.toString();
    }
}
