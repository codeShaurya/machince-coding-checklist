package banking.service;

import banking.model.Account;
import banking.model.AccountWithTrx;
import banking.model.Trx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private Map<String, Account> accounts;
    private Map<String, List<Trx>> trxs;

    public AccountService() {
        accounts = new HashMap<>();
        trxs = new HashMap<>();
    }

    public void createAccount(String accountId, String name, Long timestamp) {
        if (accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account already exists");
        }
        accounts.put(accountId, new Account(accountId, 0.0, name, timestamp));
    }

    public void deposit(String accountId, double amount, Long timestamp) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account does not exist");
        }
        Account account = accounts.get(accountId);
        account.setBalance(account.getBalance() + amount);
        Trx trx = new Trx(accountId, amount, Trx.Type.DEPOSIT, Trx.Direction.CREDIT, timestamp);
        if(!trxs.containsKey(accountId)) {
            trxs.put(accountId, new ArrayList<>());
        }

        trxs.get(accountId).add(trx);
    }

    public void withdraw(String accountId, double amount, Long timestamp) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account does not exist");
        }
        Account account = accounts.get(accountId);
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Trx trx = new Trx(accountId, amount, Trx.Type.WITHDRAWAL, Trx.Direction.DEBIT, timestamp);
        if(!trxs.containsKey(accountId)) {
            trxs.put(accountId, new ArrayList<>());
        }

        trxs.get(accountId).add(trx);
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public AccountWithTrx getAccountWithTrx(String accountId) {
        if (!accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account does not exist");
        }
        Account account = accounts.get(accountId);
        List<Trx> trxList = trxs.get(accountId);
        if( trxList == null ) {
            trxList = new ArrayList<>();
        }

        return new AccountWithTrx(account, trxList);
    }

    public void transfer(String fromAccountId, String toAccountId, double amount, Long timestamp) {
        if (!accounts.containsKey(fromAccountId) || !accounts.containsKey(toAccountId)) {
            throw new IllegalArgumentException("One or both accounts do not exist");
        }
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance in the source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Trx trxFrom = new Trx(fromAccountId, amount, Trx.Type.TRANSFER, Trx.Direction.DEBIT, timestamp);
        Trx trxTo = new Trx(toAccountId, amount, Trx.Type.TRANSFER, Trx.Direction.CREDIT, timestamp);

        if(!trxs.containsKey(fromAccountId)) {
            trxs.put(fromAccountId, new ArrayList<>());
        }
        if(!trxs.containsKey(toAccountId)) {
            trxs.put(toAccountId, new ArrayList<>());
        }

        trxs.get(fromAccountId).add(trxFrom);
        trxs.get(toAccountId).add(trxTo);
    }

    public List<Account> getTopN(Integer N, long timestamp) {
    List<Account> topAccounts = new ArrayList<>(accounts.values());
    topAccounts.sort((a, b) -> Double.compare(b.getBalance(), a.getBalance()));
    List<Account> result = new ArrayList<>();
    for (int i = 0; i < Math.min(N, topAccounts.size()); i++) {
        Account account = topAccounts.get(i);
        if (account.getTimestamp() <= timestamp) {
            result.add(account);
        }
    }

    return result;
    }

    public Map<Integer, AccountWithTrx> getTopAccountWithMoreWithdrawl(Integer N, long timestamp) {
        Map<Integer, AccountWithTrx> topAccounts = new java.util.TreeMap<>(java.util.Collections.reverseOrder());

        for (Account account : accounts.values()) {
            if (account.getTimestamp() <= timestamp) {
                List<Trx> trxList = trxs.getOrDefault(account.getAccountId(), new ArrayList<>());
                List<Trx> newTrx = trxList.stream()
                        .filter(trx -> trx.getType() == Trx.Type.WITHDRAWAL)
                        .filter(trx -> trx.getTimestamp() <= timestamp)
                        .toList();

                int sum = (int) newTrx.stream().mapToDouble(Trx::getAmount).sum();

                topAccounts.put(sum, new AccountWithTrx(account, newTrx));
            }
        }

        return topAccounts.entrySet().stream()
                .limit(N)
                .collect(java.util.LinkedHashMap::new,
                        (m, e) -> m.put(e.getKey(), e.getValue()),
                        java.util.LinkedHashMap::putAll
                );
    }
}
