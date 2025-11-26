/**
 * Base abstract class for all coffee types.
 * Contains common properties and methods for all kinds of coffee.
 */
abstract class Coffee {
    private String name;
    private double pricePerKg;
    private double weightKg;
    private double volumeLiters;
    private int quality;

    /**
     * Constructor for Coffee base class.
     * 
     * @param name coffee name
     * @param pricePerKg price per kilogram
     * @param weightKg weight in kilograms
     * @param volumeLiters volume including packaging in liters
     * @param quality coffee quality (1-10)
     * @throws IllegalArgumentException if parameters are invalid
     */
    public Coffee(String name, double pricePerKg, double weightKg, double volumeLiters, int quality) {
        if (pricePerKg <= 0 || weightKg <= 0 || volumeLiters <= 0) {
            throw new IllegalArgumentException("Price, weight and volume must be greater than 0");
        }
        if (quality < 1 || quality > 10) {
            throw new IllegalArgumentException("Coffee quality must be between 1 and 10");
        }
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.weightKg = weightKg;
        this.volumeLiters = volumeLiters;
        this.quality = quality;
    }

    public String getName() { 
        return name; 
    }
    
    public double getPricePerKg() { 
        return pricePerKg; 
    }
    
    public double getWeightKg() { 
        return weightKg; 
    }
    
    public double getVolumeLiters() { 
        return volumeLiters; 
    }
    
    public int getQuality() { 
        return quality; 
    }

    /**
     * Calculates total price of the product.
     * 
     * @return total price
     */
    public double getTotalPrice() {
        return pricePerKg * weightKg;
    }

    /**
     * Calculates price to weight ratio.
     * 
     * @return price/weight ratio
     */
    public double getPriceToWeightRatio() {
        return getTotalPrice() / weightKg;
    }

    /**
     * Abstract method to get coffee type.
     * 
     * @return coffee type description
     */
    public abstract String getCoffeeType();

    @Override
    public String toString() {
        return String.format("%s (%s): %.2f kg, %.2f L, quality: %d, price: %.2f UAH (%.2f UAH/kg)",
                name, getCoffeeType(), weightKg, volumeLiters, quality, getTotalPrice(), pricePerKg);
    }
}

/**
 * Class for bean coffee.
 */
class BeanCoffee extends Coffee {
    private String origin;

    /**
     * Constructor for BeanCoffee.
     * 
     * @param name coffee name
     * @param pricePerKg price per kilogram
     * @param weightKg weight in kilograms
     * @param volumeLiters volume including packaging in liters
     * @param quality coffee quality (1-10)
     * @param origin country of origin
     */
    public BeanCoffee(String name, double pricePerKg, double weightKg, double volumeLiters, 
                      int quality, String origin) {
        super(name, pricePerKg, weightKg, volumeLiters, quality);
        this.origin = origin;
    }

    public String getOrigin() { 
        return origin; 
    }

    @Override
    public String getCoffeeType() {
        return "Bean (" + origin + ")";
    }
}

/**
 * Class for ground coffee.
 */
class GroundCoffee extends Coffee {
    private String grindSize;

    /**
     * Constructor for GroundCoffee.
     * 
     * @param name coffee name
     * @param pricePerKg price per kilogram
     * @param weightKg weight in kilograms
     * @param volumeLiters volume including packaging in liters
     * @param quality coffee quality (1-10)
     * @param grindSize grind size description
     */
    public GroundCoffee(String name, double pricePerKg, double weightKg, double volumeLiters, 
                        int quality, String grindSize) {
        super(name, pricePerKg, weightKg, volumeLiters, quality);
        this.grindSize = grindSize;
    }

    public String getGrindSize() { 
        return grindSize; 
    }

    @Override
    public String getCoffeeType() {
        return "Ground (" + grindSize + " grind)";
    }
}

/**
 * Class for instant coffee in jars.
 */
class InstantCoffeeJar extends Coffee {
    private int jarCapacityGrams;

    /**
     * Constructor for InstantCoffeeJar.
     * 
     * @param name coffee name
     * @param pricePerKg price per kilogram
     * @param weightKg weight in kilograms
     * @param volumeLiters volume including packaging in liters
     * @param quality coffee quality (1-10)
     * @param jarCapacityGrams jar capacity in grams
     */
    public InstantCoffeeJar(String name, double pricePerKg, double weightKg, double volumeLiters, 
                            int quality, int jarCapacityGrams) {
        super(name, pricePerKg, weightKg, volumeLiters, quality);
        this.jarCapacityGrams = jarCapacityGrams;
    }

    public int getJarCapacityGrams() { 
        return jarCapacityGrams; 
    }

    @Override
    public String getCoffeeType() {
        return "Instant in jar (" + jarCapacityGrams + "g)";
    }
}

