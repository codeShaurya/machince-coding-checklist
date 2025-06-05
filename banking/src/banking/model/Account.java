package banking.model;

public class Account {
    private String accountId;
    private double balance;
    private String name;
    private Long timestamp;

    public Account(String accountId, double balance, String name, Long timestamp) {
        this.accountId = accountId;
        this.balance = balance;
        this.name = name;
        this.timestamp = timestamp;
    }

    public Account(Account account) {
        this.accountId = account.getAccountId();
        this.balance = account.getBalance();
        this.name = account.getName();
        this.timestamp = account.getTimestamp();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", timestamp=" + timestamp +
                '}' + System.lineSeparator();
    }
}
