import java.util.Scanner;

public class TesterCLI {
    public static void main(String[] args) {
        //Task1();

    }

    public static void task2(){
        System.out.println("Beginning Task 2....");
        Scanner in = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("Enter infix expression: ");
            String exp = in.nextLine();
            if(!checkBalance(exp)) {
                System.out.println("Expression is not balanced!");
                continue;
            }
            System.out.println("Prefix: "+prefix(exp));
            System.out.println("Postfix: "+postfix(exp));
        }
    }

    private static String postfix(String exp) {

        return exp;
    }

    public static String prefix(String exp){

        return exp;
    }

    /**
     * Method to make sure the (, {, and [ of an expression are balanced and in the right order.
     * @param exp The expression as a String.
     * @return True if the expression is valid.
     */
    public static boolean checkBalance(String exp){
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

    public static void Task1(){
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
