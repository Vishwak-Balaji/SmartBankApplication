
import java.util.*;
import model.Account;
import model.AccountType;
import service.BankService;
public class Main{
     public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        BankService bank = new BankService();
        while(true){
            System.out.println("\n===== SMART BANK =====");
            System.out.println("1. Open Account");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Display Accounts");
            System.out.println("6. Top N Accounts");
            System.out.println("7. Mini Statement");
            System.out.println("8. Apply Interest");
            System.out.println("9. Show Frozen Account");
            System.out.println("10. Unfreeze Account");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Enter account number : ");
                    String accNo = scan.nextLine();
                    System.out.print("Enter holder name : ");
                    String holderName = scan.nextLine();
                    System.out.println("Choose account type");
                    System.out.println("1. SAVINGS");
                    System.out.println("2. CURRENT");
                    System.out.println("3. FIXED_DEPOSIT");
                    int typeChoice = scan.nextInt();
                    AccountType type = null;
                    if(typeChoice == 1){
                        type=AccountType.SAVINGS;
                    }
                    else if(typeChoice == 2){
                        type=AccountType.CURRENT;
                    }
                    else if(typeChoice == 3){
                        type=AccountType.FIXED_DEPOSIT;
                    }
                    System.out.print("Enter Initial Balance : ");
                    double balance = scan.nextDouble();

                    bank.openAccount(accNo, holderName, type, balance);
                    break;
                case 2:
                    System.out.print("Enter the Account number : ");
                    String withdrawAcc = scan.nextLine();
                    System.out.print("Enter amount : ");
                    double withdrawAmt= scan.nextInt();
                    bank.withdraw(withdrawAcc, withdrawAmt);
                    break;
                case 3:
                    System.out.print("Enter the Account number : ");
                    String depositAcc = scan.nextLine();
                    System.out.print("Enter the amount : ");
                    double depositAmt = scan.nextDouble();
                    bank.deposit(depositAcc, depositAmt);
                    break;
                case 4:
                    System.out.print("Enter from account : ");  
                    String fromAcc = scan.nextLine();  
                    System.out.print("Enter To account : ");
                    String toAcc = scan.nextLine();
                    System.out.print("Enter Amount : ");
                    double tranAmt = scan.nextDouble();
                    bank.transfer(fromAcc, toAcc, tranAmt);
                    break;
                case 5:
                    bank.display();
                    break;
                case 6:
                    System.out.print("Enter N : ");
                    int n = scan.nextInt();
                    bank.topNAccount(n);
                    break;
               case 7:
                    System.out.print("Enter Account number : ");
                    String minAcc = scan.nextLine();
                    Account acc = bank.findAccount(minAcc);
                    if(acc != null){
                    acc.minStatement();
                    } 
                    else {
                        System.out.println("Account not found");
                    }
                    break;
                case 8:
                    bank.applyInterest();
                    break;
                case 9:
                    bank.showFrozenAccount();
                    break;
                case 10:
                    System.out.print("Enter Account number : ");
                    String unfreezeAcc = scan.nextLine();
                    bank.unfreezeAcc(unfreezeAcc);    
                    break;
                case 11:
                    System.out.print("Thank You...");
                    scan.close();
                    return;
                default:
                    System.out.print("Invalid input");
            }
        }
     }
}