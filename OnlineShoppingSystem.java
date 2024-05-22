import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Product class
class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product(int id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Description: " + description + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

// ShoppingCart class
class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void viewCart() {
        System.out.println("Shopping Cart:");
        for (Product product : products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}

// Customer class
class Customer {
    private int id;
    private String name;
    private String email;
    private ShoppingCart cart;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cart = new ShoppingCart();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    public void viewCart() {
        cart.viewCart();
    }

    public double checkout() {
        double total = cart.calculateTotal();
        cart = new ShoppingCart(); // Reset the cart after checkout
        return total;
    }
}

// Main class to simulate the online shopping system
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        // Create some products
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "smartwatch", "A high-performance smartwatch", 1200.99, 10));
        productList.add(new Product(2, "Smartphone", "Latest model smartphone", 699.99, 20));
        productList.add(new Product(3, "Headphones", "Noise-cancelling headphones", 199.99, 15));

        // Create a customer
        Customer customer = new Customer(1, "swaraj", "swaraj@example.com");

        Scanner scanner = new Scanner(System.in);
        boolean shopping = true;

        while (shopping) {
            System.out.println("\nWelcome to the Online Shopping System");
            System.out.println("1. Browse Products");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Browse products
                    System.out.println("\nAvailable Products:");
                    for (Product product : productList) {
                        System.out.println(product);
                    }
                    System.out.print("Enter Product ID to add to cart (or 0 to go back): ");
                    int productId = scanner.nextInt();
                    if (productId > 0) {
                        for (Product product : productList) {
                            if (product.getId() == productId) {
                                customer.addToCart(product);
                                System.out.println(product.getName() + " has been added to your cart.");
                            }
                        }
                    }
                    break;
                case 2:
                    // View cart
                    customer.viewCart();
                    break;
                case 3:
                    // Checkout
                    double total = customer.checkout();
                    System.out.println("Total amount to pay: $" + total);
                    break;
                case 4:
                    // Exit
                    shopping = false;
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}