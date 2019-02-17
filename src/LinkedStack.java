public class LinkedStack<T> {

    private int size;
    private Node first;

    public LinkedStack(){
        size=0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void push(T i){
        Node<T> temp = new Node<>(i,first);
        first=temp;
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
