import entities.Product;

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
                case 1 : registerProduct(sc, productMap);
                break;
                case 2 : returnResult(productMap);
                break;
                case 3 : System.out.println("Exiting program...");
                break;
                default: System.out.println("Invalid option! Please try again");
            }
        }
        while(option != 3);

        sc.close();

        System.out.print("How many products do you want to register?: ");
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
        System.out.println("3. Exit");
    }

    public static void registerProduct(Scanner sc, Map<Integer, Product> productMap) {
        //---------Criação do produto----------
            System.out.print("Enter the code of the product you wish to register: ");
            int code = sc.nextInt();
            sc.nextLine();

            if(productMap.containsKey(code)) {
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

}