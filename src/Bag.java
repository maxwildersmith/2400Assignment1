import java.util.Arrays;

public class Bag<T>{
    private int currentSize;
    private T[] data;

    private boolean integrityOK=true;
    private static final int DEFAULT_SIZE = 10, MAX_CAPACITY = 10000;

    /**
     * Default Bag constructor with default size (10).
     */
    public Bag() {
        this(DEFAULT_SIZE);
    }

    /**
     * Bag constructor with a specified size.
     * @param startSize The starting size of the bag.
     */
    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public Bag(int startSize) {
        checkCapacity(startSize);
        currentSize = 0;
        data = (T[]) new Object[startSize];
    }

    /**
     * Adds a new entry to the Bag and doubles the size of the data array if need be.
     * @param newEntry The entry to add to the bag.
     * @return True if the adding operation was performed successfully, false if the entry is already present.
     */
    public boolean add(T newEntry) {
        checkIntegrity();
        if(contains(newEntry)!=null)
            return false;
        if (isFull()) {
            T[] temp = data;
            checkCapacity(data.length*2);
            data = Arrays.copyOf(data,data.length*2);
        }
        data[currentSize++]=newEntry;
        return true;
    }

    /**
     * Checks whether the new capacity for the bag is acceptable.
     * Throws an error is the new capacity is less than 0 or greater than MAX_CAPACITY.
     * @param newCapacity New desired capacity to check.
     */
    private void checkCapacity(int newCapacity){
        if (newCapacity > MAX_CAPACITY)
            throw new IllegalStateException("Trying to make a bag that exceeds capacity");
        if (newCapacity < 0)
            throw new IllegalStateException("Trying to make a bag with negative size");
    }

    /**
     * Finds the amount of times an entry is present in the Bag.
     * @param entry Entry to search for.
     * @return Amount of times the specified entry appears.
     */
    public int getFrequencyOf(T entry) {
        checkIntegrity();

        int count = 0;
        for (T t : data)
            if (t.equals(entry))
                count++;
        return count;
    }

    /**
     * Special frequency method for the Student class to get the frequency of the class of the student provided.
     * @param entry A Student with the academic level to find the frequency of.
     * @return Number of Students with the specified class level.
     */
    public int getClassFrequencyOf(Student entry) {
        checkIntegrity();

        if(isEmpty())
            return 0;
        int count = 0;
        for (Student t : Arrays.copyOf(data,data.length,Student[].class))
            if (t!=null&&t.getAcademicLevel().equals(entry.getAcademicLevel()))
                count++;
        return count;
    }

    /**
     * Removes a specified entry from the bag and returns true if the entry was present.
     * @param entry The entry to remove.
     * @return Returns true if the entry was present and removed, false if otherwise.
     */
    public boolean remove(T entry){
        checkIntegrity();
        for(int i=0;i<getCurrentSize();i++)
            if(data[i].equals(entry)){
                data[i]=data[--currentSize];
                data[currentSize]=null;
                return true;
            }
        return false;
    }

    /**
     * Returns whether the Bag is empty or not.
     * @return True if the bag is empty, false if not.
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Returns the current size of the Bag, meaning the amount of items currently in the Bag.
     * @return The current size of the Bag.
     */
    public int getCurrentSize() {
        return currentSize;
    }

    /**
     * Checks whether the integrity of the Bag is ok.
     */
    private void checkIntegrity() {
        if (!integrityOK)
            throw new IllegalStateException("Integrity corrupted!");
    }

    /**
     * Converts the Bag to an array and returns it;
     * @return The array representing the Bag.
     */
    public T[] toArray() {
        checkIntegrity();
        return Arrays.copyOf(data,currentSize);
    }

    /**
     * Checks if the Bag is full or not.
     * @return True if the bag is full, false if otherwise.
     */
    public boolean isFull() {
        return data.length == currentSize;
    }

    public T contains(T entry){
        for(int i=0;i<currentSize;i++)
            if(data[i].equals(entry))
                return data[i];
            return null;
    }
}
