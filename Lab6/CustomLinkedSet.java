import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Implementation of the {@link Set} interface based on a doubly linked list.
 * The collection does not allow duplicate elements and maintains insertion order.
 * Parameterized with Coffee type (class from Laboratory Work #5).
 * 
 * @param <E> type of elements stored in the collection (Coffee or its descendants)
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 * @see Coffee
 */
public class CustomLinkedSet<E> implements Set<E> {
    
    /**
     * Internal class representing a node of the doubly linked list.
     * Each node contains data and references to the next and previous nodes.
     * 
     * @param <E> type of data stored in the node
     */
    private static class Node<E> {
        /** Data stored in the node */
        E data;
        
        /** Reference to the next node in the list */
        Node<E> next;
        
        /** Reference to the previous node in the list */
        Node<E> prev;
        
        /**
         * Constructor for creating a node with data.
         * 
         * @param data data to be stored in the node
         */
        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    /** Reference to the first node of the list (head) */
    private Node<E> head;
    
    /** Reference to the last node of the list (tail) */
    private Node<E> tail;
    
    /** Number of elements in the collection */
    private int size;
    
    /**
     * Empty constructor.
     * Creates an empty collection with no elements.
     */
    public CustomLinkedSet() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Constructor that accepts a single object.
     * Creates a collection with one element.
     * 
     * @param element element to be added to the collection
     */
    public CustomLinkedSet(E element) {
        this();
        add(element);
    }
    
    /**
     * Constructor that accepts a standard collection.
     * Creates a collection and copies all elements from the passed collection.
     * Duplicates are not added (Set property).
     * 
     * @param collection collection of elements to copy
     * @throws NullPointerException if the passed collection is null
     */
    public CustomLinkedSet(Collection<? extends E> collection) {
        this();
        if (collection == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        addAll(collection);
    }
    
    /**
     * Adds an element to the collection if it does not already exist.
     * Uses the equals() method to check for duplicates.
     * The element is added at the end of the list.
     * Time complexity: O(n).
     * 
     * @param element element to add
     * @return true if the element was added; false if the element already existed
     */
    @Override
    public boolean add(E element) {
        if (contains(element)) {
            return false;
        }
        
        Node<E> newNode = new Node<>(element);
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        size++;
        return true;
    }
    
    /**
     * Adds all elements from the passed collection.
     * Duplicates are not added.
     * 
     * @param c collection of elements to add
     * @return true if at least one element was added
     * @throws NullPointerException if the collection is null
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        
        boolean modified = false;
        for (E element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }
    
    /**
     * Checks if an element is contained in the collection.
     * Uses the equals() method for comparison.
     * Time complexity: O(n).
     * 
     * @param o object to search for
     * @return true if the element is found; false otherwise
     */
    @Override
    public boolean contains(Object o) {
        Node<E> current = head;
        while (current != null) {
            if (o == null ? current.data == null : o.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Checks if the collection contains all elements from the passed collection.
     * 
     * @param c collection to check
     * @return true if all elements are present
     * @throws NullPointerException if the collection is null
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Removes an element from the collection if it is present.
     * Uses the equals() method to find the element.
     * Time complexity: O(n).
     * 
     * @param o element to remove
     * @return true if the element was removed; false if the element was not found
     */
    @Override
    public boolean remove(Object o) {
        Node<E> current = head;
        
        while (current != null) {
            if (o == null ? current.data == null : o.equals(current.data)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Removes all elements that are contained in the passed collection.
     * 
     * @param c collection of elements to remove
     * @return true if at least one element was removed
     * @throws NullPointerException if the collection is null
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        boolean modified = false;
        for (Object element : c) {
            while (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }
    
    /**
     * Retains only those elements that are contained in the passed collection.
     * Removes all other elements.
     * 
     * @param c collection of elements to retain
     * @return true if the collection was modified
     * @throws NullPointerException if the collection is null
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        boolean modified = false;
        Node<E> current = head;
        
        while (current != null) {
            Node<E> next = current.next;
            if (!c.contains(current.data)) {
                remove(current.data);
                modified = true;
            }
            current = next;
        }
        return modified;
    }
    
    /**
     * Clears the collection by removing all elements.
     * After this method, the collection size becomes 0.
     * Time complexity: O(1).
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Returns the number of elements in the collection.
     * Time complexity: O(1).
     * 
     * @return size of the collection
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Checks if the collection is empty.
     * Time complexity: O(1).
     * 
     * @return true if the collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns an iterator for traversing the collection elements.
     * The iterator supports element removal during iteration.
     * 
     * @return iterator for the collection
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;
            private Node<E> lastReturned = null;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the set");
                }
                lastReturned = current;
                E data = current.data;
                current = current.next;
                return data;
            }
            
            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException("next() must be called before remove()");
                }
                CustomLinkedSet.this.remove(lastReturned.data);
                lastReturned = null;
            }
        };
    }
    
    /**
     * Converts the collection to an array of objects.
     * The order of elements in the array corresponds to the order of addition to the collection.
     * 
     * @return array containing all elements of the collection
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> current = head;
        
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
    
    /**
     * Returns an array containing all elements of the collection.
     * The type of the array is determined by the passed parameter.
     * If the array is not large enough, a new array of the required size is created.
     * 
     * @param <T> type of array elements
     * @param a array into which elements will be placed
     * @return array with collection elements
     * @throws NullPointerException if the array is null
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("Array cannot be null");
        }
        
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        
        int index = 0;
        Node<E> current = head;
        Object[] result = a;
        
        while (current != null) {
            result[index++] = current.data;
            current = current.next;
        }
        
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
    
    /**
     * Returns a string representation of the collection.
     * Format: [element1, element2, element3, ...]
     * 
     * @return string representing the collection
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Returns detailed information about the collection state.
     * Includes size, status (empty/not empty), and first/last elements.
     * 
     * @return string with detailed information
     */
    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();
        info.append("CustomLinkedSet Details:\n");
        info.append("  Size: ").append(size).append("\n");
        info.append("  Empty: ").append(isEmpty()).append("\n");
        
        if (!isEmpty()) {
            info.append("  First element: ").append(head.data).append("\n");
            info.append("  Last element: ").append(tail.data).append("\n");
        }
        
        return info.toString();
    }
}