package data;


public class Cards extends MainProduct{

    public Cards(Currency currency, String name) {
        super(currency, name, 0.00);
    }

    protected Cards() {}

    public void credit(Double creditSum) {
        if (creditSum <= getBalance() && creditSum != 0.00) {
            this.balance = getBalance()-creditSum;
        } else if (creditSum == 0.00) {
            throw new IllegalArgumentException("Сумма снятия не может быть равна нулю, укажите другую сумму");
        } else {
            throw new IllegalArgumentException(String.format("Сумма снятия '%s' больше баланса '%s', укажите другую сумму", creditSum, getBalance()));
        }
    }

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
        return "Card:" + super.toString();
    }
}
