package model;

public class Transaction {
    private String operation;
    private double amount;
    private String status;
    private String relatedAccount;

    public Transaction(String operation, double amount , String status,String relatedAccount){
        this.operation=operation;
        this.amount=amount;
        this.status= status;
        this.relatedAccount=relatedAccount;

    }
    public Transaction(String operation , double amount , String status){
        this(operation, amount, status, null);
    }

    public String getOperation(){
        return operation;
    }
    public double getAmount(){
        return amount;
    }
    public String getStatus(){
        return status;
    }
    public String getRelatedAccount(){
        return relatedAccount;
    }

}
