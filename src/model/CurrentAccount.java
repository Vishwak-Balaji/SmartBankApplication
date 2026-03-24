package model;

public class CurrentAccount extends Account {
    private static final int Min_Balance=5000;

    public CurrentAccount(String accountNumber, String holderName,double balance){
        super(accountNumber, holderName, AccountType.CURRENT, balance);
    }

    public String withdraw(double amount){
        if (isFrozen()) {
            addTransaction("Withdraw", amount, "FAILURE");
            return "Account is frozen";
        }
        if(amount>getBalance()){
            addTransaction("Withdraw", amount, "FAILURE");
            return "Insufficient balance";
        }
        if(getBalance() - amount < Min_Balance){
            addTransaction("Withdraw", amount, "FAILURE");
            return "violating minimum balance";
        }
        setBalance(getBalance()-amount);
        incrementSessionWithdrawalCount();
        addTransaction("Withdraw", amount, "SUCCESS");
        return "SUCCESS";
    }
    public double getInterestRate(){
        return 0.0;
    }
}
