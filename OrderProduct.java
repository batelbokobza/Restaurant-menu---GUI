package question2;

/*The class produces an object of the selected item type.
 * Heir from the item class.
 * In this class the object has another variable that indicates the amount of items ordered.*/
public class OrderProduct extends Product {
    private final int count;

    public OrderProduct(String name, String description, String type, String price, int count) {
        super(name, description, type, price);
        this.count = count;
    }

    public OrderProduct(Product product, int count) {
        this(product.getName(), product.getDescription(), product.getType(), String.valueOf(product.getPrice()), count);
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return getName() + " - \n" +
                getDescription() + "\n" +
                "Number of units X" + getCount() +
                " -  " + getCount() * getPrice() + "₪\n";
    }
}
