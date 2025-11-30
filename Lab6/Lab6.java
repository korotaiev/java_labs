import java.util.ArrayList;
import java.util.List;

/**
 * Main class for demonstrating CustomLinkedSet with Coffee objects.
 * Contains examples of using all three constructors and basic collection operations.
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Lab6 {
    
    /**
     * Main method for demonstrating CustomLinkedSet functionality.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Variant: 11 (Interface: Set, Structure: Doubly Linked List)");
        System.out.println();
        
        // Constructor 1: Empty constructor
        System.out.println("1. Testing empty constructor:");
        CustomLinkedSet<Coffee> emptySet = new CustomLinkedSet<>();
        System.out.println("   Created empty set");
        System.out.println("   Size: " + emptySet.size() + ", isEmpty: " + emptySet.isEmpty());
        System.out.println();
        
        // Constructor 2: Single element constructor
        System.out.println("2. Testing single element constructor:");
        Coffee singleCoffee = new BeanCoffee("Arabica Premium", 480.0, 10.0, 12.0, 9, "Ethiopia");
        CustomLinkedSet<Coffee> singleSet = new CustomLinkedSet<>(singleCoffee);
        System.out.println("   Added: " + singleCoffee.getName());
        System.out.println("   Size: " + singleSet.size());
        System.out.println();
        
        // Constructor 3: Collection constructor
        System.out.println("3. Testing collection constructor:");
        List<Coffee> coffeeList = new ArrayList<>();
        coffeeList.add(new BeanCoffee("Arabica Brazil", 450.0, 10.0, 12.0, 9, "Brazil"));
        coffeeList.add(new GroundCoffee("Espresso Ground", 380.0, 12.0, 15.0, 8, "Fine"));
        coffeeList.add(new InstantCoffeeJar("Nescafe Gold", 280.0, 5.0, 8.0, 6, 190));
        coffeeList.add(new InstantCoffeeSachet("MacCoffee 3in1", 180.0, 4.0, 6.0, 4, 50));
        
        CustomLinkedSet<Coffee> coffeeSet = new CustomLinkedSet<>(coffeeList);
        System.out.println("   Created from ArrayList with " + coffeeList.size() + " elements");
        System.out.println("   Resulting set size: " + coffeeSet.size());
        System.out.println();
        
        // Basic operations
        System.out.println("4. Testing basic operations:");
        
        // Adding new element
        Coffee newCoffee = new BeanCoffee("Robusta Vietnam", 320.0, 15.0, 18.0, 7, "Vietnam");
        boolean added = coffeeSet.add(newCoffee);
        System.out.println("   Adding new coffee '" + newCoffee.getName() + "': " + added);
        System.out.println("   Current size: " + coffeeSet.size());
        
        // Testing Set property - no duplicates
        Coffee duplicate = new BeanCoffee("Arabica Brazil", 450.0, 10.0, 12.0, 9, "Brazil");
        boolean addedDuplicate = coffeeSet.add(duplicate);
        System.out.println("   Adding duplicate 'Arabica Brazil': " + addedDuplicate);
        System.out.println("   Size remains: " + coffeeSet.size() + " (duplicates not allowed)");
        
        // Contains check
        boolean contains = coffeeSet.contains(newCoffee);
        System.out.println("   Contains 'Robusta Vietnam': " + contains);
        
        // Remove operation
        boolean removed = coffeeSet.remove(newCoffee);
        System.out.println("   Removing 'Robusta Vietnam': " + removed);
        System.out.println("   Size after removal: " + coffeeSet.size());
        System.out.println();
        
        // Iteration through collection
        System.out.println("5. Collection content (forEach iteration):");
        int index = 1;
        for (Coffee coffee : coffeeSet) {
            System.out.println("   " + index++ + ". " + coffee.getName() + 
                             " - " + coffee.getCoffeeType() + 
                             " (quality: " + coffee.getQuality() + "/10)");
        }
        System.out.println();
        
        // Additional operations
        System.out.println("6. Additional operations:");
        
        // toArray
        Object[] array = coffeeSet.toArray();
        System.out.println("   Converted to array, length: " + array.length);
        
        // containsAll
        List<Coffee> checkList = new ArrayList<>();
        checkList.add(coffeeList.get(0));
        checkList.add(coffeeList.get(1));
        System.out.println("   Contains all elements from checkList: " + coffeeSet.containsAll(checkList));
        
        // Clear operation
        System.out.println("   Size before clear: " + coffeeSet.size());
        coffeeSet.clear();
        System.out.println("   Size after clear: " + coffeeSet.size() + ", isEmpty: " + coffeeSet.isEmpty());
    }
}