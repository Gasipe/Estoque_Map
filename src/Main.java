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
                case 2 : System.out.println("listar todos produtos");
                default: System.out.println("Invalid option! Please try again");
            }
        }
        while(option != 3);

        sc.close();

        System.out.print("How many products do you want to register?: ");
        int times = sc.nextInt();


        //--------------Para exibir o resultado ---------------
        for(Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            System.out.println("-------PRODUCT-------");
            System.out.printf("Code: ", entry.getKey());
            System.out.printf("Product: ", entry.getValue().getName());
            System.out.printf("Price: %.2f" + entry.getValue().getPrice());
            System.out.printf("Quantitie: ", entry.getValue().getQuantitie());
            System.out.println("----------------------");

        }

    }
    public static void displayMenu() {
        System.out.println("\n ==== Product Management System ====");
        System.out.println("1. Register new Products: ");
        System.out.println("2. List all products: ");
        System.out.println("3. Exit");
    }

    public static void registerProduct(Map<Integer, Product> productMap) {
        //---------Criação do produto----------
        System.out.print("Enter the code of the product you wish to register: ");
        Scanner sc = new Scanner(System.in);
        int code = sc.nextInt();
            sc.nextLine();

            System.out.print("What is the name of the product?: ");
            String name = sc.nextLine();

            System.out.print("What is the price of the product?: ");
            double price = sc.nextDouble();

            System.out.print("What is the quantity?: ");
            int quantitie = sc.nextInt();
            sc.nextLine();

            Product product = new Product(name, price, quantitie);
            productMap.put(code, product);

    }

}