public class Student {
    private int studentId;
    private String first_name, last_name, academicLevel;
    public static final String FRESHMAN="Freshman", SOPHOMORE="Sophomore", JUNIOR="Junior", SENIOR="Senior";

    public Student(){

    }

    public Student(int studentId, String first_name, String last_name, String academicLevel) {
        this.studentId = studentId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.academicLevel = academicLevel;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public Student(int id){
        this(id,null,null,null);
    }

    public Student(String classLvl){
        this(-1,null,null,classLvl);
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
    public String toString() {
        return "student Id=" + studentId +
                ", first name='" + first_name + '\'' +
                ", last name='" + last_name + '\'' +
                ", academic level='" + academicLevel + '\'';
    }

    @Override
    public boolean equals(Object obj) {
        return studentId==((Student)obj).getStudentID();
    }
}
