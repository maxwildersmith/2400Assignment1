package Project;

import java.util.Arrays;

@SuppressWarnings({"unchecked", "WeakerAccess"})
public class Stack<T> implements StackInterface<T>{

    private int size;
    private final static int DEFAULT_SIZE=10;
    private T[] data;

    Stack() {
        this(DEFAULT_SIZE);
    }

    Stack(int startSize){
        size=0;
        data = (T[]) new Object[startSize];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void push(T entry){
        if(isFull())
            data = Arrays.copyOf(data,data.length*2);
        data[size++]=entry;
    }

    /**
     * Checks if the stack is full.
     * @return True if the stack is full.
     */
    private boolean isFull(){
        return data.length==size;
    }

    public T pop(){
        if(isEmpty())
            return null;
        return data[--size];
    }

    public T peek() {
        if (isEmpty())
            return null;
        return data[size-1];
    }


    public void clear(){
        data=(T[]) new Object[DEFAULT_SIZE];
    }

}
