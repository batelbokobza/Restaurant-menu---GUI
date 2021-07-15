package question2;

/*The class produces an item type object.
The object contains the item name, description, type and price.
The class contains a toString function, which return the item details.*/
public class Product {

    private final String name;
    private final String description;
    private final String type;
    private final float price;

    public Product(String name, String description, String type, String price) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = Float.parseFloat(price);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public String toString() {
        return this.getName() + " - \n" + this.getDescription() + " " + this.getPrice() + "₪";
    }

}
