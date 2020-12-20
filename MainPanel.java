package question2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* The class creates a panel that represents the menu of items received from the file, in order. */
/*The input file for the menu should be entered as follows -
For each product displayed on the menu, the following information is required, with each row individually:
line 1 - The name of the product
line 2 - Description of the product
line 3 - Type of dish (First Course / Main Course / Dessert / Drinking)
And the line 4 - cost of the dish.*/
public class MainPanel extends JPanel {

    private interface Constant{
        int CONFIRMATION = 0;
        int CANCEL = 2;
    }
    private final static String FILE_PATH = "C:\\Users\\batel\\Desktop\\JAVA מתקדם\\Maman13 - Java\\question2\\menu";
    private final static String FILE_NEW_ORDER = "C:\\Users\\batel\\Desktop\\Maman13-java\\src\\question2";
    private final ArrayList<MenuSection> menuSections;

    /*Class constructor - Create a menu by receiving a file.
     * If the file reception is incorrect, a message is thrown and the program closes.
     *  The constructor adds to the panel the course in the menu according to their type.*/
    MainPanel() {
        setLayout(new BorderLayout());

        Menu menu = null;
        try {
            menu = new Menu(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        menuSections = new ArrayList<>();
        menuSections.add(new MenuSection("First Course", menu.getFirstCourses()));
        menuSections.add(new MenuSection("Dessert", menu.getDesserts()));
        menuSections.add(new MenuSection("Main Course", menu.getMainCourses()));
        menuSections.add(new MenuSection("Drink", menu.getDrinks()));

        for (MenuSection section : menuSections) {
            panel.add(section);
        }

        createAccountOrder(panel);
    }

    /* Function creates a new order, according to items selected by the user, with the quantity of items selected,
    * If the user selects -order - a message window is displayed with the order details and the final amount.
    * Otherwise, if the user selects clear, the menu will be reset. */
    private void createAccountOrder(JPanel panel) {
        JPanel btnPanel = new JPanel();
        final JButton orderBut = new JButton("Order");
        orderBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = new Order();
                for (MenuSection menuSection : menuSections) {
                    for (OrderProduct orderProduct : menuSection.getOrderProducts()) {
                        order.add(orderProduct);
                    }
                }
                viewOrderProducts(order);
            }
        });
        btnPanel.add(orderBut);

        final JButton clearBut = new JButton("Clear");
        clearBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMenuSections();
            }
        });
        btnPanel.add(clearBut);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    /*A function that sends a message to the screen, with the order details.
     * The order details window has three action buttons - Confirm, Update and Cancel.
     * Confirmation - We will create a new file with all the order details,
     * and ask the user for a name and ID card for the file name. An additional order option then opens.
     * Update - We will return to the order window, and the user will be able to update his order.
     * Cancellation - Closes the order window, and open the possibility of a new order.*/
    private void viewOrderProducts(Order orderList) {
        Object[] options = {"Order Confirmation", "Update Order", "Cancel Order"};  //Confirmation = 0, Update = 1, Cancel = 2
        int resultAnswer = JOptionPane.showOptionDialog(null, orderList.toString(), "Order Details",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (resultAnswer == Constant.CONFIRMATION) {
            String nameOfOrder = SaveOrderDetails();
            if (nameOfOrder == null) {
                return;
            }
            try {
                createFile(orderList, nameOfOrder);
                resetMenuSections();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (resultAnswer == Constant.CANCEL) {
            resetMenuSections();
        }
    }

    /*The function resets the menu. Clears all user selections.*/
    private void resetMenuSections() {
        for (MenuSection menuSection : menuSections) {
            menuSection.reset();
        }
    }

    /*A function creates a new file, after the user has confirmed the order.*/
    private void createFile(Order order, String fileName) throws IOException {
        String newOrderDetails = order.toString();
        FileOutputStream outputFile = new FileOutputStream(FILE_NEW_ORDER + "\\" + fileName);
        outputFile.write(newOrderDetails.getBytes());
        outputFile.close();
    }

    /*A function that asks the user who confirmed his order to enter a name and ID to keep the order file on his name.
     * If the user entered an incorrect name, they will be prompted to try again.
     * The name must contain a name and ID card.*/
    private String SaveOrderDetails() {
        String nameAndId = JOptionPane.showInputDialog(null,
                "Enter Please enter a name and ID card to save the order.\n" +
                        "Please enter your details in the following way -\n" + "nameID");
        while (nameAndId != null && !CheckNameOrder(nameAndId)) {
            nameAndId = JOptionPane.showInputDialog(null,
                    "Invalid input.\n" + "The order name will contain only a name and ID card. \n" +
                            "For example Israel123456789.");
        }
        return nameAndId;
    }

    /*A function that checks if the name of the user who confirmed the order is correct.
     * The name should contain a name and ID card, when written together.*/
    private boolean CheckNameOrder(String nameAndId) {
        boolean checkName = true;
        int ind = 0;
        if (nameAndId.isEmpty() || !Character.isLetter(nameAndId.charAt(ind))) {
            return false;
        }
        for (; ind < nameAndId.length(); ind++) {
            if (!Character.isDigit(nameAndId.charAt(ind)) && !Character.isLetter(nameAndId.charAt(ind)))
                return false;
            if (Character.isDigit(nameAndId.charAt(ind)) && checkName) {
                checkName = false;
            } else if (Character.isLetter(nameAndId.charAt(ind)) && !checkName) {
                return false;
            }
        }
        //If the name entered contains digits, we return the truth.
        // Otherwise we will return a lie that was entered there only without digits.
        return !checkName;
    }
}