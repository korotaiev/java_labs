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

    /**
     * ВАЖЛИВО: Метод equals() для коректної роботи з Set.
     * Два об'єкти Coffee вважаються рівними, якщо всі їх поля збігаються.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Coffee coffee = (Coffee) obj;
        return Double.compare(coffee.pricePerKg, pricePerKg) == 0 &&
               Double.compare(coffee.weightKg, weightKg) == 0 &&
               Double.compare(coffee.volumeLiters, volumeLiters) == 0 &&
               quality == coffee.quality &&
               name.equals(coffee.name);
    }

    /**
     * ВАЖЛИВО: Метод hashCode() для коректної роботи з Set.
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Double.hashCode(pricePerKg);
        result = 31 * result + Double.hashCode(weightKg);
        result = 31 * result + Double.hashCode(volumeLiters);
        result = 31 * result + quality;
        return result;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        BeanCoffee that = (BeanCoffee) obj;
        return origin.equals(that.origin);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + origin.hashCode();
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

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        GroundCoffee that = (GroundCoffee) obj;
        return grindSize.equals(that.grindSize);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + grindSize.hashCode();
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

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        InstantCoffeeJar that = (InstantCoffeeJar) obj;
        return jarCapacityGrams == that.jarCapacityGrams;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + jarCapacityGrams;
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

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        InstantCoffeeSachet that = (InstantCoffeeSachet) obj;
        return sachetsCount == that.sachetsCount;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + sachetsCount;
    }
}