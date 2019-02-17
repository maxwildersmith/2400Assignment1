import java.util.Arrays;

public class LinkedBag<T> {
    private int currentSize;
    private Node<T> data;

    private boolean integrityOK=true;
    private static final int MAX_CAPACITY = 10000;

    /**
     * Default Bag constructor.
     */
    public LinkedBag() {
        currentSize=0;
    }
    /**
     * Adds a new entry to the Bag.
     * @param newEntry The entry to add to the bag.
     * @return True if the adding operation was performed successfully, false if the entry is already present.
     */
    public boolean add(T newEntry) {
        checkIntegrity();
        if(contains(newEntry)!=null)
            return false;
        Node<T> temp = new Node<>(newEntry,data);
        data=temp;
        currentSize++;
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
        Node t = data;
        while(t.getNext()!=null) {
            if (t.equals(entry))
                count++;
            t = t.getNext();
        }
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
        Node t = data;
        while(t!=null) {
            if (((Student)t.getData()).getAcademicLevel().equals(entry.getAcademicLevel()))
                count++;
            t=t.getNext();
        }
        return count;
    }

    /**
     * Removes a specified entry from the bag by replacing it with the data of the first one before removing the first
     * node from the Bag.
     * @param entry The entry to remove.
     * @return Returns true if the entry was present and removed, false if otherwise.
     */
    public boolean remove(T entry){
        checkIntegrity();
        Node t = data;
        while(t!=null) {
            if (t.getData().equals(entry)) {
                t.setData(data.getData());
                remove();
                return true;
            }
            t = t.getNext();
        }
        return false;
    }

    /**
     * Removes the first node in the Bag.
     * @return The first element that was removed.
     */
    public T remove(){
        checkIntegrity();
        Node temp = data;
        data=data.getNext();
        currentSize--;
        return (T)temp.getData();
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
        Node t = data;
        T[] arr = (T[]) new Object[currentSize];
        for(int i=0;i<arr.length;i++) {
            arr[i] = (T) t.getData();
            t = t.getNext();
        }
        return arr;
    }

    public T contains(T entry){
        Node t = data;
        while(t!=null) {
            if (t.getData().equals(entry))
                return (T)t.getData();
            t = t.getNext();
        }
        return null;
    }
}
