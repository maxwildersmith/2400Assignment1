package Project;

@SuppressWarnings({"unchecked","unused"})
public class LinkedStack<T> implements StackInterface<T>{

    private int size;
    private Node first;

    LinkedStack(){
        size=0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void clear() {
        first=null;
    }

    public void push(T i){
        first= (Node<T>) new Node(i,first);
        size++;
    }
    public T pop(){
        T temp = (T)first.getData();
        first=first.getNext();
        return temp;
    }
    public T peek() {
        return (T)first.getData();

    }

}
