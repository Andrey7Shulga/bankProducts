package data;

import java.util.Objects;

public class MainProduct {

    protected Currency currency;
    protected String name;
    protected Double balance;

    public MainProduct(Currency currency, String name, Double balance) {
        this.currency = currency;
        this.name = name;
        this.balance = balance;
    }

    protected MainProduct() {}

    /**
     * Пополнение продукта
     * @param depositSum - сумма пополнения в соответствующей валюте
     */
    public void deposit(Double depositSum) {
        if (depositSum <= 0.0) {
            throw new IllegalArgumentException("Пополнение не может быть отрицательным или нулевым");
        }
        this.balance = getBalance() + depositSum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainProduct)) return false;
        MainProduct that = (MainProduct) o;
        return getCurrency() == that.getCurrency() && getName().equals(that.getName()) && getBalance().equals(that.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getName(), getBalance());
    }

    @Override
    public String toString() {
        return "MainProduct{" +
                "currency=" + currency +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
