package Project;

import java.util.Vector;

@SuppressWarnings("WeakerAccess")
public class VectorStack<T> implements StackInterface<T> {

    private Vector<T> stack;
    private final static int DEFAULT_CAPACITY = 10, MAX_CAPACITY = 10000;
    private boolean integrityOK;

    public VectorStack(){
        this(DEFAULT_CAPACITY);
    }

    VectorStack(int startSize){
        checkCapacity(startSize);
        stack=new Vector<>(startSize);
        integrityOK=true;

    }

    private void checkCapacity(int newCapacity){
        if (newCapacity > MAX_CAPACITY)
            throw new IllegalStateException("Trying to make a bag that exceeds capacity");
        if (newCapacity < 0)
            throw new IllegalStateException("Trying to make a bag with negative size");
    }

    /**
     * Checks whether the integrity of the Stack is ok.
     */
    private void checkIntegrity() {
        if (!integrityOK)
            throw new IllegalStateException("Integrity corrupted!");
    }

    public void push(T entry) {
        checkIntegrity();
        stack.add(entry);
    }

    public T pop() {
        checkIntegrity();
        return stack.remove(stack.size()-1);
    }

    public T peek() {
        checkIntegrity();
        return stack.lastElement();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        checkIntegrity();
        stack.clear();
    }
}
