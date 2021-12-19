import data.Credit;
import data.Currency;
import data.MainProduct;


public class CreditCard extends MainProduct implements Credit {

    private final double rate;
    private final double limit = 1000.0;

    public CreditCard(String name, Double rate) {
        super(Currency.RUB, name);
        super.balance = 0.0 - limit;
        this.rate = rate;
    }

    @Override
    public void deposit(Double depositSum) {
        super.deposit(depositSum);
    }

    @Override
    public void credit(Double creditSum) {
        double limitAllowed = getBalance() + limit;
        if (limitAllowed >= creditSum) {
            setBalance(getBalance()-creditSum);
        } else {
            throw new IllegalArgumentException(String.format("Сумма снятия '%s' больше допустимого лимита '%s', укажите другую сумму", creditSum, limitAllowed));
        }
    }

    public Double getDebtValue (int period) {
        if (getBalance() < 0) {
            return getBalance() + getBalance()*rate*period/(365*100);
        }
        return 0.0;
    }

    @Override
    public Double getBalance() {
        return super.getBalance();
    }
}
