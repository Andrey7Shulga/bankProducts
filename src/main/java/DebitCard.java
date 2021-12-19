import data.Credit;
import data.Currency;
import data.MainProduct;

public class DebitCard extends MainProduct implements Credit {

    public DebitCard(String name) {
        super(Currency.RUB, name);
    }

    @Override
    public void deposit(Double depositSum) {
        super.deposit(depositSum);
    }

    @Override
    public void credit(Double creditSum) {
        if (creditSum <= getBalance()) {
            setBalance(getBalance()-creditSum);
        } else {
            throw new IllegalArgumentException("Сумма снятия больше баланса, укажите другую сумму");
        }
    }

}
