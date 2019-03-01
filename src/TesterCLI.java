import java.util.Scanner;

public class TesterCLI {
    public static void main(String[] args) {
//        task1();
        task2();

    }

    public static void task2(){
        System.out.println("Beginning Task 2....");
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("Enter infix expression or type 'q' to exit: ");
            String exp = in.nextLine();
            if(exp.toLowerCase().trim().charAt(0)=='q')
                running=false;
            else if(!checkBalance(exp))
                System.out.println("Expression is not balanced!");
            else {
                System.out.println("Prefix: " + prefix(exp));
                System.out.println("Postfix: " + postfix(exp));
            }
        }
    }

    public static int priorityOfOp(char op){
        int priority=0;
        switch (op){
            case '{':
            case '}':
            case '[':
            case ']':
            case '(':
            case ')':
                priority=4;
                break;
            case '^':
                priority=3;
                break;
            case '*':
            case '/':
                priority=2;
                break;
            case '+':
            case '-':
                priority=1;
                break;
        }
        return priority;
    }

    //highest priority - parentheses, exponent, */, +-   a-b+c=ab-c+     a^b^c=abc^^
    private static String postfix(String exp) {
        String out="";
        Stack<Character> ops = new Stack<>();
        char[] chars = exp.toCharArray();
        for(char c: chars){
            switch (c){
                case '+':
                case '-':
                case '/':
                case '*':
                case '(':
                case '{':
                case '[':
                    if(!ops.isEmpty()&&(priorityOfOp(c)<=priorityOfOp(ops.peek())))
                        out+=ops.pop();
                    ops.push(c);
                    break;
                case ')':
                case '}':
                case ']':
                    while(!ops.isEmpty()&&priorityOfOp(ops.peek())!=4)
                        out+=ops.pop();
                default:
                    out+=c;
            }
        }
        while(!ops.isEmpty())
            out+=ops.pop();
        return out;
    }

    private static String prefix(String exp){

        return exp;
    }

    /**
     * Method to make sure the (, {, and [ of an expression are balanced and in the right order.
     * @param exp The expression as a String.
     * @return True if the expression is valid.
     */
    private static boolean checkBalance(String exp){
        exp=exp.replaceAll(" ","");
        boolean isBalanced = true;
        char[] chars = exp.toCharArray();
        int index=0;
        Stack<Character> sym = new Stack<>();
        while(isBalanced&&index<chars.length){
            switch (chars[index]){
                case '(':
                    sym.push('(');
                    break;
                case '{':
                    sym.push('{');
                    break;
                case '[':
                    sym.push('[');
                    break;
                case ')':
                    if(sym.isEmpty())
                        isBalanced=false;
                    else
                        isBalanced=sym.pop().equals('(');
                    break;
                case '}':
                    if(sym.isEmpty())
                        isBalanced=false;
                    else
                        isBalanced=sym.pop().equals('{');
                    break;
                case ']':
                    if(sym.isEmpty())
                        isBalanced=false;
                    else
                        isBalanced=sym.pop().equals('[');
                    break;
            }
            printStack(chars[index],sym);
            index++;
        }
        return isBalanced;
    }

    public static void printStack(char c, Stack stack){
        System.out.printf("''"+c+"'\t"+stack.toString()+"\n");
    }

    /**
     * Method to create rosters and test Task #1 of the assignment while querying the user for input
     */
    private static void task1(){
        System.out.println("Beginning Task 1 part 1....");
        Bag<Student> roster = new Bag<>();
        boolean running = true;
        Scanner in = new Scanner(System.in);
        while(running){
            System.out.println("Choose an option: 1-add student 2-drop student 3-search student 4-get class info 5-exit");
            switch (in.nextInt()){
                case 1:
                    in.nextLine();
                    System.out.print("Enter name with a comma separating first and last name: ");
                    Student s = new Student();
                    String[] name = in.nextLine().split(",");
                    s.setFirst_name(name[0].trim());
                    s.setLast_name(name[1].trim());
                    System.out.print("Enter the student's class level: ");
                    s.setAcademicLevel(in.nextLine().trim());
                    System.out.print("Enter student ID: ");
                    s.setStudentId(in.nextInt());
                    if(roster.add(s))
                        System.out.println("Added "+s.getFirst_name());
                    else
                        System.out.println("ID already exists!");
                    break;
                case 2:
                    System.out.println("Enter student ID to remove: ");
                    int id = in.nextInt();
                    if(roster.remove(new Student(id)))
                        System.out.println("Removed student");
                    else
                        System.out.println("Student not found!");
                    break;
                case 3:
                    System.out.println("Enter ID to search for: ");
                    if((s=roster.contains(new Student(in.nextInt())))!=null)
                        System.out.println(s.toString());
                    else
                        System.out.println("Student not found!");
                    break;
                case 4:
                    System.out.println("Class size: "+roster.getCurrentSize());
                    System.out.println("Class is empty: "+roster.isEmpty());
                    System.out.println("Class is full: "+roster.isFull());
                    System.out.println("Freshman: "+roster.getClassFrequencyOf(new Student(Student.FRESHMAN))+"\n" +
                            "Sophomore: "+roster.getClassFrequencyOf(new Student(Student.SOPHOMORE))+"\n" +
                            "Junior: "+roster.getClassFrequencyOf(new Student(Student.JUNIOR))+"\n" +
                            "Senior: "+roster.getClassFrequencyOf(new Student(Student.SENIOR)));
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running=false;
                    break;
                default:
                    System.out.println("Unknown command");

            }
        }

        System.out.println("Beginning Task 1 - Part 2");
        running=true;
        LinkedBag<Student> roster2 = new LinkedBag<>();
        while(running) {
            System.out.println("Choose an option: 1-add student 2-drop student 3-search student 4-get class info 5-exit");
            switch (in.nextInt()) {
                case 1:
                    in.nextLine();
                    System.out.print("Enter name with a comma separating first and last name: ");
                    Student s = new Student();
                    String[] name = in.nextLine().split(",");
                    s.setFirst_name(name[0].trim());
                    s.setLast_name(name[1].trim());
                    System.out.print("Enter the student's class level: ");
                    s.setAcademicLevel(in.nextLine().trim());
                    System.out.print("Enter student ID: ");
                    s.setStudentId(in.nextInt());
                    if (roster2.add(s))
                        System.out.println("Added " + s.getFirst_name());
                    else
                        System.out.println("ID already exists");
                    break;
                case 2:
                    System.out.println("Enter student ID to remove: ");
                    int id = in.nextInt();
                    if (roster2.remove(new Student(id)))
                        System.out.println("Removed student");
                    else
                        System.out.println("Student not found!");
                    break;
                case 3:
                    System.out.println("Enter ID to search for: ");
                    if ((s = roster2.contains(new Student(in.nextInt()))) != null)
                        System.out.println(s.toString());
                    else
                        System.out.println("Student not found!");
                    break;
                case 4:
                    System.out.println("Class size: " + roster2.getCurrentSize());
                    System.out.println("Class is empty: " + roster2.isEmpty());
                    System.out.println("Freshman: " + roster2.getClassFrequencyOf(new Student(Student.FRESHMAN)) + "\n" +
                            "Sophomore: " + roster2.getClassFrequencyOf(new Student(Student.SOPHOMORE)) + "\n" +
                            "Junior: " + roster2.getClassFrequencyOf(new Student(Student.JUNIOR)) + "\n" +
                            "Senior: " + roster2.getClassFrequencyOf(new Student(Student.SENIOR)));
                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command");

            }
        }
    }
}
