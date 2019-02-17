public class ArrayStackGeneric<T> {

        private int top, size;
        private final int DEFAULT_SIZE=10;
        private T[] data;

        public ArrayStackGeneric(){
            data = (T[]) new Object[0];
        }

        public int size(){
            return data.length;
        }

        public boolean isEmpty(){
            return data.length==0;
        }
        public void push(T i){
            T[] temp = (T[]) new Object[data.length+1];
            for(int pos=0;pos<data.length;pos++)
                temp[pos]=data[pos];
            temp[data.length]=i;
            data=temp;
            top=data.length-1;
        }
        public T pop(){
            if(isEmpty())
                return null;
            T[] temp = (T[]) new Object[data.length-1];
            for(int pos=0;pos<temp.length;pos++)
                temp[pos]=data[pos];
            T ret=data[data.length-1];
            data=temp;
            top=data.length-1;

            return ret;
        }
        public T peek() {
            if (isEmpty())
                return null;
            return data[top];

        }

}
