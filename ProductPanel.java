package question2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*The class creates the courses that are displayed on the menu.
 * Each course will be inside a panel, with the CheckBox and the ComboBox.
 * The class updates the user's selections on the main menu, and updates the MenuSelection class,
 * in the courses selected, and in the number of courses selected.*/
public class ProductPanel extends JPanel {

    /*The array numberOfUnit contains the quantity of courses that can be ordered from each specific course.*/
    private static final String[] numberOfUnit = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final JComboBox comboBox;
    private final JCheckBox checkBox;
    private final Product product;

    /*Class constructor.
     * Add the courses to the main menu panel, and update according to the user's click.*/
    public ProductPanel(Product product) {
        this.product = product;
        setLayout(new FlowLayout(FlowLayout.LEADING));
        checkBox = new JCheckBox(product.getName() + " - " + product.getDescription() + " "+product.getPrice()+"₪");
        JLabel totalPrice = new JLabel("Total price: 0.0₪");
        comboBox = createQuantitySelectionBox(totalPrice, product.getPrice());

        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                comboBox.setEnabled(cb.isSelected());
                if (comboBox.isEnabled()) {
                    comboBox.setSelectedIndex(1);
                } else {
                    comboBox.setSelectedIndex(0);
                }
            }
        });
        add(checkBox);
        add(comboBox);
        add(totalPrice);
    }

    /*The function creates the quantity selection buttons.
     * When the user selects the item, the quantity selection option opens.
     * Next to each item is written its amount according to the quantity requested from this item.*/
    private JComboBox createQuantitySelectionBox(final JLabel totalPrice, final float price) {
        final JComboBox<String> selectionBox = new JComboBox<>(numberOfUnit);
        selectionBox.setEnabled(false);

        selectionBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                totalPrice.setText("Total price: " + price * selectionBox.getSelectedIndex() + "₪");
            }
        });
        return selectionBox;
    }
    /*The function resets all user selections in the main menu.*/
    public void reset() {
        checkBox.setSelected(false);
        comboBox.setSelectedIndex(0);
        comboBox.setEnabled(false);
    }

    /*The function returns true or false, if a particular course is selected.*/
    public boolean isSelected() {
        return checkBox.isSelected();
    }

    /*The function returns the item, and the quantity ordered from it.*/
    public OrderProduct getOrderProduct() {
        return new OrderProduct(product, comboBox.getSelectedIndex());
    }
}
