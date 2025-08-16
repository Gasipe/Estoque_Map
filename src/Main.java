import entities.Product;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        Map<Integer, Product> productMap = new HashMap<>();

        System.out.print("How many products do you want to register?: ");
        int times = sc.nextInt();

        for (int i = 0; i < times; i++) {
            System.out.print("Enter the code of the product you wish to register: ");
            int code = sc.nextInt();
            sc.nextLine();

            System.out.print("What is the name of the product?: ");
            String name = sc.nextLine();

            System.out.print("What is the price of the product?: ");
            double price = sc.nextDouble();

            System.out.print("What is the quantity?: ");
            int quantitie = sc.nextInt();
            sc.nextLine();

            Product product = new Product(name,price,quantitie);
            productMap.put(code, product);

            }
        for(Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            System.out.println("-------PRODUCT-------");
            System.out.println("Code: " + entry.getKey());
            System.out.println("Product: " + entry.getValue().getName());
            System.out.printf("Price: %.2f\n", entry.getValue().getPrice());
            System.out.println("Quantitie: " + entry.getValue().getQuantitie());
            System.out.println("----------------------");

        }

    }
}