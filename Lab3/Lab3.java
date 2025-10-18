package Lab3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Laboratory work #3.
 * Main executable class for demonstrating work with an array of educational institutions.
 * Performs sorting by foundation year (ascending) and rating (descending),
 * as well as searching for an identical object in the array.
 * 
 * @author Korotaiev Mykhailo
 */
public class Lab3 {
    
    /**
     * Main method of the program
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
    // Create array of educational institutions
    // Note: the sample values (all entries) are initialized inside
    // the helper method createInstitutions(). If your assignment requires
    // that all variables and their values be defined directly in the
    // executable method, you can move the initialization from
    // createInstitutions() into this main method.
    EducationalInstitution[] institutions = createInstitutions();
        
        // Print array before sorting
        System.out.println("=== Array BEFORE sorting ===");
        printArray(institutions);
        
        // Sort array by foundation year (ascending) 
        // and by rating (descending in case of same year)
        sortInstitutions(institutions);
        
        // Print array after sorting
        System.out.println("\n=== Array AFTER sorting ===");
        System.out.println("(by foundation year - ascending, by rating - descending)");
        printArray(institutions);
        
        // Create object to search for
        EducationalInstitution searchTarget = new EducationalInstitution(
            "Kyiv National University",
            "University",
            1834,
            20000,
            95.5
        );
        
        // Search for identical object in array
        System.out.println("\n=== Object search ===");
        System.out.println("Search target: " + searchTarget);
        
        int foundIndex = findInstitution(institutions, searchTarget);
        
        if (foundIndex != -1) {
            System.out.println("Object found at position: " + foundIndex);
            System.out.println("Found object: " + institutions[foundIndex]);
        } else {
            System.out.println("Object NOT found in array");
        }
    }
    
    /**
     * Creates and initializes array of educational institutions
     * 
     * @return array of EducationalInstitution objects
     */
    private static EducationalInstitution[] createInstitutions() {
        EducationalInstitution[] institutions = new EducationalInstitution[8];
        
        // Initialize array objects
        institutions[0] = new EducationalInstitution(
            "Kyiv National University",
            "University",
            1834,
            20000,
            95.5
        );
        
        institutions[1] = new EducationalInstitution(
            "Lviv Polytechnic",
            "University",
            1844,
            25000,
            92.3
        );
        
        institutions[2] = new EducationalInstitution(
            "Kharkiv Technical University",
            "University",
            1885,
            18000,
            89.7
        );
        
        institutions[3] = new EducationalInstitution(
            "Odesa Medical University",
            "University",
            1900,
            12000,
            91.2
        );
        
        institutions[4] = new EducationalInstitution(
            "Dnipro College of Technology",
            "College",
            1885,
            5000,
            85.4
        );
        
        institutions[5] = new EducationalInstitution(
            "Zaporizhzhia Technical School",
            "Technical School",
            1920,
            3000,
            78.9
        );
        
        institutions[6] = new EducationalInstitution(
            "Poltava School #5",
            "School",
            1885,
            800,
            92.1
        );
        
        institutions[7] = new EducationalInstitution(
            "Chernihiv Pedagogical University",
            "University",
            1916,
            8000,
            87.6
        );
        
        return institutions;
    }
    
    /**
     * Sorts array of educational institutions by foundation year (ascending),
     * and in case of same year - by rating (descending)
     * 
     * @param institutions array to sort
     */
    private static void sortInstitutions(EducationalInstitution[] institutions) {
        // Create comparator for complex sorting
        Comparator<EducationalInstitution> complexComparator = 
            Comparator.comparingInt(EducationalInstitution::getFoundationYear)
                      .thenComparing(Comparator.comparingDouble(
                          EducationalInstitution::getRating).reversed());
        
        // Sort array using standard Arrays.sort() method
        Arrays.sort(institutions, complexComparator);
    }
    
    /**
     * Searches for identical object in array
     * 
     * @param institutions array to search in
     * @param target object to search for
     * @return index of found object or -1 if not found
     */
    private static int findInstitution(EducationalInstitution[] institutions, 
                                       EducationalInstitution target) {
        // Sequential search in array
        for (int i = 0; i < institutions.length; i++) {
            if (institutions[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Prints array of educational institutions to console
     * 
     * @param institutions array to print
     */
    private static void printArray(EducationalInstitution[] institutions) {
        for (int i = 0; i < institutions.length; i++) {
            System.out.println("[" + i + "] " + institutions[i]);
        }
    }
}
