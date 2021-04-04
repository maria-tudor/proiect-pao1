import java.util.ArrayList;

public class Facultate {
    ArrayList<Subject> subjects = new ArrayList<Subject>();
    ArrayList<Student> students= new ArrayList<Student>();
    ArrayList<Mark> marks = new ArrayList<Mark>();
    ArrayList<Professor> professors = new ArrayList<Professor>();

    public void addStudentToCollege(Student stud){
        this.students.add(stud);
    }

    public void addSubjectToCollege(Subject sub){
        this.subjects.add(sub);
    }

    public void addProfessorToCollege(Professor prof){
        this.professors.add(prof);
    }

    public void addMarkToCollege(Mark m){
        this.marks.add(m);
    }
}
