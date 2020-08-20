import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * @author Dolunay Dagci
 * CISS111-360
 * Asignment: Ch13 Cell Phone Packages
 * Due Date: 3.3.19
 * Fixed Date: 3.16.19
 * This program displays a menu system which allows user to select one package, one phone, and any desired option
 */

public class DD_CellPhonePackages extends JFrame {

    private JLabel messageLabel; //Display total
    private JMenuBar menuBar;
    private JMenu fileMenu, planMenu, phoneMenu, optionsMenu;
    private JRadioButtonMenuItem package1Item, package2Item, package3Item, phone1Item, phone2Item, phone3Item;
    private JMenuItem exitItem;
    private JCheckBoxMenuItem voiceMailItem, SMS_Item;
    private ButtonGroup packages, phones;
    private DecimalFormat df = new DecimalFormat("#0.00"); //a decimal format to round total 2 decimal places
    private static double packageTotal, phoneTotal, SMSTotal, voiceTotal;

    /**
     * constructor
     */
    public DD_CellPhonePackages() {
        setTitle("Cell Phone Packages");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageLabel = new JLabel("Total: $0", SwingConstants.CENTER);

        messageLabel.setPreferredSize(new Dimension(400,200));

        packages = new ButtonGroup();
        phones = new ButtonGroup();

        add(messageLabel);

        //build menu
        buildMenuBar();
        //after building menu, add selections to their button groups
        packages.add(package1Item); packages.add(package2Item); packages.add(package3Item);
        phones.add(phone1Item); phones.add(phone2Item); phones.add(phone3Item);

        pack();
        setVisible(true);
    }

    private void buildMenuBar() {
        menuBar = new JMenuBar();


        buildFileMenu();
        buildPlanMenu();
        buildPhoneMenu();
        buildOptionsMenu();

        menuBar.add(fileMenu);
        menuBar.add(planMenu);
        menuBar.add(phoneMenu);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
    }

    private void buildFileMenu() {

        /**
         * Exit
         */
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ExitListener());

