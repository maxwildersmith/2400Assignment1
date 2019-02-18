import java.util.Arrays;

public class InClass {
    String[] data;
    int numberOfEntries;

    public static void main(String[] args) {
        new InClass();
    }
    public InClass(){
        data= new String[10];
        data[0]="Alice";
        data[1]="Bob";
        data[2]="Doug";
        data[3]="Haley";
        numberOfEntries=4;
        makeRoom(2);
        data[2]="John";
        remove(3);
        System.out.println(Arrays.toString(data));
    }
    public void makeRoom(int pos){
        for(int i=numberOfEntries;i>=pos;i--)
            data[i+1]=data[i];
        data[pos]=null;
        numberOfEntries++;
    }
    public void remove(int pos){
        for(int i=pos;i<numberOfEntries;i++)
            data[i]=data[i+1];
        data[numberOfEntries]=null;
        numberOfEntries--;
    }
    public String replace(int pos, String newEntry){
        if(pos<0||pos>=numberOfEntries)
            throw new IndexOutOfBoundsException("Position outside of index");
        String temp = data[pos];
        data[pos] = newEntry;
        return temp;
    }
}
