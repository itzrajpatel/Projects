import java.util.*;
import java.io.*;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class MenuSubcategory {
    private String subcategoryName;
    private List<MenuItem> items;

    public MenuSubcategory(String subcategoryName) {
        this.subcategoryName = subcategoryName;
        this.items = new ArrayList<>();
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }
}

class MenuCategory {
    private String categoryName;
    private List<MenuSubcategory> subcategories;

    public MenuCategory(String categoryName) {
        this.categoryName = categoryName;
        this.subcategories = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<MenuSubcategory> getSubcategories() {
        return subcategories;
    }

    public void addSubcategory(MenuSubcategory subcategory) {
        subcategories.add(subcategory);
    }
}

class ShoppingCart {
    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

class PaymentSystem {
    public static boolean processPayment(double amount) {
        return true;
    }
}

public class FoodOrderingSystem {

    public static void searchMenuByName(List<MenuCategory> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the food dish you want to search for:");
        String dishName = scanner.nextLine().toLowerCase();
    
        boolean found = false;
    
        for (MenuCategory category : menu) {
            for (MenuSubcategory subcategory : category.getSubcategories()) {
                for (MenuItem item : subcategory.getItems()) {
                    if (item.getName().toLowerCase().contains(dishName)) {
                        System.out.println("Food Dish Found:");
                        System.out.println("Category: " + category.getCategoryName());
                        System.out.println("Subcategory: " + subcategory.getSubcategoryName());
                        System.out.println("Dish Name: " + item.getName());
                        System.out.println("Price: ₹" + item.getPrice());
                        found = true;
                    }
                }
            }
        }
    
        if (!found) {
            System.out.println("Food dish not found.");
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<MenuCategory> menu = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart();

        // Create menu categories and subcategories
        MenuCategory Chinese = new MenuCategory("Chinese");
        MenuSubcategory mainDishes = new MenuSubcategory("Main Dishes");
        mainDishes.addItem(new MenuItem("Manchurian", 160.99));
        mainDishes.addItem(new MenuItem("Noodles", 100.50));
        mainDishes.addItem(new MenuItem("Fried Rice", 150.70));
        mainDishes.addItem(new MenuItem("Momos", 50.00));
        Chinese.addSubcategory(mainDishes);

        MenuSubcategory soups = new MenuSubcategory("Soups");
        soups.addItem(new MenuItem("Hot and Sour Soup", 100.30));
        soups.addItem(new MenuItem("Egg Drop Soup", 120.00));
        soups.addItem(new MenuItem("Manchow", 130.99));
        Chinese.addSubcategory(soups);

        MenuCategory Punjabi = new MenuCategory("Punjabi");
        MenuSubcategory mainDishesPunjabi = new MenuSubcategory("Main Dishes");
        mainDishesPunjabi.addItem(new MenuItem("Paneer Tikka Masala", 250.00));
        mainDishesPunjabi.addItem(new MenuItem("Chole Bhature", 150.99));
        mainDishesPunjabi.addItem(new MenuItem("Dal Makhani", 200.60));
        mainDishesPunjabi.addItem(new MenuItem("Lassi", 100.00));
        mainDishesPunjabi.addItem(new MenuItem("Aloo Paratha", 25.00));
        mainDishesPunjabi.addItem(new MenuItem("Chole Kulche", 200.79));
        Punjabi.addSubcategory(mainDishesPunjabi);

        MenuCategory Continental = new MenuCategory("Continental");
        MenuSubcategory mainDishesContinental = new MenuSubcategory("Main Dishes");
        mainDishesContinental.addItem(new MenuItem("Ravioli", 350.00));
        mainDishesContinental.addItem(new MenuItem("Risotto", 550.99));
        mainDishesContinental.addItem(new MenuItem("Spaghetti", 400.60));
        mainDishesContinental.addItem(new MenuItem("Lasagna", 700.00));
        mainDishesContinental.addItem(new MenuItem("Gyoza", 600.00));
        mainDishesContinental.addItem(new MenuItem("Cannelloni", 500.79));
        Continental.addSubcategory(mainDishesContinental);

        MenuCategory Salads = new MenuCategory("Salads");
        MenuSubcategory mainDishesSalads = new MenuSubcategory("Main Dishes");
        mainDishesSalads.addItem(new MenuItem("Greek Salad", 250.00));
        mainDishesSalads.addItem(new MenuItem("Chilli Paneer", 150.99));
        mainDishesSalads.addItem(new MenuItem("Mexican", 200.60));
        mainDishesSalads.addItem(new MenuItem("Pesto Veggie", 100.00));
        mainDishesSalads.addItem(new MenuItem("Italian", 250.00));
        mainDishesSalads.addItem(new MenuItem("Tandoori", 200.79));
        Salads.addSubcategory(mainDishesSalads);

        MenuCategory Italian = new MenuCategory("Italian");
        MenuSubcategory mainDishesItalian = new MenuSubcategory("Main Dishes");
        mainDishesItalian.addItem(new MenuItem("Pizza", 250.00));
        mainDishesItalian.addItem(new MenuItem("White Sauce Pasta", 150.99));
        mainDishesItalian.addItem(new MenuItem("Mecroni", 200.60));
        mainDishesItalian.addItem(new MenuItem("Fentuccine", 300.00));
        mainDishesItalian.addItem(new MenuItem("PennevArrabiata", 200.00));
        mainDishesItalian.addItem(new MenuItem("Garlic Bread", 150.79));
        Italian.addSubcategory(mainDishesItalian);

        MenuCategory Deserts = new MenuCategory("Deserts");
        MenuSubcategory mainDishesDeserts = new MenuSubcategory("Main Dishes");
        mainDishesDeserts.addItem(new MenuItem("Gulab Jamun", 250.00));
        mainDishesDeserts.addItem(new MenuItem("Rass Malai", 150.99));
        mainDishesDeserts.addItem(new MenuItem("Rabdi", 200.60));
        mainDishesDeserts.addItem(new MenuItem("Ice Cream", 100.50));
        mainDishesDeserts.addItem(new MenuItem("Brownie with ice cream", 200.00));
        mainDishesDeserts.addItem(new MenuItem("Saundesh", 150.79));
        Deserts.addSubcategory(mainDishesDeserts);

        menu.add(Chinese);
        menu.add(Punjabi);
        menu.add(Continental);
        menu.add(Salads);
        menu.add(Italian);
        menu.add(Deserts);

        System.out.println("Welcome to the Food Ordering System!");

        while (true) {
            System.out.println("Menu Categories:");
            System.out.println("1. Chinese");
            System.out.println("2. Punjabi");
            System.out.println("3. Continental");
            System.out.println("4. Salads");
            System.out.println("5. Italian");
            System.out.println("6. Deserts");
            System.out.println("7. Search");
        
            System.out.println("Enter the option number (0 to checkout):");
            int categoryChoice = -1;
        
            while (categoryChoice < 0 || categoryChoice > 7) {
                try {
                    categoryChoice = scanner.nextInt();
                    if (categoryChoice < 0 || categoryChoice > 7) {
                        System.out.println("Invalid option. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume the invalid input
                }
            }
            
            if (categoryChoice == 0) {
                // Checkout process
                double total = cart.calculateTotal();
                System.out.println("Order Summary:");
                for (MenuItem item : cart.getItems()) {
                    System.out.println(item.getName() + " - ₹"+ item.getPrice());
                }
                System.out.println("Total: ₹" + total);
                
                // Payment Method
                System.out.println("Do you want to proceed with payment? (yes/no)");
                String paymentChoice = scanner.next().toLowerCase();

                if (paymentChoice.equalsIgnoreCase("yes")) {
                    if (PaymentSystem.processPayment(total)) {
                        System.out.println("How do you want to pay? (Online / Credit Card)");
                        String pay = scanner.next().toLowerCase();
                        scanner.nextLine();
                        if(pay.equals("online")) {
                            while (true) {
                                System.out.println("Enter your 10 digit UPI Id");
                                String upi = scanner.next();
                            if (upi.length() == 10) {
                                System.out.println("Payment successful. Thank you for your order!");
                                break;
                            } else {
                                System.out.println("Invalid UPI ID. Please enter a 10-digit numeric UPI ID.");
                                }
                            }
                            break;
                        } do {
                                while (true) {
                                System.out.println("Enter your credit card number");
                                String cc = scanner.nextLine();
                                if (cc.length() == 16) {
                                    System.out.println("Payment successful. Thank you for your order!");
                                    break;
                                } else {
                                    System.out.println("Invalid Credit Card number. Please enter a 16 digit Credit Card number.");
                                }
                            }
                        } while(pay.equals("credit card"));
                        break;
                    } else {
                        System.out.println("Payment failed. Please try again.");
                    }
                } else {
                    System.out.println("Order canceled.");
                    break;
                }
            } else if (categoryChoice == 7) {
                // Search option
                searchMenuByName(menu);
            } else if (categoryChoice > 0 && categoryChoice <= menu.size()) {
                MenuCategory selectedCategory = menu.get(categoryChoice - 1);

                System.out.println("Subcategories in " + selectedCategory.getCategoryName() + ":");
                for (int i = 0; i < selectedCategory.getSubcategories().size(); i++) {
                    System.out.println((i + 1) + ". " + selectedCategory.getSubcategories().get(i).getSubcategoryName());
                }

                System.out.println("Enter the subcategory number to view items:");
                int subcategoryChoice = scanner.nextInt();

                if (subcategoryChoice > 0 && subcategoryChoice <= selectedCategory.getSubcategories().size()) {
                    MenuSubcategory selectedSubcategory = selectedCategory.getSubcategories().get(subcategoryChoice - 1);

                    System.out.println("Items in " + selectedSubcategory.getSubcategoryName() + ":");
                    for (int i = 0; i < selectedSubcategory.getItems().size(); i++) {
                        MenuItem item = selectedSubcategory.getItems().get(i);
                        System.out.println((i + 1) + ". " + item.getName() + " - ₹" + item.getPrice());
                    }

                    System.out.println("Enter the item number to add to your cart:");
                    int itemChoice = scanner.nextInt();

                    if (itemChoice > 0 && itemChoice <= selectedSubcategory.getItems().size()) {
                        MenuItem selectedItem = selectedSubcategory.getItems().get(itemChoice - 1);
                        cart.addItem(selectedItem);
                        System.out.println(selectedItem.getName() + " added to cart.");
                    } else {
                        System.out.println("Invalid item choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid subcategory choice. Please try again.");
                }
            } else {
                System.out.println("Invalid category choice. Please try again.");
            }
        }

        scanner.close();
    }
}