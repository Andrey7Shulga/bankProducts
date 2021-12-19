import data.Credit;
import data.Currency;
import data.MainProduct;

public class Deposit extends MainProduct {

    private boolean isActive;

    public Deposit(Currency currency, String name) {
        super(currency, name);
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
    public Double getBalance() {
        if(!getActive()) {
            throw new IllegalArgumentException("Вклад закрыт. Баланс нулевой");
        }
        return super.getBalance();
    }

    public void closeDeposit (Deposit deposit) {
        if (deposit.getBalance() > 0.0) {
            throw new IllegalArgumentException("Нельзя закрыть вклад с ненулевм остатком");
        }
        deposit.setActive(false);
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
