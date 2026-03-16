package model;

public class FixedDepositAccount extends Account{
    public FixedDepositAccount(String accountNumber, String holderName,double Balance){
        super(accountNumber, holderName, AccountType.FIXED_DEPOSIT, Balance);
    }
    public boolean withdraw(double amount){
        addTransaction("Withdraw", amount, "Failure");
        return false;
    }
    public double getInterestRate(){
        return 0.7;
    }
}
