package Project;

import java.util.Arrays;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal", "unchecked"})
public class Bag<T> implements BagInterface<T>{
    private int currentSize;
    private T[] data;

    private boolean integrityOK=true;
    private static final int DEFAULT_SIZE = 10, MAX_CAPACITY = 10000;

    /**
     * Default Project.Bag constructor with default size (10).
     */
    public Bag() {
        this(DEFAULT_SIZE);
    }

    /**
     * Project.Bag constructor with a specified size.
     * @param startSize The starting size of the bag.
     */
    public Bag(int startSize) {
        checkCapacity(startSize);
        currentSize = 0;
        data = (T[]) new Object[startSize];
    }

    public boolean add(T newEntry) {
        checkIntegrity();
        if(contains(newEntry)!=null)
            return false;
        if (isFull()) {
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

    public int getFrequencyOf(T entry) {
        checkIntegrity();

        int count = 0;
        for (T t : data)
            if (t.equals(entry))
                count++;
        return count;
    }

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

    public T remove(){
        checkIntegrity();
        if(isEmpty())
            return null;
        T tmp = data[currentSize];
        data[currentSize]=null;
        return tmp;
    }


    public void clear(){
        data = (T[])new Object[DEFAULT_SIZE];
        currentSize=0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }


    public int getCurrentSize() {
        return currentSize;
    }

    /**
     * Checks whether the integrity of the Project.Bag is ok.
     */
    private void checkIntegrity() {
        if (!integrityOK)
            throw new IllegalStateException("Integrity corrupted!");
    }

    public T[] toArray() {
        checkIntegrity();
        return Arrays.copyOf(data,currentSize);
    }

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
