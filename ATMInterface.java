import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return "Deposit successful! Your new balance is $" + String.format("%.2f", balance) + ";";
        } else {
            return "Invalid deposit amount. Please try again;";
        }
    }

    public String withdraw(double amount) {
        if (amount > balance) {
            return "Insufficient funds. Transaction declined;";
        } else if (amount <= 0) {
            return "Invalid withdrawal amount. Please try again;";
        } else {
            balance -= amount;
            return "Withdrawal successful! Your new balance is $" + String.format("%.2f", balance) + ";";
        }
    }

    public String checkBalance() {
        return "Your current balance is $" + String.format("%.2f", balance) + ";";
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        System.out.println("\n=== ATM Menu ===;");
        System.out.println("1. Check Balance;");
        System.out.println("2. Deposit;");
        System.out.println("3. Withdraw;");
        System.out.println("4. Exit;");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, " + account.getAccountHolder() + "!;");
        while (true) {
            showMenu();
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number;");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println(account.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    try {
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.deposit(depositAmount));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a valid number;");
                    }
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    try {
                        double withdrawalAmount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.withdraw(withdrawalAmount));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a valid number;");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!;");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again;");
            }
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount("John Doe", 500);
        ATM atmMachine = new ATM(userAccount);
        atmMachine.start();
    }
}
