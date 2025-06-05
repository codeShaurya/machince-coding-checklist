package banking.model;

import java.util.List;

public class AccountWithTrx extends Account {
    private List<Trx> trx;

    public AccountWithTrx(String accountId, double balance, String name, Long timestamp, List<Trx> trx) {
        super(accountId, balance, name, timestamp);
        this.trx = trx;
    }

    public AccountWithTrx(Account account, List<Trx> trx) {
        super(account);
        this.trx = trx;
    }

    public List<Trx> getTrx() {
        return trx;
    }

    public void setTrx(List<Trx> trx) {
        this.trx = trx;
    }

    @Override
    public String toString() {
        return "AccountWithTrx{" +
                "trx=" + trx +
                '}' + System.lineSeparator();
    }
}
