package model;

public class Transaction {
    private String operation;
    private double amount;
    private String status;

    public Transaction(String operation, double amount , String status){
        this.operation=operation;
        this.amount=amount;
        this.status= status;

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

}
