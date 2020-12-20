package question2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*Class of the menu.
 * There are 4 arrays in the class, representing the existing item types.
 * The class receives the information from the file, and puts the items by type into the appropriate array.*/
public class Menu {

    private interface Constant{
        int NAME = 4;
        int DESCRIPTION = 3;
        int TYPE = 2;
        int PRICE = 1;
    }
    private final ArrayList<Product> firstCourses = new ArrayList<>();
    private final ArrayList<Product> mainCourses = new ArrayList<>();
    private final ArrayList<Product> desserts = new ArrayList<>();
    private final ArrayList<Product> drinks = new ArrayList<>();

    /*File reception function.*/
    public Menu(String path) throws IOException {
        File text = new File(path);
        //Creating Scanner instance to read File
        Scanner input = new Scanner(text);
        while (input.hasNextLine()) {
            Product product = getProductFromFile(input);
            addItemByType(product);
        }
        input.close();
    }

    /*A function that continues to receive from the file the corresponding lines
     * in the order of the information about the items in the file.*/
    private static Product getProductFromFile(Scanner input) {
        int numOfAttributesInProduct = 4;
        String name = null, description = null,  category = null,  price = null;
        while (input.hasNextLine() && numOfAttributesInProduct > 0) {
            String line = input.nextLine();
            if (line.isEmpty())
                continue;
            else if (numOfAttributesInProduct == Constant.NAME)
                name = line;
            else if (numOfAttributesInProduct == Constant.DESCRIPTION)
                description = line;
            else if (numOfAttributesInProduct == Constant.TYPE)
                category = line;
            else if (numOfAttributesInProduct == Constant.PRICE)
                price = line;
            numOfAttributesInProduct--;
        }
        return new Product(name, description, category, price);
    }

    public ArrayList<Product> getFirstCourses() {
        return firstCourses;
    }

    public ArrayList<Product> getMainCourses() {
        return mainCourses;
    }

    public ArrayList<Product> getDesserts() {
        return desserts;
    }

    public ArrayList<Product> getDrinks() {
        return drinks;
    }

    /*The function adds the item to the appropriate array, by item type.*/
    private void addItemByType(Product product) {
        switch (product.getType()) {
            case "First Course":
                firstCourses.add(product);
                break;
            case "Main Course":
                mainCourses.add(product);
                break;
            case "Dessert":
                desserts.add(product);
                break;
            case "Drinking":
                drinks.add(product);
                break;
        }
    }
}
