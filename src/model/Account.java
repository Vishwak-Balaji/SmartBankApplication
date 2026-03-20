package model;

public abstract class Account {
    private static final int Max_Transaction=5;
    
    private String accountNumber;
    private String holderName;
    private AccountType accountType;
    private double balance;
    private boolean frozen;
    private int sessionWithdrawalCount;
    private Transaction [] transaction;
    private int transactionCount;
    private int transactionHead;
    private boolean interestApplied;
    public Account( String accountNumber, String holderName,AccountType accountType,double balance){
        this.accountNumber=accountNumber;
        this.holderName=holderName;
        this.accountType=accountType;
        this.balance = balance;
        this.frozen=false;
        this.interestApplied=false;
        this.sessionWithdrawalCount=0;
        this.transaction = new Transaction[Max_Transaction];
        this.transactionCount=0;
        this.transactionHead=0;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getHolderName(){
        return holderName;
    }
    public AccountType getAccountType(){
        return accountType;
    }
    public double getBalance(){
        return balance;
    }
    public boolean isFrozen(){
        return frozen;
    }
    public boolean getInterestApplied(){
        return interestApplied;
    }
    public void setFrozen(boolean frozen){
        this.frozen= frozen;
    }
    public void setBalance(double balance){
        this.balance=balance;
    }
    public void resetSessionWithdrawalCount(){
        sessionWithdrawalCount=0;
    }
    public void incrementSessionWithdrawalCount(){
        sessionWithdrawalCount++;
    }
    public void setInterestApplied(boolean interestApplied){
        this.interestApplied=interestApplied;
    }
    public int getSessionWithdrawalCount(){
        return sessionWithdrawalCount;
    }
    public void addTransaction(String operation, double amount, String status){
        Transaction t = new Transaction(operation, amount, status);
        addTransactionInternal(t);
    }
    public void addTransaction(String operation, double amount, String status,String relatedAccount){
        Transaction t = new Transaction(operation, amount, status,relatedAccount);
        addTransactionInternal(t);
    }
    public void addTransactionInternal(Transaction t){
        int index =(transactionHead + transactionCount)%Max_Transaction;
        transaction[index]=t;
        
        if(transactionCount<Max_Transaction){
            transactionCount++;
        }
        else{
            transactionHead = (transactionHead + 1)%Max_Transaction;
        }
    }
    public void deposit(double amount){
        if(frozen){
            addTransaction("Deposit", amount, "Account Frozen");
            return;
        }
        balance+=amount;
        addTransaction("Deposit", amount, "Success");
    }
    public void minStatement(){
    System.out.println("Last Five Transaction : ");

    for(int i = 0; i < transactionCount; i++){
        int index = (transactionHead + i) % Max_Transaction;
        Transaction t = transaction[index];

        if(t.getRelatedAccount() != null){
            if(t.getOperation().equals("Transfer Out")){
                System.out.println("Transfered To: " + t.getRelatedAccount() + " | " + t.getAmount() + " | " + t.getStatus());
            }
            else if(t.getOperation().equals("Transfer In")){
                System.out.println("Transfered From: " + t.getRelatedAccount() + " | " + t.getAmount() + " | " + t.getStatus());
            }
        }
        else{
            System.out.println(t.getOperation() + " | " + t.getAmount() + " | " + t.getStatus());
        }
    }
}
    public abstract  boolean withdraw(double amount);
    public abstract double getInterestRate();

    public boolean withdrawWithoutTransaction(double amount){
        if(balance < amount){
            return false;
        }
        balance -= amount;
        incrementSessionWithdrawalCount();
        return true;
    }
}