        //when user clicks alt-x, it exits automatically
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_DOWN_MASK);
        exitItem.setAccelerator(keyStroke);

        fileMenu = new JMenu("File");
        fileMenu.add(exitItem);
    }

    private void buildPlanMenu() {
        //Package 1: 300 min per month $45 per month
        //Package 2: 800 min per month $65.00
        //Package 3: 1500 $99
        /**
         * Package 1
         */
        package1Item = new JRadioButtonMenuItem("Package 1");
        package1Item.setMnemonic(KeyEvent.VK_1);
        package1Item.setToolTipText("300 minute per month $45.00 per month");
        package1Item.addActionListener(new packageListener());

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.ALT_DOWN_MASK);
        package1Item.setAccelerator(keyStroke);
        /**
         * Package 2
         */
        package2Item = new JRadioButtonMenuItem("Package 2");
        package2Item.setMnemonic(KeyEvent.VK_2);
        package2Item.setToolTipText("800 minute per month $65.00 per month");
        package2Item.addActionListener(new packageListener());

         keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.ALT_DOWN_MASK);
        package2Item.setAccelerator(keyStroke);
        /**
         * Package 3
         */
        package3Item = new JRadioButtonMenuItem("Package 3");
        package3Item.setMnemonic(KeyEvent.VK_3);
        package3Item.setToolTipText("1500 minute per month $99.00 per month");
        package3Item.addActionListener(new packageListener());

        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.ALT_DOWN_MASK);
        package3Item.setAccelerator(keyStroke);

        planMenu = new JMenu("Plans");

        //add to menu
        planMenu.add(package1Item);
        planMenu.add(package2Item);
        planMenu.add(package3Item);
    }

    private void buildPhoneMenu() {
        //phone sale (6% (0.06) tax applies)
        //model 100: $29.95
        //model 110: $49.95
        //model 200: $99.95

        /**
         * model 100
         */
        phone1Item = new JRadioButtonMenuItem("Model 100");
        phone1Item.setMnemonic(KeyEvent.VK_F1);
        phone1Item.setToolTipText("$29.95");
        phone1Item.addActionListener(new phoneListener());

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.ALT_DOWN_MASK);
        phone1Item.setAccelerator(keyStroke);
        /**
         *  model 110
         */
        phone2Item = new JRadioButtonMenuItem("Model 110");
        phone2Item.setMnemonic(KeyEvent.VK_F2);
        phone2Item.setToolTipText("$49.95");
        phone2Item.addActionListener(new phoneListener());

        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_DOWN_MASK);
        phone2Item.setAccelerator(keyStroke);
        /**
         * model 200
         */
        phone3Item = new JRadioButtonMenuItem("Model 200");
        phone3Item.setMnemonic(KeyEvent.VK_F3);
        phone3Item.setToolTipText("$99.95");
        phone3Item.addActionListener(new phoneListener());

        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.ALT_DOWN_MASK);
        phone3Item.setAccelerator(keyStroke);

        phoneMenu = new JMenu("Phones");

        phoneMenu.add(phone1Item);
        phoneMenu.add(phone2Item);
        phoneMenu.add(phone3Item);
    }

    private void buildOptionsMenu() {
        //options (all per month)
        //voice mail: $5.00
        //text-messaging $10.00
        /**
         * Voice Mail Option
         */
        voiceMailItem = new JCheckBoxMenuItem("Voice Mail");
        voiceMailItem.setMnemonic(KeyEvent.VK_V);
        voiceMailItem.setToolTipText("$5.00 per month");
        voiceMailItem.addActionListener(new optionsListener());

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.ALT_DOWN_MASK);
        voiceMailItem.setAccelerator(keyStroke);

        /**
         * Text-Messaging Option
         */
        SMS_Item = new JCheckBoxMenuItem("Text-Messaging");
        SMS_Item.setMnemonic(KeyEvent.VK_T);
        SMS_Item.setToolTipText("$10.00 per month");
        SMS_Item.addActionListener(new optionsListener());

        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.ALT_DOWN_MASK);
        SMS_Item.setAccelerator(keyStroke);

        optionsMenu = new JMenu("Options");

        optionsMenu.add(voiceMailItem);
        optionsMenu.add(SMS_Item);
    }

    /** LISTENERS */

    /**
     * Listener for Exit
     */
    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        } }

    private class packageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (package1Item.isSelected()) packageTotal = 45;

            if (package2Item.isSelected()) packageTotal=65;

            if (package3Item.isSelected()) packageTotal=99;

            messageLabel.setText("Total: $" + df.format(SMSTotal+voiceTotal+packageTotal+phoneTotal));
        }
    }
    private class phoneListener implements ActionListener {

        public double totalPrice_Phone(double price) {
            return phoneTotal = (price * 0.06) + price;
        } //a total calculator for phone with tax

        @Override
        public void actionPerformed(ActionEvent e) {
            if (phone1Item.isSelected()) totalPrice_Phone(29.95); //total+=29.95;

            if (phone2Item.isSelected()) totalPrice_Phone(49.95); //total+=49.95;

            if (phone3Item.isSelected()) totalPrice_Phone(99.95); //total+=99.95;

            messageLabel.setText("Total: $" + df.format(SMSTotal+voiceTotal+packageTotal+phoneTotal));
        }
    }
    private class optionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (voiceMailItem.isSelected()) voiceTotal=5;
            else voiceTotal=0;
            if (SMS_Item.isSelected()) SMSTotal=10;
            else SMSTotal=0;
            messageLabel.setText("Total: $" + df.format(SMSTotal+voiceTotal+packageTotal+phoneTotal)); //Now,in my fixed code, it sums up all the total prices

        }
    }

    public static void main(String[] args) {
        new DD_CellPhonePackages();
    }
}