package Project;

@SuppressWarnings({"unchecked","unused"})
public class LinkedStack<T> implements StackInterface<T>{

    private int size;
    private Node first;

    LinkedStack(){
        size=0;
        first = null;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void clear() {
        first=null;
        size=0;
    }

    public void push(T entry){
        first = (Node<T>) new Node(entry, first);
        size++;
    }
    public T pop(){
        T temp = (T)first.getData();
        first=first.getNext();
        size--;
        return temp;
    }
    public T peek() {
        return (T)first.getData();
    }

}
