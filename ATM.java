import java.util.Scanner;

public class ATM {
    private static class Account {
        private String accountNumber;
        private String pin;
        private double balance;
        private StringBuilder history;

        public Account(String accountNumber, String pin, double balance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
            this.history = new StringBuilder();
            history.append("Account created with balance: ₹").append(balance).append("\n");
        }

        public boolean authenticate(String inputPin) {
            return this.pin.equals(inputPin);
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
            history.append("Deposited: ₹").append(amount).append(" | New Balance: ₹").append(balance).append("\n");
        }

        public boolean withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                history.append("Withdrew: ₹").append(amount).append(" | New Balance: ₹").append(balance).append("\n");
                return true;
            }
            history.append("Failed withdrawal attempt: ₹").append(amount).append("\n");
            return false;
        }

        public String getHistory() {
            return history.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account("12345678", "4321", 5000.0);

        System.out.println("Welcome to the Java ATM Simulator!");

        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter PIN: ");
            String inputPin = scanner.next();
            if (account.authenticate(inputPin)) {
                break;
            } else {
                System.out.println("Incorrect PIN. Attempts left: " + (--attempts));
                if (attempts == 0) {
                    System.out.println("Too many failed attempts. Exiting...");
                    return;
                }
            }
        }

        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: ₹" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Transaction History:\n" + account.getHistory());
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
