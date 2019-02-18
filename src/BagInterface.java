interface BagInterface<T> {
    public int getCurrentSize();
    public boolean isEmpty();
    public boolean add(T entry);
    public T remove();
    public boolean remove(T entry);
    public void clear();
    public int frequencyOf(T entry);
    public boolean contains(T entry);
    public T[] toArray();
}
