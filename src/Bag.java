import java.util.Arrays;

public class Bag<T> {
    private int currentSize;
    private T[] data;

    private boolean integrityOK;
    private static final int DEFAULT_SIZE = 10, MAX_CAPACITY = 10000;

    public Bag() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings({"unchecked", "WeakerAccess"})
    public Bag(int startSize) {
        checkCapacity(startSize);
        currentSize = 0;
        data = (T[]) new Object[startSize];
    }

    public boolean add(T newEntry) {
        checkIntegrity();
        if (isFull()) {
            T[] temp = data;
            checkCapacity(data.length*2);
            data = Arrays.copyOf(data,data.length*2);
        }
        return true;
    }

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

    public boolean remove(T entry){
        checkIntegrity();

    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    private void checkIntegrity() {
        if (!integrityOK)
            throw new IllegalStateException("Integrity corrupted!");
    }

    public T[] toArray() {
        return data;
    }

    public boolean isFull() {
        return data.length == currentSize;
    }
}
