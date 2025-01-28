/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ndegepetrolstation23;
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ndegepetrolstation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NdegePetrolStation23 extends JFrame {

// Declare components
JLabel titleLabel, fuelLabel, quantityLabel, priceLabel, totalLabel, paymentLabel, offerLabel;
JTextField quantityField;
JComboBox<String> fuelCombo, paymentCombo, cardTypeCombo;
JButton calculateButton, printButton;
JTextArea receiptArea;

// Declare variables
String fuelType, paymentMethod, cardType;
int quantity, price, totalAmount;
boolean carWash, wheelAlignment;

public NdegePetrolStation23() {
// Set up the frame
setTitle("Ndege Petrol Station");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(500, 400);
setLocationRelativeTo(null);

// Create components
titleLabel = new JLabel("Ndege Petrol Station");
titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
titleLabel.setForeground(Color.RED);

fuelLabel = new JLabel("Fuel Type:");
quantityLabel = new JLabel("Quantity:");
priceLabel = new JLabel("Price/Unit:");
totalLabel = new JLabel("Total Amount:");
paymentLabel = new JLabel("Payment Method:");
offerLabel = new JLabel("Offers:");

quantityField = new JTextField(10);

String[] fuelOptions = {"Petrol", "Diesel", "Kerosine", "Gas", "Electricity", "Oil & Lubes"};
fuelCombo = new JComboBox<>(fuelOptions);

String[] paymentOptions = {"Cash", "Credit Card"};
paymentCombo = new JComboBox<>(paymentOptions);

String[] cardTypeOptions = {"Gold", "Silver", "Platinum"};
cardTypeCombo = new JComboBox<>(cardTypeOptions);

calculateButton = new JButton("Calculate");
printButton = new JButton("Print Receipt");

receiptArea = new JTextArea();
receiptArea.setEditable(false);

// Add action listeners
calculateButton.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
calculateTotal();
}
});

printButton.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
printReceipt();
}
});

// Layout components using a GridBagLayout
setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(5, 5, 5, 5);

gbc.gridx = 0;
gbc.gridy = 0;
gbc.gridwidth = 2;
add(titleLabel, gbc);

gbc.gridy = 1;
gbc.gridwidth = 1;
add(fuelLabel, gbc);
gbc.gridx = 1;
add(fuelCombo, gbc);

gbc.gridy = 2;
add(quantityLabel, gbc);
gbc.gridx = 1;
add(quantityField, gbc);

gbc.gridy = 3;
add(priceLabel, gbc);
// Price will be dynamically calculated based on fuel type
gbc.gridx = 1;
add(new JLabel(" "), gbc); // Placeholder

gbc.gridy = 4;
add(paymentLabel, gbc);
gbc.gridx = 1;
add(paymentCombo, gbc);

gbc.gridy = 5;
add(new JLabel("Card Type:"), gbc); // Visible only for Credit Card
gbc.gridx = 1;
add(cardTypeCombo, gbc);

gbc.gridy = 6;
add(totalLabel, gbc);
gbc.gridx = 1;
add(new JLabel(" "), gbc); // Placeholder

gbc.gridy = 7;
add(calculateButton, gbc);

gbc.gridy = 8;
add(printButton, gbc);

gbc.gridy = 9;
gbc.gridwidth = 2;
gbc.fill = GridBagConstraints.BOTH;
gbc.weighty = 1.0;
add(new JScrollPane(receiptArea), gbc);

// Initially hide cardTypeCombo
cardTypeCombo.setVisible(false);

// Add payment method listener
paymentCombo.addItemListener(new ItemListener() {
@Override
public void itemStateChanged(ItemEvent e) {
if (e.getStateChange() == ItemEvent.SELECTED && "Credit Card".equals(paymentCombo.getSelectedItem())) {
cardTypeCombo.setVisible(true);
} else {
cardTypeCombo.setVisible(false);
}
}
});

setVisible(true);
}

private void calculateTotal() {
try {
fuelType = (String) fuelCombo.getSelectedItem();
quantity = Integer.parseInt(quantityField.getText());

switch (fuelType) {
case "Petrol":
price = 2800;
break;
case "Diesel":
price = 2900;
break;
case "Kerosine":
price = 2000;
break;
case "Gas":
price = 2000;
break;
case "Electricity":
price = 1500;
break;
case "Oil & Lubes":
price = 10000;
break;
default:
price = 0;
}

totalAmount = quantity * price;

// Calculate and display offers (if applicable)
offerLabel.setText("");
if ("Credit Card".equals(paymentCombo.getSelectedItem())) {
cardType = (String) cardTypeCombo.getSelectedItem();
if ("Gold".equals(cardType)) {
offerLabel.setText("Offers: Free Car Wash, Free Wheel Alignment");
carWash = true;
wheelAlignment = true;
} else if ("Silver".equals(cardType)) {
offerLabel.setText("Offers: Free Wheel Alignment");
carWash = false;
wheelAlignment = true;
} else if ("Platinum".equals(cardType)) {
offerLabel.setText("Offers: None");
carWash = false;
wheelAlignment = false;
}
} else {
// Cash payment: Offer car wash and wheel alignment options
carWash = JOptionPane.showConfirmDialog(this, "Do you want a car wash? (10000 Tshs)", "Car Wash",
JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
wheelAlignment = JOptionPane.showConfirmDialog(this, "Do you want wheel alignment? (5000 Tshs)",
"Wheel Alignment", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
}

// Update price and total labels
priceLabel.setText("Price/Unit: " + price + " Tshs");
totalLabel.setText("Total Amount: " + totalAmount + " Tshs");

} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(this, "Invalid quantity input.", "Error", JOptionPane.ERROR_MESSAGE);
}
}

private void printReceipt() {
receiptArea.setText("");
receiptArea.append("Ndege Petrol Station\n");
receiptArea.append("---------------------\n");
receiptArea.append("Fuel Type: " + fuelType + "\n");
receiptArea.append("Quantity: " + quantity + "\n");
receiptArea.append("Price/Unit: " + price + " Tshs\n");
receiptArea.append("Total Amount: " + totalAmount + " Tshs\n");
receiptArea.append("---------------------\n");
receiptArea.append(offerLabel.getText() + "\n");
receiptArea.append("---------------------\n");
receiptArea.append("Thank you for using Ndege services.\n");
receiptArea.append("You are warmly welcomed again.\n");
}

public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
@Override
public void run() {
new NdegePetrolStation23();
}
});
}
} 