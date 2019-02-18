import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Tester extends JFrame {
    /**
     * Make many comments!!
     *
     * for bags and stacks
     *
     * implement the stack check balance
     *
     * readme.txt describe how to run your code
     *
     * and need a detailed report and explanation with program's output(pdf)
     *
     * jar and source
     *
     * MaximumWilder-Smith_project1.zip
     */
    public static void main(String[] args) {

    }

    /**
     * Creates a tester window that allows the user to select which task they wish you test.
     */
    public Tester(){
        setTitle("Assignment 1");
        setSize(400,200);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));

        JButton resizeBag = new JButton("Task 1 - Step 1");
        resizeBag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResizableBagTester();
            }
        });
        resizeBag.setAlignmentX(CENTER_ALIGNMENT);
        JButton linkedBag = new JButton("Task 1 - Step 2");
        resizeBag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        linkedBag.setAlignmentX(CENTER_ALIGNMENT);
        JButton task1 = new JButton("Task 2");
        resizeBag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        task1.setAlignmentX(CENTER_ALIGNMENT);
        JLabel text = new JLabel("Select a portion to test:");
        text.setAlignmentX(CENTER_ALIGNMENT);

        add(text);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(resizeBag);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(linkedBag);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(task1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ResizableBagTester extends JFrame implements TaskTester{
        JTextArea display;
        JButton add, search;
        JTextField idBox;
        Bag<Student> roster;
        JPanel buttonPanel;

        public ResizableBagTester(){
            setTitle("Student Roster");
            setVisible(true);
            setSize(new Dimension(300,160));
            setLayout(new BorderLayout(5,5));
            display = new JTextArea();
            add = new JButton("Add");
            search = new JButton("Search");
            idBox = new JTextField();
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(display,BorderLayout.CENTER);
            add(buttonPanel,BorderLayout.LINE_END);
            buttonPanel.add(add);
            buttonPanel.add(new JLabel("ID to search"));
            buttonPanel.add(idBox);
            buttonPanel.add(search);

            roster = new Bag<>();
            updateInfoText();

            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new StudentViewer(null,ResizableBagTester.this);
                }
            });
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Student s = roster.contains(new Student(Integer.parseInt(idBox.getText())));
                    if(s!=null)
                        new StudentViewer(s,ResizableBagTester.this);
                    else
                        idBox.setText("");
                }
            });
        }

        public void remove(Student student){
            roster.remove(student);
            updateInfoText();
        }

        public void add(Student student){
            roster.add(student);
            updateInfoText();
        }

        public void updateInfoText(){
            display.setText("Class size: "+roster.getCurrentSize()+
                    "\nIs class full: "+roster.isFull()+
                    "\nIs class empty: "+roster.isEmpty()+
                    "\nFreshman: "+roster.getClassFrequencyOf(new Student(Student.FRESHMAN))+
                    "\nSophomore: "+roster.getClassFrequencyOf(new Student(Student.SOPHOMORE))+
                    "\nJunior: "+roster.getClassFrequencyOf(new Student(Student.JUNIOR))+
                    "\nSenior: "+roster.getClassFrequencyOf(new Student(Student.SENIOR)));
        }
    }

    public class StudentViewer extends JFrame{

        JTextField firstName, lastName, id;
        JComboBox classLvl;
        JButton close, save;

        public StudentViewer(Student s, TaskTester roster){
            setSize(200,300);

            setVisible(true);
            setLayout(new FlowLayout());
            String[] classes={Student.FRESHMAN,Student.SOPHOMORE,Student.JUNIOR,Student.SENIOR};
            classLvl = new JComboBox(classes);
            firstName = new JTextField();
            lastName = new JTextField();
            id = new JTextField();
            close = new JButton("close");
            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispatchEvent(new WindowEvent(StudentViewer.this,WindowEvent.WINDOW_CLOSING));
                }
            });

            if(s==null){
                setTitle("Add Student");
                firstName.setText("First name");
                lastName.setText("Last name");
                id.setText("ID");
                save=new JButton("Add");
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        roster.add(new Student(Integer.parseInt(id.getText()),firstName.getText(),lastName.getText(),classes[classLvl.getSelectedIndex()]));
                        dispatchEvent(new WindowEvent(StudentViewer.this,WindowEvent.WINDOW_CLOSING));

                    }
                });
            } else {
                setTitle(s.getFirst_name()+", "+s.getFirst_name());
                char l = s.getAcademicLevel().charAt(1);
                classLvl.setSelectedIndex(l=='r'?0:l=='o'?1:l=='u'?2:3);
                firstName.setText(s.getFirst_name());
                lastName.setText(s.getLast_name());
                id.setText(s.getStudentID()+"");
                save=new JButton("Drop");
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        roster.remove(s);
                        dispatchEvent(new WindowEvent(StudentViewer.this,WindowEvent.WINDOW_CLOSING));
                    }
                });

                classLvl.setEditable(false);
                firstName.setEditable(false);
                lastName.setEditable(false);
                id.setEditable(false);
            }
            add(firstName);
            add(lastName);
            add(id);
            add(classLvl);
            add(close);
            add(save);

        }
    }
}
