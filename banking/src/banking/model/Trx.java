package banking.model;

public class Trx {
    private String accountId;
    private double amount;
    private Type type;
    private Direction direction;
    private Long timestamp;

    public Trx(String accountId, double amount, Type type, Direction direction, Long timestamp) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.direction = direction;
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public enum Type {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    public enum Direction {
        CREDIT, DEBIT
    }

    @Override
    public String toString() {
        return "Trx{" +
                "accountId='" + accountId + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", direction=" + direction +
                ", timestamp=" + timestamp +
                '}' + System.lineSeparator();
    }
}
