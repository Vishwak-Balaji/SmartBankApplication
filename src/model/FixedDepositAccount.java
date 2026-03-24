package model;

public class FixedDepositAccount extends Account{
    public FixedDepositAccount(String accountNumber, String holderName,double Balance){
        super(accountNumber, holderName, AccountType.FIXED_DEPOSIT, Balance);
    }
    public String withdraw(double amount){
        addTransaction("Withdraw", amount, "FAILURE");
        return "NO withdraw for FIXED_DEPOSIT";
    }
    public double getInterestRate(){
        return 0.7;
    }
}
