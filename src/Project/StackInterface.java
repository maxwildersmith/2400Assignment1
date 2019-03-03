package Project;


@SuppressWarnings({"UnnecessaryInterfaceModifier","unused"})
public interface StackInterface<T> {
    /**
     * Pushes a new data entry to the top of the stack.
     * @param entry Data to push
     */
    public void push(T entry);

    /**
     * Pops the top data and removes it from the Project.Stack.
     * @return The top bit of data.
     */
    public T pop();

    /**
     * Peeks at the top data without removing it.
     * @return The top bit of data.
     */
    public T peek();

    /**
     * Checks if the Project.Stack is empty.
     * @return True if the Project.Stack is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Clears all data in the Project.Stack.
     */
    public void clear();
}
