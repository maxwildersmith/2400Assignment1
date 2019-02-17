public interface BagInterface<T> {
    public int getCurrentSize();
    public boolean isEmpty();
    public boolean add(T entry);
    public T remove();
    public boolean remove(T entry);
    public void clear();
    public int frequencyOf(T entry);
}
