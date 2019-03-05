package Project;

@SuppressWarnings({"unchecked", "FieldCanBeLocal"})
public class LinkedBag<T> implements BagInterface<T>{
    private int currentSize;
    private Node<T> data;

    private boolean integrityOK=true;

    /**
     * Default Project.Bag constructor.
     */
    LinkedBag() {
        currentSize=0;
    }

    public boolean add(T newEntry) {
        checkIntegrity();
        if(contains(newEntry)!=null)
            return false;
        data= new Node<>(newEntry,data);
        currentSize++;
        return true;
    }

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
     * Special frequency method for the Project.Student class to get the frequency of the class of the student provided.
     * @param entry A Project.Student with the academic level to find the frequency of.
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

    public boolean isFull() {
        return false;
    }

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

    public void clear() {
        data=null;
        currentSize=0;
    }

    public T remove(){
        checkIntegrity();
        Node temp = data;
        data=data.getNext();
        currentSize--;
        return (T)temp.getData();
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
