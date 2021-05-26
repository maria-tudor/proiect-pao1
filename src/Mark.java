import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Comparator;

public class Mark {
    private final Student student;
    private final Professor professor;
    private final Subject subject;
    private final Date data;
    private int value;

    public Mark(Student stud, Professor prof, Subject sub, String dat, int val) throws ParseException {
        this.student = stud;
        this.professor = prof;
        this.subject = sub;
        Date convertedDate = new SimpleDateFormat("yyyy/MM/dd").parse(dat);
        this.data = convertedDate;
        this.value = val;
        addNew();
    }

    public void addNew(){
        this.student.addSubject(this.subject);
        this.professor.addStudent(this.student);
        this.professor.addSubject(this.subject);
        this.subject.addProfessor(this.professor);
        this.student.marks.add(this);
        Collections.sort(this.student.marks, Mark.MarkValueComparator);
    }

    public static Comparator<Mark> MarkValueComparator = new Comparator<Mark>() {

        public int compare(Mark m1, Mark m2) {
            int markValue1 = m1.getValue();
            int markValue2 = m2.getValue();

            return markValue2 - markValue1;
        }};

    public void display(){
        System.out.println("Student name: " + this.student.getName() + ", Professor name: " + this.professor.getName()
                + ", Subject name: " + this.subject.getName() + ", Data: " + this.data + ", Nota: " + this.value);
    }

    public Date getDate() {
        return data;
    }

    public String getStudentName(){ return this.student.getName(); }

    public void showStudentDetails(){ this.student.display(); }

    public void showProfessorDetails(){ this.professor.display(); }

    public void showSubjectDetails(){ this.subject.display(); }

    public String getProfessorName(){ return this.professor.getName(); }

    public String getSubjectName(){ return this.subject.getName(); }

    public int getValue() {
        return value;
    }

    public void setValue(int val) {
        this.value = val;
    }

}
