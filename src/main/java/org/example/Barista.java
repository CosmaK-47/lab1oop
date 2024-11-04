package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Barista {
    private final Scanner scanner = new Scanner(System.in);

    public void takeOrder() {
        List<Coffee> orders = new ArrayList<>();

        do {
            System.out.println("Enter coffee type (cappuccino, americano, pumpkin spice latte, syrup cappuccino): ");
            String coffeeType = scanner.nextLine().toLowerCase();

            Coffee order = createCoffee(coffeeType); // Only Barista creates Coffee
            if (order != null) {
                orders.add(order);
                makeCoffee(List.of(order));
            } else {
                System.out.println("Sorry, we don't have that type of coffee.");
            }

            System.out.println("Would you like to order something else? (Enter 'yes' or 'no')");
        } while (scanner.nextLine().toLowerCase().equals("yes"));

        System.out.println("Your order is complete. Thank you!");
    }

    private Coffee createCoffee(String type) {
        switch (type) {
            case "cappuccino":
                return new Cappuccino(Intensity.NORMAL, 100);
            case "americano":
                return new Americano(Intensity.STRONG, 200);
            case "pumpkin spice latte":
                return new PumpkinSpiceLatte(Intensity.LIGHT, 150, 30);
            case "syrup cappuccino":
                return createSyrupCappuccino();
            default:
                return null;
        }
    }

    private SyrupCappuccino createSyrupCappuccino() {
        System.out.println("Choose a syrup flavor (macadamia, vanilla, coconut, caramel, chocolate, popcorn): ");
        String syrupType = scanner.nextLine().toUpperCase();

        SyrupType syrup;
        try {
            syrup = SyrupType.valueOf(syrupType);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid syrup choice. Defaulting to VANILLA.");
            syrup = SyrupType.VANILLA;
        }

        return new SyrupCappuccino(Intensity.NORMAL, 120, syrup);
    }

    public void makeCoffee(List<Coffee> orders) {
        for (Coffee coffee : orders) {
            coffee.printDetails();

            if (coffee instanceof Cappuccino && !(coffee instanceof SyrupCappuccino)) {
                ((Cappuccino) coffee).makeCappuccino();
            } else if (coffee instanceof PumpkinSpiceLatte) {
                ((PumpkinSpiceLatte) coffee).makePumpkinSpiceLatte();
            } else if (coffee instanceof Americano) {
                ((Americano) coffee).makeAmericano();
            } else if (coffee instanceof SyrupCappuccino) {
                ((SyrupCappuccino) coffee).makeSyrupCappuccino();
            }
        }
    }
}
