package question2;

import java.util.ArrayList;

/*Class of order items.
 * The class contains an array which contains the ordered items, including the quantity of each item.
 * And contains a print function of all selected items, including a final amount to pay for them.*/
public class Order {

    private final ArrayList<OrderProduct> orderProducts;

    public Order() {
        orderProducts = new ArrayList<>();
    }

    /*The function adds an ordered item to the list of items ordered by the user.*/
    public void add(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }

    @Override
    public String toString() {
        double totalPayment = 0;
        StringBuilder sb = new StringBuilder("Your order details: \n");
        sb.append("--------------------------- \n");
        for (OrderProduct orderProduct : orderProducts) {
            sb.append("\n**");
            sb.append(orderProduct.toString());
            totalPayment += orderProduct.getCount() * orderProduct.getPrice();
        }
        return sb + "\nTotal payment: " + totalPayment + "₪\n\n";
    }
}