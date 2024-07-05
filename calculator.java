import java.awt.*;
import java.awt.event.*;

public class calculator extends Frame implements ActionListener {

    private TextField tfNum1, tfNum2, tfResult;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    private Label lblNum1, lblNum2, lblResult;

    public calculator() {
        setTitle("AWT Calculator");
        setSize(300, 300);
        setLayout(new GridLayout(5, 2)); 
        lblNum1 = new Label("Number 1:");
        lblNum2 = new Label("Number 2:");
        lblResult = new Label("Result:");
        tfNum1 = new TextField();
        tfNum2 = new TextField();
        tfResult = new TextField();
        btnAdd = new Button("+");
        btnSubtract = new Button("-");
        btnMultiply = new Button("*");
        btnDivide = new Button("/");
        add(lblNum1);
        add(tfNum1);
        add(lblNum2);
        add(tfNum2);
        add(btnAdd);
        add(btnSubtract);
        add(btnMultiply);
        add(btnDivide);
        add(lblResult);
        add(tfResult);

        btnAdd.addActionListener(this);
        btnSubtract.addActionListener(this);
        btnMultiply.addActionListener(this);
        btnDivide.addActionListener(this);

            addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(tfNum1.getText());
            double num2 = Double.parseDouble(tfNum2.getText());
            double result = 0;

            if (e.getSource() == btnAdd) {
                result = num1 + num2;
            } else if (e.getSource() == btnSubtract) {
                result = num1 - num2;
            } else if (e.getSource() == btnMultiply) {
                result = num1 * num2;
            } else if (e.getSource() == btnDivide) {
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero!");
                }
                result = num1 / num2;
            }

            tfResult.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            tfResult.setText("Invalid input");
        } catch (ArithmeticException ex) {
            tfResult.setText(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new calculator();
    }
}