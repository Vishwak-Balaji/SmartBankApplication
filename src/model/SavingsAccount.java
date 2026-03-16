package model;

public class SavingsAccount extends Account {
    private static final int Min_Balance = 1000;
    private static final int DailyWithdrawalLimit = 10000;

    public SavingsAccount(String accountNumber , String holderName,double balance){
        super(accountNumber,holderName,AccountType.SAVINGS,balance);
    }
    public boolean withdraw(double amount){
        if(isFrozen()){
            addTransaction("Withdraw", amount, "The account is frozen");
            return false;
        }
        if(amount>DailyWithdrawalLimit){
            addTransaction("Withdraw", amount, "Exceeds daily withdrawal limit");
            return false;
        }
        if(getBalance()-amount<Min_Balance){
            addTransaction("withdraw", amount, "Violating minimum balance");
            return false;
        }
        if(amount>getBalance()-Min_Balance){
            addTransaction("Withdraw", amount, "Insuffieient Balance");
        }
        setBalance(getBalance()-amount);
        incrementSessionWithdrawalCount();
        addTransaction("Withdraw", amount, "Success");
        return true;
    }
    public double getInterestRate(){
        return 0.4;
    }
}
