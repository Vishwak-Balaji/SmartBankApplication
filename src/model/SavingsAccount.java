package model;

public class SavingsAccount extends Account {
    private static final int Min_Balance = 1000;
    private static final int DailyWithdrawalLimit = 10000;

    public SavingsAccount(String accountNumber , String holderName,double balance){
        super(accountNumber,holderName,AccountType.SAVINGS,balance);
    }
    public String withdraw(double amount){
        if(isFrozen()){
            addTransaction("Withdraw", amount, "FAILURE");
            return "The account is frozen";
        }
        if(amount>DailyWithdrawalLimit){
            addTransaction("Withdraw", amount, "FAILURE");
            return "Exceeds daily withdrawal limi";
        }
        if(getBalance()-amount<Min_Balance){
            addTransaction("withdraw", amount, "FAILURE");
            return "Violating minimum balance";
        }
        if(amount>getBalance()){
            addTransaction("Withdraw", amount, "FAILURE");
            return "Insuffieient Balance";
        }
        setBalance(getBalance()-amount);
        incrementSessionWithdrawalCount();
        addTransaction("Withdraw", amount, "SUCCESS");
        return "SUCCESS";
    }
    public double getInterestRate(){
        return 0.4;
    }
}
