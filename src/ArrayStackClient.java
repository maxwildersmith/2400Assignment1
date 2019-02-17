import java.util.Scanner;
import java.io.File;
import java.io.IOException;
class ArrayStackClient{
	public static void main(String []args){		
		//Create an object of ArrayStackInteger
		Stack<Integer> scores = new Stack<>();
		//Read the scores from a text file and add it to the stack
		File myFile;
		Scanner scan;
		String str;
		int val;
		try {
			myFile=new File(args[0]);
            scan=new Scanner(myFile);
			while(scan.hasNextLine()){
				str=scan.nextLine();
				//convert string to integer value
				val=Integer.parseInt(str);
				scores.push(new Integer(val));
			}
		}catch(IOException ioe){
			System.out.println("The file can not be read");
		}
		//find the average value of the grades
		double total=0,count=scores.size();
		while(!scores.isEmpty())
			total+=scores.pop();
		System.out.println(total/count);
	}
}