import entities.Product;

import javax.swing.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Map<Integer, Product> productMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        int option;
        do {
            displayMenu();
            System.out.println("Choose an option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    registerProduct(sc, productMap);
                    break;
                case 2:
                    returnResult(productMap);
                    break;
                case 3:
                    searchForCodeOrName(productMap, sc);
                    break;
                case 4:
                    updateProduct(sc, productMap);
                    break;
                case 5:
                    removeProduct(sc, productMap);
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option! Please try again");
            }
        }
        while (option != 6);

        sc.close();

        int times = sc.nextInt();


    }

    public static void returnResult(Map<Integer, Product> productMap) {
        //--------------Para exibir o resultado ---------------
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            System.out.println("-------PRODUCT-------");
            System.out.printf("Code: %d\n", entry.getKey());
            System.out.printf("Product: %s\n", entry.getValue().getName());
            System.out.printf("Price: %.2f\n", entry.getValue().getPrice());
            System.out.printf("Quantitie: %d\n", entry.getValue().getQuantitie());
            System.out.println("----------------------");

        }
    }


    public static void displayMenu() {
        //-------Menu-------
        System.out.println("\n ==== Product Management System ====");
        System.out.println("1. Register new Products: ");
        System.out.println("2. List all products: ");
        System.out.println("3. Search product: ");
        System.out.println("4. update product: ");
        System.out.println("5. Remove product: ");
        System.out.println("6. Exit");
    }

    public static void registerProduct(Scanner sc, Map<Integer, Product> productMap) {
        //---------Criação do produto----------
        System.out.print("Enter the code of the product you wish to register: ");
        int code = sc.nextInt();
        sc.nextLine();

        if (productMap.containsKey(code)) {
            System.out.println("Error: Product code " + code + "already exist!");
            return;
        }

        System.out.print("What is the name of the product?: ");
        String name = sc.nextLine();

        System.out.print("What is the price of the product?: ");
        double price = sc.nextDouble();

        System.out.print("What is the quantity?: ");
        int quantitie = sc.nextInt();
        sc.nextLine();

        Product product = new Product(name, price, quantitie);
        productMap.put(code, product);
        System.out.println("Product registered sucessfully! code: " + code);

    }

    public static void searchForCodeOrName(Map<Integer, Product> productMap, Scanner sc) {
        if (productMap.isEmpty()) {
            System.out.println("No products registered");
            return;
        }

        sc.nextLine();
        String op;

        do {
            System.out.print("Search by [C]ode or [N]ame? (C/N): ");
            op = sc.nextLine().trim().toUpperCase();

            if (!op.equals("C") && !op.equals("N")) {
                System.out.println("Error! Please enter C or N");
            }
        } while (!op.equals("C") && !op.equals("N"));

        if (op.equals("C")) {
            System.out.print("Enter the product code: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                System.out.print("Enter the product code: ");
            }
            int searchCode = sc.nextInt();
            sc.nextLine();

            if (productMap.containsKey(searchCode)) {
                Product product = productMap.get(searchCode);
                System.out.printf("\nProduct: %s\nCode: %d\nPrice: %.2f\nQuantity: %d\n",
                        product.getName(),
                        searchCode,
                        product.getPrice(),
                        product.getQuantitie());
            } else {
                System.out.println("Product not found!");
            }
        } else {
            System.out.print("Enter the product name: ");
            String searchName = sc.nextLine().toLowerCase();
            boolean found = false;

            for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
                Product product = entry.getValue();
                if (product.getName().toLowerCase().contains(searchName)) {
                    System.out.printf("\nProduct: %s\nCode: %d\nPrice: %.2f\nQuantity: %d\n",
                            product.getName(),
                            entry.getKey(),
                            product.getPrice(),
                            product.getQuantitie());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No products found with name: " + searchName);
            }
        }
    }

    public static void updateProduct(Scanner sc, Map<Integer, Product> productMap) {
        System.out.println("Enter product code to update: ");
        int code = sc.nextInt();
        sc.nextLine();
        do {
            if (productMap.containsKey(code)) {
                Product product = productMap.get(code);
                System.out.println("Cuurent product: " + product.getName());

                System.out.print("New name (press Enter to keep current): ");
                String newName = sc.nextLine();

                if (!newName.isEmpty()) product.setName(newName);

                System.out.print("New price: ");
                double newPrice = sc.nextDouble();
                if (newPrice > 0) product.setPrice(newPrice);

                System.out.print("Quantity: ");
                int newQuantity = sc.nextInt();
                if (newQuantity > 0) product.setQuantitie(newQuantity);

                System.out.println("Product update sucessfully!");
            } else {
                System.out.println("Invalid code");
            }
        } while (!productMap.containsKey(code));
    }

    public static void removeProduct(Scanner sc, Map<Integer, Product> productMap) {
        System.out.println("Enter the code of the product you want to delete: ");
        int code = sc.nextInt();
        sc.nextLine();
        Product product = productMap.get(code);

        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
            if (productMap.containsKey(code)) {
                productMap.remove(code);
                System.out.printf("Product (%s) removed", product.getName().toUpperCase());
                return;
            } else {
                attempts++;
                System.out.println("Invalid code");
                if (attempts < maxAttempts) {
                    System.out.println("Try again");
                }
            }
        }
        System.out.println("Maximum attempts reached. Operation cancelled");
    }
}