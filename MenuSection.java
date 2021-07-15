package question2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*The class creates the products that are on the menu.
 * In the class there is an array, which holds an object that contains all the panels that are part of the menu,
 * with the option of selection and quantity.*/
public class MenuSection extends JPanel {

    public final ArrayList<ProductPanel> products = new ArrayList<>();

    /*class constructor - Adds course to the main menu.*/
    public MenuSection(String type, ArrayList<Product> menuProducts) {
        JLabel label = new JLabel(type, SwingConstants.LEFT);
        label.setFont(new Font("Ariel", Font.BOLD, 20));
        label.setForeground(Color.MAGENTA);
        setLayout(new GridLayout(10, 1));
        add(label);
        for (Product product : menuProducts) {
            ProductPanel productPanel = new ProductPanel(product);
            products.add(productPanel);
            add(productPanel);
        }
    }

    /*A function that resets the array that contains all the course displayed on the menu.*/
    public void reset() {
        for (ProductPanel product : products) {
            product.reset();
        }
    }

    /*The function scans the entire menu, creating a user order.
     * It checks if the user has selected the product, and if so, adds it to the created order.*/
    public ArrayList<OrderProduct> getOrderProducts() {
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
        for (ProductPanel product : products) {
            if (product.isSelected()) {
                orderProducts.add(product.getOrderProduct());
            }
        }
        return orderProducts;
    }
}
