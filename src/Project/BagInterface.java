package Project;

@SuppressWarnings({"unused", "UnusedReturnValue"})
interface BagInterface<T> {
    /**
     * Returns the current size of the Project.Bag, meaning the amount of items currently in the Project.Bag.
     * @return The current size of the Project.Bag.
     */
    int getCurrentSize();

    /**
     * Returns whether the Project.Bag is empty or not.
     * @return True if the bag is empty, false if not.
     */
    boolean isEmpty();

    /**
     * Adds a new entry to the Project.Bag and doubles the size of the data array if need be.
     * @param entry The entry to add to the bag.
     * @return True if the adding operation was performed successfully, false if the entry is already present.
     */
    boolean add(T entry);

    /**
     * Removes an unspecified object from the Project.Bag.
     * @return The object removed.
     */
    T remove();

    /**
     * Removes a specified entry from the bag and returns true if the entry was present.
     * @param entry The entry to remove.
     * @return Returns true if the entry was present and removed, false if otherwise.
     */
    boolean remove(T entry);

    /**
     * Clears all the data in the Project.Bag.
     */
    void clear();
    /**
     * Finds the amount of times an entry is present in the Project.Bag.
     * @param entry Entry to search for.
     * @return Amount of times the specified entry appears.
     */
    int getFrequencyOf(T entry);

    /**
     * Searches the Bag to to see if a given data object is present.
     * @param entry The data to search for.
     * @return The Bag entry containing the data.
     */
    T contains(T entry);

    /**
     * Converts the Project.Bag to an array and returns it;
     * @return The array representing the Project.Bag.
     */
    T[] toArray();
}
