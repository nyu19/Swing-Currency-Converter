import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingCurrencyConverter extends JFrame{
    private JPanel mainPanel;
    private JComboBox inputCurrencyCB;
    private JComboBox outputCurrencyCB;
    private JTextField inputField;
    private JTextField outputField;
    private JLabel input;
    private JLabel output;
//    private String[] countries;
    private String previousAmount = "";
    private String previousIP = "";
    private String previousOP = "";

    private void setJCB(){
        for (String o:new CurrencyList().get()) {
            inputCurrencyCB.addItem(o);
            outputCurrencyCB.addItem(o);
        }
    }
    SwingCurrencyConverter(){
        this.setContentPane(mainPanel);
        setJCB();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/exchange.png"));
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean flag = e.getKeyCode() == 10;

                if (inputField.getText().equals(previousAmount) &&
                        inputCurrencyCB.getSelectedItem().toString().equals(previousIP) &&
                        outputCurrencyCB.getSelectedItem().toString().equals(previousOP) && flag){
                    System.out.println("Same Input!");
                }
                else if (inputCurrencyCB.getSelectedItem() == outputCurrencyCB.getSelectedItem() && flag){
                    System.out.println("Same Input and Output Currency");
                    outputField.setText(inputField.getText());
                }
                else if (flag && !(previousAmount.equals(inputField.getText()))){
                    outputField.setText(new ApiHandler().getConvertedValues(
                            inputField.getText(),inputCurrencyCB.getSelectedItem().toString(), outputCurrencyCB.getSelectedItem().toString()
                    ));
                    previousAmount = inputField.getText();
                    previousIP = inputCurrencyCB.getSelectedItem().toString();
                    previousOP = outputCurrencyCB.getSelectedItem().toString();
                    System.out.println("Calculation has been Successful!");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new SwingCurrencyConverter();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}
