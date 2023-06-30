import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame {
    private HashMap<String, Double> exchangeRates;

    private JTextField amountField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JButton convertButton;
    private JLabel resultLabel;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        // Add exchange rates here
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.9);
        exchangeRates.put("GBP", 0.78);
        exchangeRates.put("JPY", 110.19);

        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        amountField = new JTextField(10);
        fromCurrencyComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        toCurrencyComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = fromCurrencyComboBox.getSelectedItem().toString();
                    String toCurrency = toCurrencyComboBox.getSelectedItem().toString();
                    double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
                    resultLabel.setText("Converted amount: " + convertedAmount + " " + toCurrency);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid amount.");
                } catch (IllegalArgumentException ex) {
                    resultLabel.setText("Invalid currency.");
                }
            }
        });

        add(new JLabel("Amount:"));
        add(amountField);
        add(new JLabel("From:"));
        add(fromCurrencyComboBox);
        add(new JLabel("To:"));
        add(toCurrencyComboBox);
        add(convertButton);
        add(resultLabel);

        pack();
        setLocationRelativeTo(null); // Center the window
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);
            return amount * (toRate / fromRate);
        } else {
            throw new IllegalArgumentException("Invalid currency.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CurrencyConverter app = new CurrencyConverterApp();
                app.setVisible(true);
            }
        });
    }
}