package Project;


import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings({"unchecked", "StringConcatenationInLoop", "deprecation", "UnnecessaryLocalVariable"})
public class TesterCLI {

    /**
     * Main method to run Tasks 1 and 2.
     */
    public static void main(String[] args) {

        System.out.println("CS 2400  -  Project #1\nProfessor Damavandi\nSubmission by Maximum Wilder-Smith\n\n");

        System.out.println("\nBeginning Task 1 part 1....");
        task1(new Bag());
        System.out.println("\nBeginning Task 1 part 2....");
        task1(new LinkedBag());

        System.out.println("\n\nBeginning Task 2 with resizable array Stacks....");
        task2(Stack.class);
        System.out.println("\nBeginning Task 2 with linked chain Stacks....");
        task2(LinkedStack.class);
        System.out.println("\nBeginning Task 2 with vector Stacks....");
        task2(VectorStack.class);

        System.out.println("\nThank you and have a nice day!");

    }


    /**
     * Method to create rosters and test Task #1 of the assignment while querying the user for input
     */
    private static void task1(BagInterface rosterType){
        BagInterface<Student> roster = rosterType;
        boolean running = true;
        Scanner in = new Scanner(System.in);
        while(running){
            System.out.println("\nChoose an option: 1-add student 2-drop student 3-search student 4-get class info 5-exit");
            try {
                switch (in.nextInt()) {
                    case 1:
                        in.nextLine();
                        System.out.print("Enter name with a comma separating first and last name: ");
                        Student s = new Student();
                        String[] name = in.nextLine().split(",");
                        try {
                            s.setFirst_name(name[0].trim());
                            s.setLast_name(name[1].trim());
                            System.out.print("Enter the student's class level [1-Freshman 2-Sophomore 3-Junior 4-Senior]: ");
                            char level = in.nextLine().trim().charAt(0);
                            s.setAcademicLevel(level == '1' ? Student.FRESHMAN : level == '2' ? Student.SOPHOMORE : level == '3' ? Student.JUNIOR : level == '4' ? Student.SENIOR : Student.UNKNOWN);
                            System.out.print("Enter student ID: ");
                            s.setStudentId(in.nextInt());
                            if (roster.add(s))
                                System.out.println("Added " + s.getFirst_name());
                            else
                                System.out.println("ID already exists!");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.print("Please enter the name like first, last");
                        }
                        break;
                    case 2:
                        System.out.println("Enter student ID to remove: ");
                        int id = in.nextInt();
                        if (roster.remove(new Student(id)))
                            System.out.println("Removed student");
                        else
                            System.out.println("Project.Student not found!");
                        break;
                    case 3:
                        System.out.println("Enter ID to search for: ");
                        if ((s = roster.contains(new Student(in.nextInt()))) != null)
                            System.out.println(s.toString());
                        else
                            System.out.println("Project.Student not found!");
                        break;
                    case 4:
                        System.out.println("Class size: " + roster.getCurrentSize());
                        System.out.println("Class is empty: " + roster.isEmpty());
                        System.out.println("Class is full: " + roster.isFull());
                        System.out.println("Freshman: " + roster.getClassFrequencyOf(new Student(Student.FRESHMAN)) + "\n" +
                                "Sophomore: " + roster.getClassFrequencyOf(new Student(Student.SOPHOMORE)) + "\n" +
                                "Junior: " + roster.getClassFrequencyOf(new Student(Student.JUNIOR)) + "\n" +
                                "Senior: " + roster.getClassFrequencyOf(new Student(Student.SENIOR)) +
                                (roster.getClassFrequencyOf(new Student(Student.UNKNOWN)) > 0 ?
                                        ("\nUnknown: " + roster.getClassFrequencyOf(new Student(Student.UNKNOWN))) : ""));
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Unknown command");

                }
            }catch (InputMismatchException e){
                System.out.println("Please select a commands number");
                    in.nextLine();
            }
        }
    }

    /**
     * This method runs task 2 which handles infix, postfix and prefix expressions with stacks.
     * @param type A class object for the type of Stack to use, implement StackInterface
     */
    private static void task2(Class type)  {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("Enter infix expression or type 'q' to exit: ");
            String exp = in.nextLine().replaceAll(" ","");
            if(exp.toLowerCase().trim().charAt(0)=='q')
                running=false;
            else if(!checkBalance(exp))
                System.out.println("Expression is not balanced!");
            else {
                System.out.println("Prefix: " + prefix(exp, type));
                System.out.println("Postfix: " + postfix(exp, type));
            }
        }
    }

    /**
     * A helper method that returns a characters operation priority, or -1 if it is not an operation.
     * @param op The character to evaluate.
     * @return An int representing the operations priority.
     */
    private static int priorityOfOp(char op){
        int priority;
        switch (op){
            case '{':
            case '}':
            case '[':
            case ']':
            case '(':
            case ')':
                priority=0;
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
            default:
                priority = -1;
                break;
        }
        return priority;
    }

    /**
     * This method converts an infix expression into its postfix form.
     * @param exp A String of a balanced infix expression.
     * @param type A StackInterface that specifies the type of Stacks to use.
     * @return A String for the postfix expression.
     */
    private static String postfix(String exp, Class<? extends StackInterface> type){
        String out="";
        StackInterface<Character> ops;
        try {
            ops = type.newInstance();

            char[] chars = exp.toCharArray();
            for(char c:chars){
                switch (c){
                    case '^':
                        ops.push(c);
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        while(!ops.isEmpty()&&priorityOfOp(c)<=priorityOfOp(ops.peek()))
                            out+=ops.pop();
                        ops.push(c);
                        break;
                    case '(':
                    case '[':
                    case '{':
                        ops.push(c);
                        break;
                    case ')':
                    case ']':
                    case '}':
                        char top=ops.pop();
                        while(priorityOfOp(top)!=priorityOfOp('(')) {
                            out += top;
                            top = ops.pop();
                        }
                        break;
                    default:
                        out+=c;
                        break;
                }
            }
            while(!ops.isEmpty())
                out+=ops.pop();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * This method converts an infix expression into its prefix form.
     * @param exp A String of a balanced infix expression.
     * @param type A StackInterface that specifies the type of Stacks to use.
     * @return The String for the prefix expression.
     */
    private static String prefix(String exp, Class<? extends StackInterface> type){
        StackInterface<Character> ops;
        StackInterface<String> vals;
        String out = "";
        try {
            ops = type.newInstance();
            vals = type.newInstance();

            char[] chars=exp.toCharArray();
            for(char c: chars){
                switch (c){
                    case '(':
                    case '{':
                    case '[':
                        ops.push(c);
                        break;
                    case ')':
                    case '}':
                    case ']':
                        while(!ops.isEmpty()&&priorityOfOp(ops.peek())!=priorityOfOp('(')) {
                            String two = vals.pop();
                            vals.push(""+ops.pop() + vals.pop() + two);
                        }
                        ops.pop();
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                    case '^':
                        while(!ops.isEmpty()&&priorityOfOp(c)<=priorityOfOp(ops.peek())) {
                            String two=vals.pop();
                            vals.push(""+ops.pop()+vals.pop()+two);
                        }
                        ops.push(c);
                        break;
                    default:
                        vals.push(""+c);
                        break;

                }
            }
            while(!ops.isEmpty()){
                String two=vals.pop();
                vals.push(ops.pop()+vals.pop()+two);
            }
            out=vals.pop();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return out;
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
            index++;
        }
        return isBalanced;
    }

}
