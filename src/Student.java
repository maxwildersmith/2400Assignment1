public class Student {
    private int studentId;
    private String first_name, last_name, academicLevel;

    public Student(int studentId, String first_name, String last_name, String academicLevel) {
        this.studentId = studentId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.academicLevel = academicLevel;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public int getStudentID(){
        return studentId;
    }

    @Override
    public boolean equals(Object obj) {
        return studentId==((Student)obj).getStudentID();
    }
}