/**
 * Class for instant coffee in sachets.
 */
class InstantCoffeeSachet extends Coffee {
    private int sachetsCount;

    /**
     * Constructor for InstantCoffeeSachet.
     * 
     * @param name coffee name
     * @param pricePerKg price per kilogram
     * @param weightKg weight in kilograms
     * @param volumeLiters volume including packaging in liters
     * @param quality coffee quality (1-10)
     * @param sachetsCount number of sachets
     */
    public InstantCoffeeSachet(String name, double pricePerKg, double weightKg, double volumeLiters, 
                               int quality, int sachetsCount) {
        super(name, pricePerKg, weightKg, volumeLiters, quality);
        this.sachetsCount = sachetsCount;
    }

    public int getSachetsCount() { 
        return sachetsCount; 
    }

    @Override
    public String getCoffeeType() {
        return "Instant in sachets (x" + sachetsCount + ")";
    }
}

/**
 * Class for managing coffee van operations.
 * Handles loading, sorting, and searching coffee items.
 */
class CoffeeVan {
    private Coffee[] coffeeItems;
    private int itemCount;
    private double maxVolumeLiters;
    private double maxBudget;
    private double currentVolume;
    private double currentCost;

    /**
     * Constructor for CoffeeVan.
     * 
     * @param capacity maximum number of items
     * @param maxVolumeLiters maximum volume of the van
     * @param maxBudget maximum budget
     * @throws IllegalArgumentException if parameters are invalid
     */
    public CoffeeVan(int capacity, double maxVolumeLiters, double maxBudget) {
        if (capacity <= 0 || maxVolumeLiters <= 0 || maxBudget <= 0) {
            throw new IllegalArgumentException("Van parameters must be greater than 0");
        }
        this.coffeeItems = new Coffee[capacity];
        this.itemCount = 0;
        this.maxVolumeLiters = maxVolumeLiters;
        this.maxBudget = maxBudget;
        this.currentVolume = 0;
        this.currentCost = 0;
    }

