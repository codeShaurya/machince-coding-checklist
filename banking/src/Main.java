import banking.service.AccountService;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        try {
            accountService.createAccount("1", "Krishna", System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            accountService.createAccount("2", "Balram", System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            accountService.createAccount("3", "Madhumangal", System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        accountService.deposit("1", 10000, System.currentTimeMillis());
        accountService.deposit("1", 2000, System.currentTimeMillis());
        accountService.deposit("2", 1000, System.currentTimeMillis());
        accountService.deposit("2", 2000, System.currentTimeMillis());
        accountService.deposit("2", 3000, System.currentTimeMillis());

        accountService.deposit("3", 5000, System.currentTimeMillis());
        accountService.deposit("3", 4000, System.currentTimeMillis());

        try {
            accountService.withdraw("3", 5000, System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            accountService.withdraw("1", 8000, System.currentTimeMillis());
            accountService.withdraw("1", 100, System.currentTimeMillis());
            accountService.withdraw("1", 100, System.currentTimeMillis());
            accountService.withdraw("2", 100, System.currentTimeMillis());
            accountService.withdraw("2", 150, System.currentTimeMillis());
            accountService.withdraw("2", 300, System.currentTimeMillis());
            accountService.withdraw("3", 700, System.currentTimeMillis());
            accountService.withdraw("3", 100, System.currentTimeMillis());
            accountService.withdraw("3", 1000, System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            accountService.transfer("1", "2", 400, System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("TOP ACCOUNTS");
            System.out.println(accountService.getTopN(2, System.currentTimeMillis()));
            System.out.println("TOP ACCOUNTS WITH MORE DEPOSIT");
            System.out.println(accountService.getTopAccountWithMoreWithdrawl(2, System.currentTimeMillis()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
//            System.out.println(accountService.getAccountWithTrx("1"));
//            System.out.println(accountService.getAccountWithTrx("2"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}