package service;
import model.*;
public class BankService {
    private static final int Max_Account = 50;
    private Account [] accounts;
    private int accountCount;

    public BankService(){
         accounts = new Account[Max_Account];
         accountCount=0;
    }
    public void openAccount(String accNo , String name , AccountType type , double balance){
        if(accountCount>Max_Account){
            System.out.println("Account Limit Reached");
            return;
        }
        if(findAccount(accNo)!=null){
            System.out.println("The account already exist.");
            return;
        }
        switch(type){
            case SAVINGS:
                if(balance<1000){
                    System.out.println("Minimum balance for Savings Account is 1000");
                    return;
                }
                break;
            case CURRENT:
                if(balance<5000){
                    System.out.println("Minimum balance for Current Account is 5000");
                    return;
                }    
                break;
            case FIXED_DEPOSIT:
                if(balance<10000){
                    System.out.println("Minimum balance for Fixed deposit Account is 10000");
                    return;
                }    
                break;
        }
        Account acc = null;
        switch (type){
            case SAVINGS:

                acc= new SavingsAccount(accNo, name, balance);
                break;
            case CURRENT:
                acc=new CurrentAccount(accNo, name, balance);
                break;
            case FIXED_DEPOSIT:
                acc = new FixedDepositAccount(accNo, name, balance);
                break;  
        }
        accounts[accountCount++]=acc;
        System.out.println("Account created successfully");
    }
    public Account findAccount(String accNo){
        for(int i=0;i<accountCount;i++){
            if(accounts[i].getAccountNumber().equals(accNo)){
                return accounts[i];
            }
        }
        System.out.println("Account not Found");
        return null;
    }
    public void deposit(String accNo, double amount){
        Account acc = findAccount(accNo);
        if(acc==null){
            System.out.println("Account not found!");
            return;
        }
        if(amount<1 || amount >100000){
            System.out.println("Invalid amount!");
            return;
        }
        acc.deposit(amount);
        System.out.println("Depostied Successfully");
    }
    public void checkFraud(Account acc , double amount){
        if(acc.getSessionWithdrawalCount()>5){
            acc.setFrozen(true);
            System.out.println("Account frozen because of too many withdrawals.!");
        }
        if(amount >50000 && amount  %1000==0){
            acc.setFrozen(true);
            System.out.println("Account is froze due to suspicious transaction.!");
        }
    }
    public void withdraw(String accNo, double amount){
        Account acc = findAccount(accNo);
        if(acc==null){
            System.out.println("Account not Found!");
            return ;
        }
        boolean success = acc.withdraw(amount);
        if(success){
            checkFraud(acc, amount);
            System.out.println("Success");
        }
        else{
            System.out.println("Failure");
        }
    }
    public void transfer(String fromAcc , String toAcc , double amount){
        Account sender = findAccount(fromAcc);
        Account receiver = findAccount(toAcc);
        if(sender == null || receiver == null){
            System.out.println("Account not found");
            return;
        }
        if(sender.isFrozen() || receiver.isFrozen()){
            System.out.println("One account is frozen");
            return;
        }
        if(amount <= 0){
            System.out.println("Invalid amount");
            return;
        }
        boolean success = sender.withdrawWithoutTransaction(amount);
        if(success){
            receiver.setBalance(receiver.getBalance() + amount);
            sender.addTransaction("Transfer Out", amount, "Success", receiver.getAccountNumber());
            receiver.addTransaction("Transfer In", amount, "Success", sender.getAccountNumber());
            System.out.println("Transferred successfully");
        } 
        else {
            System.out.println("Transfer failed");
        }
    }
    public void display(){
        for(int i=0;i<accountCount;i++){
            Account acc = accounts[i];
            System.out.println(acc.getAccountNumber()+" | "+acc.getHolderName()+" | "+acc.getAccountType()+" | "+acc.getBalance());
        }
    }
    public void topNAccount(int n){
        if(n>accountCount){
            n=accountCount;
        }
        Account [] temp = new Account[accountCount];

        for(int i=0;i<accountCount;i++){
            temp[i]=accounts[i];
        }

        for(int i=0;i<accountCount-1;i++){
            for(int j =0 ; j<accountCount-i-1;j++){
                if(temp[j].getBalance()<temp[j+1].getBalance()){
                    Account t = temp[j];
                    temp[j]=temp[j+1];
                    temp[j+1]=t;
                }
            }
        }
        for(int i =0;i<n;i++){
            System.out.println(temp[i].getAccountNumber()+" | "+temp[i].getHolderName()+" | "+temp[i].getAccountType()+" | "+temp[i].getBalance());
        }
    }
    public void applyInterest(){
        for(int i=0;i<accountCount;i++){
            Account acc = accounts[i];
            if(!acc.isFrozen() && !acc.getInterestApplied()){
                double rate = acc.getInterestRate();
                double interest = acc.getBalance()*rate/12;
                acc.setBalance(acc.getBalance()+interest);
                acc.setInterestApplied(true);
            }
        }
        System.out.println("Interest Applied");
    }

    public void unfreezeAcc(String accNo){
        Account acc = findAccount(accNo);

        if(acc==null){
            System.out.println("Account not found");
            return;
        }
        acc.setFrozen(false);
        acc.resetSessionWithdrawalCount();
        System.out.println("Account Unfreezed.!");
    }

}
