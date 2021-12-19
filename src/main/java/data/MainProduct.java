package data;

import lombok.Data;

@Data
public abstract class MainProduct {

    protected Currency currency;
    protected String name;
    protected Double balance;

    public MainProduct(Currency currency, String name) {
        this.currency = currency;
        this.name = name;
        this.balance = 0.0;
    }

    protected MainProduct() {
    }

    /**
     * Пополнение продукта
     * @param depositSum - сумма пополнения в соответствующей валюте
     */
    public void deposit(Double depositSum) {
        if (depositSum <= 0.0) {
            throw new IllegalArgumentException("Пополнение не может быть отрицательным или нулевым");
        }
        setBalance(getBalance() + depositSum);

    }
}
