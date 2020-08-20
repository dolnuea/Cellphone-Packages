import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GUICellPhonePackages {
    public static class CellPhonePackage extends JFrame {
        /**
         *
         */
        private static final long serialVersionUID = 6548829860028144965L;
        private static final int WINDOW_WIDTH = 400;
        private static final int WINDOW_HEIGHT = 200;
        private static final double CELL_PHONE_TAX = 1.06;
        private static JPanel panel;
        private static JLabel lblPackages;
        private static JLabel lblPhones;
        private static JLabel lblOptions;
        private static JLabel lblTotalIs;
        private static JLabel lblOutputTotal;
        private static JComboBox cmbPackages;
        private static JComboBox cmbCellPhones;
        private static JList lstOptions;
        private static String[] strArrPackages = {
                "300 minutes per month: $45.00 per month",
                "800 minutes per month: $65.00 per month",
                "1500 minutes per month: $99.00 per month"
        };
        private static String[] strArrPhones = {
                "Model 100: $29.95",
                "Model 110: $49.95",
                "Model 200: $99.95"
        };
        private static String[] strArrOptions = {
                "Voice mail: $5.00 per month",
                "Text messaging: $10.00 per month"
        };
        private DecimalFormat df = new DecimalFormat("#0.00");
        private static double packageTotal;
        private static double phoneTotal;
        private static double optionTotal;
        // Public Constructor
        public CellPhonePackage () {
            // Create a new window
            JFrame window = new JFrame();
            // Display the title
            window.setTitle("Cell Phone Packages");
            // Set the size of the window
            window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            // Specify an action for the close button
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Build the panel and add it to the frame
            buildPanel();
            // Add the panel to the frame's content pane
            window.add(panel);
            // Display the window
            window.setVisible(true);
        }
        private void buildPanel() {
            // Create new labels
            lblPackages = new JLabel("Choose your package:");
            lblPhones = new JLabel("Choose your cell phone:");
            lblOptions = new JLabel("Choose your options:");
            lblTotalIs = new JLabel("Your total is:");
            lblOutputTotal = new JLabel();
            // Create combo boxes
            cmbPackages = new JComboBox(strArrPackages);
            cmbCellPhones = new JComboBox(strArrPhones);
            // Create list component
            lstOptions = new JList(strArrOptions);
            // Set the selection to Multiple
            lstOptions.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            // Action Listeners and List Selection Listener
            cmbPackages.addActionListener(new packageListener());
            cmbCellPhones.addActionListener(new phoneListener());
            lstOptions.addListSelectionListener(new optionsListener());
            // Instantiate the Panel object in the variable you already declared
            panel = new JPanel();
            // Add all the components to the object
            panel.setLayout(new GridLayout(4, 2));
            panel.add(lblPackages);
            panel.add(cmbPackages);
            panel.add(lblPhones);
            panel.add(cmbCellPhones);
            panel.add(lblOptions);
            panel.add(lstOptions);
            panel.add(lblTotalIs);
            panel.add(lblOutputTotal);
        }
        public class packageListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect the data select
                int packageSelected =
                        cmbPackages.getSelectedIndex();
                // Which package did they choose?
                if (packageSelected == 0) {
                    packageTotal = 45;
                } else if (packageSelected == 1) {
                    packageTotal = 65;
                } else if (packageSelected == 2) {
                    packageTotal = 99;
                }
                // Display the total
                lblOutputTotal.setText("$" +
                        df.format(optionTotal+packageTotal+phoneTotal));
            }
        }
        public class phoneListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect the data select
                int phoneSelected =
                        cmbCellPhones.getSelectedIndex();
                // Which phone did they choose?
                if (phoneSelected == 0) {
                    phoneTotal = 29.95;
                } else if (phoneSelected == 1) {
                    phoneTotal = 49.95;
                } else if (phoneSelected == 2) {
                    phoneTotal = 99.95;
                }
                // Don't forget to tax
                phoneTotal *= CELL_PHONE_TAX;
                // Display the total
                lblOutputTotal.setText("$" +
                        df.format(optionTotal+packageTotal+phoneTotal));
            }
        }
        public class optionsListener implements ListSelectionListener {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                double temp = 0;
                // Collect the data select
                int optionSelected[] =
                        lstOptions.getSelectedIndices();
                for (int i = 0; i < optionSelected.length; i++) {
                    // Which options did they choose?
                    if (optionSelected[i] == 0) {
                        temp += 5;
                    }
                    if (optionSelected[i] == 1) {
                        temp += 10;
                    }
                }
                optionTotal = temp;
                // Display the total
                lblOutputTotal.setText("$" +
                        df.format(optionTotal+packageTotal+phoneTotal));
            }
        }
    }
    // Main method:
    public static void main(String[] args) {
        new CellPhonePackage();
    }
}