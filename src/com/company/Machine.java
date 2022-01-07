package com.company;

import java.util.Scanner;

class CoffeeMaker {
    // class for different types of coffee
    // drinks that a machine can make
    private static class Coffee {
        int price;
        int water;
        int milk;
        int beans;
        int cups;

        public Coffee(int price, int water, int milk, int beans, int cups) {
            this.price = price;
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
        }
    }

    // the resources that this type of machine holds
    int waterSupply;
    int milkSupply;
    int beansSupply;
    int cupsSupply;
    int money;

    // setting the amount of resources a machine starts with
    public CoffeeMaker(int water, int milk, int beans, int cups, int startCash) {
        this.waterSupply = water;
        this.milkSupply = milk;
        this.beansSupply = beans;
        this.cupsSupply = cups;
        this.money = startCash;
    }

    // creating instances for the different types
    // of coffee that this type of machine can make
    Coffee espresso = new Coffee(4, 250, 0, 16, 1);
    Coffee latte = new Coffee(7, 350, 75, 20, 1);
    Coffee cappuccino = new Coffee(6, 200, 100, 12, 1);

    // creating a scanner object
    Scanner scanner = new Scanner(System.in);

    // displaying a machine's current resources
    public void statusDisplay() {
        System.out.println("The coffee machine has:");
        System.out.println(this.waterSupply + " ml of water");
        System.out.println(this.milkSupply + " ml of milk");
        System.out.println(this.beansSupply + " g of coffee beans");
        System.out.println(this.cupsSupply + " disposable cups");
        System.out.println("$" + this.money + " of money");
        System.out.println();
    }

    // check to see if there are enough resources to make a cup
    private String resourceCheck(Coffee type) {
        String resource;
        if (type.water > this.waterSupply) {
            resource = "water";
        } else if (type.milk > this.milkSupply) {
            resource = "milk";
        } else if (type.beans > this.beansSupply) {
            resource = "beans";
        } else if (type.cups > this.cupsSupply) {
            resource = "cups";
        } else {
            resource = "enough";
        }
        return resource;
    }

    // making a cup of coffee if there are enough resources
    private void makeCoffee(Coffee type) {
        String resource = resourceCheck(type);
        if (resource.equals("enough")) {
            System.out.println("I have enough resources, making you a coffee!\n");
            this.resourceAdjust(type);
        } else {
            System.out.println("Sorry, not enough " + resource + "!\n");
        }
    }

    public void coffeeChoice() {
        System.out.print("What do you want to buy? ");
        System.out.println("1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> this.makeCoffee(this.espresso);
            case "2" -> this.makeCoffee(this.latte);
            case "3" -> this.makeCoffee(this.cappuccino);
            default -> {
            }
        }
    }

    // adjusting the machine's resources based
    // on the type of coffee that is made
    private void resourceAdjust(Coffee type) {
        this.waterSupply -= type.water;
        this.milkSupply -= type.milk;
        this.beansSupply -= type.beans;
        this.cupsSupply -= type.cups;
        this.money += type.price;
    }

    // getting the resources that are to be added to a machine
    public void restock() {
        // in the order of water, milk, beans, & cups
        int[] ingredients = new int[4];

        System.out.println("Write how many ml of water you want to add:");
        ingredients[0] = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        ingredients[1] = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        ingredients[2] = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        ingredients[3] = scanner.nextInt();
        machineFill(ingredients);
    }

    // adding resources to a machine
    private void machineFill(int[] ingredients) {
        this.waterSupply += ingredients[0];
        this.milkSupply += ingredients[1];
        this.beansSupply += ingredients[2];
        this.cupsSupply += ingredients[3];
    }

    // taking the money out of a machine
    public void getMoney() {
        System.out.println("I gave you $" + this.takeMoney() + "\n");
    }

    private int takeMoney() {
        int payOut = this.money;
        this.money -= payOut;
        return payOut;
    }

}

public class Machine {

    public static void main(String[] args) {
	    // enumerator for the state of the coffee machine
        //enum MachineState {BUY, FILL, TAKE, REMAINING, EXIT}

        // creating a coffee machine
        CoffeeMaker aMachine = new CoffeeMaker(400, 540, 120, 9, 550);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String choice = scanner.nextLine();

        while (!choice.equalsIgnoreCase("exit")) {
            switch(choice) {
                case "buy": {
                    aMachine.coffeeChoice();
                }
                break;
                case "fill": {
                    aMachine.restock();
                }
                break;
                case "take": {
                    aMachine.getMoney();
                }
                break;
                case "remaining": {
                    aMachine.statusDisplay();
                }
                break;
                case "exit": break;
            }
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            choice = scanner.nextLine();
        }


    }
}