    /**
     * Adds coffee to the van.
     * 
     * @param coffee coffee object to add
     * @return true if successfully added
     */
    public boolean addCoffee(Coffee coffee) {
        try {
            if (coffee == null) {
                throw new NullPointerException("Coffee object cannot be null");
            }
            if (itemCount >= coffeeItems.length) {
                throw new ArrayIndexOutOfBoundsException("Van is fully loaded (array is full)");
            }
            if (currentVolume + coffee.getVolumeLiters() > maxVolumeLiters) {
                throw new IllegalStateException("Insufficient volume in the van");
            }
            if (currentCost + coffee.getTotalPrice() > maxBudget) {
                throw new IllegalStateException("Budget exceeded");
            }

            coffeeItems[itemCount++] = coffee;
            currentVolume += coffee.getVolumeLiters();
            currentCost += coffee.getTotalPrice();
            System.out.println("Added: " + coffee.getName());
            return true;

        } catch (NullPointerException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        } catch (IllegalStateException e) {
            System.err.println("Cannot add " + coffee.getName() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Sorts coffee items by price to weight ratio.
     */
    public void sortByPriceToWeightRatio() {
        if (itemCount == 0) {
            System.out.println("Van is empty, nothing to sort");
            return;
        }

        for (int i = 0; i < itemCount - 1; i++) {
            for (int j = 0; j < itemCount - i - 1; j++) {
                if (coffeeItems[j].getPriceToWeightRatio() > 
                    coffeeItems[j + 1].getPriceToWeightRatio()) {
                    Coffee temp = coffeeItems[j];
                    coffeeItems[j] = coffeeItems[j + 1];
                    coffeeItems[j + 1] = temp;
                }
            }
        }
        System.out.println("Items sorted by price/weight ratio");
    }

    /**
     * Finds coffee items within quality range.
     * 
     * @param minQuality minimum quality
     * @param maxQuality maximum quality
     * @return array of found items
     * @throws IllegalArgumentException if quality range is invalid
     */
    public Coffee[] findByQualityRange(int minQuality, int maxQuality) {
        if (minQuality < 1 || maxQuality > 10 || minQuality > maxQuality) {
            throw new IllegalArgumentException("Invalid quality range (1-10)");
        }

        int count = 0;
        for (int i = 0; i < itemCount; i++) {
            if (coffeeItems[i].getQuality() >= minQuality && 
                coffeeItems[i].getQuality() <= maxQuality) {
                count++;
            }
        }

        Coffee[] result = new Coffee[count];
        int index = 0;
        for (int i = 0; i < itemCount; i++) {
            if (coffeeItems[i].getQuality() >= minQuality && 
                coffeeItems[i].getQuality() <= maxQuality) {
                result[index++] = coffeeItems[i];
            }
        }

        return result;
    }

    /**
     * Displays van information.
     */
    public void displayInfo() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("VAN INFORMATION");
        System.out.println("=".repeat(80));
        System.out.printf("Loaded items: %d / %d%n", itemCount, coffeeItems.length);
        System.out.printf("Used volume: %.2f / %.2f L (%.1f%%)%n", 
                         currentVolume, maxVolumeLiters, (currentVolume/maxVolumeLiters)*100);
        System.out.printf("Used budget: %.2f / %.2f UAH (%.1f%%)%n", 
                         currentCost, maxBudget, (currentCost/maxBudget)*100);
        System.out.println("-".repeat(80));

        if (itemCount == 0) {
            System.out.println("Van is empty");
        } else {
            for (int i = 0; i < itemCount; i++) {
                System.out.printf("%2d. %s%n", i + 1, coffeeItems[i]);
            }
        }
        System.out.println("=".repeat(80));
    }

    public int getItemCount() { 
        return itemCount; 
    }
    
    public double getCurrentVolume() { 
        return currentVolume; 
    }
    
    public double getCurrentCost() { 
        return currentCost; 
    }
}

/**
 * Main class for coffee van management system.
 * Demonstrates inheritance, polymorphism, and exception handling.
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Lab5 {
    public static void main(String[] args) {
        System.out.println("COFFEE VAN MANAGEMENT SYSTEM - Variant 11\n");

        try {
            CoffeeVan van = new CoffeeVan(15, 500.0, 50000.0);

            System.out.println("Loading coffee into the van...\n");

            van.addCoffee(new BeanCoffee("Arabica Brazil", 450.0, 10.0, 12.0, 9, "Brazil"));
            van.addCoffee(new BeanCoffee("Robusta Vietnam", 320.0, 15.0, 18.0, 7, "Vietnam"));
            van.addCoffee(new BeanCoffee("Arabica Colombia", 520.0, 8.0, 10.0, 10, "Colombia"));
            
            van.addCoffee(new GroundCoffee("Espresso Ground", 380.0, 12.0, 15.0, 8, "Fine"));
            van.addCoffee(new GroundCoffee("Americano Ground", 340.0, 10.0, 13.0, 7, "Medium"));
            van.addCoffee(new GroundCoffee("Turkish Ground", 420.0, 7.0, 9.0, 9, "Extra Fine"));
            
            van.addCoffee(new InstantCoffeeJar("Nescafe Gold", 280.0, 5.0, 8.0, 6, 190));
            van.addCoffee(new InstantCoffeeJar("Jacobs Monarch", 310.0, 6.0, 9.0, 7, 190));
            van.addCoffee(new InstantCoffeeJar("Ambassador", 250.0, 8.0, 12.0, 5, 95));
            
            van.addCoffee(new InstantCoffeeSachet("MacCoffee 3in1", 180.0, 4.0, 6.0, 4, 50));
            van.addCoffee(new InstantCoffeeSachet("Petrovskaya Sloboda", 150.0, 3.5, 5.5, 3, 50));
            van.addCoffee(new InstantCoffeeSachet("Carte Noire", 220.0, 3.0, 5.0, 5, 30));

            van.displayInfo();

            System.out.println("\nSorting items by price/weight ratio...\n");
            van.sortByPriceToWeightRatio();
            van.displayInfo();

            System.out.println("\nSearching for coffee with quality 7-10...\n");
            Coffee[] premiumCoffee = van.findByQualityRange(7, 10);
            
            System.out.println("Found " + premiumCoffee.length + " premium quality items:");
            System.out.println("-".repeat(80));
            for (int i = 0; i < premiumCoffee.length; i++) {
                System.out.printf("%d. %s%n", i + 1, premiumCoffee[i]);
            }

            System.out.println("\nSearching for coffee with quality 3-5...\n");
            Coffee[] budgetCoffee = van.findByQualityRange(3, 5);
            
            System.out.println("Found " + budgetCoffee.length + " budget items:");
            System.out.println("-".repeat(80));
            for (int i = 0; i < budgetCoffee.length; i++) {
                System.out.printf("%d. %s%n", i + 1, budgetCoffee[i]);
            }

            System.out.println("\nDemonstrating exception handling:\n");
            
            van.addCoffee(null);
            
            try {
                Coffee invalidCoffee = new BeanCoffee("Test", -100, 5, 5, 5, "Test");
            } catch (IllegalArgumentException e) {
                System.err.println("Error creating coffee: " + e.getMessage());
            }
            
            try {
                van.findByQualityRange(15, 20);
            } catch (IllegalArgumentException e) {
                System.err.println("Error in search: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("\nCritical error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}