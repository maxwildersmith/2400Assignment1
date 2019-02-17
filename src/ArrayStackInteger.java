public class ArrayStackInteger {

    private int top, size;
    private final int DEFAULT_SIZE=10;
    private Integer[] data;

    public ArrayStackInteger(){
        data = new Integer[0];
    }

    public int size(){
        return data.length;
    }

    public boolean isEmpty(){
        return data.length==0;
    }
    public void push(int i){
        Integer[] temp = new Integer[data.length+1];
        for(int pos=0;pos<data.length;pos++)
            temp[pos]=data[pos];
        temp[data.length]=i;
        data=temp;
        top=data.length-1;
    }
    public Integer pop(){
        if(isEmpty())
            return null;
        Integer[] temp = new Integer[data.length-1];
        for(int pos=0;pos<temp.length;pos++)
            temp[pos]=data[pos];
        int ret=data[data.length-1];
        data=temp;
        top=data.length-1;

        return ret;
    }
    public Integer peek() {
        if (isEmpty())
            return null;
        return data[top];

    }
}
