import javax.swing.*;
import java.awt.*;

class BankAccount {
    private int balance;
    private final String pin = "1234";

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public boolean validatePin(String inputPin) {
        return pin.equals(inputPin);
    }

    public boolean withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public boolean authenticate(String pin) {
        return account.validatePin(pin);
    }

    public String deposit(int amount) {
        if (account.deposit(amount)) {
            return "Deposited ₹" + amount + " successfully.\nCurrent Balance: ₹" + account.getBalance();
        } else {
            return "Invalid amount.";
        }
    }

    public String withdraw(int amount) {
        if (amount <= 0) {
            return "Enter a valid amount to withdraw.";
        }
        if (account.withdraw(amount)) {
            return "Withdrawn ₹" + amount + " successfully.\nCurrent Balance: ₹" + account.getBalance();
        } else {
            return "Insufficient balance.";
        }
    }

    public String checkBalance() {
        return "Your current balance is ₹" + account.getBalance();
    }
}

public class ATMInterfaceUI extends JFrame {
    private ATM atm;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextArea messageArea;

    public ATMInterfaceUI() {
        setTitle("ATM Machine");
        setSize(550, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BankAccount account = new BankAccount(10000);
        atm = new ATM(account);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createWelcomePanel(), "Welcome");
        cardPanel.add(createMainMenuPanel(), "Menu");
        cardPanel.add(createTransactionPanel("Deposit"), "Deposit");
        cardPanel.add(createTransactionPanel("Withdraw"), "Withdraw");

        add(cardPanel);
        cardLayout.show(cardPanel, "Welcome");

        setVisible(true);
    }

    @SuppressWarnings("unused")
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("👋 Welcome to the ATM!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(welcomeLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> cardLayout.show(cardPanel, "Menu"));
        panel.add(startButton, BorderLayout.SOUTH);

        return panel;
    }

    @SuppressWarnings("unused")
    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Choose an option:", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton checkBalanceBtn = new JButton("Check Balance");

        depositBtn.addActionListener(e -> cardLayout.show(cardPanel, "Deposit"));
        withdrawBtn.addActionListener(e -> cardLayout.show(cardPanel, "Withdraw"));
        checkBalanceBtn.addActionListener(e -> showMessage(atm.checkBalance()));

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(checkBalanceBtn);

        panel.add(buttonPanel, BorderLayout.CENTER);

        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false);
        panel.add(new JScrollPane(messageArea), BorderLayout.SOUTH);

        return panel;
    }

    @SuppressWarnings("unused")
    private JPanel createTransactionPanel(String type) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

    JLabel titleLabel = new JLabel(type + " Operation", SwingConstants.CENTER);
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

    JLabel pinLabel = new JLabel("Enter ATM PIN:");
    JPasswordField pinField = new JPasswordField(10);

    JLabel amountLabel = new JLabel("Enter amount:");
    JTextField amountField = new JTextField(10);

    JButton proceedButton = new JButton("Proceed");
    proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    proceedButton.setMaximumSize(new Dimension(100, 35));

    proceedButton.addActionListener(e -> {
        String pin = new String(pinField.getPassword());
        String amtText = amountField.getText();

        if (!atm.authenticate(pin)) {
            showMessage("Invalid PIN. Try again.");
            return;
        }

        try {
            int amount = Integer.parseInt(amtText);
            String result = (type.equals("Deposit"))
                    ? atm.deposit(amount)
                    : atm.withdraw(amount);

            showMessage(result);
            pinField.setText("");
            amountField.setText("");
            cardLayout.show(cardPanel, "Menu");
        } catch (NumberFormatException ex) {
            showMessage("⚠️ Please enter a valid number.");
        }
    });

    // Align components vertically
    panel.add(titleLabel);
    panel.add(Box.createVerticalStrut(15));
    panel.add(pinLabel);
    panel.add(pinField);
    panel.add(Box.createVerticalStrut(10));
    panel.add(amountLabel);
    panel.add(amountField);
    panel.add(Box.createVerticalStrut(20));
    panel.add(proceedButton);

    return panel;
}


    private void showMessage(String message) {
        messageArea.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMInterfaceUI::new);
    }
}
