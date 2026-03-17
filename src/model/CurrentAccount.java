package model;

public class CurrentAccount extends Account {
    private static final int Min_Balance=5000;

    public CurrentAccount(String accountNumber, String holderName,double balance){
        super(accountNumber, holderName, AccountType.CURRENT, balance);
    }

    public boolean withdraw(double amount){
        if (isFrozen()) {
            addTransaction("Withdraw", amount, "Account is frozen");
            return false;
        }
        if(amount>getBalance()){
            addTransaction("Withdraw", amount, "Insufficient balance");
            return false;
        }
        if(Min_Balance<getBalance()-amount){
            addTransaction("Withdraw", amount, "violating minimum balance");
            return false;
        }
        setBalance(getBalance()-amount);
        incrementSessionWithdrawalCount();
        addTransaction("Withdraw", amount, "Success");
        return true;
    }
    public double getInterestRate(){
        return 0.0;
    }
}
