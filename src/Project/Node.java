package Project;

@SuppressWarnings({"WeakerAccess","unused"})
public class Node<T> {
    private T data;
    private Node next;

    /**
     * Gets the Node's data.
     * @return The Node data.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the Node's data.
     * @param data Data to set.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the next Node in the chain.
     * @return The next Node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next node in the chain.
     * @param next The next Node to be set.
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Create Node with nothing next in chain
     * @param data The data for the Node.
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * Creates a Node with a following Node in the chain.
     * @param data The data for the Node.
     * @param next The next Node in the chain.
     */
    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }
}
