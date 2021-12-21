package data;


public class Cards extends MainProduct{

    public Cards(Currency currency, String name) {
        super(currency, name, 0.00);
    }

    protected Cards() {}

    public void credit(Double creditSum) {
        if (creditSum <= getBalance() && creditSum != 0.00) {
            setBalance(getBalance()-creditSum);
        } else if (creditSum == 0.00) {
            throw new IllegalArgumentException("Сумма снятия не может быть равна нулю, укажите другую сумму");
        } else {
            throw new IllegalArgumentException(String.format("Сумма снятия '%s' больше баланса '%s', укажите другую сумму", creditSum, getBalance()));
        }
    }

    @Override
    public String toString() {
        return "Card:" + super.toString();
    }
}
